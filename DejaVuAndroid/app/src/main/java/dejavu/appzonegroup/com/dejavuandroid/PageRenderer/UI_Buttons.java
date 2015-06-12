package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * @see { "ButtonName":"Next", "Name":"Save Clicked", "SectionColumn":null,
 *      "ID":128,
 * 
 *      "IsToSave":false, "VersionNumber":null, "InstitutionCode":null,
 *      "SuccessMessage":null, "FailureMessage":null }
 */

public class UI_Buttons {

	private String buttonName, name, sectionColumn, id, versionNumber,
			institutionCode, successMessage, failureMessage;
	private boolean isToSave;

	public UI_Buttons(JSONObject b) throws JSONException {
		// TODO Auto-generated constructor stub
		setButtonName(b.getString("ButtonName"));
		setName(b.getString("Name"));
		setSectionColumn(b.getString("SectionColumn"));
		setId(b.getString("ID"));
		setToSave(b.getBoolean("IsToSave"));
		setVersionNumber(b.getString("VersionNumber"));
		setInstitutionCode(b.getString("InstitutionCode"));
		setSuccessMessage(b.getString("SuccessMessage"));
		setFailureMessage(b.getString("FailureMessage"));
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSectionColumn() {
		return sectionColumn;
	}

	public void setSectionColumn(String sectionColumn) {
		this.sectionColumn = sectionColumn;
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
		return isToSave;
	}

	public void setToSave(boolean isToSave) {
		this.isToSave = isToSave;
	}
}
