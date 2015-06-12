package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.Authetication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Activities.MainActivity;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

public class PasswordAuth extends ActionBarActivity {
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_auth);
        passwordEditText = (EditText) findViewById(R.id.password_field);

        ((TextView) findViewById(R.id.welcomeText)).setText("Hi " + new UserDetailsSharePreferences(this).getOtherName() + "!");
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    verify();
                    return true;
                }
                return false;
            }
        });

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });


    }

    public void verify() {
        if (passwordEditText.getText().toString().equalsIgnoreCase(new UserDetailsSharePreferences(this).getPassword())) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            new ShowMessage(this, "Invalid password", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
