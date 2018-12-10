package cn.dashingqi.com.dandroidframe;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.dashingqi.com.dandroidframe.fragment.TestFragment;
import cn.dashingqi.com.dandroidframe.ui.widget.DTabHeard;

public class SecondActivity extends AppCompatActivity {

    private DTabHeard mDTabHeard;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();
    }

    private void initData() {
        titleList.add("今天");
        titleList.add("明天");

        fragmentList.add(TestFragment.getInstance(titleList.get(0)));
        fragmentList.add(TestFragment.getInstance(titleList.get(1)));

        mDTabHeard.configDTabHeard(titleList,fragmentList,getSupportFragmentManager(),false);

    }

    private void initView() {
        mDTabHeard = findViewById(R.id.mDTabHeard);
    }
}
