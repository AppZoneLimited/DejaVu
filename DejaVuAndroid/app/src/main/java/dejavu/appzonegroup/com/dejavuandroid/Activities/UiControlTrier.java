package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;
import com.appzone.zone.orchestra.engine.enums.StepTypeEnum;
import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.CustomViews.MyScroll;
import dejavu.appzonegroup.com.dejavuandroid.Functions.StepManager;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.UI_Type;
import dejavu.appzonegroup.com.dejavuandroid.Map.viewControl;
import dejavu.appzonegroup.com.dejavuandroid.ObjectParceable.StepParceble;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Buttons;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_CommandName;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Sections;
import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_SingleField;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_Button;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_CheckBox;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DropDown;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_FileChooser;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextArea;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextField;

/**
 * Created by CrowdStar on 3/6/2015.
 */
public class UiControlTrier extends Fragment implements FileChooserListener {

    View rootView;
    UiControlTrier uiControlTrier;
    LinearLayout rootLayout;
    public static final String STEP_KEY = "step";
    Step step;
    JSONObject stepData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.ui_control, container, false);
        Point size = new Point();
        WindowManager w = getActivity().getWindowManager();
        w.getDefaultDisplay().getSize(size);

        if (ListFunction.isAutoScroll) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ((size.y - 200)/2));
            LinearLayout falseLinearLayout = (LinearLayout) rootView.findViewById(R.id.false_scroll);
            falseLinearLayout.setLayoutParams(params);
        }

        // getActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE);
        step = ((StepParceble) getArguments().getParcelable(STEP_KEY)).getmStep();
        stepData = ((StepParceble) getArguments().getParcelable(STEP_KEY)).getmStepData();


        UI_CommandName commandName = null;
        try {
            commandName = new UI_CommandName(new JSONObject(step.getCommandNameString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<UI_Sections> sectionsArrayList = commandName.getSections();
        rootLayout = (LinearLayout) rootView.findViewById(R.id.root_linear_vertical);
        for (int sectionIndex = 0; sectionIndex < sectionsArrayList.size(); sectionIndex++) {
            LinearLayout view = sectionLayout(sectionsArrayList.get(sectionIndex));
            if (view != null) {
                addPageButton(sectionsArrayList.get(sectionIndex), view, null);

                if (!sectionsArrayList.get(sectionIndex).isColumn()) {
                    UI_Object ui_object = new UI_Object();
                    ui_object.setContext(getActivity());
                    ui_object.setType(UI_Type.DJV_Label);
                    ui_object.setName(sectionsArrayList.get(sectionIndex).getSectionName());
                    rootLayout.addView(viewControl.getViewByType(ui_object));
                }
                rootLayout.addView(view);
            }
        }

        LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.bottom_layout);
        linearLayout.removeAllViews();
        addPageButton(null, linearLayout, commandName);

        //rootLayout.addView(linearLayout);


        return rootView;
    }


    private long lastScrollUpdate = -1;


    private class ScrollStateHandler implements Runnable {

        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastScrollUpdate) > 100) {
                lastScrollUpdate = -1;
                if (ListFunction.isAutoScroll) {
                    getActivity().findViewById(R.id.false_scroll).setVisibility(View.GONE);
                    getActivity().findViewById(R.id.function_image).setVisibility(View.GONE);
                    getActivity().findViewById(R.id.bottom_layout).setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
                    getActivity().findViewById(R.id.bottom_layout).startAnimation(animation);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                ValueAnimator realSmoothScrollAnimation =
                                        ValueAnimator.ofInt(stickyScrollView.getScrollY(),View.FOCUS_UP);
                                realSmoothScrollAnimation.setDuration(500);
                                realSmoothScrollAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator animation) {
                                        int scrollTo = (Integer) animation.getAnimatedValue();
                                        stickyScrollView.scrollTo(0, scrollTo);
                                    }
                                });

                                realSmoothScrollAnimation.start();
                            } else {
                                stickyScrollView.smoothScrollTo(0, View.FOCUS_UP);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    ListFunction.isAutoScroll = false;
                }
            } else {
                new Handler().postDelayed(this, 100);
            }
        }
    }

    MyScroll stickyScrollView;

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), nextAnim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (ListFunction.isAutoScroll) {
                    stickyScrollView = (MyScroll) getActivity().findViewById(R.id.notifyScroll);
                    stickyScrollView.setScrollViewListener(new MyScroll.ScrollViewListener() {
                        @Override
                        public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
                            if (lastScrollUpdate == -1) {
                                new Handler().postDelayed(new ScrollStateHandler(), 100);
                            }

                            lastScrollUpdate = System.currentTimeMillis();
                        }
                    });

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        ValueAnimator realSmoothScrollAnimation =
                                ValueAnimator.ofInt(stickyScrollView.getScrollY(), getActivity().findViewById(R.id.function_image).getBottom() + 15);
                        realSmoothScrollAnimation.setDuration(500);
                        realSmoothScrollAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int scrollTo = (Integer) animation.getAnimatedValue();
                                stickyScrollView.scrollTo(0, scrollTo);
                            }
                        });

                        realSmoothScrollAnimation.start();
                    } else {
                        stickyScrollView.smoothScrollTo(0, getActivity().findViewById(R.id.function_image).getBottom());
                    }

                    //stickyScrollView.smoothScrollTo(0, getActivity().findViewById(R.id.function_image).getBottom() + 15);


                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return anim;
    }


    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(CategoryFragment.titleStack.get(CategoryFragment.titleStack.size() - 1));
    }

    public Step getStepParceble() {
        return ((StepParceble) getArguments().getParcelable(STEP_KEY)).getmStep();
    }

    public void addPageButton(UI_Sections ui_sections, LinearLayout linearLayout, UI_CommandName commandName) {
        ArrayList<UI_Buttons> buttonsArrayList;
        if (commandName == null) {
            buttonsArrayList = ui_sections.getButton();
        } else {
            buttonsArrayList = commandName.getButtons();
        }
        for (int buttonIndex = 0; buttonIndex < buttonsArrayList.size(); buttonIndex++) {
            try {
                JSONObject buttonJsonObject = new JSONObject();
                buttonJsonObject.put(buttonsArrayList.get(buttonIndex).getButtonName(), buttonsArrayList.get(buttonIndex).getName());
                Log.e(buttonsArrayList.get(buttonIndex).getButtonName(), buttonsArrayList.get(buttonIndex).getName());
                UI_Object ui_object = new UI_Object();
                ui_object.setContext(getActivity());
                ui_object.setStepData(buttonJsonObject);
                ui_object.setName(buttonsArrayList.get(buttonIndex).getButtonName());
                ui_object.setType(UI_Type.DJV_Button);
                final DJV_Button djv_button = (DJV_Button) viewControl.getViewByType(ui_object);

                djv_button.getButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JSONObject o1 = readOutPut(djv_button.getCustomViewAttribute().getValue());
                        Log.e("formoutput: ", o1.toString());
                        if (isEntity) {
                            step.setStepEntityResultCallBack(readOutPut(djv_button.getCustomViewAttribute().getValue()), step.getStepAbstract(), new StepResultCallback() {
                                @Override
                                public void onStepResult(StepsAbstraction stepsAbstraction, Step step, JSONObject jsonObject) {

                                }

                                @Override
                                public void onGetNextStep(Step step, JSONObject prevStepData, StepTypeEnum stepTypeEnum, boolean b) {
                                    Log.e("prev: ", prevStepData.toString());
                                    step.setPrevStepResult(prevStepData);
                                    StepManager.nextStepManager(getActivity(), rootLayout, step, stepTypeEnum, prevStepData, getFragmentManager());

                                }
                            });
                        } else {
                            step.setStepResultCallBack(readOutPut(djv_button.getCustomViewAttribute().getValue()), step.getStepAbstract(), new StepResultCallback() {
                                @Override
                                public void onStepResult(StepsAbstraction stepsAbstraction, Step step, JSONObject jsonObject) {

                                }

                                @Override
                                public void onGetNextStep(Step step, JSONObject prevStepData, StepTypeEnum stepTypeEnum, boolean b) {

                                    StepManager.nextStepManager(getActivity(), rootLayout, step, stepTypeEnum, prevStepData, getFragmentManager());
                                }


                            });
                        }
                    }

                });
                djv_button.setGravity(Gravity.BOTTOM);
                linearLayout.addView(djv_button);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void setSectionHeader(String name) {
        UI_Object ui_object = new UI_Object();
        ui_object.setContext(getActivity());
        ui_object.setType(UI_Type.DJV_Label);
        ui_object.setName(name);
        rootLayout.addView(viewControl.getViewByType(ui_object));
    }

    public LinearLayout sectionLayout(UI_Sections sections) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
        ArrayList<UI_SingleField> fieldArrayList = sections.getField();

        if (fieldArrayList.size() == 0) {
            setSectionHeader(sections.getSectionName());
//            getActivity().getActionBar().setTitle(sections.getSectionName());
            return null;
        } else {
            for (int fieldIndex = 0; fieldIndex < fieldArrayList.size(); fieldIndex++) {
                String fieldName = fieldArrayList.get(fieldIndex).getFieldName();
                if (fieldName != null) {

                    JSONObject object = step.getStepEntityData(step.getPrevStepResult());
                    Log.e("field: ", fieldName);
                    Log.e("json: ", object.toString());

                }
                UI_Object ui_object = new UI_Object();
                ui_object.setContext(getActivity());

                ui_object.setUi_spec(fieldArrayList.get(fieldIndex).getSpec());
                ui_object.setName(fieldArrayList.get(fieldIndex).getName());
                ui_object.setUi_singleField(fieldArrayList.get(fieldIndex));
                ui_object.setStepData(stepData);
                ui_object.setEntityName(fieldArrayList.get(fieldIndex).getFieldName());
                ui_object.setFragmentManager(getFragmentManager());
                ui_object.setListener(UiControlTrier.this);

                ui_object.setType(fieldArrayList.get(fieldIndex).getType());
                linearLayout.addView(viewControl.getViewByType(ui_object));

            }
        }
        return linearLayout;
    }


    public boolean isEntity;
    public String entityName;

    public JSONObject readOutPut(String eventData) {
        JSONObject jsonObject = new JSONObject();
        recursiveLoopChildren(rootLayout, jsonObject);

        JSONObject object = new JSONObject();
        if (isEntity) {

            try {
                JSONObject object1 = new JSONObject();
                object1.put(entityName, jsonObject);
                object.put("EventData", object1);
                object.put("EventName", eventData);
            } catch (JSONException e) {
                Log.e("error: ", e.getMessage());
            }
        } else {
            try {
                object.put("EventData", jsonObject);
                object.put("EventName", eventData);
            } catch (JSONException e) {
                Log.e("error: ", e.getMessage());
            }
        }
        return object;
    }

    public void recursiveLoopChildren(ViewGroup parent, JSONObject jsonObject) {

        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                if (child instanceof Spinner)
                    viewInstance(child, jsonObject);
                else if (child instanceof DJV_TextField)
                    viewInstance(child, jsonObject);
                else if (child instanceof DJV_TextArea)
                    viewInstance(child, jsonObject);
                else
                    recursiveLoopChildren((ViewGroup) child, jsonObject);
            } else {
                if (child != null) {
                    viewInstance(child, jsonObject);
                }
            }
        }
    }

    public void viewInstance(View view, JSONObject jsonObject) {
        String key = "";
        String value = "";
        if (view instanceof DJV_DatePicker) {
            key = ((DJV_DatePicker) view).getCustomViewAttribute().getValueKey();
            value = ((DJV_DatePicker) view).getText().toString();
            String fieldName = ((DJV_DatePicker) view).getUi_object().getUi_singleField().getFieldName();

            if (fieldName != null) {
                isEntity = true;
                entityName = fieldName;
            }
        } else if (view instanceof DJV_TextField) {
            key = ((DJV_TextField) view).getCustomViewAttribute().getValueKey();
            value = ((DJV_TextField) view).getView().getText().toString();
            String fieldName = ((DJV_TextField) view).getUi_object().getUi_singleField().getFieldName();
            if (fieldName != null) {
                isEntity = true;
                entityName = fieldName;
            }
        } else if (view instanceof DJV_TextArea) {
            key = ((DJV_TextArea) view).getCustomViewAttribute().getValueKey();
            value = ((DJV_TextArea) view).getView().getText().toString();
            String fieldName = ((DJV_TextArea) view).getUi_object().getUi_singleField().getFieldName();
            if (fieldName != null) {
                isEntity = true;
                entityName = fieldName;
            }
        } else if (view instanceof DJV_FileChooser) {
            key = ((DJV_FileChooser) view).getCustomViewAttribute().getValueKey();
            value = ((DJV_FileChooser) view).getText().toString();
            String fieldName = ((DJV_FileChooser) view).getUi_object().getUi_singleField().getFieldName();
            if (fieldName != null) {
                isEntity = true;
                entityName = fieldName;
            }
        } else if (view instanceof DJV_CheckBox) {
            DJV_CheckBox djv_checkBox = ((DJV_CheckBox) view);
            if (djv_checkBox.isChecked()) {
                key = (djv_checkBox).getCustomViewAttribute().getValueKey();
                value = (djv_checkBox).getText().toString();
            }
            String fieldName = ((DJV_CheckBox) view).getUi_object().getUi_singleField().getFieldName();
            if (fieldName != null) {
                isEntity = true;
                entityName = fieldName;
            }
        } else if (view instanceof DJV_DropDown) {
//            DJV_DropDown djv_dropDown = ((DJV_DropDown) view);
//            key = (djv_dropDown).getCustomViewAttribute().getValueKey();
//            String[] items = djv_dropDown.getUi_object().getUi_singleField().getSourceContent().split(",");
//            if (djv_dropDown.getUi_object().isParameterMode()) {
//                if (djv_dropDown.getUi_object().getEntityObject().size() > 0)
//                    value = djv_dropDown.getUi_object().getEntityObject().get(djv_dropDown.getSelectedItemPosition()).getValue();
//
//            } else
//                value = items[djv_dropDown.getSelectedItemPosition()];
//            String fieldName = ((DJV_DropDown) view).getUi_object().getUi_singleField().getFieldName();
//            if (fieldName != null) {
//                isEntity = true;
//                entityName = fieldName;
//            }
        }

        try {
            if (!key.isEmpty())
                jsonObject.put(key, value);
        } catch (JSONException e) {
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ((TextView) manipView).setText(data.getData().getPath());
        }
    }

    View manipView;


    @Override
    public void openFileChooser(View view) {
        manipView = view;
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("audio/*");
        chooseFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(chooseFile, "Choose a file"), 2);
    }
}
