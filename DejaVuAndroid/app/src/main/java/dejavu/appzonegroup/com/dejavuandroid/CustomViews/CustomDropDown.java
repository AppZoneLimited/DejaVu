package dejavu.appzonegroup.com.dejavuandroid.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 5/22/2015.
 */
public class CustomDropDown extends LinearLayout {
    public CustomDropDown(Context context) {
        super(context);
        initialise();
    }

    public CustomDropDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public CustomDropDown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialise();
    }

    public CustomDropDown(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialise();
    }

    private void initialise() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.djv_dropdown, this);

    }
}
