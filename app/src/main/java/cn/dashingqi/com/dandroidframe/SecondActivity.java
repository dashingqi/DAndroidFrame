package cn.dashingqi.com.dandroidframe;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.dashingqi.com.dandroidframe.activity.BaseActivity;
import cn.dashingqi.com.dandroidframe.fragment.TestFragment;
import cn.dashingqi.com.dandroidframe.ui.widget.DTabHeard;

public class SecondActivity extends BaseActivity {

    private DTabHeard mDTabHeard;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_second;
    }

    public void initData() {
        titleList.add("今天");
        titleList.add("明天");

        fragmentList.add(TestFragment.getInstance(titleList.get(0)));
        fragmentList.add(TestFragment.getInstance(titleList.get(1)));

        mDTabHeard.configDTabHeard(titleList,fragmentList,getSupportFragmentManager(),false);

    }

    public void initView() {
        mDTabHeard = findViewById(R.id.mDTabHeard);
    }
}
