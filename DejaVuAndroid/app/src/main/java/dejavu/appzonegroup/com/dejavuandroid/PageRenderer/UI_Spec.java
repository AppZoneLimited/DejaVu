package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;

import com.appzone.zone.orchestra.engine.datatypes.Format;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class UI_Spec {

	/**
	 * "Spec":{ "IsRequired":true, "IsPrimaryIdentifier":false,
	 * "IsUnique":false, "IsEditable":false, "IsNotEditable":false,
	 * "FixedLength":0, "MinimumLength":0, "MaximumLength":0, "Format":{
	 * "Name":"String", "RegexFormat":null, "Validation":{
	 * "Name":"Regular Expression Validator", "FullLibraryName":
	 * "DejaVu.Automation.ValidationModule.ValidateRegularExpression, DejaVu.Automation.ValidationModule"
	 * , "LibraryPath":"DejaVu.ParameterValidation.RegEx.Module.dll",
	 * "ShortLibraryName"
	 * :"DejaVu.Automation.ValidationModule.ValidateRegularExpression", "ID":1,
	 * "IsToSave":false, "VersionNumber":null, "InstitutionCode":null,
	 * "SuccessMessage":null, "FailureMessage":null }, "ValidationFields":{
	 * "RegularExpression":"^.*$" }, "ID":49, "IsToSave":false,
	 * "VersionNumber":null, "InstitutionCode":null, "SuccessMessage":null,
	 * "FailureMessage":null }, "ParameterMode":"Custom", "ID":16208,
	 * "IsToSave":false, "VersionNumber":null, "InstitutionCode":null,
	 * "SuccessMessage":null, "FailureMessage":null },
	 */

	private String id, versionNumber, institutionCode, successMessage,
			failureMessage,SourceContent;

	private String parameterMode;

	private int fixedLength, minimumLength, maximumLength;

	private boolean isToSave, isRequired, isPrimaryIdentifier, isUnique,
			isEditable, isNotEditable;
	private Format format;

	public UI_Spec(JSONObject s) throws JSONException {
		if (s != null) {
			// TODO Auto-generated constructor stub
			setRequired(s.optBoolean("IsRequired"));
			setPrimaryIdentifier(s.optBoolean("IsPrimaryIdentifier"));
			setUnique(s.optBoolean("IsUnique"));
			setEditable(s.optBoolean("IsEditable"));
			setNotEditable(s.optBoolean("IsNotEditable"));
			setFixedLength(s.optInt("FixedLength"));
			setMinimumLength(s.optInt("MinimumLength"));
			setMaximumLength(s.optInt("MaximumLength"));
			setFormat(new Format(s.optJSONObject("Format")));
			setParameterMode(s.optString("ParameterMode"));
			setId(s.optString("ID"));
			setToSave(s.optBoolean("IsToSave"));
			setVersionNumber(s.optString("VersionNumber"));
			setInstitutionCode(s.optString("InstitutionCode"));
			setSuccessMessage(s.optString("SuccessMessage"));
			setFailureMessage(s.optString("FailureMessage"));
		}
	}

	public String getParameterMode() {
		return parameterMode;
	}

	public void setParameterMode(String parameterMode) {
		this.parameterMode = parameterMode;
	}

	public int getFixedLength() {
		return fixedLength;
	}

	public void setFixedLength(int fixedLength) {
		this.fixedLength = fixedLength;
	}

	public int getMinimumLength() {
		return minimumLength;
	}

	public void setMinimumLength(int minimumLength) {
		this.minimumLength = minimumLength;
	}

	public int getMaximumLength() {
		return maximumLength;
	}

	public void setMaximumLength(int maximumLength) {
		this.maximumLength = maximumLength;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isPrimaryIdentifier() {
		return isPrimaryIdentifier;
	}

	public void setPrimaryIdentifier(boolean isPrimaryIdentifier) {
		this.isPrimaryIdentifier = isPrimaryIdentifier;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public boolean isNotEditable() {
		return isNotEditable;
	}

	public void setNotEditable(boolean isNotEditable) {
		this.isNotEditable = isNotEditable;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
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

    public void setSourceContent(String sourceContent) {
        SourceContent = sourceContent;
    }

    public String getSourceContent() {
        return SourceContent;
    }
}
