package com.mfzn.deepuses.net;

import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import java.io.File;

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

}
