package dejavu.appzonegroup.com.dejavuandroid.DataBases;


import android.content.Context;

import com.orm.androrm.CharField;
import com.orm.androrm.Filter;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emacodos on 2/18/2015.
 */

public class ClientFlows extends Model {

    public static final String COLUMN_ID = "Id";

    protected CharField FlowsID;
    protected CharField Flows;

    public ClientFlows(){
        super();

        Flows = new CharField();
        FlowsID = new CharField();
    }

    public static final QuerySet<ClientFlows> objects(Context context) {
        return Model.objects(context, ClientFlows.class);
    }

    public String getFlowsID() {
        return FlowsID.get();
    }

    public void setFlowsID(String flowsID) {
        FlowsID.set(flowsID);
    }

    public String getFlows() {
        return Flows.get();
    }

    public void setFlows(String content) {
        Flows.set(content);
    }

    public static ClientFlows getFlowById(Context context, String id){
        Filter filter = new Filter();
        filter.is("FlowsID", id);
        ArrayList<ClientFlows> flowses = (ArrayList<ClientFlows>) ClientFlows.objects(context).filter(filter).toList();
        if (flowses.size() > 0){
            return flowses.get(0);
        }
        else {
            return null;
        }
    }

    public static ArrayList<ClientFlows> getAllFlows(Context context){
        return (ArrayList<ClientFlows>) ClientFlows.objects(context).all().toList();
    }

    public static List<String> getAll(Context context){
        List<String> ids = new ArrayList<>();
        List<ClientFlows> list = ClientFlows.objects(context).all().toList();
        for (ClientFlows c : list){
            ids.add(c.getFlowsID());
        }
        return ids;
    }

    public static String getFlowByIdAsString(Context context, String id){
        Filter filter = new Filter();
        filter.is("FlowsID", id);
        ArrayList<ClientFlows> flowses = (ArrayList<ClientFlows>) ClientFlows.objects(context).filter(filter).toList();
        if (flowses.size() > 0){
            return flowses.get(0).getFlows();
        }
        else {
            return null;
        }
    }
}
