package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.CardAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.DebitCardModel;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class DebitCardAuthentication {

    private Context mContext;
    private CardAuthenticationListener mCardAuthenticationListener;


    public DebitCardAuthentication(Context context, CardAuthenticationListener mListener, ArrayList<DebitCardModel> debitCardModels) {
        mContext = context;
        mCardAuthenticationListener = mListener;
        submitCardDetails(debitCardModels);
    }

    private void submitCardDetails(final ArrayList<DebitCardModel> debitCardModels) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("PhoneNumber", new UserDetailsSharePreferences(mContext).getUserPhoneNumber());
                    jsonObject.addProperty("AccountNumber", new UserDetailsSharePreferences(mContext).getAccountNumber());
                    jsonObject.addProperty("Pin", new UserDetailsSharePreferences(mContext).getPin());
                    jsonObject.addProperty("Password", new UserDetailsSharePreferences(mContext).getPassword());
                    jsonObject.addProperty("cvv", debitCardModels.get(0).getCvv());
                    jsonObject.addProperty("ExpiringDate", debitCardModels.get(0).getExpDate());
                    jsonObject.addProperty("debitPin", debitCardModels.get(0).getPin());
                    jsonObject.addProperty("CardNumber", debitCardModels.get(0).getCardNumber());
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
                if (s.equalsIgnoreCase("200")) {
                    mCardAuthenticationListener.onCardAuthenticated();
                } else if (s.equalsIgnoreCase("900")) {
                    mCardAuthenticationListener.onInvalidCardDetails();
                } else {
                    mCardAuthenticationListener.onRequestFailed();
                }

            }
        }.execute();
    }
}
