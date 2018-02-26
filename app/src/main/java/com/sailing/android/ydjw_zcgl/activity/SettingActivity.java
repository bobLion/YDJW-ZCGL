package com.sailing.android.ydjw_zcgl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sailing.android.ydjw_zcgl.R;
import com.sailing.android.ydjw_zcgl.application.GlobalApplication;
import com.sailing.android.ydjw_zcgl.base.BaseActivity;
import com.sailing.android.ydjw_zcgl.util.BindValueUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity {

    @BindView(R.id.rl_profile_info)
    private RelativeLayout mRelProfile;
    @BindView(R.id.rel_setting)
    private RelativeLayout mRelSetting;
    private Context mContext;
    @BindView(R.id.tv_profile_user_name)
    private TextView mTvUserName;
    @BindView(R.id.tv_profile_department)
    private TextView mTvDepartment;
    @BindView(R.id.iv_profile_header)
    private ImageView mImgHeader;

    private String headUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mContext = this;
        BindValueUtils.bindTextValue(mTvUserName, GlobalApplication.getInstance().getUserInfoEntity().getUserName());
        BindValueUtils.bindTextValue(mTvDepartment,
                getString(R.string.tv_profile_department, GlobalApplication.getInstance().getUserInfoEntity().getOrgName()));
//        headUrl = GlobalApplication.getInstance().getUserInfoEntity().getZjzurl();
        /*if(null != headUrl && !headUrl.equals("") ){
            ImageOptions imageOptions = new ImageOptions.Builder().setParamsBuilder(new ImageOptions.ParamsBuilder() {
                @Override
                public RequestParams buildParams(RequestParams requestParams, ImageOptions imageOptions) {
                    requestParams.addHeader(AppConfig.PROTOCAL_HEADER, AppImageBuilderInterceptor.getProtocalHeader());
                    return requestParams;
                }
            }).setImageScaleType(ImageView.ScaleType.FIT_CENTER).build();
            ImageManagerProxy.image().bind(mImgHeader, headUrl,imageOptions);
        }*/
    }

    @OnClick(R.id.rl_profile_info)
    private void setOnclickProfile(View view){
//        Intent intent = new Intent(mContext, ProfileInfoActivity.class);
//        startActivity(intent);
    }

    @OnClick(R.id.rel_setting)
     void onClickSetting(View view){
//        Intent intent = new Intent(mContext,CardReaderSettingActivity.class);
//        startActivity(intent);
    }

    @OnClick(R.id.ll_notice_back)
     void onBackClick(View view){
        finish();
    }

    @OnClick(R.id.rel_update_password)
     void onUpdatePassword(View view){
//        Intent intent = new Intent(mContext,UpdatePasswordActivity.class);
//        startActivity(intent);
    }

    @OnClick(R.id.rel_profile_about)
     void onAboutApplicationClick(View view){
//        Intent intent = new Intent(mContext,AboutApplicationActivity.class);
//        startActivity(intent);
    }
}
