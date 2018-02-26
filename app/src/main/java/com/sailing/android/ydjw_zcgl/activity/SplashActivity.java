package com.sailing.android.ydjw_zcgl.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sailing.android.ydjw_zcgl.R;
import com.sailing.android.ydjw_zcgl.application.GlobalApplication;
import com.sailing.android.ydjw_zcgl.base.BaseActivity;
import com.sailing.android.ydjw_zcgl.config.AppConfig;
import com.sailing.android.ydjw_zcgl.entity.UserInfoEntity;
import com.sailing.android.ydjw_zcgl.http.ResponseResult;
import com.sailing.android.ydjw_zcgl.http.RestCreator;
import com.sailing.android.ydjw_zcgl.param.LoginParam;
import com.sailing.android.ydjw_zcgl.util.PermissionsCheckerUtil;
import com.sailing.android.ydjw_zcgl.util.PreferencesUtils;
import com.sailing.android.ydjw_zcgl.util.StringUtils;
import com.sailing.android.ydjw_zcgl.util.ToastUtils;
import com.sailing.android.ydjw_zcgl.widget.DialogManager;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    private Intent loginIntent;
    private Runnable splashRunnable = new SplashRunnable();
    private Handler handler = new Handler();
    private Dialog loadingDialog = null;
    //所需权限
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private PermissionsCheckerUtil mPermissionsCheckerUtil; //权限检测器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        mPermissionsCheckerUtil = new PermissionsCheckerUtil(mContext);
        loginIntent = new Intent();
        initPermission();
    }

    private void initPermission() {
        if (mPermissionsCheckerUtil.lacksPermissions(PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, AppConfig.PERMISSION_REQUEST_CODE);
        } else {
            handler.postDelayed(splashRunnable, 2000);
        }
    }

    private class SplashRunnable implements Runnable {
        @Override
        public void run() {
            /*String sdCardStatus = Environment.getExternalStorageState();
            if (sdCardStatus.equals(Environment.MEDIA_MOUNTED)) {
                //拷贝数据库
//                FileUtils.copyFileFromAssets(mContext, AppConfig.APP_DATABASE_VERSION_CODE, dbVersionFileName);
                *//*String targetFileName = AppConfig.getDatebasePath().concat("/")
                        .concat(AppConfig.APP_DATEBASE_NAME);*//*
                String targetFileName = getApplicationContext().getDatabasePath(AppConfig.APP_DATABASE_NAME).getParent().concat("/").concat(AppConfig.APP_DATABASE_NAME);
                if (!FileUtils.isFileExist(targetFileName)) {
                    FileUtils.copyFileFromAssets(mContext, AppConfig.APP_DATABASE_NAME, targetFileName);
                }
                //车辆品牌数据库
                String brandDBFileName = getApplicationContext().getDatabasePath(AppConfig.VEHICLE_BRAND_DATABASE_NAME).getParent().concat("/").concat(AppConfig.VEHICLE_BRAND_DATABASE_NAME);
                if (!FileUtils.isFileExist(brandDBFileName)) {
                    FileUtils.copyFileFromAssets(mContext, AppConfig.VEHICLE_BRAND_DATABASE_NAME, brandDBFileName);
                }

//                FileUtils.copyFileFromAssets(mContext, AppConfig.VEHICLE_BRAND_DATABASE_NAME, brandDBFileName);
                //拷贝数据库版本号文件
                String dbVersionFileName = getApplicationContext().getDatabasePath(AppConfig.APP_DATABASE_VERSION_CODE).getParent().concat("/").concat(AppConfig.APP_DATABASE_VERSION_CODE);
                if (!FileUtils.isFileExist(dbVersionFileName)) {
                    FileUtils.copyFileFromAssets(mContext, AppConfig.APP_DATABASE_VERSION_CODE, dbVersionFileName);
                }
                //创建存放下载的推送的报告的文件夹
                File file = new File(AppConfig.getDocPath());
                if (!file.exists()) {
                    file.mkdirs();
                }
            }*/
            if (PreferencesUtils.getBoolean(mContext, "aotoLogin", false) == false) {
                loginIntent.setClass(mContext, LoginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }else {
                String code = PreferencesUtils.getString(mContext, "username");
                String password = PreferencesUtils.getString(mContext, "password");
                if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(code)) {
                    autoLogin(code, password);
                } else {
                    ToastUtils.show(mContext, "账户或密码为空，请重新登录！", Toast.LENGTH_LONG);
                    loginIntent.setClass(mContext, LoginActivity.class);
                    startActivity(loginIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }
        }
    }

    private void autoLogin(final String userCode, final String password) {
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
                    UserInfoEntity userInfoEntity = JSON.parseObject(result.getContent(), UserInfoEntity.class);
                    GlobalApplication.getInstance().setUserInfoEntity(userInfoEntity);
                    loginIntent.setClass(mContext, MainActivity.class);
                    startActivity(loginIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                } else if (result != null && (ResponseResult.DATA_EMPTY_CODE.equals(result.getResponseCode()) || ResponseResult.VERIFY_ERROR_CODE.equals(result.getResponseCode()))) {
                    ToastUtils.show(mContext, result.getMessage());
                    loginIntent.setClass(mContext, LoginActivity.class);
                    startActivity(loginIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                } else {
                    ToastUtils.show(mContext, getString(R.string.msg_server_error));
                    loginIntent.setClass(mContext, LoginActivity.class);
                    startActivity(loginIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                loadingDialog.dismiss();
                ToastUtils.show(mContext, getString(R.string.msg_server_error));
                loginIntent.setClass(mContext, LoginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppConfig.PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    handler.postDelayed(splashRunnable, 2000);
                } else {
                    finish();
                }
        }
    }
}
