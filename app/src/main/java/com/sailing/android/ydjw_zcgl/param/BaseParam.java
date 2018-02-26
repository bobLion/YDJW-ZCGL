package com.sailing.android.ydjw_zcgl.param;


import com.sailing.android.ydjw_zcgl.application.GlobalApplication;
import com.sailing.android.ydjw_zcgl.entity.UserInfoEntity;

import java.io.Serializable;

/**
 * Created by eagle on 2017-10-10 16:28
 */

public class BaseParam implements Serializable {
    private String userCode;
    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    //private String packageName;

    public BaseParam() {
        UserInfoEntity userInfoEntity = GlobalApplication.getInstance().getUserInfoEntity();
        if (userInfoEntity != null){
            this.userCode = userInfoEntity.getUserCode();
            this.permission = userInfoEntity.getPermission();
        }
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /*public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }*/
}
