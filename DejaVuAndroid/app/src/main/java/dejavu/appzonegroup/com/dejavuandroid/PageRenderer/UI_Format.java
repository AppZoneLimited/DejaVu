
package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class UI_Format {
	
	private String name, id, versionNumber,
	institutionCode, successMessage, failureMessage;
	private String regexFormat;
	private UI_Validation validation;
	private UI_ValidationField vf;
	
	private boolean isToSave;
	
	public UI_Format(JSONObject f) throws JSONException{
		if (f != null) {
			// TODO Auto-generated constructor stub
			setName(f.optString("Name"));
			setRegexFormat(f.optString("RegexFormat"));
			setValidation(new UI_Validation(f.optJSONObject("Validation")));
			setVf(new UI_ValidationField(f.optJSONObject("ValidationFields")));
			setId(f.optString("ID"));
			setToSave(f.optBoolean("IsToSave"));
			setVersionNumber(f.optString("VersionNumber"));
			setInstitutionCode(f.optString("InstitutionCode"));
			setSuccessMessage(f.optString("SuccessMessage"));
			setFailureMessage(f.optString("FailureMessage"));
		}
	}
	
	public String getRegexFormat() {
		return regexFormat;
	}

	public void setRegexFormat(String regexFormat) {
		this.regexFormat = regexFormat;
	}

	public UI_Validation getValidation() {
		return validation;
	}

	public void setValidation(UI_Validation validation) {
		this.validation = validation;
	}

	public UI_ValidationField getVf() {
		return vf;
	}

	public void setVf(UI_ValidationField vf) {
		this.vf = vf;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
