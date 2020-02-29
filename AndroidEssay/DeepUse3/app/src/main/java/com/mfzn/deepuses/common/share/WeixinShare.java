package com.mfzn.deepuses.common.share;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.utils.Util;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import static com.mfzn.deepuses.utils.UIUtils.showToast;

/**
 * @author yz @date 2020-02-22
 */
public class WeixinShare {

    private static final float MAX_SIZE_OF_ICON = 32.0f * 1024;

    private static volatile WeixinShare mInstance;
    private final IWXAPI mWeixinAPI;

    private WeixinShare(Activity context) {
        this.mWeixinAPI = BaseApplication.api;
    }

    public static synchronized WeixinShare getInstance(Activity context) {
        if (mInstance == null) {
            mInstance = new WeixinShare(context);
        }
        return mInstance;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isWXAppInstalled() {
        return mWeixinAPI != null && mWeixinAPI.isWXAppInstalled();
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean shareImageToFriend(Bitmap image) {
        return shareImage(image, true);
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean shareImageToCircle(Bitmap image) {
        return shareImage(image, false);
    }

    private boolean shareImage(Bitmap bitmap, boolean isToFriend) {
        if (!isWXAppInstalled()) {
            showToast(BaseApplication.getContext().getString(R.string.please_install_wechat));
            return false;
        }
        if (bitmap == null) {
            showToast("分享失败");
            return false;
        }

        WXImageObject imgObj = new WXImageObject(bitmap);
        WXMediaMessage msg = new WXMediaMessage(imgObj);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession
                : SendMessageToWX.Req.WXSceneTimeline;
        return mWeixinAPI.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }
}
