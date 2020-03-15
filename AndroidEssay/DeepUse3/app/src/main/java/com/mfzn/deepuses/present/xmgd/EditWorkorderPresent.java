package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.EditWorkorderActivity;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.net.ImageUploadManager;
import com.mfzn.deepuses.utils.UserHelper;

import java.io.File;
import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EditWorkorderPresent extends XPresent<EditWorkorderActivity> {

    public void gongdanShuxing(String orderNo) {
        ApiServiceManager.lookAsOrder(orderNo)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<GongdanShuxingModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<GongdanShuxingModel> result) {
                        getV().gongdanShuxingSuccess(result.getRes());
                    }
                });
    }

    /**
     * 上传图片
     */

    private int uploadImageIndex = 0;

    public void upLoadFile(List<File> files) {
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
                            getV().uploadIconSuccess(fileUrls.toString());
                        }
                    }

                    @Override
                    public void uoloadFailed(String error) {
                        uploadImageIndex++;
                        if (uploadImageIndex == files.size() - 1) {
                            getV().uploadIconSuccess(fileUrls.toString());
                        }
                    }
                });
            }
        }
    }

    public void editWorkorder(String orderNo, String shType, String contactName, String contactPhone, String wishTime, String content, String fileId) {
        ApiHelper.getApiService().editWorkorder(UserHelper.getToken(), UserHelper.getUid(), orderNo, shType, contactName, contactPhone, wishTime, content, fileId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        getV().editWorkorderSuccess();
                    }
                });
    }
}
