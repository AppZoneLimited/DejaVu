package dejavu.appzonegroup.com.dejavuandroid.Interfaces;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public interface TokenAuthenticationListener {
    public void onAuth();

    public void onFailedAuth();

    public void onFailedRequest();
}
