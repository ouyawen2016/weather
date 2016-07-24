package com.linjintao.weather.util;

import android.app.ProgressDialog;

/**
 * 这是加载框的显示与关闭
 */
public class ProgressHelper {
    private ProgressDialog mProgressDialog;
    public ProgressHelper(ProgressDialog p) {
        this.mProgressDialog = p;
    }
    //显示进度条
    public  void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(MyApplication.getContext());
            mProgressDialog.setMessage("正在加载中...");
        }
        mProgressDialog.show();
    }

    //关闭进度条
    public  void closeProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

}
