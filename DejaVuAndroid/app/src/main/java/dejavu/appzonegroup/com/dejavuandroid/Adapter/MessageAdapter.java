package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/7/2015.
 */
public class MessageAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    public MessageAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.message_item, parent, false);
        }
        return convertView;
    }
}
