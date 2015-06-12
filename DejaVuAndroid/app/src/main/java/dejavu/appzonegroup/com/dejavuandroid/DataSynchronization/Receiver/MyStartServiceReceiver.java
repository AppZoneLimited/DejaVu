package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.FlowSyncService;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

public class MyStartServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        //Start Flow Sync
        FlowSyncService.startActionDailySync(context,
                new UserDetailsSharePreferences(context).getUserPhoneNumber());
    }
}
