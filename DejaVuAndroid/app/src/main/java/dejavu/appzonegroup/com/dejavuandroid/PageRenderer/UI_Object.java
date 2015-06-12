package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.Entity;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;

/**
 * Created by CrowdStar on 4/22/2015.
 */
public class UI_Object {
    UI_Spec ui_spec;
    UI_SingleField ui_singleField;
    JSONObject stepData;
    Context context;
    FragmentManager fragmentManager;
    FileChooserListener listener;
    boolean isParameterMode;
    ArrayList<Entity> entityObject;

    String entityName;

    String type,name;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isParameterMode() {
        return isParameterMode;
    }

    public void setParameterMode(boolean isParameterMode) {
        this.isParameterMode = isParameterMode;
    }

    public ArrayList<Entity> getEntityObject() {
        return entityObject;
    }

    public void setEntityObject(ArrayList<Entity> entityObject) {
        this.entityObject = entityObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setUi_singleField(UI_SingleField ui_singleField) {
        this.ui_singleField = ui_singleField;
    }

    public void setUi_spec(UI_Spec ui_spec) {
        this.ui_spec = ui_spec;
    }

    public UI_SingleField getUi_singleField() {
        return ui_singleField;
    }

    public UI_Spec getUi_spec() {
        return ui_spec;
    }

    public void setStepData(JSONObject stepData) {
        this.stepData = stepData;
    }

    public JSONObject getStepData() {
        return stepData;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setListener(FileChooserListener listener) {
        this.listener = listener;
    }

    public FileChooserListener getListener() {
        return listener;
    }
}
