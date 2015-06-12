package dejavu.appzonegroup.com.dejavuandroid.NotificationManager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import dejavu.appzonegroup.com.dejavuandroid.Constant.AppConstant;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class UpdateNotiManager {
    NotificationManager mNotificationManager;

    public UpdateNotiManager(Context context, String message) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Perform an update")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                        .setContentText(message);

        final String appPackageName = context.getPackageName(); // Can also use getPackageName(), as below
        Intent contentIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, contentIntent, 0);

        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
