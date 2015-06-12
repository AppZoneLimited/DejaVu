package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dejavu.appzonegroup.com.dejavuandroid.Adapter.ContactAdapter;
import dejavu.appzonegroup.com.dejavuandroid.Adapter.PeopleAdapter;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.Contact;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/7/2015.
 */
public class PeopleTabFragment extends Fragment {//implements LoaderManager.LoaderCallbacks<Cursor> {

    PeopleAdapter peopleAdapter;
    static boolean cond = true;

    private ContactAdapter mContactAdapter;
    private ListView listView;
    private EditText mSearchText;
    public static List<Contact> mZones;
    public static List<Contact> mNotZones;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_layout, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);
        mSearchText = (EditText) rootView.findViewById(R.id.search);

//        if (cond) {
//            getActivity().getSupportLoaderManager().initLoader(0, savedInstanceState, this);
//            cond = false;
//        }
//        peopleAdapter = new PeopleAdapter(getActivity(), null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchContact(s.toString().toLowerCase(), mZones, mNotZones);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mContactAdapter = new ContactAdapter(getActivity(), mZones, mNotZones);
        listView.setAdapter(mContactAdapter);
        listView.setFastScrollEnabled(true);

        return rootView;
    }

    private void searchContact(CharSequence s, List<Contact> mZoneContacts, List<Contact> mNotZoneContacts) {
        List<Contact> zones = new ArrayList<>(mZoneContacts);
        List<Contact> notZones = new ArrayList<>(mNotZoneContacts);
        for (Iterator<Contact> it=zones.iterator(); it.hasNext();) {
            if (!it.next().getName().toLowerCase().contains(s))
                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
        }
        for (Iterator<Contact> it=notZones.iterator(); it.hasNext();) {
            if (!it.next().getName().toLowerCase().contains(s))
                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
        }
        mContactAdapter.refill(zones, notZones);
    }

    /*@Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getActivity(), ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, "upper(" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + ") ASC");
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {

         peopleAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        peopleAdapter.swapCursor(null);
    }*/

    @Override
    public void onResume() {
        super.onResume();
//        if (!cond)
//            getActivity().getSupportLoaderManager().restartLoader(0, getArguments(), PeopleTabFragment.this);
    }
}
