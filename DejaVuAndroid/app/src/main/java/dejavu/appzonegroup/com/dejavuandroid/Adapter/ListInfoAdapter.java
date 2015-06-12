package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Models.InfoModel;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/26/2015.
 */
public class ListInfoAdapter extends BaseAdapter {


    private LayoutInflater mLayoutInflater;
    private ArrayList<InfoModel> infoModelArrayList;

    public ListInfoAdapter(Context context, ArrayList<InfoModel> infoModels) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        infoModelArrayList = infoModels;
    }

    @Override
    public int getCount() {
        return infoModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.info_list_item, parent, false);
            holder.messageTextView = (TextView) convertView.findViewById(R.id.list_item_message);
            holder.subjectTextView = (TextView) convertView.findViewById(R.id.list_item_subject);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.subjectTextView.setText(infoModelArrayList.get(position).getSubject());
        holder.messageTextView.setText(infoModelArrayList.get(position).getMessage());
        convertView.setTag(holder);

        return convertView;
    }
}
