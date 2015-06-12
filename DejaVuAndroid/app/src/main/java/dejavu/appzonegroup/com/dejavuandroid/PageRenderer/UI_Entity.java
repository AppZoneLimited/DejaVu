package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CrowdStar on 4/22/2015.
 */
public class UI_Entity {

    String name, id, versionNumber, institutionCode, successMessage, failureMessage;
    boolean ToSave;


    public UI_Entity(String string) {
        try {
            JSONObject jsonObject = new JSONObject(string);
            setName(jsonObject.optString("Name"));
            setId(jsonObject.optString("ID"));
            setVersionNumber(jsonObject.optString("VersionNumber"));
            setInstitutionCode(jsonObject.optString("InstitutionCode"));
            setSuccessMessage(jsonObject.optString("SuccessMessage"));
            setFailureMessage(jsonObject.optString("FailureMessage"));
            setToSave(jsonObject.optBoolean("IsToSave"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public boolean isToSave() {
        return ToSave;
    }

    public void setToSave(boolean toSave) {
        ToSave = toSave;
    }
}
