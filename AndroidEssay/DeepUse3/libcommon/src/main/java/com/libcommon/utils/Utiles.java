package com.libcommon.utils;

import android.content.Context;

/**
 * @author yz @date 2020-03-30
 */
public class Utiles {

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }
}
