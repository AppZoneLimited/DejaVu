package dejavu.appzonegroup.com.dejavuandroid.Functions;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;
import com.appzone.zone.orchestra.engine.enums.StepTypeEnum;
import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;
import com.appzone.zone.orchestra.engine.script.interpeter.JSInterpreterEngine;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Activities.UiControlTrier;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Interface.EntitySynchronisationListener;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.LocalEntityService;
import dejavu.appzonegroup.com.dejavuandroid.Enums.ServiceUITypes;
import dejavu.appzonegroup.com.dejavuandroid.ObjectParceable.StepParceble;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;


/**
 * Created by emacodos on 4/10/2015.
 */
public class StepManager {

    public static final String TAG = StepManager.class.getSimpleName();


    public static void nextStepManager(final Context context, final View rootView, final Step step,
                                       final StepTypeEnum stepTypeEnum, JSONObject stepData,
                                       final FragmentManager fragmentManager) {
        String serviceName = step.getServiceName();
        switch (stepTypeEnum) {
            case SERVICE_UI_ENUM:
                //Init demo enum type of qrcode
                ServiceUITypes sut = ServiceUITypes.QR_SCANNER;

                StepParceble stepParceble = new StepParceble(step, stepData, null);

                final UiControlTrier uiControlTrier = new UiControlTrier();
                Bundle bundle = new Bundle();
                bundle.putParcelable(UiControlTrier.STEP_KEY, stepParceble);
                uiControlTrier.setArguments(bundle);

//                Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_right);
//                rootView.startAnimation(animation);
//                animation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                        FragmentTransaction transaction = fragmentManager.beginTransaction();
//                        transaction.addToBackStack(null);
//                        transaction.setCustomAnimations(R.animator.slide_left, R.animator.slide_right, R.animator.slide_left, R.animator.slide_right);
//                        transaction.add(R.id.content_frame, uiControlTrier).commitAllowingStateLoss();
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });


                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down, R.animator.slide_up, R.animator.slide_down);
                transaction.add(R.id.content_frame, uiControlTrier).commitAllowingStateLoss();

                break;

            case SERVICE_ENTITY_ENUM:
                final JSONObject data;
                data = stepData;
                try {
                    LocalEntityService.startLocalEntityService(context,
                            new JSONObject(step.getCommandName().getCommandNameString()),
                            data, new EntitySynchronisationListener() {
                                @Override
                                public void onResultRetrieved(JSONObject obj) {
                                    Log.e("r1", obj.toString());
                                    step.setStepEntityResultCallBack(obj, step.getStepAbstract(), new StepResultCallback() {
                                        @Override
                                        public void onStepResult(StepsAbstraction stepsAbstraction, Step step, JSONObject jsonObject) {

                                        }

                                        @Override
                                        public void onGetNextStep(Step nextStep, JSONObject jsonObject, StepTypeEnum stepTypeEnum, boolean b) {
                                            Log.e("t1", jsonObject.toString());
                                            nextStepManager(context, rootView, nextStep, stepTypeEnum, jsonObject, fragmentManager);
                                        }
                                    });
                                }
                            }, fragmentManager);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;

            case SERVICE_SCRIPT_ENUM:
                JSONObject object = null;
                try {
                    object = new JSONObject(step.getCommandName().getCommandNameString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String name = object != null ? object.optString("Name") : null;
                String args = object != null ? object.optString("Arguments") : null;
                String script = object != null ? object.optString("Script") : null;
                JSONObject result;
                if (name == null) {
                    result = executeScript(context, script);
                } else {
                    result = executeFunction(context, name, args, script);
                }
                step.setStepResultCallBack(result, step.getStepAbstract(), new StepResultCallback() {
                    @Override
                    public void onStepResult(StepsAbstraction stepAbstraction, Step s, JSONObject result) {
                        //Result
                    }

                    @Override
                    public void onGetNextStep(Step nextStep, JSONObject jsonObject, StepTypeEnum stepTypeEnum, boolean b) {
                        nextStepManager(context, rootView, nextStep, stepTypeEnum, jsonObject, fragmentManager);
                    }


                });
                break;

            case SERVICE_GOTO_ENUM:
                new ShowMessage(context, "Goto step", Toast.LENGTH_LONG);
                String stepStringId = step.getStepGotoId();


                new AsyncTask<Void, Void, Void>() {
                    ProgressDialog pd;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        pd = new ProgressDialog(context);
                        pd.setMessage("Making GoTO call...");
                        pd.show();
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        pd.dismiss();
                    }
                }.execute();
//                nextStepManager(context, step, stepData, fragmentManager);
//                JSONObject jsonObject = new JSONObject();// GoTo.makeRequest(context, step.getCommandName().getCommandNameString());
//                step.setStepResultCallBack(jsonObject, step.getStepAbstract(), new StepResultCallback() {
//                    @Override
//                    public void onStepResult(StepsAbstraction stepAbstraction, Step s, JSONObject result) {
//                        //REsult
//                    }
//
//                    @Override
//                    public void onGetNextStep(Step nextStep, JSONObject prevStepData) {
//                        nextStepManager(context, nextStep, prevStepData, fragmentManager);
//                    }
//                });
                break;
        }
    }

    private static JSONObject executeFunction(Context context, String name, String args, String script) {
        final JSONObject[] resultObj = {null};

        final JSInterpreterEngine jse = new JSInterpreterEngine(context);
        final String funcScript = jse.generateFunction(name, script, args);
        ArrayList<Object> argsArray = JSInterpreterEngine.generateArrayList(args);
        Object[] argsObject = jse.generateArgs(argsArray.size(), argsArray);

        jse.evaluateScript().callFunction(funcScript, new JsCallback() {
            @Override
            public void onResult(String s) {
                resultObj[0] = new JSONObject();
                try {
                    resultObj[0].put("Result", s);
                    Log.e("Result", s);
                } catch (JSONException e) {
                    Log.e(TAG + "OnFunctionResult", e.getMessage());
                }
            }
        }, name, argsObject);
        return resultObj[0];
    }

    private static JSONObject executeScript(Context context, String script) {
        final JSONObject[] resultObj = {null};
        JSInterpreterEngine jsEngine = new JSInterpreterEngine(context);
        jsEngine.evaluateScript().evaluate(script, new JsCallback() {

            @Override
            public void onResult(String arg0) {
                resultObj[0] = new JSONObject();
                try {
                    resultObj[0].put("Result", arg0);
                    //new ShowMessage(context, arg0, Toast.LENGTH_LONG);
                    Log.e("Result", arg0);
                } catch (JSONException e) {
                    Log.e(TAG + "OnScriptResult", e.getMessage());
                }
            }
        });
        return resultObj[0];
    }
}
