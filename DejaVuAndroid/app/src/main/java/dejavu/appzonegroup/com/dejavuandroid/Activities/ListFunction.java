package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import com.appzone.zone.orchestra.engine.MobileFlow;
import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Adapter.ListFunctionAdapter;
import dejavu.appzonegroup.com.dejavuandroid.CustomViews.NestedListView;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.ClientFlows;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.Function;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.FunctionCategory;
import dejavu.appzonegroup.com.dejavuandroid.ObjectParceable.StepParceble;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 4/6/2015.
 */
public class ListFunction extends Fragment {
    public static final String TAG = ListFunction.class.getSimpleName();

    public static String CHILD = "child";
    public static String CHILD_ID = "child_id";
    public static String FLOW = "flow";
    public static String FLOW_ID = "flow_id";
    ArrayList<ListModel> listModelArrayList = new ArrayList<>();


    private ArrayList<String> arrayListId;
    private Step mStep;
    View rootView;
    dejavu.appzonegroup.com.dejavuandroid.CustomViews.NestedListView listView;
    ListFunction listfunction;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //getActivity().setTitle("Zone App");

        rootView = inflater.inflate(R.layout.list_function, container, false);
        listView = (NestedListView) rootView.findViewById(R.id.function_list);

        ArrayList<String> childArrayList = getArguments().getStringArrayList(CHILD);
        ArrayList<String> childIDArrayList = getArguments().getStringArrayList(CHILD_ID);
        ArrayList<String> flowArrayList = getArguments().getStringArrayList(FLOW);
        ArrayList<String> flowIDArrayList = getArguments().getStringArrayList(FLOW_ID);


        for (int index = 0; index < childArrayList.size(); index++) {
            ListModel listModel = new ListModel();
            listModel.setName(childArrayList.get(index));
            listModel.setID(childIDArrayList.get(index));
            listModel.setFlow(false);
            listModelArrayList.add(listModel);
        }

        for (int index = 0; index < flowArrayList.size(); index++) {
            ListModel listModel = new ListModel();
            listModel.setName(flowArrayList.get(index));
            listModel.setID(flowIDArrayList.get(index));
            listModel.setFlow(true);
            listModelArrayList.add(listModel);
        }


        listView.setAdapter(new ListFunctionAdapter(getActivity(), listModelArrayList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listModelArrayList.get(position).isFlow()) {
                    String clientFlow = ClientFlows.getFlowByIdAsString(getActivity(), listModelArrayList.get(position).getID());
                    if (clientFlow != null) {
                        loadFlow(clientFlow);
                    }
                } else {
                    ArrayList<FunctionCategory> childCategory = FunctionCategory.getChild(getActivity(), Integer.parseInt(listModelArrayList.get(position).getID()));
                    ArrayList<String> childArrayList = new ArrayList<>();
                    ArrayList<String> childID = new ArrayList<>();
                    ArrayList<Function> functionArrayList = Function.getFunctionByCategory(getActivity(), Integer.parseInt(listModelArrayList.get(position).getID()));
                    ArrayList<String> flowArrayList = new ArrayList<>();
                    ArrayList<String> flowIDArrayList = new ArrayList<>();
                    for (int x = 0; x < childCategory.size(); x++) {
                        childArrayList.add(childCategory.get(x).getName());
                        childID.add("" + childCategory.get(x).getId());
                    }

                    for (int x = 0; x < functionArrayList.size(); x++) {
                        flowArrayList.add(functionArrayList.get(x).getName());
                        flowIDArrayList.add("" + functionArrayList.get(x).getFlowGuid());
                    }
                    listfunction = new ListFunction();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(ListFunction.CHILD, childArrayList);
                    bundle.putStringArrayList(ListFunction.CHILD_ID, childID);
                    bundle.putStringArrayList(ListFunction.FLOW, flowArrayList);
                    bundle.putStringArrayList(ListFunction.FLOW_ID, flowIDArrayList);
                    listfunction.setArguments(bundle);


                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right);
                    listView.startAnimation(animation);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.animator.slide_left, R.animator.slide_right, R.animator.slide_left, R.animator.slide_right);
                            transaction.addToBackStack(null);
                            transaction.add(R.id.content_frame, listfunction).commitAllowingStateLoss();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }


                CategoryFragment.titleStack.push(listModelArrayList.get(position).getName());
//                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//                toolbar.setTitle(listModelArrayList.get(position).getName());
            }
        });
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(CategoryFragment.titleStack.get(CategoryFragment.titleStack.size() - 1));
    }

    static public boolean isAutoScroll = false;
    public void loadFlow(String JsonString) {
        isAutoScroll = true;
        Log.e("json: ", JsonString);
        MobileFlow mf = new MobileFlow(JsonString); // Create flow object
        StepsAbstraction sa = mf.getstepAbstractionion(); // Returns an object
        Step initStep = sa.getNextStep();

        StepParceble stepParceble = new StepParceble(initStep, new JSONObject(), null);

        final UiControlTrier uiControlTrier = new UiControlTrier();
        Bundle bundle = new Bundle();
        bundle.putParcelable(UiControlTrier.STEP_KEY, stepParceble);
        uiControlTrier.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down, R.animator.slide_up, R.animator.slide_down);
        transaction.add(R.id.content_frame, uiControlTrier).commitAllowingStateLoss();

    }



}