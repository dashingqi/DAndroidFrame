package cn.dashingqi.com.dandroidframe.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * <p>文件描述：<p>
 * <p>作者：DashingQi <p>
 * <p>创建时间：2018/12/10<p>
 * <p>更改时间：2018/12/10<p>
 * <p>版本号：1<p>
 */
public abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //*************************需要子类实现的方法****************************
    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    private void doBeforeSetContentView(){
        //无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 配置沉浸式状态栏
     */
    public void configStatusBar(int color) {

        //API>=21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(color);
            ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content))
                    .getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);

        } else {
            // 21> API >=19
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                View statusView = createStatusView(this, color);
                ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
                decorView.addView(statusView);
                ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content))
                        .getChildAt(0);
                rootView.setFitsSystemWindows(true);
                rootView.setClipToPadding(true);
            }
        }
    }

    /**
     * 获取一个和状态栏一样大小的矩形
     * @param activity
     * @param color
     * @return
     */
    private View createStatusView(Activity activity, int color){
        //获取到状态栏的id
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        //获取到状态栏的高度
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        //绘制一个和状态栏一样高度的矩形
        View view = new View(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(color);

        return view;

    }
}
