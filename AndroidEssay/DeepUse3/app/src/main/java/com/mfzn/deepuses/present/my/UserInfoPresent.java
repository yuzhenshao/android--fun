package com.mfzn.deepuses.present.my;

import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.activitymy.setting.PersonInfoActivity;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.net.ImageUploadManager;
import com.mfzn.deepuses.net.UploadApi;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class UserInfoPresent extends XPresent<PersonInfoActivity> {

    //用户信息
    public void userInfo() {
        ApiHelper.getApiService().appUserInfo(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<UserInfoModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<UserInfoModel> result) {
                        getV().userInfoSuccess(result.getRes());
                    }

                    @Override
                    public void onComplete() {
//                        getV().hideDialog();
                    }
                });
    }

    //用户信息
    public void modifyName(String userName) {
//        getV().showDialog();
        ApiServiceManager.appModifyName(userName)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
//                        getV().modifyName(result.getMsg());
                    }

                    @Override
                    public void onComplete() {
//                        getV().hideDialog();
                    }
                });
    }

    /**
     * 上传图片
     */
    public void upLoadFile(final File file) {
        ImageUploadManager.uploadImage(file, new ImageUploadManager.ImageUploadCallback() {

            @Override
            public void uploadSuccess(String url) {
                uploadAvatar(url);
            }

            @Override
            public void uoloadFailed(String error) {
                ToastUtil.showToast(BaseApplication.getContext(), error);
            }
        });
    }

    private void uploadAvatar(String userAvatar) {
        ApiServiceManager.uploadAvatar(userAvatar)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(BaseApplication.getContext(), "头像上传失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        getV().uploadIconSuccess(userAvatar);
                    }
                });
    }

    /**
     * 根据文件的名称判断文件的Mine值
     */
    private String getMediaType(String fileName) {
        FileNameMap map = URLConnection.getFileNameMap();
        String contentTypeFor = map.getContentTypeFor(fileName);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
