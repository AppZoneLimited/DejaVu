package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.PasswordPinModel;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.PasswordPinAuthentication;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.GeneralPreference;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class PasswordPinAuth extends Fragment implements AuthenticationListener {
    EditText passwordEditText;
    EditText pinEditText;
    EditText verifyPasswordEditText;
    EditText verifyPinEditText;
    ProgressFragment progressFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.password_pin_auth_fragment, container, false);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.password_layout);
        progressFragment = new ProgressFragment();
        if (!GeneralPreference.isPasswordAllowed(getActivity()))
            linearLayout.setVisibility(View.GONE);
        passwordEditText = (EditText) rootView.findViewById(R.id.password_field);
        pinEditText = (EditText) rootView.findViewById(R.id.pin_field);

        verifyPasswordEditText = (EditText) rootView.findViewById(R.id.verify_password_field);
        verifyPinEditText = (EditText) rootView.findViewById(R.id.verify_pin_field);
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GeneralPreference.isPasswordAllowed(getActivity())) {
                    if (passwordEditText.getText().toString().trim().isEmpty() || verifyPasswordEditText.getText().toString().isEmpty()
                            || pinEditText.getText().toString().trim().isEmpty() || verifyPinEditText.getText().toString().isEmpty()
                            ) {
                        new ShowMessage(getActivity(), "Empty password and pin field", Toast.LENGTH_LONG);
                    } else if (passwordEditText.getText().toString().trim().equalsIgnoreCase(verifyPasswordEditText.getText().toString())
                            && verifyPinEditText.getText().toString().trim().equalsIgnoreCase(pinEditText.getText().toString())
                            ) {
                        progressFragment.show(getFragmentManager(), "");
                        new PasswordPinAuthentication(getActivity(), PasswordPinAuth.this, buildPasswordPinModel());
                    } else {
                        new ShowMessage(getActivity(), "Details do not match", Toast.LENGTH_LONG);
                    }
                } else {
                    if (pinEditText.getText().toString().trim().isEmpty() || verifyPinEditText.getText().toString().isEmpty()
                            ) {
                        new ShowMessage(getActivity(), "pin field", Toast.LENGTH_LONG);
                    } else if (verifyPinEditText.getText().toString().trim().equalsIgnoreCase(pinEditText.getText().toString())) {
                        progressFragment.show(getFragmentManager(), "");
                        new PasswordPinAuthentication(getActivity(), PasswordPinAuth.this, buildPasswordPinModel());
                    } else {
                        new ShowMessage(getActivity(), "Details do not match", Toast.LENGTH_LONG);
                    }
                }
            }
        });
        return rootView;
    }

    @Override
    public void onAuth() {
        progressFragment.dismiss();
        //use successfully login
    }

    @Override
    public void onAuthRejected() {
        progressFragment.dismiss();
        new UserDetailsSharePreferences(getActivity()).setPassword(passwordEditText.getText().toString().trim());
        new UserDetailsSharePreferences(getActivity()).setPin(pinEditText.getText().toString().trim());
        if (GeneralPreference.getFlowType(getActivity()) == FlowConstant.BANK_FLOW) {
            new FragmentChanger(getFragmentManager(), GeneralPreference.isOTP(getActivity()), GeneralPreference.isHardToken(getActivity()), GeneralPreference.isDebit(getActivity()));
        } else if (GeneralPreference.getFlowType(getActivity()) == FlowConstant.GENERIC_FLOW) {
            new FragmentChanger(getFragmentManager(), new DebitCard());

        }
    }

    private ArrayList<PasswordPinModel> buildPasswordPinModel() {
        ArrayList<PasswordPinModel> passwordPinModels = new ArrayList<>();
        PasswordPinModel model = new PasswordPinModel();
        model.setPassword(passwordEditText.getText().toString());
        model.setPin(Integer.parseInt(pinEditText.getText().toString()));
        passwordPinModels.add(model);

        return passwordPinModels;
    }
}
