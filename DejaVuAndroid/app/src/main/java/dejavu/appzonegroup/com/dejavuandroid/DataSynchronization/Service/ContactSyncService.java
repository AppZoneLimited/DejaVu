package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.Contact;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Utils.RetryManager;


/**
 * Created by emacodos on 2/26/2015.
 */

/**
 * @author Onyejekwe E. C emacodos
 *         An {@link android.app.IntentService} subclass for handling asynchronous task requests in
 *         a service on a separate handler thread.
 */
public class ContactSyncService extends IntentService {

    public static final String TAG = ContactSyncService.class.getSimpleName();

    public static final String ACTION_CONTACT_SYNC = "com.zoneapp.action.CONTACT.SYNC";

    public static final String NAME_PHONE_NUMBERS = "PhoneNumbers";

    public static final String URL_CONTACT = "http://165.233.246.31:11984/ZoneFlowsApi/api/Account/GetRegisteredContacts/";

    private Object mContactGroup = new Object();

    public ContactSyncService() {
        super("ContactSyncService");
    }

    public static void startContactSync(Context context) {
        Intent intent = new Intent(context, ContactSyncService.class);
        intent.setAction(ACTION_CONTACT_SYNC);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Retrieve All Contact
        //Remove Duplicate
        //Sort to get real Mobile Contact
        //Push All to Server
        //Retrieve OnZone Contact
        //Save all to the database

        JSONObject json = getContactJson();
        String contactStr = getZoneContact(json.toString());

        if (contactStr != null) {
            try {
                JSONArray jsonArray = new JSONArray(contactStr);

                for (int i = 0; i < jsonArray.length(); i++) {
                    String name = null, number;
                    number = (String) jsonArray.get(i);
                    // Update Contact
                    Contact contact = Contact.getContactByPhone(this, number);
                    if (contact != null) {
                        contact.setNumber(number);
                        contact.setOnZone(true);
                        contact.save(this);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG+"/result/json:Contact", e.getMessage());
            }
        }

    }

    private JSONObject getContactJson() {
        List<Contact> contacts = new ArrayList<>();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = '1'";

        Cursor people = getContentResolver().query(uri, projection, selection, null, null);

        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        people.moveToFirst();
        do {
            String name = people.getString(indexName);
            String number = people.getString(indexNumber);
            // Do work...
//            if (isValidPhoneNumber(number)) {
                Contact contact = Contact.getContactByPhone(this, number);
                if (contact == null){
                    contact = new Contact();
                }
                contact.setName(name);
                contact.setNumber(number);
                contacts.add(contact);
//            }
        } while (people.moveToNext());

        List<Contact> contactList = new ArrayList<>(contacts);
        saveContacts(contactList);

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i = 0; i < contactList.size(); i++) {
            array.put(contactList.get(i).getNumber());
        }
        try {
            json.put(NAME_PHONE_NUMBERS, array);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG+"/json:Contact", e.getMessage());
        }
        return json;
    }

    private void saveContacts(List<Contact> contacts) {
        for (int i=0; i<contacts.size(); i++){
            contacts.get(i).save(this);
        }
    }

    private boolean isValidPhoneNumber(CharSequence phoneNumber) {
        String expression = "^[0-9-1+]{10,15}$";
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phoneNumber);
        return (matcher.matches());
    }

    private String getZoneContact(String contact) {
        //This method connects to the internet to verify a contact
        String output = null;
        RetryManager retryManager = new RetryManager();
        if (isNetworkAvailable()) {
            while (retryManager.shouldRetry()) {
//                JsonObject json = new JsonParser().parse(contact).getAsJsonObject();
                JsonObject json = new JsonObject();
                json.addProperty("PhoneNumbers", "08055884161");
                try {
                    output = Ion.with(this)
                            .load("GET", URL_CONTACT)
                            .setJsonObjectBody(json)
                            .group(mContactGroup)
                            .asString()
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG + "/url/contact", e.getMessage());
                    if (isNetworkAvailable()) {
                        try {
                            retryManager.errorOccured();
                        } catch (Exception e1) {
                            Log.e(TAG + "/url/contact/retry", e1.getMessage());
                            return null;
                        }
                    } else {
                        FlowSyncService.sendInternetBroadcast(this, ACTION_CONTACT_SYNC, null);
                        stopSelf();
                        break;
                    }
                }
            }
            return output;
        } else {
            try {
                setMobileDataEnabled(this, true);
                if (isNetworkAvailable())
                    return getZoneContact(contact);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("err_enable_network", e.getMessage());
            }
        }
        return null;
    }

    /*
    * Check for network connection availability
    */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void setMobileDataEnabled(Context context, boolean enabled) throws Exception {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
        iConnectivityManagerField.setAccessible(true);
        final Object iConnectivityManager = iConnectivityManagerField.get(conman);
        final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
    }
}
