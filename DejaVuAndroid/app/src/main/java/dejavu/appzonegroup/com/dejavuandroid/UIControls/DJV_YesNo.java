package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 3/20/2015.
 */
public class DJV_YesNo extends LinearLayout {
    private UI_Model ui_models;
    private UI_Object ui_object;

    public void setUi_object(UI_Object ui_object) {
        this.ui_object = ui_object;
    }

    public UI_Object getUi_object() {
        return ui_object;
    }

    public final void setViewAttribute(UI_Object ui_object) {
        label.setText(ui_object.getName());
        ui_models = new AttributeDefiner().AttributeReader(ui_object.getUi_spec(), ui_object.getStepData(), ui_object.getName());

    }


    public final void setDefaultAttribute() {
        setId(Integer.parseInt(getCustomViewAttribute().getSpec().getId()));
    }

    public final UI_Model getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_YesNo(Context context) {
        super(context);
    }

    public DJV_YesNo(Context context, UI_Object ui_object) {
        super(context);
        initialise();
        setViewAttribute(ui_object);
        setDefaultAttribute();

    }

    TextView label;
    LinearLayout linearLayout;
    Switch toggleButton;

    private void initialise() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.djv_yes_no, this);
        label = (TextView) findViewById(R.id.label);
        toggleButton = (Switch)findViewById(R.id.Switch);
        toggleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!linearLayout.isFocused()){
                    linearLayout.requestFocus();
                }
            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.root);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.requestFocus();
                setToggleButton();
            }
        });

        linearLayout.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    label.setTextColor(getResources().getColor(R.color.light_blue));
                } else {
                    label.setTextColor(getResources().getColor(R.color.dark_gray));
                }
                linearLayout.setSelected(hasFocus);
                setToggleButton();
            }
        });
    }

    public void setToggleButton(){
        if(toggleButton.isChecked()){
            toggleButton.setChecked(false);
        }else{
            toggleButton.setChecked(true);
        }
    }
}
