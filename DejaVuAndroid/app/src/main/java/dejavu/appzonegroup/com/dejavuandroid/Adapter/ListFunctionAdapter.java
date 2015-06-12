package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Activities.ListModel;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/26/2015.
 */
public class ListFunctionAdapter extends BaseAdapter {


    private LayoutInflater mLayoutInflater;
    private ArrayList<ListModel> functionCategories;

    public ListFunctionAdapter(Context context, ArrayList<ListModel> functions) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        functionCategories = functions;
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
            if (functionCategories.get(position).isFlow())
                convertView = mLayoutInflater.inflate(R.layout.list_function_flow_item, parent, false);
            else
                convertView = mLayoutInflater.inflate(R.layout.list_function_item, parent, false);

            holder.functionButton = (TextView) convertView.findViewById(R.id.function_item);
        } else {
            holder = (FunctionHolder) convertView.getTag();
        }


        holder.functionButton.setText(functionCategories.get(position).getName());
        convertView.setTag(holder);

        return convertView;
    }
}
