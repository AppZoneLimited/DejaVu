package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Interface;

/**
 * Created by emacodos on 5/19/2015.
 */
public interface FlowSynchronisationListener {
//Called when all functions and function category are successfully downloaded
    void onDownloadSuccess();

//Called when functions and function category download fails
    void onDownloadFailure();

//Called when each client flow are successfully downloaded
    void onFlowDownloadSuccess(String guid);

//Called when each client flow download fails.
    void onFlowDownloadFailure(String guid);
}
