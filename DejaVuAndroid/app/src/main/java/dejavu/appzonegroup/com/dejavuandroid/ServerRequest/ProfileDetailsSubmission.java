package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.ProfileDetailsSubmissionListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.UserDetailsModel;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class ProfileDetailsSubmission {

    private Context mContext;
    private ProfileDetailsSubmissionListener mDetailsSubmissionListener;


    public ProfileDetailsSubmission(Context context, ProfileDetailsSubmissionListener listener, ArrayList<UserDetailsModel> userDetailsModels) {
        mContext = context;
        mDetailsSubmissionListener = listener;
        submitDetails(userDetailsModels);
    }

    private void submitDetails(final ArrayList<UserDetailsModel> userDetailsModels) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("Dob", userDetailsModels.get(0).getDob());
                    jsonObject.addProperty("email", userDetailsModels.get(0).getEmailAddress());
                    jsonObject.addProperty("name", userDetailsModels.get(0).getName());
                    jsonObject.addProperty("otherName", userDetailsModels.get(0).getOtherName());
                    jsonObject.addProperty("gender", userDetailsModels.get(0).getDob());
                    String mIon = Ion.with(mContext)
                            .load("POST", "http://192.168.2.236:11984/api/Account/Register")
                            .setJsonObjectBody(jsonObject)
                            .asString()
                            .get();
                    return mIon;
                } catch (Exception e) {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("200")) {
                    mDetailsSubmissionListener.onDetailsSubmitted();
                } else if (s.equalsIgnoreCase("900")) {
                    mDetailsSubmissionListener.onDetailsSubmissionRejected();
                } else {
                    mDetailsSubmissionListener.onSubmissionFailed();
                }
            }
        }.execute();
    }
}
