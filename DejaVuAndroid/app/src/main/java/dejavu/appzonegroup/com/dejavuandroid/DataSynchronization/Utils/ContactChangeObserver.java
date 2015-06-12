package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Utils;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.ContactSyncService;

/**
 * Created by emacodos on 6/11/2015.
 */
public class ContactChangeObserver extends ContentObserver {

    private Context mContext;
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public ContactChangeObserver(Handler handler, Context context) {
        super(handler);
        this.mContext = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        ContactSyncService.startContactSync(mContext);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
    }
}
