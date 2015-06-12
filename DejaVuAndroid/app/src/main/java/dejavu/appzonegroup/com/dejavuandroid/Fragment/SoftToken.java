package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Activities.MainActivity;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.GoogleCloudMessagingListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.TokenAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.TokenRequestListener;
import dejavu.appzonegroup.com.dejavuandroid.PushNotification.PushRegistration;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.SoftTokenAuthentication;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class SoftToken extends Fragment implements GoogleCloudMessagingListener,
        TokenAuthenticationListener, TokenRequestListener {

    TextView tokenRetryField;
    EditText tokenField;
    private ProgressFragment progressFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_soft_token_reg, container, false);

        new SoftTokenAuthentication(getActivity()).getSoftToken(this, new UserDetailsSharePreferences(getActivity()).getUserPhoneNumber());

        tokenField = (EditText) rootView.findViewById(R.id.token_field);
        tokenRetryField = (TextView) rootView.findViewById(R.id.token_retry_label);
        progressFragment = new ProgressFragment();
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tokenField.getText().toString().isEmpty()) {
                    new ShowMessage(getActivity(), "Token can not be empty", Toast.LENGTH_LONG);
                } else {
                    progressFragment.show(getFragmentManager(), "");
                    //new SoftTokenAuthentication(getActivity(), SoftToken.this, tokenField.getText().toString());

                    new SoftTokenAuthentication(getActivity()).verifySoftToken(SoftToken.this, tokenField.getText().toString(),
                            new UserDetailsSharePreferences(getActivity()).getUserPhoneNumber());
                }
            }
        });
        tokenRetryField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SoftTokenAuthentication(getActivity()).getSoftToken(SoftToken.this, new UserDetailsSharePreferences(getActivity()).getUserPhoneNumber());
            }
        });
        return rootView;
    }

    @Override
    public void onRegistered() {
//        new UserDetailsSharePreferences(getActivity()).setFullyAuth(true);
//        startActivity(new Intent(getActivity(), CategoryFragment.class));
//        getActivity().finish();

        progressFragment.dismiss();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onRegistrationFailed() {
        new ShowMessage(getActivity(), "Could not receive necessary info fully. What should happen ?", Toast.LENGTH_LONG);
    }

    @Override
    public void onAuth() {
    }

    @Override
    public void onFailedAuth() {
        //new ShowMessage(getActivity(), "Invalid pin ?", Toast.LENGTH_LONG);

        new ShowMessage(getActivity(), "just assumed oauth", Toast.LENGTH_LONG);
        new UserDetailsSharePreferences(getActivity()).setRegister(true);
        new PushRegistration(getActivity(), this);
    }

    @Override
    public void onFailedRequest() {
    }

    @Override
    public void onTokenRequested() {
        new ShowMessage(getActivity(), "pin request success", Toast.LENGTH_SHORT);
    }

    @Override
    public void onTokenRequestFailed() {
        new ShowMessage(getActivity(), "pin request failed", Toast.LENGTH_SHORT);
    }
}
