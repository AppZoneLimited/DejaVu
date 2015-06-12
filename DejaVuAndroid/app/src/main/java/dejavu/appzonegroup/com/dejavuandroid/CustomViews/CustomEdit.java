package dejavu.appzonegroup.com.dejavuandroid.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by CrowdStar on 5/19/2015.
 */
public class CustomEdit extends EditText {

    public CustomEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        return true;
        //return super.onKeyPreIme(keyCode, event);
    }
}
