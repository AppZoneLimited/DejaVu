package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.ProgressFragment;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.PasswordPinModel;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PasswordPinAuthentication {

    private Context mContext;
    private AuthenticationListener authenticationListener;


    public PasswordPinAuthentication(Context context, AuthenticationListener listener, ArrayList<PasswordPinModel> passwordPinModels) {
        authenticationListener = listener;
        mContext = context;
        AuthUserDetails(passwordPinModels);
    }

    private void AuthUserDetails(final ArrayList<PasswordPinModel> passwordPinModels) {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                try {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("PhoneNumber", new UserDetailsSharePreferences(mContext).getUserPhoneNumber());
                    jsonObject.addProperty("AccountNumber", new UserDetailsSharePreferences(mContext).getAccountNumber());
                    jsonObject.addProperty("pin", passwordPinModels.get(0).getPin());
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
                    authenticationListener.onAuth();
                } else {
                    authenticationListener.onAuthRejected();
                }
            }
        }.execute();
    }
}
