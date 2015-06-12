package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails;

import android.content.Context;
import android.telephony.TelephonyManager;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class UserDetailsFromPhone {
    Context mContext;

    public UserDetailsFromPhone(Context context) {
        mContext = context;
    }

    public String getPhoneNumber() {
        TelephonyManager tMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if(tMgr.getLine1Number() != null){
            return  tMgr.getLine1Number();
        }
        return "";
    }
}
