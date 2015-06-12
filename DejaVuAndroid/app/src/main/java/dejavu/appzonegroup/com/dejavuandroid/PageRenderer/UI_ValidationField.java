package dejavu.appzonegroup.com.dejavuandroid.PageRenderer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class UI_ValidationField {
	private String regularExpression;

	public UI_ValidationField(JSONObject vf) throws JSONException {
		// TODO Auto-generated constructor stub
		if (vf != null) {
			setRegularExpression(vf.optString("RegularExpression"));
		}
	}

	public String getRegularExpression() {
		return regularExpression;
	}

	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}
}
