package com.sailing.android.ydjw_zcgl.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sailing.android.ydjw_zcgl.R;
import com.sailing.android.ydjw_zcgl.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bob on 2018/2/25.
 */

public class ResearchAssetFragment extends BaseFragment {

    @BindView(R.id.root)
    LinearLayout root;

    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_research_asset_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        initStatusBar();

    }

    private void initStatusBar() {
        //状态栏沉浸
        int statusBarHeight = 60;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        root.setPadding(0, statusBarHeight, 0, 0);
    }
}
