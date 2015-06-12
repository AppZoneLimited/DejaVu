package dejavu.appzonegroup.com.dejavuandroid.JSONReader;

import org.json.JSONArray;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.onPinRequest;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PinRequestJSONReader {


    public PinRequestJSONReader(String result, onPinRequest pinRequest) {
        try {
            JSONArray pinRequestJsonString = new JSONArray(result);
            String key = pinRequestJsonString.getJSONObject(0).getString("key");
            pinRequest.onPinRequested(key);

        } catch (Exception e) {
            switch (Integer.parseInt(result)) {
                case ServerResponseCodes.DENY_REQUEST:
                    pinRequest.onPinRequestDenied();
                    break;
                default:
                    pinRequest.onRequestFailed();
            }
            pinRequest.onRequestFailed();
        }
    }


}
