package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Adapter.ListInfoAdapter;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.UserInfoDBHelper;
import dejavu.appzonegroup.com.dejavuandroid.Models.InfoModel;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class InfoActivity extends ListFragment {
    ArrayList<InfoModel> infoModelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_layout, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Cursor cursor = new UserInfoDBHelper(getActivity()).ReadNotification();
        while (cursor.moveToNext()) {
            InfoModel infoModel = new InfoModel();
            infoModel.setSubject(cursor.getString(0));
            infoModel.setMessage(cursor.getString(1));
            infoModelArrayList.add(infoModel);
        }
        setListAdapter(new ListInfoAdapter(getActivity(), infoModelArrayList));
    }
}
