package dejavu.appzonegroup.com.dejavuandroid.Models;


import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Spec;

/**
 * Created by CrowdStar on 3/8/2015.
 */
public class UI_Model {

    private UI_Spec spec;
    private String value;
    private String valueKey;


    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }

    public String getValueKey() {
        return valueKey;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setSpec(UI_Spec spec) {
        this.spec = spec;
    }

    public UI_Spec getSpec() {
        return spec;
    }
}
