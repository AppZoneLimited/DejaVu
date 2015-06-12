package dejavu.appzonegroup.com.dejavuandroid.Activities;

/**
 * Created by CrowdStar on 4/20/2015.
 */
public class ListModel {
    String name,id;
    boolean flow;

    public void setID(String childID) {
        this.id = childID;
    }

    public void setName(String childName) {
        this.name = childName;
    }

    public void setFlowGUID(String flowGUID) {
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setFlow(boolean flow) {
        this.flow = flow;
    }

    public boolean isFlow() {
        return flow;
    }
}
