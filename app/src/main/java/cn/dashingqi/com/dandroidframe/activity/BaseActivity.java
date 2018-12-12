package cn.dashingqi.com.dandroidframe.activity;

import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;


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

}
