package studentnetwork.android.com.studentnetwork.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrateur on 25/04/2018.
 */

public class NoSwipeableViewPager extends ViewPager {
    public NoSwipeableViewPager(Context context) {
        super(context);
       // setMyScroller();
    }

    public NoSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
       // setMyScroller();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }
}
