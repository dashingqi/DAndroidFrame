package cn.dashingqi.com.dandroidframe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.dashingqi.com.dandroidframe.R;

/**
 * <p>文件描述：<p>
 * <p>作者：DashingQi <p>
 * <p>创建时间：2018/12/10<p>
 * <p>更改时间：2018/12/10<p>
 * <p>版本号：1<p>
 */
public class TestFragment extends Fragment {

    private TextView mTextView;

    private boolean mIsVisibleToUser;
    private boolean mIsFirstVisible ;
    private String text;

    public static TestFragment getInstance(String content) {
        Bundle bundle = new Bundle();
        bundle.putString("text", content);
        TestFragment testFragment = new TestFragment();
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;

        if (mIsVisibleToUser && mIsFirstVisible){
            setData();
            mIsFirstVisible = false;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("text");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsFirstVisible = true;
        if (mIsVisibleToUser){
            setData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, container, false);
        mTextView = view.findViewById(R.id.mTextView);
        return view;
    }

    public void setData(){
        if (!TextUtils.isEmpty(text)){
            mTextView.setText(text);
        }
    }
}
