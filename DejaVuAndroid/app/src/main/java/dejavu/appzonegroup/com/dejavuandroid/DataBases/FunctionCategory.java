package dejavu.appzonegroup.com.dejavuandroid.DataBases;

import android.content.Context;

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
public class FunctionCategory extends Model {

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_VERSION_NUMBER = "VersionNumber";
    public static final String COLUMN_PARENT_CATEGORY_ID = "ParentCategory";

    protected CharField Name;
    protected CharField VersionNumber;
    protected CharField Value;
    protected IntegerField ParentCategoryID;

    public FunctionCategory(){
        super(true);

        Name = new CharField();
        VersionNumber = new CharField();
        Value = new CharField();
        ParentCategoryID = new IntegerField();
    }

    public static final QuerySet<FunctionCategory> objects(Context context) {
        return objects(context, FunctionCategory.class);
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String customerName) {
        Name.set(customerName);
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

    public Integer getParentCategoryID() {
        return ParentCategoryID.get();
    }

    public void setParentCategoryID(Integer parentCategoryID) {
        ParentCategoryID.set(parentCategoryID);
    }
    public static FunctionCategory getFunctionCategoryById(Context context, int id) {
        return FunctionCategory.objects(context).get(id);
    }

    public static ArrayList<FunctionCategory> getAllFunctionCategory(Context context) {
        return (ArrayList<FunctionCategory>) FunctionCategory.objects(context).all().toList();
    }

    public static List<Integer> getAll(Context context) {
        List<Integer> ids = new ArrayList<>();
        List<FunctionCategory> list = FunctionCategory.objects(context).all().toList();
        for (FunctionCategory c : list){
            ids.add(c.getId());
        }
        return ids;
    }

    public static ArrayList<FunctionCategory> getParent(Context context) {
        Filter filter = new Filter();
        filter.is("ParentCategoryID", 0);
        return (ArrayList<FunctionCategory>) FunctionCategory.objects(context).filter(filter).toList();
    }

    public static ArrayList<FunctionCategory> getChild(Context context, int parentId) {
        Filter filter = new Filter();
        filter.is("ParentCategoryID", parentId);
        return (ArrayList<FunctionCategory>) FunctionCategory.objects(context).filter(filter).toList();
    }

    public static ArrayList<Integer> getAllCategoryIds(Context context) {
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<FunctionCategory> functionCategories = getAllFunctionCategory(context);
        if (functionCategories.size() > 0){
            for (int i=0; i<functionCategories.size(); i++) {
                ids.add(functionCategories.get(i).getId());
            }
        }
        return ids;
    }

    public static String getFunctionCategoryByIdAsString(Context context, int id) {
        return FunctionCategory.objects(context).get(id).getValue();
    }

    public static String getAllFunctionCategoriesAsString(Context context) {
        ArrayList<FunctionCategory> categories = (ArrayList<FunctionCategory>) FunctionCategory
                .objects(context).all().toList();
        JSONArray array = null;
        if (categories.size() > 0){
            array = new JSONArray();
            for (int i=0; i<categories.size(); i++) {
                array.put(categories.get(i).getValue());
            }
            return array.toString();
        }
        return null;
    }
}
