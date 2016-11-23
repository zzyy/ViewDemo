package com.zy.md.utils.common;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by Simon on 2016/9/29.
 */
public class ToastUtils {

    private static ToastUtils sInstance;
    private Context mContext;
    private Toast mToast;

    private ToastUtils(Context context) {
        mContext = context.getApplicationContext();
    }

    public static ToastUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (ToastUtils.class) {
                if (sInstance == null) {
                    sInstance = new ToastUtils(context);
                }
            }
        }
        return sInstance;
    }


    /**
     * 显示一个短toast
     *
     * @param text toast的文本
     */
    public Toast s(String text) {
        showToast(text, Toast.LENGTH_SHORT);
        return mToast;
    }

    /**
     * 显示一个短toast
     *
     * @param textId toast的文本的ResourcesId
     */
    public Toast s(@StringRes int textId) {
        String text = mContext.getResources().getString(textId);
        showToast(text, Toast.LENGTH_SHORT);
        return mToast;
    }


    /**
     * 显示一个长toast
     *
     * @param text toast的文本
     */
    public Toast l(String text) {
        showToast(text, Toast.LENGTH_LONG);
        return mToast;
    }

    /**
     * 显示一个长toast
     *
     * @param textId toast的文本的ResourcesId
     */

    public Toast l(@StringRes int textId) {
        String text = mContext.getResources().getString(textId);
        showToast(text, Toast.LENGTH_LONG);
        return mToast;
    }


    private void showToast(final String text, final int duration) {
        if (ThreadInfoUtils.isMainThread()){
            if (mToast == null) {
                mToast = Toast.makeText(mContext, text, duration);
            } else {
                mToast.setText(text);
                mToast.setDuration(duration);
            }
            mToast.show();
        }else {
            ThreadInfoUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(mContext, text, duration);
                    } else {
                        mToast.setText(text);
                        mToast.setDuration(duration);
                    }
                    mToast.show();
                }
            });
        }

    }

    public void cancle() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
