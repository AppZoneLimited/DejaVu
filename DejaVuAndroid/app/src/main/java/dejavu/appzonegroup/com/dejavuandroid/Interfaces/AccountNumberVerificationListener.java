package dejavu.appzonegroup.com.dejavuandroid.Interfaces;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public interface AccountNumberVerificationListener extends FailedInterface {
    public void onAccountVerified();
    public void onAccountVerificationDenied();
}
