package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.CheckBox;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_CheckBox extends CheckBox {

    private UI_Model ui_models;
    private UI_Object ui_object;


    public void setUi_object(UI_Object ui_object) {
        this.ui_object = ui_object;
    }

    public UI_Object getUi_object() {
        return ui_object;
    }

    public final void setViewAttribute(UI_Object ui_object) {
        ui_models = new AttributeDefiner().AttributeReader(ui_object.getUi_spec(), ui_object.getStepData(), ui_object.getName());
        setUi_object(ui_object);
    }


    public final void setDefaultAttribute() {
        setTextColor(Color.BLACK);
        setText(getCustomViewAttribute().getValue());
    }

    public final UI_Model getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public DJV_CheckBox(Context context) {
        super(context);
    }

    public DJV_CheckBox(Context context, AttributeSet attrs, int defStyleAttr, UI_Object ui_object) {
        super(context, attrs, defStyleAttr);
        setViewAttribute(ui_object);
        setDefaultAttribute();
    }
}
