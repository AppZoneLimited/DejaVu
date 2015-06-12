package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import dejavu.appzonegroup.com.dejavuandroid.Fragment.DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.DateSetListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;

/**
 * Created by CrowdStar on 3/8/2015.
 */
public class DJV_DatePicker extends Button implements DateSetListener, View.OnClickListener {

    private FragmentManager fragmentManager;

    public DJV_DatePicker(Context context) {
        super(context);
    }

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
        setText("Date of Birth");
        setOnClickListener(this);
        setTextColor(Color.BLACK);
    }

    public UI_Model getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_DatePicker(Context context, UI_Object ui_object) {
        super(context);
        fragmentManager = ui_object.getFragmentManager();
        setViewAttribute(ui_object);
        setDefaultAttribute();
    }


    @Override
    public void onDateSet(String dateString) {
        setText(dateString);
    }

    @Override
    public void onClick(View v) {
        DatePicker datePicker = new DatePicker();
        datePicker.newInstance(DJV_DatePicker.this).show(fragmentManager, "Choose date of birth");
    }
}
