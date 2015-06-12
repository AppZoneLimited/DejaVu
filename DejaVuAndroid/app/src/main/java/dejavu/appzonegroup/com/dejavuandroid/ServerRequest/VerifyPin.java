package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.net.Uri;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.pinVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.JSONReader.PinVerificationJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class VerifyPin {

    private pinVerificationListener mPinVerificationListener;


    public VerifyPin(String pin, String key, String phone, pinVerificationListener pinVerificationListener) {
        mPinVerificationListener = pinVerificationListener;
        sendPin(pin, key, phone);
    }

    private void sendPin(final String pin, final String key, final String phone) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                Uri uri = new Uri.Builder()
                        .scheme("http")
                        .authority("dejazuzoneandroid.appspot.com")
                        .path("authPin")
                        .appendQueryParameter("key", key)
                        .appendQueryParameter("pin", pin)
                        .appendQueryParameter("phone", "+234" + phone)
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
                new PinVerificationJsonReader(result, mPinVerificationListener);
            }
        }.execute();
    }

}
