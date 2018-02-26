package com.sailing.android.ydjw_zcgl.application;

import android.app.Application;

import com.rey.material.app.ThemeManager;
import com.sailing.android.ydjw_zcgl.entity.UserInfoEntity;
import com.sailing.android.ydjw_zcgl.util.NetworkUtils;

import org.greenrobot.greendao.database.Database;


public class GlobalApplication extends Application {
    private static GlobalApplication application;
    public static GlobalApplication getInstance() {
        return application;
    }
    private UserInfoEntity userInfoEntity;

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }


    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //material  UI组件
        ThemeManager.init(this, 2, 0, null);
    }


    public String getIp() {
        return NetworkUtils.getIPAddress(getApplicationContext());
    }
}