package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class UI_Validation {
	/*
	 * "Name":"Regular Expression Validator", "FullLibraryName":
	 * "DejaVu.Automation.ValidationModule.ValidateRegularExpression, DejaVu.Automation.ValidationModule"
	 * , "LibraryPath":"DejaVu.ParameterValidation.RegEx.Module.dll",
	 * "ShortLibraryName"
	 * :"DejaVu.Automation.ValidationModule.ValidateRegularExpression", "ID":1,
	 * "IsToSave":false, "VersionNumber":null, "InstitutionCode":null,
	 * "SuccessMessage":null, "FailureMessage":null
	 */

	private String name, id, versionNumber, institutionCode, successMessage,
			failureMessage, fullLibraryName, libraryPath, shortLibraryName;

	private boolean isToSave;

	public UI_Validation(JSONObject v) throws JSONException {
		if (v != null) {
			// TODO Auto-generated constructor stub
			setName(v.optString("Name"));
			setFullLibraryName(v.optString("FullLibraryName"));
			setLibraryPath(v.optString("LibraryPath"));
			setShortLibraryName(v.optString("ShortLibraryName"));
			setId(v.optString("ID"));
			setToSave(v.optBoolean("IsToSave"));
			setVersionNumber(v.optString("VersionNumber"));
			setInstitutionCode(v.optString("InstitutionCode"));
			setSuccessMessage(v.optString("SuccessMessage"));
			setFailureMessage(v.optString("FailureMessage"));
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

	public String getFullLibraryName() {
		return fullLibraryName;
	}

	public void setFullLibraryName(String fullLibraryName) {
		this.fullLibraryName = fullLibraryName;
	}

	public String getLibraryPath() {
		return libraryPath;
	}

	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	public String getShortLibraryName() {
		return shortLibraryName;
	}

	public void setShortLibraryName(String shortLibraryName) {
		this.shortLibraryName = shortLibraryName;
	}

	public boolean isToSave() {
		return isToSave;
	}

	public void setToSave(boolean isToSave) {
		this.isToSave = isToSave;
	}

}
