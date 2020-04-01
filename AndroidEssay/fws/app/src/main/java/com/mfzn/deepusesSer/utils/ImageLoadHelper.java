package com.mfzn.deepusesSer.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.net.ApiHelper;

/**
 * 加载图片辅助类
 *
 * @author zhangbostay
 * @date 2019/4/12
 */
public class ImageLoadHelper {

    public static void load(ImageView target, Integer resourceId) {
        Glide.with(target.getContext()).load(resourceId)
                .into(target);
    }

    public static void load(ImageView target, String url, int resId) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(resId)
                .error(resId);
        Glide.with(target.getContext()).load(ApiHelper.BASE_URL + url)
                .apply(requestOptions)
                .into(target);
//        ILFactory.getLoader().loadNet(target, ApiHelper.BASE_URL + url,
//                new ILoader.Options(R.mipmap.icon_default, R.mipmap.icon_default).scaleType(ImageView.ScaleType.CENTER_CROP));
    }

    /**
     * 加载图片
     *
     * @param url
     * @param target
     */
    public static void load(ImageView target, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_default)
                .error(R.mipmap.icon_default)
                .centerCrop();
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        Glide.with(target.getContext()).load(ApiHelper.BASE_URL + url)
                .apply(requestOptions)
                .into(target);
//        ILFactory.getLoader().loadNet(target, ApiHelper.BASE_URL + url,
//                new ILoader.Options(R.mipmap.icon_default, R.mipmap.icon_default).scaleType(ImageView.ScaleType.CENTER_CROP));
    }

    /**
     * 加载图片
     *
     * @param url
     * @param target
     */
    public static void loadNoType(ImageView target, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_default)
                .error(R.mipmap.icon_default);
        Glide.with(target.getContext()).load(ApiHelper.BASE_URL + url)
                .apply(requestOptions)
                .into(target);
//        ILFactory.getLoader().loadNet(target, ApiHelper.BASE_URL + url,
//                new ILoader.Options(R.mipmap.icon_default, R.mipmap.icon_default).scaleType(ImageView.ScaleType.CENTER_CROP));
    }

    /**
     * 加载头像
     *
     * @param url
     * @param target
     */
    public static void loadHead(ImageView target, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_default)
                .error(R.mipmap.icon_default);
        Glide.with(target.getContext()).load(ApiHelper.BASE_URL + url)
                .apply(requestOptions)
                .into(target);
//        ILFactory.getLoader().loadNet(target, ApiHelper.BASE_URL + url,
//                new ILoader.Options(R.mipmap.icon_default_head, R.mipmap.icon_default_head).scaleType(ImageView.ScaleType.FIT_XY));
    }

    public static void loadResource(ImageView target, int resId) {
        Glide.with(target.getContext()).load(resId)
                .into(target);
//        ILFactory.getLoader().loadResource(target, resId, null);
    }

}
