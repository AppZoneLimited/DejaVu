package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 3/5/2015.
 */
public class DJV_Button extends LinearLayout {

    private UI_Model ui_models;
    private UI_Object ui_object;

    public void setUi_object(UI_Object ui_object) {
        this.ui_object = ui_object;
    }

    public UI_Object getUi_object() {
        return ui_object;
    }

    public void setViewAttribute(UI_Object ui_object) {
        ui_models = new AttributeDefiner().AttributeReader(ui_object.getUi_spec(), ui_object.getStepData(), ui_object.getName());
        setUi_object(ui_object);

    }


    public void setDefaultAttribute() {

        button.setText(getCustomViewAttribute().getValueKey());
    }

    public UI_Model getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_Button(Context context) {
        super(context);
    }


    public DJV_Button(Context context, AttributeSet attrs, int defStyleAttr, UI_Object ui_object) {
        super(context, attrs, defStyleAttr);
        initialise();
        setViewAttribute(ui_object);
        setDefaultAttribute();
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    Button button;

    private void initialise() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.djv_button, this);
        button = (Button) findViewById(R.id.text);

    }
}
