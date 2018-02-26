package com.sailing.android.ydjw_zcgl.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by eagle on 2017-6-6 22:48
 */

public class BaseActivity extends AppCompatActivity {
    public long exitTime;
    public Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StatusBarCompatUtil.compat(this, Color.RED);
    }

    @Override
    public void onBackPressed() {
        if (onBackQuit()) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        } else {
            super.onBackPressed();
        }
    }

    protected boolean onBackQuit() {
        return false;
    }
}
