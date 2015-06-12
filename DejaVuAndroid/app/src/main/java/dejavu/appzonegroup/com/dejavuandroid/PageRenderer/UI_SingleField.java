package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;


import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class UI_SingleField {
	
	private String name;
    private String id;
    private String versionNumber;
    private String institutionCode;
    private String successMessage;
    private String failureMessage;
    private String sourceContent;
    private String type;
    private String dataSourceType;
    private String pageEntity;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {

        try {
            JSONObject jsonObject = null;
            jsonObject = new JSONObject(fieldName);
            this.fieldName = jsonObject.optString("FieldName");
        } catch (JSONException e) {
            this.fieldName = null;
            e.printStackTrace();
        }

    }

    private String fieldName;
    private String sectionColumn;
    private String customSpec;
    private String entitySource;
	private boolean isToSave, isCustom;
	private int rowNumber, theMatchMode;
	private UI_Spec spec;
	
	public UI_SingleField(JSONObject af) throws JSONException{
		
		setName(af.optString("Name"));
        setPageEntity(af.optString("ThePageEntity"));
        setFieldName(getPageEntity());
		setType(af.optString("Type"));
		setDataSourceType(af.optString("DataSourceType"));
		setSectionColumn(af.optString("SectionColumn"));
		setSpec(new UI_Spec(af.optJSONObject("Spec")));
		setCustom(af.optBoolean("IsCustom"));
		setSourceContent(af.optString("SourceContent"));
		setRowNumber(af.optInt("RowNumber"));
		setCustomSpec(af.optString("CustomSpec"));
		setEntitySource(af.optString("EntitySource"));
		setTheMatchMode(af.optInt("TheMatchMode"));
		setId(af.optString("ID"));
		setToSave(af.optBoolean("IsToSave"));
		setVersionNumber(af.optString("VersionNumber"));
		setInstitutionCode(af.optString("InstitutionCode"));
		setSuccessMessage(af.optString("SuccessMessage"));
		setFailureMessage(af.optString("FailureMessage"));
	}

    public String getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(String pageEntity) {
        this.pageEntity = pageEntity;
    }

    public String getName() {
		return name;
	}
	public UI_Spec getSpec() {
		return spec;
	}

	public void setSpec(UI_Spec spec) {
		this.spec = spec;
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
	public String getSourceContent() {
		return sourceContent;
	}
	public void setSourceContent(String sourceContent) {
		this.sourceContent = sourceContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getSectionColumn() {
		return sectionColumn;
	}
	public void setSectionColumn(String sectionColumn) {
		this.sectionColumn = sectionColumn;
	}
	public String getCustomSpec() {
		return customSpec;
	}
	public void setCustomSpec(String customSpec) {
		this.customSpec = customSpec;
	}
	public String getEntitySource() {
		return entitySource;
	}
	public void setEntitySource(String entitySource) {
		this.entitySource = entitySource;
	}
	public boolean isToSave() {
		return isToSave;
	}
	public void setToSave(boolean isToSave) {
		this.isToSave = isToSave;
	}
	public boolean isCustom() {
		return isCustom;
	}
	public void setCustom(boolean isCustom) {
		this.isCustom = isCustom;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getTheMatchMode() {
		return theMatchMode;
	}
	public void setTheMatchMode(int theMatchCode) {
		this.theMatchMode = theMatchCode;
	}
}
