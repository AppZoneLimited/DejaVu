package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.enums.StepTypeEnum;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/6/2015.
 */
public class ListFunctionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_frame_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ListFunction listfunction = new ListFunction();
        listfunction.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, listfunction).commitAllowingStateLoss();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        handleBackPress();
    }

    public void handleBackPress() {
        try {
            Log.e("method ", "onInit");
            Fragment s = getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if (s instanceof UiControlTrier) {
                Log.e("method ", "UIControlFragment");
                Step step = ((UiControlTrier) s).getStepParceble();
                if (step.getStepTypeEnum() == StepTypeEnum.SERVICE_UI_ENUM) {
                    if (step.canRollBack()) {
                        Log.e("method ", "normalpop");
                        //getSupportFragmentManager().popBackStackImmediate();
                    } else {
                        CategoryFragment.titleStack.clear();
                        finish();
                    }
                }
            } else {
                findViewById(R.id.bottom_layout).setVisibility(View.GONE);
                findViewById(R.id.function_image).setVisibility(View.VISIBLE);
                CategoryFragment.titleStack.pop();
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle(CategoryFragment.titleStack.get(CategoryFragment.titleStack.size() - 1));
                Log.e("Value", s.getClass().getSimpleName());
            }


        } catch (Exception ex) {
            Log.e("method ", "jhjfkjk");
            //getSupportFragmentManager().popBackStackImmediate();
        }
    }
}
