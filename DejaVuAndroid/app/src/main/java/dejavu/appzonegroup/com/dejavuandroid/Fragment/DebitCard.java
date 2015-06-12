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

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Activities.MainActivity;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.CardAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.DateSetListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.GoogleCloudMessagingListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.DebitCardModel;
import dejavu.appzonegroup.com.dejavuandroid.PushNotification.PushRegistration;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.DebitCardAuthentication;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class DebitCard extends Fragment implements CardAuthenticationListener, DateSetListener, GoogleCloudMessagingListener {
    TextView datePickerTextView;
    TextView cardNumberTextView;
    TextView cvvTextView;
    TextView pinTextView;
    boolean isDateSet = false;
    private ProgressFragment progressFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.debit_card_reg, container, false);
        progressFragment = new ProgressFragment();
        datePickerTextView = (TextView) rootView.findViewById(R.id.data_picker_text);
        datePickerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePickerInstance = new DatePicker().newInstance(DebitCard.this);
                datePickerInstance.show(getFragmentManager(), "Pick expiring date");
            }
        });

        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDateSet) {
                    new ShowMessage(getActivity(), "Date is not set yet", Toast.LENGTH_LONG);
                } else if (cvvTextView.getText().toString().isEmpty()) {
                    new ShowMessage(getActivity(), "Cvv is empty", Toast.LENGTH_LONG);
                } else if (pinTextView.getText().toString().isEmpty()) {
                    new ShowMessage(getActivity(), "pin is not set yet", Toast.LENGTH_LONG);
                } else if (cardNumberTextView.getText().toString().isEmpty()) {
                    new ShowMessage(getActivity(), "card number is not set yet", Toast.LENGTH_LONG);
                } else {
                    progressFragment.show(getFragmentManager(), "");
                    new DebitCardAuthentication(getActivity(), DebitCard.this, buildModel());
                }
            }
        });

        cardNumberTextView = (EditText) rootView.findViewById(R.id.card_number);
        cvvTextView = (EditText) rootView.findViewById(R.id.cvv);
        pinTextView = (EditText) rootView.findViewById(R.id.pin);
        return rootView;
    }


    @Override
    public void onCardAuthenticated() {
        new ShowMessage(getActivity(), "oauth", Toast.LENGTH_LONG);
    }

    @Override
    public void onInvalidCardDetails() {
        new ShowMessage(getActivity(), "invalid", Toast.LENGTH_LONG);
    }

    @Override
    public void onRequestFailed() {
        new ShowMessage(getActivity(), "just assumed oauth", Toast.LENGTH_LONG);
        new UserDetailsSharePreferences(getActivity()).setRegister(true);
        new PushRegistration(getActivity(), DebitCard.this);
    }

    private ArrayList<DebitCardModel> buildModel() {
        ArrayList<DebitCardModel> debitCardModelArrayListModels = new ArrayList<>();
        DebitCardModel debitCardModel = new DebitCardModel();
        debitCardModel.setCardNumber(cardNumberTextView.getText().toString());
        debitCardModel.setCvv(cvvTextView.getText().toString());
        debitCardModel.setExpDate(datePickerTextView.getText().toString());
        debitCardModel.setPin(pinTextView.getText().toString());
        debitCardModelArrayListModels.add(debitCardModel);
        return debitCardModelArrayListModels;


    }

    @Override
    public void onDateSet(String dateString) {
        isDateSet = true;
        datePickerTextView.setText(dateString);
    }

    @Override
    public void onRegistered() {
        progressFragment.dismiss();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();

    }

    @Override
    public void onRegistrationFailed() {
        progressFragment.dismiss();
        new ShowMessage(getActivity(), "Could not receive neccesary info fully. What should happen ?", Toast.LENGTH_LONG);
    }
}
