package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.PhoneSubmissionCallback;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 3/30/2015.
 */
public class PhoneSubmission extends AsyncTask {

    PhoneSubmissionCallback submissionCallback;
    Context mContext;

    public PhoneSubmission(Context context, PhoneSubmissionCallback callback) {
        submissionCallback = callback;
        mContext = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("PhoneNumber", new UserDetailsSharePreferences(mContext).getUserPhoneNumber());
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
        super.onPostExecute(o);

        try {
            String responseString = o.toString().replace("\"", "");
            int response = Integer.parseInt(o.toString());
            if (response == ServerResponseCodes.SUCCESS) {
                submissionCallback.onPhoneSubmitted();
            } else {
                submissionCallback.onSubmissionFailed(o.toString());
            }
        } catch (Exception ex) {
            submissionCallback.onSubmissionFailed(o.toString());
        }
    }
}
