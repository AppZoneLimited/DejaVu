package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 3/6/2015.
 */
public class DJV_TextField extends LinearLayout {


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
        label.setText(getCustomViewAttribute().getValueKey());
        if (getCustomViewAttribute().getValue().isEmpty()) {
            textView.setHint("Enter " + getCustomViewAttribute().getValueKey());
        } else {
            textView.setText(getCustomViewAttribute().getValue());
        }
        textView.setSingleLine();

    }

    public EditText getView(){
        return textView;
    }


    public final UI_Model getCustomViewAttribute() {
        return ui_models;
    }


    public DJV_TextField(Context context, AttributeSet attrs, int defStyleAttr, UI_Object ui_object) {
        super(context, attrs, defStyleAttr);
        initialise();
        setViewAttribute(ui_object);
        setDefaultAttribute();
    }

    EditText textView;
    TextView label;
    LinearLayout linearLayout;

    private void initialise() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.djv_textfield, this);
        textView = (EditText) findViewById(R.id.text);
        label = (TextView) findViewById(R.id.label);
        linearLayout = (LinearLayout) findViewById(R.id.root);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.requestFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        textView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    label.setTextColor(getResources().getColor(R.color.light_blue));
                }else{
                    label.setTextColor(getResources().getColor(R.color.dark_gray));
                }
                linearLayout.setSelected(hasFocus);
            }
        });

    }
}
