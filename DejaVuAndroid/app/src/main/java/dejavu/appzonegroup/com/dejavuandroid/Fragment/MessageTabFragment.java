package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import dejavu.appzonegroup.com.dejavuandroid.Adapter.HistoryAdapter;
import dejavu.appzonegroup.com.dejavuandroid.Adapter.MessageAdapter;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/7/2015.
 */
public class MessageTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_layout, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.setAdapter(new MessageAdapter(getActivity()));

        return rootView;
    }
}
