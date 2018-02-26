package com.sailing.android.ydjw_zcgl.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.sailing.android.ydjw_zcgl.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by eagle on 2017-10-11 16:00
 */

public class DialogManager {
    public static Dialog getLoadingDialog(Context context, boolean enableCancel) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(R.layout.dialog_loading, null);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(contentView);
        dialog.setCancelable(enableCancel);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        if (dialog != null) {
            AVLoadingIndicatorView mProgress = (AVLoadingIndicatorView) contentView.findViewById(R.id.progress_loading);
            mProgress.show();
        }
        return dialog;
    }
}
