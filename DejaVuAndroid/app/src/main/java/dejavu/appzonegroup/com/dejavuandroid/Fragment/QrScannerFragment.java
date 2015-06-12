package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;
import com.appzone.zone.orchestra.engine.enums.StepTypeEnum;
import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;
import com.appzone.zone.orchestra.engine.qrcodescanner.QRCodeScannerView;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

public class QrScannerFragment extends Fragment implements QRCodeScannerView.ResultHandler{

    QRCodeScannerView mScannerView;
    Step step;
    StepsAbstraction sa;

    public QrScannerFragment() {
        // Required empty public constructor
    }

    public QrScannerFragment(Step step, StepsAbstraction sa){
        this.step = step;
        this.sa = sa;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create new scannerview
        mScannerView = new QRCodeScannerView(getActivity());

        return mScannerView;
    }


    @Override
    public void handleResult(Result resultRAW) {
        //Handle result from scanning here
        JSONObject result = null;
        try {
            result = new JSONObject(resultRAW.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(result != null) {
            this.step.setStepResultCallBack(result, this.sa, new StepResultCallback() {
                @Override
                public void onStepResult(StepsAbstraction stepsAbstraction, Step step, JSONObject jsonObject) {
                    //Perform any operation with current step here
                }

                @Override
                public void onGetNextStep(Step step, JSONObject jsonObject, StepTypeEnum stepTypeEnum, boolean b) {
                    Step nextStep = step;
                }


            });
        }
    }
}
