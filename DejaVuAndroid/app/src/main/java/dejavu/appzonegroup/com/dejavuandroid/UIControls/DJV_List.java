package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_List extends ListView {
    private UI_Model ui_models;
    private UI_Object ui_object;
    private String[] items;
    private ArrayAdapter itemAdapter;


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

        items = getCustomViewAttribute().getValue().split(",");

    }

    public final UI_Model getCustomViewAttribute() {
        return ui_models;
    }


    public DJV_List(Context context) {
        super(context);
    }


    public DJV_List(Context context, UI_Object ui_object) {
        super(context);
        setViewAttribute(ui_object);
        setDefaultAttribute();
        itemAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, items) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                //we know that simple_spinner_item has android.R.id.text1 TextView:

        /* if(isDroidX) {*/
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);//choose your color :)
                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                //we know that simple_spinner_item has android.R.id.text1 TextView:

        /* if(isDroidX) {*/
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);//choose your color :)
                return view;
            }
        };
        setAdapter(itemAdapter);

    }


}
