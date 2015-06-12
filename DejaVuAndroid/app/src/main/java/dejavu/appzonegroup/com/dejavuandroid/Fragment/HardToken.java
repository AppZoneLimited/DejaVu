package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Activities.MainActivity;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.GoogleCloudMessagingListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.TokenAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.PushNotification.PushRegistration;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.HardTokenAuthentication;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class HardToken extends Fragment implements TokenAuthenticationListener, GoogleCloudMessagingListener {

    EditText tokenField;
    private ProgressFragment progressFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_token_reg, container, false);
        tokenField = (EditText) rootView.findViewById(R.id.token_field);
        progressFragment = new ProgressFragment();
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tokenField.getText().toString().isEmpty()) {
                    new ShowMessage(getActivity(),"Token can not be empty",Toast.LENGTH_LONG);
                } else {
                    progressFragment.show(getFragmentManager(), "");
                    new HardTokenAuthentication(getActivity(), HardToken.this, tokenField.getText().toString());
                }
            }
        });
        return rootView;
    }


    @Override
    public void onAuth() {

    }

    @Override
    public void onFailedAuth() {

    }

    @Override
    public void onFailedRequest() {
        new UserDetailsSharePreferences(getActivity()).setRegister(true);
        new PushRegistration(getActivity(), HardToken.this);
    }

    @Override
    public void onRegistered() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onRegistrationFailed() {
        new ShowMessage(getActivity(), "Could not receive neccesary info fully. What should happen ?", Toast.LENGTH_LONG);
    }
}
