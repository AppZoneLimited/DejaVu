package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Interface;

import org.json.JSONObject;

/**
 * Created by emacodos on 5/27/2015.
 */
public interface EntitySynchronisationListener {
    void onResultRetrieved(JSONObject obj);
}
