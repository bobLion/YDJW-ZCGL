package com.sailing.android.ydjw_zcgl.util;

import android.widget.TextView;

/**
 * Created by eagle on 2017-9-18 18:52
 */

public class BindValueUtils {
    public static void bindTextValue(TextView view, String value) {
        if (view != null) {
            if (!StringUtils.isBlank(value)) {
                view.setText(value);
            } else {
                view.setText("无");
            }
        }
    }

    public static void bindTextValue(TextView view, String value, String defaultValue) {
        if (view != null) {
            if (!StringUtils.isBlank(value)) {
                view.setText(value);
            } else {
                view.setText(defaultValue);
            }
        }
    }
}
