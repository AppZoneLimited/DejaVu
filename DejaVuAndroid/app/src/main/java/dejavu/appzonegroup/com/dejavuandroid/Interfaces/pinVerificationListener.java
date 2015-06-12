package dejavu.appzonegroup.com.dejavuandroid.Interfaces;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public interface pinVerificationListener {
    public void onPinValid();

    public void onInvalidPin();

    public void onPinVerificationFailed(String failure);
}
