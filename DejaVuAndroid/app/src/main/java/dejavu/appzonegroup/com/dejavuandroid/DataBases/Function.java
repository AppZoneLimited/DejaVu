package dejavu.appzonegroup.com.dejavuandroid.DataBases;

import android.content.Context;

import com.orm.androrm.BooleanField;
import com.orm.androrm.CharField;
import com.orm.androrm.Filter;
import com.orm.androrm.IntegerField;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emacodos on 2/18/2015.
 */
public class Function extends Model {

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_VERSION_NUMBER = "VersionNumber";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_FLOWGUID = "FlowGuid";
    public static final String COLUMN_CATEGORY_ID = "CategoryId";

    protected CharField Value;
    protected CharField VersionNumber;
    protected CharField Name;
    protected CharField Description;
    protected CharField FlowGuid;
    protected IntegerField CategoryID;
    protected BooleanField success;

    public Function(){
        super(true);

        Value = new CharField();
        VersionNumber = new CharField();
        Name = new CharField();
        Description = new CharField();
        FlowGuid = new CharField();
        CategoryID = new IntegerField();
        success = new BooleanField();
    }

    public static final QuerySet<Function> objects(Context context) {
        return objects(context, Function.class);
    }

    public String getValue() {
        return Value.get();
    }

    public void setValue(String value) {
        Value.set(value);
    }

    public String getVersionNumber() {
        return VersionNumber.get();
    }

    public void setVersionNumber(String versionNo) {
        VersionNumber.set(versionNo);
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String customerName) {
        Name.set(customerName);
    }

    public String getDescription() {
        return Description.get();
    }

    public void setDescription(String description) {
        Description.set(description);
    }

    public String getFlowGuid() {
        return FlowGuid.get();
    }

    public void setFlowGuid(String flowGuid) {
        FlowGuid.set(flowGuid);
    }

    public Integer getCategoryID() {
        return CategoryID.get();
    }

    public void setCategoryID(Integer categoryID) {
        CategoryID.set(categoryID);
    }

    public Boolean isSuccess() {
        return success.get();
    }

    public void setSuccess(Boolean state) {
        success.set(state);
    }

    public static ArrayList<Function> getAllFunctions(Context context) {
        return (ArrayList<Function>) Function.objects(context).all().toList();
    }

    public static List<Integer> getAll(Context context) {
        List<Integer> ids = new ArrayList<>();
        List<Function> list = Function.objects(context).all().toList();
        for (Function c : list){
            ids.add(c.getId());
        }
        return ids;
    }

    public static Function getFunctionById(Context context, int id) {
        return Function.objects(context).get(id);
    }

    public static String getFunctionByIdAsString(Context context, int id) {
        return Function.objects(context).get(id).getValue();
    }

    public static Function getFunctionByFlowId(Context context, String flowId) {
        Filter filter = new Filter();
        filter.is("FlowGuid", flowId);
        ArrayList<Function> functions = (ArrayList<Function>) Function.objects(context)
                .filter(filter).toList();
        if (functions.size() > 0) {
            return functions.get(0);
        }
        else return null;
    }

    public static String getAllFunctionsAsString(Context context) {
        ArrayList<Function> functions = (ArrayList<Function>) Function.objects(context).all().toList();
        JSONArray array = null;
        if (functions.size() > 0){
            array = new JSONArray();
            for (int i=0; i<functions.size(); i++) {
                array.put(functions.get(i).getValue());
            }
            return array.toString();
        }
        return null;
    }

    public static ArrayList<Function> getFunctionByCategory(Context context, int categoryId) {
        Filter filter = new Filter();
        filter.is("CategoryId", categoryId);
        ArrayList<Function> functions = (ArrayList<Function>) Function.objects(context)
                .filter(filter).toList();
       return functions;
    }
}
