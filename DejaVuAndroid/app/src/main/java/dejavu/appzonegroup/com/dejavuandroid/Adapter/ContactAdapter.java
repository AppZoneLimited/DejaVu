package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;

import java.util.ArrayList;
import java.util.List;

import dejavu.appzonegroup.com.dejavuandroid.Activities.ContactActivity;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.Contact;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by emacodos on 6/9/2015.
 */
public class ContactAdapter extends BaseAdapter {

    private static final int TYPE_BODY_ZONE = 0;
    private static final int TYPE_BODY = 1;
    private static final int TYPE_MAX_COUNT = 2;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Contact> mNotZoneContacts;
    private List<Contact> mZoneContacts;
    private ArrayList<Integer> mData;

    public ContactAdapter(Context context, List<Contact> zoneContacts, List<Contact> notZoneContacts) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mNotZoneContacts = notZoneContacts;
        this.mZoneContacts = zoneContacts;
        this.mData = new ArrayList<>();

        for (int i = 0; i < mZoneContacts.size(); i++) {
            mData.add(TYPE_BODY_ZONE);
        }
        for (int i = 0; i < mNotZoneContacts.size(); i++) {
            mData.add(TYPE_BODY);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).equals(TYPE_BODY_ZONE)) {
            return TYPE_BODY_ZONE;
        } else {
            return TYPE_BODY;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Contact getItem(int position) {
        if (mData.get(position).equals(TYPE_BODY_ZONE)) {
            return mZoneContacts.get(position);
        } else {
            int offset = position - mZoneContacts.size();
            return mNotZoneContacts.get(offset);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_BODY_ZONE:
                    convertView = mInflater.inflate(R.layout.people_item_zone, parent, false);
                    holder.NameText = (TextView) convertView.findViewById(R.id.name);
                    holder.ContactImage = (ImageView) convertView.findViewById(R.id.contactImage);
                    holder.RippleHolder = (RippleView) convertView.findViewById(R.id.more);
                    break;

                case TYPE_BODY:
                    convertView = mInflater.inflate(R.layout.people_item, parent, false);
                    holder.NameText = (TextView) convertView.findViewById(R.id.name);
                    holder.ContactImage = (ImageView) convertView.findViewById(R.id.contactImage);
                    holder.RippleHolder = (RippleView) convertView.findViewById(R.id.more);
                    holder.InviteButton = (Button) convertView.findViewById(R.id.invite);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Contact model;
        model = getItem(position);

        holder.NameText.setText(model.getName());
        holder.ContactImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_avater));

        holder.RippleHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ContactActivity.class);
                mContext.startActivity(intent);

            }
        });
        if (holder.InviteButton != null) {
            holder.InviteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Invite Contact
                }
            });
        }
        return convertView;
    }

    public void refill(List<Contact> zoneContacts, List<Contact> notZoneContacts) {
        mData.clear();

        this.mNotZoneContacts = notZoneContacts;
        this.mZoneContacts = zoneContacts;

        for (int i = 0; i < zoneContacts.size(); i++) {
            mData.add(TYPE_BODY_ZONE);
        }
        for (int i = 0; i < notZoneContacts.size(); i++) {
            mData.add(TYPE_BODY);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView NameText;
        public ImageView ContactImage;
        public RippleView RippleHolder;
        public Button InviteButton;
    }

}

