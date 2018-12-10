package cn.dashingqi.com.dandroidframe.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <p>文件描述：<p>
 * <p>作者：DashingQi <p>
 * <p>创建时间：2018/12/10<p>
 * <p>更改时间：2018/12/10<p>
 * <p>版本号：1<p>
 */
public class DViewPager extends ViewPager {
    private boolean isCanScroll;

    public DViewPager(@NonNull Context context) {
        super(context);
    }

    public DViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll)
            return super.onInterceptTouchEvent(ev);
        else return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll)
            return super.onTouchEvent(ev);
        else return false;
    }
}
