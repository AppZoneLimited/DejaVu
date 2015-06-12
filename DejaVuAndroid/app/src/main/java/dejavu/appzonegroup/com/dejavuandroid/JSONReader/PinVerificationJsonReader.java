package dejavu.appzonegroup.com.dejavuandroid.JSONReader;

import org.json.JSONArray;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.pinVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.VerifyPin;
import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PinVerificationJsonReader {


    public PinVerificationJsonReader(String result, pinVerificationListener pinVerificationListener) {
        switch (Integer.parseInt(result.trim())) {
            case ServerResponseCodes.SUCCESS:
                pinVerificationListener.onPinValid();
                break;
            case ServerResponseCodes.DENY_REQUEST:
                pinVerificationListener.onInvalidPin();
                break;
            default:
                pinVerificationListener.onPinVerificationFailed(result);
        }

    }
}
