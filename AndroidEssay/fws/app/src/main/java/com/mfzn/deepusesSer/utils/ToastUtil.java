package com.mfzn.deepusesSer.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by kail on 15/12/22.
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * 能够连续弹吐司，不用等上个消失
     *
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (Looper.myLooper() == Looper.getMainLooper()) { // UI主线程
            if (toast == null) {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            }
            toast.setText(text);
            toast.show();
        }
    }
}
