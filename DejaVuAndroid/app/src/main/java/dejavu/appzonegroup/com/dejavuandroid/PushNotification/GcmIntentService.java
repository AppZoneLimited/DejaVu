package dejavu.appzonegroup.com.dejavuandroid.PushNotification;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.FlowSyncService;


/**
 * Created by CrowdStar on 2/16/2015.
 */
public class GcmIntentService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * Used to name the worker thread, important only for debugging.
     */
    public GcmIntentService() {
        super("GcmIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);
        if (GoogleCloudMessaging.
                MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            FlowSyncService.startActionCloudMessage(GcmIntentService.this, extras.getString("message"));
        }

    }
}
