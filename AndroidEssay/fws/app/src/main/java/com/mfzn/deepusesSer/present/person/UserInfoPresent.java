package com.mfzn.deepusesSer.present.person;

import com.mfzn.deepusesSer.BaseApplication;
import com.mfzn.deepusesSer.activitymy.setting.PersonInfoActivity;
import com.mfzn.deepusesSer.model.UploadContractModel;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.model.person.UserInfoModel;
import com.mfzn.deepusesSer.model.person.UserUploadModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.ApiService;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.net.ImageUploadManager;
import com.mfzn.deepusesSer.net.UploadApi;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.utils.UserHelper;

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
                .subscribe(new ApiSubscriber<HttpResult<UserModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<UserModel> result) {
                        getV().userInfoSuccess(result.getRes());
                    }

                    @Override
                    public void onComplete() {
//                        getV().hideDialog();
                    }
                });
    }

    //用户信息
    public void modifyName(String u_name) {
//        getV().showDialog();
        ApiHelper.getApiService().appModifyName(UserHelper.getToken(), UserHelper.getUid(),u_name)
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
        ImageUploadManager.uploadImage(file,new ImageUploadManager.ImageUploadCallback(){

            @Override
            public void uploadSuccess(String url) {
                uploadAvatar(url);
            }

            @Override
            public void uoloadFailed(String error) {
                ToastUtil.showToast(BaseApplication.getContext(), "图片上传失败，请稍后重试");
            }
        });
    }

    private void uploadAvatar(String userAvatar) {
        ApiHelper.getApiService().uploadAvatar(UserHelper.getToken(), UserHelper.getUid(),userAvatar)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(BaseApplication.getContext(), "图片上传失败，请稍后重试");
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
