package dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class ShowMessage {
    public ShowMessage(Context context, String message, int length) {
        if (length == 0) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}

