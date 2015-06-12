package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.TokenAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class HardTokenAuthentication {
    private Context mContext;
    private TokenAuthenticationListener mAuthenticationListener;


    public HardTokenAuthentication(Context context, TokenAuthenticationListener listener, String hardKey) {
        mAuthenticationListener = listener;
        mContext = context;
        sendHardToken(hardKey);
    }

    private void sendHardToken(final String token) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("PhoneNumber", new UserDetailsSharePreferences(mContext).getUserPhoneNumber());
                    jsonObject.addProperty("AccountNumber", new UserDetailsSharePreferences(mContext).getAccountNumber());
                    jsonObject.addProperty("Pin", new UserDetailsSharePreferences(mContext).getPin());
                    jsonObject.addProperty("Password", new UserDetailsSharePreferences(mContext).getPassword());
                    jsonObject.addProperty("token", token);
                    String mIon = Ion.with(mContext)
                            .load("POST", "http://192.168.2.163:11984/api/Account/Register")
                            .setJsonObjectBody(jsonObject)
                            .asString()
                            .get();
                    return mIon;
                } catch (Exception e) {
                    return "" + ServerResponseCodes.UNEXPECTED_ERROR;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s.equalsIgnoreCase("200")){
                    mAuthenticationListener.onAuth();
                }else if(s.equalsIgnoreCase("900")){
                    mAuthenticationListener.onFailedAuth();
                }else{
                    mAuthenticationListener.onFailedRequest();
                }
            }
        }.execute();
    }

}
