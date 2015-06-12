package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AccountNumberVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.AccountNumberVerification;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.GeneralPreference;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class BankAccountNumber extends Fragment implements AccountNumberVerificationListener {
    EditText accountNumberEditText;
    ProgressFragment progressFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bank_account_number_fragment, container, false);
        accountNumberEditText = (EditText) rootView.findViewById(R.id.account_number_field);
        progressFragment = new ProgressFragment();
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressFragment.show(getFragmentManager(), "");
                new AccountNumberVerification(BankAccountNumber.this, getActivity()).execute(accountNumberEditText.getText().toString());
            }
        });
        return rootView;
    }


    @Override
    public void onAccountVerified() {
        progressFragment.dismiss();
        new UserDetailsSharePreferences(getActivity()).setAccountNumber(accountNumberEditText.getText().toString().trim());
        //new FragmentChanger(getFragmentManager(), true, false, true);
    }

    @Override
    public void onAccountVerificationDenied() {
        progressFragment.dismiss();
    }

    @Override
    public void onRequestFailed() {
        progressFragment.dismiss();
        new UserDetailsSharePreferences(getActivity()).setAccountNumber(accountNumberEditText.getText().toString().trim());
        new FragmentChanger(getFragmentManager(), new PasswordPinAuth());
    }
}
