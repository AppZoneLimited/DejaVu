package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com "Category":{ "Name":"Account Management",
 *          "Description":"This is for grouping accounts", "ID":16,
 *          "IsToSave":false, "VersionNumber":null, "InstitutionCode":null,
 *          "SuccessMessage":null, "FailureMessage":null },
 */

public class UI_Category {

	private String name, description, id, versionNumber, institutionCode,
			successMessage, failureMessage;
	private boolean isToSave;

	public UI_Category(JSONObject c) throws JSONException {
		if (c != null) {
			// TODO Auto-generated constructor stub
			setName(c.getString("Name"));
			setDescription(c.getString("Description"));
			setId(c.getString("ID"));
			setToSave(c.getBoolean("IsToSave"));
			setVersionNumber(c.getString("VersionNumber"));
			setInstitutionCode(c.getString("InstitutionCode"));
			setSuccessMessage(c.getString("SuccessMessage"));
			setFailureMessage(c.getString("FailureMessage"));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
