package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Interface.FlowSynchronisationListener;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.ContactSyncService;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.FlowSyncService;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Utils.ServiceManager;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by emacodos on 4/28/2015.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    public static boolean active = false;
    public static Intent mIntent;
    public static String mId;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Bundle extra = intent.getExtras();
        String action = intent.getAction();
        switch (action) {
            case FlowSyncService.ACTION_SYNC:
                active = true;
                mIntent = intent;
                break;
            case FlowSyncService.ACTION_CLOUD_MESSAGE:
                active = true;
                mIntent = intent;
                mId = intent.getStringExtra(FlowSyncService.PARAM_MESSAGE);
                break;
        }
        if (active) {
            if (checkInternet(context)) {
                final String intentAction = mIntent.getAction();
                switch (intentAction) {
                    case FlowSyncService.ACTION_SYNC:
                        FlowSyncService.startActionSync(context,
                                new UserDetailsSharePreferences(context).getUserPhoneNumber(),
                                new FlowSynchronisationListener() {
                            @Override
                            public void onDownloadSuccess() {

                            }

                            @Override
                            public void onDownloadFailure() {

                            }

                            @Override
                            public void onFlowDownloadSuccess(String guid) {

                            }

                            @Override
                            public void onFlowDownloadFailure(String guid) {

                            }
                        });
                        break;
                    case FlowSyncService.ACTION_CLOUD_MESSAGE:
                        FlowSyncService.startActionCloudMessage(context, mId);
                        break;
                    case FlowSyncService.ACTION_DAILY_SYNC:
                        FlowSyncService.startActionDailySync(context,
                                new UserDetailsSharePreferences(context).getUserPhoneNumber());
                        break;
                    case ContactSyncService.ACTION_CONTACT_SYNC:
                        ContactSyncService.startContactSync(context);
                        break;
                }
                active = false;
            }
        }
    }


    boolean checkInternet(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        return serviceManager.isNetworkAvailable();
    }

}
