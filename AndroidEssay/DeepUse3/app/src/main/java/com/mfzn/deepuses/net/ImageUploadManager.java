package com.mfzn.deepuses.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author syz @date 2020-03-11
 */
public class ImageUploadManager {

    public static void uploadImage(File imageFile, ImageUploadCallback callback) {
        ApiHelper.getApiService().getQiniuToken()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        callback.uoloadFailed(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        String token = (String) reuslt.getRes();
                        qiniuUploadManager.put(imageFile, getKey(), token, (key, info, response)
                                -> callback.uploadSuccess(key), null);
                    }
                });
    }


    private static UploadManager qiniuUploadManager = new UploadManager(new Configuration.Builder()
            .zone(Zone.zone2).build());


    private static String getKey() {
        return "userInfo/avatar/" + System.currentTimeMillis();
    }

    public interface ImageUploadCallback {
        void uploadSuccess(String url);

        void uoloadFailed(String error);
    }

    public static Bitmap revitionImageSize(String path, int size) {
        // 取得图片
        InputStream temp = null;
        Bitmap bitmap = null;
        try {

            temp = new FileInputStream(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(temp, null, options);
            temp.close();
            int i = 0;
            while (true) {
                if ((options.outWidth >> i) * (options.outHeight >> i) <= size) {
                    temp = new FileInputStream(path);
                    options.inSampleSize = (int) Math.pow(2.0D, i);
                    options.inJustDecodeBounds = false;

                    bitmap = BitmapFactory.decodeStream(temp, null, options);
                    break;
                }
                i += 1;
            }
        } catch (IOException ignored) {
        } finally {
            try {
                if (temp != null) {
                    temp.close();
                }
            } catch (IOException var6) {
            }
        }

        return bitmap;
    }
}
