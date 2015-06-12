package dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.Entity;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Interface.EntitySynchronisationListener;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.ProgressFragment;


/**
 * Created by emacodos on 3/7/2015.
 */

/*
* NOTES:
* For the Local event type
*
* 1. create: add new data to the data to the database ie the data passed on the create method
* 2. update: updates an existing record on the database. when data parameter on the update method
 *           is null, an error is returned. while otherwise the id from the data is used to identify
 *           the row to be updated.
 *3. retrieve: gets the records stored in the database, if data parameter is null, every row
 *             associated with the entity is retrieved. while otherwise the id from the data is used
 *             to identify the row to be retrieved.
 *4. delete: deletes the records stored in the database. if data parameter is null, every row
 *           associated with the entity is deleted. while otherwise the id from the data parameter
 *           is used to identify the row to be deleted.
*/

public class LocalEntityService extends IntentService {

    public static final String TAG = LocalEntityService.class.getSimpleName();

    public static final String PARAM_INSTRUCTION = "instruction";
    public static final String PARAM_DATA = "data";

    public static final String OPERATION_CREATE = "create";
    public static final String OPERATION_UPDATE = "update";
    public static final String OPERATION_RETRIEVE = "retrieve";
    public static final String OPERATION_DELETE = "delete";

    public static final String NAME_TYPE = "type";
    public static final String NAME_OPERATION = "operation";
    public static final String NAME_ENTITY = "entity";
    public static final String NAME_EVENT_NAME = "EventName";
    public static final String NAME_EVENT_DATA = "EventData";
    public static final String NAME_REASON = "Reason";

//    public static final String VALUE_CREATED = "Entity Created";
//    public static final String VALUE_UPDATED = "Entity Updated";
//    public static final String VALUE_RETRIEVED = "Entity Retrieved";
//    public static final String VALUE_DELETED = "Entity Deleted";
//    public static final String VALUE_UPLOADED = "Entity Uploaded";
    public static final String VALUE_SUCCESS = "Operation Successful";

    public static final String VALUE_ERROR = "Entity Operation Failed";

    public static final String TYPE_SERVER = "server";
    public static final String TYPE_LOCAL = "local";

    public static final String URL_ENTITY = "http://165.233.246.29/ZoneEntityService/api/EntityDataService/performcrud";

    private static JSONObject mData, mInstruction;

    private static Context mContext;
    public static Object mEntityGroup = new Object();
    public static EntitySynchronisationListener mSync;

//    static Step mStep;
//    static StepsAbstraction mStepsAbstraction;
//    static View mView;
    static FragmentManager mFragmentManager;


      ProgressFragment mProgressFragment;

    public static void startLocalEntityService(Context context, JSONObject instruction, JSONObject data,
                                               EntitySynchronisationListener sync, FragmentManager fragmentManager) {//, Step step, StepsAbstraction stepsAbstraction, View view, FragmentManager fragmentManager) {
        Intent intent = new Intent(context, LocalEntityService.class);
        mInstruction = instruction;
        mData = data;
        mContext = context;
        mSync = sync;
//        mStep = step;
//        mStepsAbstraction = stepsAbstraction;
//        mView = view;
        mFragmentManager = fragmentManager;
        context.startService(intent);
    }

    public LocalEntityService() {
        super("LocalEntityService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Ion.getDefault(this).configure().setLogging(TAG, Log.ERROR);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mProgressFragment = new ProgressFragment();
        mProgressFragment.setCancelable(false);
        mProgressFragment.show(mFragmentManager, "");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProgressFragment.dismiss();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            JSONObject message = localEntityService();
//            mStep.setStepResultCallBack(message, mStepsAbstraction, new StepResultCallback() {
//                @Override
//                public void onStepResult(StepsAbstraction stepsAbstraction, Step step, JSONObject jsonObject) {
//
//                }
//
//                @Override
//                public void onGetNextStep(Step step, JSONObject jsonObject, StepTypeEnum stepTypeEnum, boolean b) {
//                    StepManager.nextStepManager(mContext, mView, step, stepTypeEnum, jsonObject, mFragmentManager);
//                }
//            });
            mSync.onResultRetrieved(message);
        }
    }

