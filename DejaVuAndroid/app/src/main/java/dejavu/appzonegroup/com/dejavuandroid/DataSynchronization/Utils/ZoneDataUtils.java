package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.ClientFlows;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.Contact;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.Entity;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.Function;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.FunctionCategory;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Receiver.MyStartServiceReceiver;


/**
 * Created by emacodos on 4/2/2015.
 */
public class ZoneDataUtils {


    /*
    * Sync database tables
    */
    public static void syncDB(Context context) {
        List<Class<? extends Model>> models = new ArrayList<Class<? extends Model>>();
        models.add(Function.class);
        models.add(FunctionCategory.class);
        models.add(ClientFlows.class);
        models.add(Entity.class);
        models.add(Contact.class);

        DatabaseAdapter.setDatabaseName("zone_db");
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.setModels(models);
    }

    /*
    * Set alarm service for daily data sync
    */
    public static void startAlarmService(Context context, long interval){
        AlarmManager service = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, MyStartServiceReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, i,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar cal = Calendar.getInstance();
        // start 24 hrs after boot completed
        cal.add(Calendar.SECOND, 3600);
        // fetch every 24 hours
        // InexactRepeating allows Android to optimize the energy consumption
//        service.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//                cal.getTimeInMillis(), interval, pending);

        service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                interval, pending);
    }

    /*
    * Check for network connection availability
    */
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void copyDBToSDCard() {
        try {
            InputStream myInput = new FileInputStream("/data/data/dejavu.appzonegroup.com.dejavuandroid/databases/zone_db");

            File file = new File(Environment.getExternalStorageDirectory().getPath()+"/zone_db");
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    Log.e("FO", "File creation failed for " + file);
                }
            }

            OutputStream myOutput = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+"/zone_db");

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
            Log.e("FO","copied");

        } catch (Exception e) {
            Log.e("FO","exception="+e);
        }
    }
}
