package dejavu.appzonegroup.com.dejavuandroid.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Adapter.DropDownAdapter;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.selectionListener;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/22/2015.
 */
public class SpinnerDialog extends DialogFragment {
    static private selectionListener selectionListener;
    static public final String ITEMS_KEYS = "items";
    static public final String TITLE = "title";

    public SpinnerDialog spinnerDialog(selectionListener listener, ArrayList items,String title) {
        SpinnerDialog spinnerDialog = new SpinnerDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        bundle.putStringArrayList(ITEMS_KEYS,items);
        selectionListener = listener;
        spinnerDialog.setArguments(bundle);
        return spinnerDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.drop_down_layout, null);
        ListView listView = (ListView) v.findViewById(android.R.id.list);
        listView.setAdapter(new DropDownAdapter(getActivity(), getArguments().getStringArrayList(ITEMS_KEYS)));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectionListener.onItemSelected(position, getArguments().getStringArrayList(ITEMS_KEYS).get(position));
                dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getArguments().getString(TITLE)).setView(v);

        return builder.create();

    }
}
