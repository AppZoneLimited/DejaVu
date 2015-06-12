package dejavu.appzonegroup.com.dejavuandroid.JSONReader;

import org.json.JSONArray;
import org.json.JSONObject;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.ConfigurationRequest;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class ConfigurationJsonReader {


    public ConfigurationJsonReader(String result, ConfigurationRequest.onConfigurationRequest mConfigurationRequest) {
        try {
            JSONObject configurationJsonObject = new JSONObject(result);
            String flowString = configurationJsonObject.optString("AppUser");
            int flow = FlowConstant.WRONG_FLOW;
            if (flowString.equalsIgnoreCase("Generic")) {
                flow = FlowConstant.GENERIC_FLOW;
            } else if (flowString.equalsIgnoreCase("Bank")) {
                flow = FlowConstant.BANK_FLOW;
            }
            JSONArray array = new JSONArray(configurationJsonObject.opt("AuthenticationOptions").toString());
            boolean debit = false, password = false, hardToken = false, softToken = false;
            for (int x = 0; x < array.length(); x++) {
                System.out.println(array.get(x));
                switch (array.get(x).toString()) {
                    case "HARDWARETOKEN":
                        hardToken = true;
                        break;
                    case "PASSWORD":
                        password = true;
                        break;
                    case "OTP":
                        softToken = true;
                        break;
                    case "DEBITCARD":
                        debit = true;
                        break;
                }
            }

            mConfigurationRequest.onConfigurationRequestSuccessful(flow, debit, password, hardToken, softToken);

        } catch (Exception e) {
            mConfigurationRequest.onConfigurationRequestFailed(e.getMessage());
        }
    }
}
