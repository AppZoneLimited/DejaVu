package dejavu.appzonegroup.com.dejavuandroid.Interfaces;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public interface CardAuthenticationListener {
    public void onCardAuthenticated();

    public void onInvalidCardDetails();

    public void onRequestFailed();
}
