package dejavu.appzonegroup.com.dejavuandroid.CustomViews;

import android.content.Context;
import android.util.AttributeSet;

import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;

/**
 * Created by CrowdStar on 5/27/2015.
 */
public class MyScroll extends StickyScrollView {

    public interface ScrollViewListener {
        void onScrollChanged(StickyScrollView scrollView,
                             int x, int y, int oldx, int oldy);
    }
    private ScrollViewListener scrollViewListener = null;
    public MyScroll(Context context) {
        super(context);
    }

    public MyScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
