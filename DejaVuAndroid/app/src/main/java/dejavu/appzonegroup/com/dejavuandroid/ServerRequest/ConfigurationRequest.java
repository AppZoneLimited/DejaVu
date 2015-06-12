package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.JSONReader.ConfigurationJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class ConfigurationRequest {

    private onConfigurationRequest mConfigurationRequest;
    private Context mContext;

    public interface onConfigurationRequest {
        public void onConfigurationRequestSuccessful(int flow, boolean debit, boolean password, boolean hardToken, boolean softToken);

        public void onConfigurationRequestFailed(String mes);
    }

    public ConfigurationRequest(Context context, onConfigurationRequest configurationRequest, String type) {
        mConfigurationRequest = configurationRequest;
        mContext = context;
        sendRequestToServer(type);
    }

    public void sendRequestToServer(final String type) {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                Uri.Builder builder = Uri.parse("http://165.233.246.31:11984/ZoneServiceApi/api/Configurations/Get/" + type).buildUpon();
                Uri uri = builder.build();
                HttpGet httpGet = new HttpGet(uri.toString());
                HttpClient httpClient = new DefaultHttpClient();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                try {
                    return httpClient.execute(httpGet,responseHandler);
                } catch (IOException e) {
                    return ""+ ServerResponseCodes.UNEXPECTED_ERROR;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                new ConfigurationJsonReader(result, mConfigurationRequest);
            }
        }.execute();
    }
}
