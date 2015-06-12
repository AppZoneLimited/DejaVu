package dejavu.appzonegroup.com.dejavuandroid.SharePreferences;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ClientResponseCode;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class GcmSharedPreferences {
    private Context mContext;

    public GcmSharedPreferences(Context context) {
        mContext = context;
    }

    public void setRegistrationId(String regId) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".regId", regId).commit();
    }

    public String getRegistrationId() {
            return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".regId", "");
    }

    public void setAppVersion(int version) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putInt(getClass().getSimpleName() + ".version", version).commit();
    }

    public int getAppVersion() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getInt(getClass().getSimpleName() + ".version", 0);
    }

    public int getAppPackageVersion() {
        try {
            PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), ClientResponseCode.PACKAGE_NAME_ERROR);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return ClientResponseCode.PACKAGE_NAME_ERROR;
        }
    }
}
