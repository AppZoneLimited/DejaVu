package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class MyScheduleReceiver extends BroadcastReceiver {

    // restart service every 24 hours
    public static final long REPEAT_TIME = 1000 * 60 * 60 * 24;

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        AlarmManager service = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, MyStartServiceReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, i,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar cal = Calendar.getInstance();
        // start 30 seconds after boot completed
        cal.add(Calendar.SECOND, 0);
        // fetch every 30 seconds
        // InexactRepeating allows Android to optimize the energy consumption
//        service.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//                cal.getTimeInMillis(), REPEAT_TIME, pending);

         service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
         REPEAT_TIME, pending);
    }
}
