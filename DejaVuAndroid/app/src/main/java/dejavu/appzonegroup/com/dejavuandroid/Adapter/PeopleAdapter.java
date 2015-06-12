package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twotoasters.sectioncursoradapter.SectionCursorAdapter;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/7/2015.
 */
public class PeopleAdapter extends SectionCursorAdapter {

    LayoutInflater mLayoutInflater;

    public PeopleAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    protected Object getSectionFromCursor(Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        return name.toUpperCase().substring(0, 1);
    }



    @Override
    protected View newSectionView(Context context, Object o, ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.people_header, viewGroup, false);
    }

    @Override
    protected void bindSectionView(View view, Context context, int i, Object o) {
        ((TextView) view.findViewById(R.id.text)).setText((String) o);
    }

    @Override
    protected View newItemView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return mLayoutInflater.inflate(R.layout.people_item, viewGroup, false);
    }

    @Override
    protected void bindItemView(View view, Context context, Cursor cursor) {
        try {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            TextView nameTextView = (TextView) view.findViewById(R.id.name);
            ImageView imageView = (ImageView) view.findViewById(R.id.contactImage);
            String uri = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            try {
                imageView.setImageURI(Uri.parse(uri));
            } catch (Exception e) {
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_avater));
            }
            nameTextView.setText(name);
            view.setTag(nameTextView);
        } catch (Exception ex) {

        }
    }


    @Override
    protected int getMaxIndexerLength() {
        return 1;
    }


}
