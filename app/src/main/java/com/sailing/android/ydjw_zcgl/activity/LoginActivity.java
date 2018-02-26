package com.sailing.android.ydjw_zcgl.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

import com.alibaba.fastjson.JSON;
import com.sailing.android.ydjw_zcgl.R;
import com.sailing.android.ydjw_zcgl.application.GlobalApplication;
import com.sailing.android.ydjw_zcgl.base.BaseActivity;
import com.sailing.android.ydjw_zcgl.entity.UserInfoEntity;
import com.sailing.android.ydjw_zcgl.http.ResponseResult;
import com.sailing.android.ydjw_zcgl.http.RestCreator;
import com.sailing.android.ydjw_zcgl.param.LoginParam;
import com.sailing.android.ydjw_zcgl.util.PreferencesUtils;
import com.sailing.android.ydjw_zcgl.util.StringUtils;
import com.sailing.android.ydjw_zcgl.util.ToastUtils;
import com.sailing.android.ydjw_zcgl.widget.CustomEditText;
import com.sailing.android.ydjw_zcgl.widget.DialogManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_name)
    CustomEditText etLoginName;
    @BindView(R.id.et_login_password)
    CustomEditText etLoginPassword;
    @BindView(R.id.cb_auto_login)
    CheckBox cbAutoLogin;

    private Dialog loadingDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
    }


    @Override
    protected boolean onBackQuit() {
        return true;
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        final String userCode = etLoginName.getText().toString().trim();
        if (StringUtils.isBlank(userCode)) {
            ToastUtils.show(mContext, getString(R.string.tip_name_is_empty));
            return;
        }
        final String password = etLoginPassword.getText().toString().trim();
        if (StringUtils.isBlank(password)) {
            ToastUtils.show(mContext, getString(R.string.tip_password_is_empty));
            return;
        }
        loadingDialog = DialogManager.getLoadingDialog(this, true);
        LoginParam loginParam = new LoginParam();
        loginParam.setUserCode(userCode);
        loginParam.setPassword(password);
        Call<ResponseResult> call = RestCreator.getRestService().login(JSON.toJSONString(loginParam));
        loadingDialog.show();
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                loadingDialog.dismiss();
                ResponseResult result = response.body();
                if (result != null && ResponseResult.SUCCEED_CODE.equals(result.getResponseCode())) {
                    UserInfoEntity userInfoEntity = JSON.parseObject(result.getContent(),UserInfoEntity.class);
                    GlobalApplication.getInstance().setUserInfoEntity(userInfoEntity);
                    //点击自动登录
                    if (cbAutoLogin.isChecked()) {
                        PreferencesUtils.putBoolean(mContext,"aotoLogin",true);
                        PreferencesUtils.putString(mContext,"username",userCode);
                        PreferencesUtils.putString(mContext,"password",password);
                    }else {
                        PreferencesUtils.putBoolean(mContext,"aotoLogin",false);
                        PreferencesUtils.putString(mContext,"username","");
                        PreferencesUtils.putString(mContext,"password","");
                    }
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }else if (result != null && (ResponseResult.DATA_EMPTY_CODE.equals(result.getResponseCode()) || ResponseResult.VERIFY_ERROR_CODE.equals(result.getResponseCode()))){
                    ToastUtils.show(mContext, result.getMessage());
                }else{
                    ToastUtils.show(mContext, getString(R.string.msg_server_error));
                }
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                loadingDialog.dismiss();
                ToastUtils.show(mContext, getString(R.string.msg_server_error));
            }
        });
    }
}
