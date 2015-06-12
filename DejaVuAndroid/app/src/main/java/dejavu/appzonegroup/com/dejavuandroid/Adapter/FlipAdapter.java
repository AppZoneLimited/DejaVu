package dejavu.appzonegroup.com.dejavuandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import dejavu.appzonegroup.com.dejavuandroid.R;

public class FlipAdapter extends BaseAdapter {

    public interface Callback {
        public void onPageRequested(int page);
    }

    static class Item {
        static long id = 0;

        long mId;

        public Item() {
            mId = id++;
        }

        long getId() {
            return mId;
        }
    }

    private LayoutInflater inflater;
    private Context mContext;
    private int h;
    private Callback callback;
    private List<Item> items = new ArrayList<Item>();

    public FlipAdapter(Context context, int height) {
        mContext = context;
        h = height;
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < 2; i++) {
            items.add(new Item());
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.page, parent, false);

            holder.gridView = (GridView) convertView.findViewById(R.id.home_grid);
            holder.gridView.setMinimumHeight(h);
//            Toast.makeText(mContext, "heigth: " + h, Toast.LENGTH_LONG).show();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.gridView.setAdapter(new HomeAdapter(mContext, h));
        holder.gridView.setVerticalScrollBarEnabled(false);
        holder.gridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                }
                return false;
            }

        });


        return convertView;
    }

    static class ViewHolder {
        GridView gridView;
    }


}
