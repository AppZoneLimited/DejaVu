package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AccountNumberVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class AccountNumberVerification extends AsyncTask {

    private Context mContext;
    private AccountNumberVerificationListener mVerificationListener;

    public AccountNumberVerification(AccountNumberVerificationListener mListener, Context context) {
        mVerificationListener = mListener;
        mContext = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("PhoneNumber", new UserDetailsSharePreferences(mContext).getUserPhoneNumber());
            jsonObject.addProperty("AccountNumber", params[0].toString().trim());
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
    protected void onPostExecute(Object o) {
        if (o.toString().equalsIgnoreCase("200")) {
            mVerificationListener.onAccountVerified();
        } else if (o.toString().equalsIgnoreCase("900")) {
            mVerificationListener.onAccountVerificationDenied();
        } else {
            mVerificationListener.onRequestFailed();
        }
    }
}
