package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 3/6/2015.
 */
public class DJV_ReadOnly extends LinearLayout {


    private UI_Model ui_models;
    private UI_Object ui_object;

    public void setUi_object(UI_Object ui_object) {
        this.ui_object = ui_object;
    }

    public UI_Object getUi_object() {
        return ui_object;
    }

    public final void setViewAttribute(UI_Object ui_object) {
        ui_models = new AttributeDefiner().AttributeReader(ui_object.getUi_spec(), ui_object.getStepData(), ui_object.getName(), ui_object.getEntityName());
        setUi_object(ui_object);
    }


    public final void setDefaultAttribute() {
        label.setText(getCustomViewAttribute().getValueKey());
        textView.setText(getCustomViewAttribute().getValue());
        setEnabled(false);

    }

    public final UI_Model getCustomViewAttribute() {
        return ui_models;
    }


    public DJV_ReadOnly(Context context, AttributeSet attrs, int defStyleAttr, UI_Object ui_object) {
        super(context, attrs, defStyleAttr);
        initialise();
        setViewAttribute(ui_object);
        setDefaultAttribute();
    }

    TextView textView;
    TextView label;

    private void initialise() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.djv_readonly, this);
        textView = (TextView) findViewById(R.id.text);
        label = (TextView) findViewById(R.id.label);
    }

}
