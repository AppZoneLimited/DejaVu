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

/**
 * Created by emacodos on 2/18/2015.
 */
public class Entity extends Model {

    public static final String COLUMN_ID = "::ID::";
    public static final String COLUMN_TABLE_NAME = "tableName";
    public static final String COLUMN_SYNC = "sync";

    protected IntegerField entityId;
    protected CharField tableName;
    protected CharField value;
    protected BooleanField sync;

    public Entity(){
        super();

        entityId = new IntegerField();
        tableName = new CharField();
        value = new CharField();
        sync = new BooleanField();
    }

    public static final QuerySet<Entity> objects(Context context) {
        return objects(context, Entity.class);
    }

    public int getEntityId() {
        return entityId.get();
    }

    public void setEntityId(int id) {
        this.entityId.set(id);
    }

    public String getEntityName() {
        return tableName.get();
    }

    public void setEntityName(String entityName) {
        tableName.set(entityName);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String content) {
        value.set(content);
    }

    public boolean isSync(){
        return sync.get();
    }

    public void setSync(boolean state){
        sync.set(state);
    }

    public static ArrayList<Entity> getAllEntityByName(Context context, String entityName){
        Filter filter = new Filter();
        filter.is(Entity.COLUMN_TABLE_NAME, entityName);
        return  (ArrayList<Entity>) Entity.objects(context).filter(filter).toList();
    }

    public static Entity getEntityById(Context context, String entity, int entityId){
        Filter filter = new Filter();
        filter.is(Entity.COLUMN_TABLE_NAME, entity);
        filter.is("entityId", entityId);
        ArrayList<Entity> entities =  (ArrayList<Entity>) Entity.objects(context).filter(filter).toList();
        if (entities.size() > 0){
            return entities.get(0);
        }
        return null;
    }

    public static String getAllEntityByNameAsString(Context context, String entityName){
        Filter filter = new Filter();
        filter.is(Entity.COLUMN_TABLE_NAME, entityName);
        ArrayList<Entity> entities =   (ArrayList<Entity>) Entity.objects(context)
                .filter(filter).toList();
        JSONArray array = null;
        if (entities.size() > 0){
            array = new JSONArray();
            for (int i=0; i<entities.size(); i++) {
                array.put(entities.get(i).getValue());
            }
            return array.toString();
        }
        return null;
    }

    /*
    * return Entity not uploaded to the server
    */
    public static ArrayList<Entity> getUnSyncedEntity(Context context) {
        Filter filter = new Filter();
        filter.is(Entity.COLUMN_SYNC, 0);
        return (ArrayList<Entity>) Entity.objects(context).filter(filter).toList();
    }

}
