package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.onPinRequest;
import dejavu.appzonegroup.com.dejavuandroid.JSONReader.PinRequestJSONReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PinRequest {


    private Context mContext;
    private onPinRequest mPinRequest;


    public PinRequest(Context context, onPinRequest pinRequest, String phoneNumber) {
        mContext = context;
        mPinRequest = pinRequest;
        sendNumberToServer(phoneNumber);
    }

    private void sendNumberToServer(final String phoneNumber) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                Uri uri = new Uri.Builder()
                        .scheme("http")
                        .authority("dejazuzoneandroid.appspot.com")
                        .path("regPhone")
                        .appendQueryParameter("phone", phoneNumber)
                        .build();
                HttpPost httpPost = new HttpPost(uri.toString());
                HttpClient httpClient = new DefaultHttpClient();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                try {
                    return httpClient.execute(httpPost, responseHandler);
                } catch (IOException e) {
                    return "" + ServerResponseCodes.UNEXPECTED_ERROR;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                new PinRequestJSONReader(result, mPinRequest);
                // read response from server to make sure that
            }
        }.execute();

    }
}