    public JSONObject localEntityService() {
        String type, operation, entity;

        if (mInstruction == null || TextUtils.isEmpty(mInstruction.toString())) {
            return eventError("Null instruction Value");
        } else {
            //Parse JSON instruction to Java Object
            try {
                JSONObject inst = mInstruction;
                type = inst.getString(NAME_TYPE).toLowerCase();
                operation = inst.getString(NAME_OPERATION).toLowerCase();
                entity = inst.getString(NAME_ENTITY);
            } catch (Exception e) {
                return eventError("Bad Instruction");
            }

            //Check for the method type
            if (type.equalsIgnoreCase(TYPE_SERVER)) {//Do server work
                return doServerOperation(mInstruction, mData);
            } else {//Do local work
                if (operation.equalsIgnoreCase(OPERATION_CREATE)) {//Do Create
                    return addNewRecord(entity, mData);
                } else if (operation.equals(OPERATION_RETRIEVE)) {//Do Retrieve
                    return queryDB(entity, mData);
                } else if (operation.equals(OPERATION_UPDATE)) {//Do Update
                    return updateRecord(entity, mData);
                } else if (operation.equals(OPERATION_DELETE)) {//Do Delete
                    return deleteRecord(entity, mData);
                } else {
                    return eventError("Unknown Operation");
                }
            }
        }
    }

    public JSONObject doServerOperation(JSONObject instruction, JSONObject data) {

        Ion.getDefault(this).configure().setLogging("MyLogs", Log.ERROR);

        if (instruction == null || TextUtils.isEmpty(instruction.toString())) {
            return eventError("Null Instruction Value");
        } else {
            if (isNetworkAvailable()) {
                JsonObject dataObj;
                if (data == null || TextUtils.isEmpty(data.toString().trim())) {
                    dataObj = null;
                } else {
                    dataObj = new JsonParser().parse(data.toString()).getAsJsonObject();
                }
                JsonObject insObj = new JsonParser().parse(instruction.toString()).getAsJsonObject();
                JsonObject json = new JsonObject();
                json.add(PARAM_INSTRUCTION, insObj);
                json.add(PARAM_DATA, dataObj);

                String mOutput;
                try {
                    mOutput = Ion.with(mContext)
                            .load("POST", URL_ENTITY)
                            .setJsonObjectBody(json)
                            .group(mEntityGroup)
                            .asString()
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                    return eventError("Connection Timed Out");
                }
                if (mOutput != null) {
                    try {
                        return new JSONObject(mOutput);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return eventError("Server Error");
                    }
                } else
                    return eventError("Cannot Connect to the Url");
            } else {
                try {
                    setMobileDataEnabled(mContext, true);
                    if (isNetworkAvailable())
                        return doServerOperation(instruction, data);
                    else
                        return eventError("Internet Unavailable");
                } catch (Exception e) {
                    e.printStackTrace();
                    return eventError("Internet Unavailable");
                }
            }
        }
    }

    public static JSONObject addNewRecord(String entity, JSONObject data) {
        if (entity == null || TextUtils.isEmpty(entity)) {
            return eventError("Null entity Value");
        } else if (data == null || TextUtils.isEmpty(data.toString())) {
            return eventError("Null data Value");
        } else {
            Entity table = new Entity();
            table.setEntityName(entity);
            table.setValue(data.toString());
            if (table.save(mContext)) {
                try {
                    uploadToServer(table);
                } catch (JSONException e) {
                    return eventError("Invalid Data");
                }
                return eventSuccess(VALUE_SUCCESS, mData);
            } else {
                return eventError("SQL Error");
            }
        }
    }

    public static void uploadToServer(Entity entity) throws JSONException {
        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(entity);

        JSONArray json = new JSONArray();
        JSONObject data = new JSONObject(entity.getValue());
        JSONObject object = new JSONObject();
        object.put(NAME_ENTITY, entity.getEntityName());
        object.put(PARAM_DATA, data);
        json.put(object);

        FlowSyncService.uploadEntities(mContext, entities, json);
    }

