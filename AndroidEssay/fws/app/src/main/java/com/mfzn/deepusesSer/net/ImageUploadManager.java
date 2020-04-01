package com.mfzn.deepusesSer.net;

import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author syz @date 2020-03-11
 */
public class ImageUploadManager {

    private  static int uploadImageIndex = 0;
    public static void upLoadFile(List<File> files,ImageUploadCallback callback) {
        uploadImageIndex=0;
        StringBuffer fileUrls = new StringBuffer();
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).exists()) {
                ImageUploadManager.uploadImage(files.get(i), new ImageUploadManager.ImageUploadCallback() {

                    @Override
                    public void uploadSuccess(String url) {
                        uploadImageIndex++;
                        if (uploadImageIndex > 0) {
                            fileUrls.append(",");
                        }
                        fileUrls.append(url);
                        if (uploadImageIndex == files.size()) {
                            callback.uploadSuccess(fileUrls.toString());
                        }
                    }

                    @Override
                    public void uoloadFailed(String error) {
                        uploadImageIndex++;
                        if (uploadImageIndex == files.size() - 1) {
                            callback.uoloadFailed("");
                        }
                    }
                });
            }
        }
    }


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
