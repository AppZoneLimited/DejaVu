package dejavu.appzonegroup.com.dejavuandroid.Functions;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

/**
 * Created by emacodos on 4/9/2015.
 */
public class GoTo {

    public static final String TAG = GoTo.class.getSimpleName();

    private static Object mGotoObject = new Object();

    public static JSONObject makeRequest(Context context, String command) {
        JsonObject json = new JsonParser().parse(command).getAsJsonObject();
        String url = json.get("Url").getAsString();
        JsonObject jsonObject = json.getAsJsonObject("Data");
        String method = json.get("Method").getAsString();

        String response = null;
        try {
            response = Ion.with(context)
                    .load(method, url)
                    .setJsonObjectBody(jsonObject)
                    .group(mGotoObject)
                    .asString()
                    .get();
            return new JSONObject(response);
        } catch (Exception e) {
            Log.e(TAG + "/Response", e.getMessage());
        }
        return null;
    }
}
