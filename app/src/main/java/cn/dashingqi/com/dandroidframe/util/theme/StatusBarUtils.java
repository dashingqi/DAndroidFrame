package cn.dashingqi.com.dandroidframe.util.theme;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import cn.dashingqi.com.dandroidframe.util.log.DLoggerUtils;

/**
 * <p>文件描述：透明状态栏的工具类 <p>
 * <p>作者：DashingQi <p>
 * <p>创建时间：2018/12/12<p>
 * <p>更改时间：2018/12/12<p>
 * <p>版本号：1<p>
 */
public class StatusBarUtils {

    /**
     * 配置透明状态栏
     *
     * @param activity
     * @param color
     */
    public static void configStatusBarColor(Activity activity, int color) {
        config(activity, color);
    }

    /**
     * 针对Api 19 到 21 和 Api>=21 做不同的适配
     *
     * @param activity
     * @param color
     */
    private static void config(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上才算正式的沉浸式，在这个版本上 Google推出了MaterialDesign分格的UI库
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //之前为状态栏是绘制一个View，给这个View绘制一个特定高度和StatusBar相同
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(color);

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                //设置为透明状态栏
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //创建一个StatusBar等高的View
                View statusBarView = createStatusBarView(activity, color);
                ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                decorView.addView(statusBarView);


            }
        }
        //使用ToolBar需要设置为 NoActionBar 主题  此时ToolBar会和StatusBar 重叠到一起，影响视觉效果，
        // 此时 可以设置一下跟布局为 android:fitSystemWindows = "true"
        ViewGroup contentView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        contentView.setFitsSystemWindows(true);
        contentView.setClipToPadding(true);
    }

    /**
     * 创建一个和StatusBar高度相同的 矩形View，起到一个占位的作用
     *
     * @param activity
     * @param color
     * @return
     */
    private static View createStatusBarView(Activity activity, int color) {
        int id = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(id);
        DLoggerUtils.d("Status", statusBarHeight);

        View view = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        view.setLayoutParams(params);
        view.setBackgroundColor(color);
        return view;

    }

}
