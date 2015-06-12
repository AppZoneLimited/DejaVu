package dejavu.appzonegroup.com.dejavuandroid.ObjectParceable;

import android.os.Parcel;
import android.os.Parcelable;

import com.appzone.zone.orchestra.engine.datatypes.Step;

import org.json.JSONObject;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.onScreen;

/**
 * Created by CrowdStar on 4/22/2015.
 */
public class StepParceble implements Parcelable {
    private Step mStep;
    private JSONObject mStepData;
    private onScreen mOnScreen;

    public StepParceble(Step step, JSONObject stepData, onScreen onScreen) {
        setmStep(step);
        setmStepData(stepData);
        setmOnScreen(onScreen);
    }

    public onScreen getmOnScreen() {
        return mOnScreen;
    }

    public void setmOnScreen(onScreen mOnScreen) {
        this.mOnScreen = mOnScreen;
    }

    public void setmStep(Step mStep) {
        this.mStep = mStep;
    }

    public void setmStepData(JSONObject mStepData) {
        this.mStepData = mStepData;
    }

    public JSONObject getmStepData() {
        return mStepData;
    }

    public Step getmStep() {
        return mStep;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        try {
            dest.writeArray(new Object[]{mStep, mStepData});
        } catch (Exception e) {
        }
    }


}
