package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.Entity;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.SpinnerDialog;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.selectionListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Entity;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_DropDown extends LinearLayout implements selectionListener {
    private UI_Model ui_models;
    private String[] items;
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
        if (getUi_object().getUi_spec() == null){
            items = getUi_object().getUi_singleField().getSourceContent().split(",");
            textView.setText(items[0]);
        }
        else if (getUi_object().getUi_spec().getParameterMode().equalsIgnoreCase("entity")) {
            getUi_object().setParameterMode(true);
            String entityString = getUi_object().getUi_singleField().getEntitySource();

            UI_Entity entity = new UI_Entity(entityString);
            ArrayList<Entity> entityArrayList = Entity.getAllEntityByName(getUi_object().getContext(), entity.getName());
            getUi_object().setEntityObject(entityArrayList);
            String keep = "";
            for (int entityIndex = 0; entityIndex < entityArrayList.size(); entityIndex++) {
                try {
                    JSONObject jsonObject = new JSONObject(entityArrayList.get(entityIndex).getValue());
                    keep = jsonObject.optString("::DisplayName::") + ",";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (!keep.trim().isEmpty()) {
                items = keep.substring(0, keep.length() - 1).split(",");
                textView.setText(items[0]);
            } else {
                items = new String[0];
            }
        } else {
            items = getUi_object().getUi_singleField().getSourceContent().split(",");
            textView.setText(items[0]);
        }
    }

    public final UI_Model getCustomViewAttribute() {
        return ui_models;
    }


    public DJV_DropDown(Context context) {
        super(context);
    }

    public DJV_DropDown(Context context, UI_Object ui_object) {
        super(context);
        initialise();
        setViewAttribute(ui_object);
        setDefaultAttribute();

    }

    TextView textView;
    TextView label;
    LinearLayout linearLayout;

    private void initialise() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.djv_dropdown, this);
        textView = (TextView) findViewById(R.id.text);
        label = (TextView) findViewById(R.id.label);
        linearLayout = (LinearLayout) findViewById(R.id.root);
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.requestFocus();
                label.setTextColor(getResources().getColor(R.color.light_blue));

                ArrayList arrayList = new ArrayList(Arrays.asList(items));
                SpinnerDialog spinnerDialog = new SpinnerDialog().spinnerDialog(DJV_DropDown.this, arrayList, ui_object.getName());
                spinnerDialog.show(ui_object.getFragmentManager(), "");
            }
        });

        linearLayout.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    label.setTextColor(getResources().getColor(R.color.light_blue));
                    ArrayList arrayList = new ArrayList(Arrays.asList(items));
                    SpinnerDialog spinnerDialog = new SpinnerDialog().spinnerDialog(DJV_DropDown.this, arrayList, ui_object.getName());
                    spinnerDialog.show(ui_object.getFragmentManager(), "");

                } else {
                    label.setTextColor(getResources().getColor(R.color.dark_gray));
                }
                linearLayout.setSelected(hasFocus);
            }
        });


    }

    @Override
    public void onItemSelected(int index, String text) {
        textView.setText(text);
    }
}
