package dejavu.appzonegroup.com.dejavuandroid.ShellFramework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Activities.CategoryFragment;
import dejavu.appzonegroup.com.dejavuandroid.Activities.MainActivity;
import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.FragmentChanger;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.PhoneRegistration;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.GoogleCloudMessagingListener;
import dejavu.appzonegroup.com.dejavuandroid.PushNotification.PushRegistration;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.ConfigurationRequest;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.Authetication.PasswordAuth;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.GeneralPreference;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;


public class SplashScreen extends FragmentActivity implements ConfigurationRequest.onConfigurationRequest, GoogleCloudMessagingListener {
    private UserDetailsSharePreferences userDetailsSharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        userDetailsSharePreferences = new UserDetailsSharePreferences(this);
        if (userDetailsSharePreferences.isRegistered()) {
            if (userDetailsSharePreferences.isFullyAuth()) {
                if (GeneralPreference.isPasswordAllowed(this))
                    startActivity(new Intent(SplashScreen.this, PasswordAuth.class));
                else
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            } else {
                new PushRegistration(SplashScreen.this, SplashScreen.this);
            }
        } else {
            new ConfigurationRequest(this, this, "test");
        }
    }


    @Override
    public void onConfigurationRequestSuccessful(int flow, boolean debit, boolean password, boolean hardToken, boolean softToken) {
        GeneralPreference.setFlowType(SplashScreen.this, flow);
        GeneralPreference.setAllowPassword(SplashScreen.this, password);
        GeneralPreference.setDebit(SplashScreen.this, debit);
        GeneralPreference.setOTP(SplashScreen.this, softToken);
        GeneralPreference.setHardToken(SplashScreen.this, hardToken);

        if (flow == FlowConstant.GENERIC_FLOW) {
            new FragmentChanger(getSupportFragmentManager(), new PhoneRegistration());
        } else if (flow == FlowConstant.BANK_FLOW) {
            new FragmentChanger(getSupportFragmentManager(), new PhoneRegistration());
        } else {
            new ShowMessage(SplashScreen.this, "Wrong flow", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onConfigurationRequestFailed(String mes) {
        new ShowMessage(SplashScreen.this, mes, Toast.LENGTH_LONG);
//        /new ConfigurationRequest(this, this,"test");
    }

    @Override
    public void onRegistered() {
        userDetailsSharePreferences.setFullyAuth(true);
        startActivity(new Intent(SplashScreen.this, CategoryFragment.class));
        finish();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRegistrationFailed() {
        new PushRegistration(SplashScreen.this, SplashScreen.this);
    }
}
