package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_FileChooser extends Button implements View.OnClickListener {


    private UI_Model ui_models;
    private UI_Object ui_object;
    FileChooserListener mListener;


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
        setText(getCustomViewAttribute().getValue());
        setTextColor(Color.BLACK);
        setText("Select File");
    }


    public UI_Model getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_FileChooser(Context context) {
        super(context);
    }

    public DJV_FileChooser(Context context, AttributeSet attrs, int defStyleAttr, UI_Object ui_object) {
        super(context);
        mListener = ui_object.getListener();
        setViewAttribute(ui_object);
        setDefaultAttribute();
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mListener.openFileChooser(v);
    }
}
