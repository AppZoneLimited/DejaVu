package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.widget.Toast;


/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PinReceiver extends BroadcastReceiver {


    public PinReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] puds = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[]) puds[0]);
        if (messages.getMessageBody().startsWith("Zone Pin: ")) {
            String pin = messages.getMessageBody().replace("Zone Pin: ", "");
            LocalBroadcastManager broadCast = LocalBroadcastManager.getInstance(context);
            intent = new Intent("dejavu.BroadcastReceivers").putExtra("pin", pin);
            broadCast.sendBroadcast(intent);
        } else {
            Toast.makeText(context, messages.getDisplayMessageBody(), Toast.LENGTH_LONG).show();
        }
    }
}
