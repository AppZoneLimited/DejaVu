package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails;

import android.content.Context;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class GeneralPreference {

    public static void setFlowType(Context context, int flowType) {
        context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).edit().putInt(GeneralPreference.class.getSimpleName() + ".flowType", flowType).commit();
    }

    public static int getFlowType(Context context) {
        return context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).getInt(GeneralPreference.class.getSimpleName() +".flowType", FlowConstant.WRONG_FLOW);
    }

    public static void setAllowPassword(Context context, boolean password) {
        context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(GeneralPreference.class.getSimpleName() + ".password", password).commit();
    }

    public static boolean isPasswordAllowed(Context context) {
        return context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).getBoolean(GeneralPreference.class.getSimpleName() +".password", false);
    }

    public static void setHardToken(Context context, boolean hardToken) {
        context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(GeneralPreference.class.getSimpleName() + ".hardToken", hardToken).commit();
    }

    public static boolean isHardToken(Context context) {
        return context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).getBoolean(GeneralPreference.class.getSimpleName() +".hardToken", false);
    }

    public static void setOTP(Context context, boolean otp) {
        context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(GeneralPreference.class.getSimpleName() + ".otp", otp).commit();
    }

    public static boolean isOTP(Context context) {
        return context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).getBoolean(GeneralPreference.class.getSimpleName() +".otp", false);
    }

    public static void setDebit(Context context, boolean debit) {
        context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(GeneralPreference.class.getSimpleName() + ".debit", debit).commit();
    }

    public static boolean isDebit(Context context) {
        return context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).getBoolean(GeneralPreference.class.getSimpleName() +".debit", false);
    }
}
