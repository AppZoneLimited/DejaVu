package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.TokenAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.TokenRequestListener;


/**
 * Created by CrowdStar on 2/12/2015.
 */
public class SoftTokenAuthentication {

    public static final String TAG = SoftTokenAuthentication.class.getSimpleName();

    public static final String URL_VERIFY_PIN = "http://165.233.246.31:11984/ZoneFlowsApi/api/Account/VerifySoftToken";
    public static final String URL_PIN_REQUEST = "http://165.233.246.31:11984/ZoneFlowsApi/api/Account/GetSoftToken";

    private Context mContext;


    public SoftTokenAuthentication(Context context) {
        mContext = context;
    }

    public void getSoftToken(TokenRequestListener listener, final String phoneNumber) {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("PhoneNumber", phoneNumber);

        try {
            String result = Ion.with(mContext)
                    .load("GET", URL_PIN_REQUEST)
                    .setJsonObjectBody(jsonBody)
                    .asString()
                    .get();
            Log.e(TAG, result);
            if (result.equals("\"200\"")) {
                listener.onTokenRequested();
            }
            else {
                listener.onTokenRequestFailed();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            listener.onTokenRequestFailed();
        }
    }

    public void verifySoftToken(TokenAuthenticationListener listener, final String code, final String phone) {

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("PhoneNumber", phone);
        jsonBody.addProperty("Code", code);

        try {
            String result = Ion.with(mContext)
                    .load("POST", URL_VERIFY_PIN)
                    .setJsonObjectBody(jsonBody)
                    .asString()
                    .get();
            Log.e(TAG, result);
            if (result.equals("\"200\"")) {
                listener.onAuth();
            }
            else {
                listener.onFailedAuth();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            listener.onFailedAuth();
        }
    }
}
