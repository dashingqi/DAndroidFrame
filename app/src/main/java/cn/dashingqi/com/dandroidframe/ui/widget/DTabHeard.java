package cn.dashingqi.com.dandroidframe.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.dashingqi.com.dandroidframe.R;
import cn.dashingqi.com.dandroidframe.ui.adapter.DViewPagerAdapter;

/**
 * <p>文件描述：<p>
 * <p>作者：DashingQi <p>
 * <p>创建时间：2018/12/10<p>
 * <p>更改时间：2018/12/10<p>
 * <p>版本号：1<p>
 */
public class DTabHeard extends LinearLayout {

    private DViewPager mDViewPager;
    private TabLayout mTabLayout;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    public DTabHeard(Context context) {
        this(context, null);
    }

    public DTabHeard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DTabHeard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化view
     *
     * @param mContext 上下文环境
     */
    private void initView(Context mContext) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_tabheard, this);
        mDViewPager = view.findViewById(R.id.mDViewPager);
        mTabLayout = view.findViewById(R.id.mTabLayout);
    }

    /**
     * 配置TabHeard
     */
    public void configDTabHeard(List<String> titleList, List<Fragment> fragmentList, FragmentManager fm,boolean isCanScroll) {
        mTitleList = titleList;
        mFragmentList = fragmentList;

        DViewPagerAdapter dViewPagerAdapter = new DViewPagerAdapter(fm, titleList, fragmentList);
        //设置是否能够左右滑动
        mDViewPager.setCanScroll(isCanScroll);

        mDViewPager.setAdapter(dViewPagerAdapter);

        mTabLayout.setupWithViewPager(mDViewPager);


    }
}
