package dejavu.appzonegroup.com.dejavuandroid.SharePreferences;

import android.content.Context;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class UserDetailsSharePreferences {

    private Context mContext;

    public UserDetailsSharePreferences(Context context) {
        mContext = context;
    }


    public void setAccountNumber(String number) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".accountNumber", number).commit();
    }

    public String getAccountNumber() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".accountNumber", "");
    }
    public void setUserPhoneNumber(String number) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".phoneNumber", number).commit();
    }

    public String getUserPhoneNumber() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".phoneNumber", "");
    }

    public void setLastName(String lastName) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".lastName", lastName).commit();

    }

    public String getLastName() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".lastName", "");
    }

    public void setOtherName(String otherName) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".otherName", otherName).commit();

    }

    public String getOtherName() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".otherName", "");
    }

    public void setGenderValue(int genderValue) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putInt(getClass().getSimpleName() + ".genderValue", genderValue).commit();

    }

    public int getGenderValue() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getInt(getClass().getSimpleName() + ".genderValue", -1);
    }


    public void setDate(long dateTime) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putLong(getClass().getSimpleName() + ".date", dateTime).commit();

    }

    public long getDate() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getLong(getClass().getSimpleName() + ".date", -1l);
    }


    public void setEmail(String email) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".email", email).commit();

    }

    public String getEmail() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".email", "");
    }

    public void setPassword(String password) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".password", password).commit();

    }

    public String getPassword() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".password", "");
    }

    public void setPin(String pin) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".pin", pin).commit();

    }

    public String getPin() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".pin", "");
    }

    public void setRegister(boolean status) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(getClass().getSimpleName() + ".isReg", status).commit();

    }

    public boolean isRegistered() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getBoolean(getClass().getSimpleName() + ".isReg", false);
    }

    public void setFullyAuth(boolean status) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(getClass().getSimpleName() + ".auth", status).commit();

    }

    public boolean isFullyAuth() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getBoolean(getClass().getSimpleName() + ".auth", false);
    }

    public void setKey(String key) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".key", key).commit();

    }

    public String getKey() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".key", "");
    }

    public void setFirstFlowDownloaded(boolean status) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(getClass().getSimpleName() + ".firstFlow", status).commit();

    }

    public boolean isFirstFlowDownloaded() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getBoolean(getClass().getSimpleName() + ".firstFlow", false);
    }

}
