package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/22/2015.
 */
public class DropDownAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList items;

    public DropDownAdapter(Context context, ArrayList arrayList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items = arrayList;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drop_down_item, parent,false);
            convertView.setTag(holder.spinnerItemTextView);
        }
        holder.spinnerItemTextView = (TextView) convertView.findViewById(R.id.spinner_item);
        holder.spinnerItemTextView.setText(items.get(position).toString());

        return convertView;
    }

    private class Holder {
        TextView spinnerItemTextView;
    }
}
