package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.FunctionCategory;
import dejavu.appzonegroup.com.dejavuandroid.Models.InfoModel;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/26/2015.
 */
public class FunctionAdapter extends BaseAdapter {


    private LayoutInflater mLayoutInflater;
    private ArrayList<FunctionCategory> functionCategories;

    public FunctionAdapter(Context context, ArrayList<FunctionCategory> categories) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        functionCategories = categories;
    }

    @Override
    public int getCount() {
        return functionCategories.size();
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
        FunctionHolder holder;
        if (convertView == null) {
            holder = new FunctionHolder();
            convertView = mLayoutInflater.inflate(R.layout.function_item, parent, false);

            holder.functionButton = (TextView) convertView.findViewById(R.id.function_item);
        } else {
            holder = (FunctionHolder) convertView.getTag();
        }


        holder.functionButton.setText(functionCategories.get(position).getName());
        convertView.setTag(holder);

        return convertView;
    }
}
