package dejavu.appzonegroup.com.dejavuandroid.NotificationManager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import dejavu.appzonegroup.com.dejavuandroid.Activities.CategoryFragment;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class InfoNotificationManager {
    NotificationManager mNotificationManager;

    public InfoNotificationManager(Context context, String message, String sender) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(sender)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .setSummaryText(message)
                                .bigText(message))
                        .setContentText(message);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, CategoryFragment.class), 0);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(2, mBuilder.build());
    }
}