    public static JSONObject queryDB(String entity, JSONObject data) {
        if (data == null) {
            ArrayList<Entity> entities = Entity.getAllEntityByName(mContext, entity);
            if (entities.size() > 0) {
                JSONObject output = new JSONObject();
                JSONArray dataList = new JSONArray();
                for (int i = 0; i < entities.size(); i++) {
                    dataList.put(entities.get(i).getValue());
                }
                try {
                    output.put(NAME_EVENT_NAME, VALUE_SUCCESS);
                    output.put(NAME_EVENT_DATA, dataList);
                    return output;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return eventError("JSON Error");
                }
            } else {
                return eventError("No such Entity Found");
            }
        } else {
            JSONObject object;
                object = data;
            int id;
            try {
                id = object.getInt(Entity.COLUMN_ID);
            } catch (JSONException e) {
                e.printStackTrace();
                return eventError("Invalid ID");
            }
            Entity table = Entity.getEntityById(mContext, entity, id);
            if (table != null) {
                try {
                    return eventSuccess(VALUE_SUCCESS, new JSONObject(table.getValue()));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return eventError("Invalid Data");
                }
            } else {
                return eventError("No such Entity Found");
            }
        }
    }

    public static JSONObject updateRecord(String entity, JSONObject data) {
        if (data == null || TextUtils.isEmpty(data.toString())) {
            return eventError("Null data Value");
        } else {
            JSONObject object;
                object = data;
            int id;
            try {
                id = object.getInt(Entity.COLUMN_ID);
            } catch (JSONException e) {
                e.printStackTrace();
                return eventError("Invalid ID");
            }
            Entity table = Entity.getEntityById(mContext, entity, id);
            if (entity != null) {
                table.setValue(data.toString());
                table.setSync(false);
                table.save(mContext);
                return eventSuccess(VALUE_SUCCESS, mData);
            } else {
                return eventError("No such Entity Found");
            }
        }
    }

    public static JSONObject deleteRecord(String entity, JSONObject data) {
        if (data == null || TextUtils.isEmpty(data.toString())) {
            ArrayList<Entity> entities = Entity.getAllEntityByName(mContext, entity);
            if (entities.size() > 0) {
                JSONObject output = new JSONObject();
                JSONArray dataList = new JSONArray();
                for (int i = 0; i < entities.size(); i++) {
                    String dataStr = entities.get(i).getValue();
                    dataList.put(dataStr);
                    entities.get(i).delete(mContext);
                }
                try {
                    output.put(NAME_EVENT_NAME, VALUE_SUCCESS);
                    output.put(NAME_EVENT_DATA, dataList);
                    return output;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return eventError("JSON Error");
                }
            } else {
                return eventError("No Such Entity Found");
            }
        } else {
            JSONObject object;
                object = data;
            int id;
            try {
                id = object.getInt(Entity.COLUMN_ID);
            } catch (JSONException e) {
                e.printStackTrace();
                return eventError("Invalid ID");
            }
            Entity table = Entity.getEntityById(mContext, entity, id);
            if (table != null) {
                table.delete(mContext);
                return eventSuccess(VALUE_SUCCESS, mData);
            } else {
                return eventError("No such Entity Found");
            }
        }
    }

    private static JSONObject eventError(String reason) {
        JSONObject out = new JSONObject();
        try {
            out.put(NAME_EVENT_NAME, VALUE_ERROR);
            out.put(NAME_REASON, reason);
            return out;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject eventSuccess(String eventName, JSONObject data) {
        JSONObject output = new JSONObject();
        try {
            output.put(NAME_EVENT_NAME, eventName);
            output.put(NAME_EVENT_DATA, data);
            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    * Check for network connection availability
    */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void setMobileDataEnabled(Context context, boolean enabled) throws Exception {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
        iConnectivityManagerField.setAccessible(true);
        final Object iConnectivityManager = iConnectivityManagerField.get(conman);
        final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
    }

}