package dejavu.appzonegroup.com.dejavuandroid.Interfaces;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public interface onPinRequest {
    public void onPinRequested(String key);//server successfully sent pin to the user

    public void onPinRequestDenied();//server reject request to give user pin

    public void onRequestFailed();
}
