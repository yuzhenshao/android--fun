package com.mfzn.deepuses.present.my;

import com.mfzn.deepuses.activitymy.setting.PersonInfoActivity;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.net.UploadApi;
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

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
//                .addFormDataPart("token", token);//ParamKey.TOKEN 自定义参数key常量类，即参数名
        RequestBody imageBody = RequestBody.create(MediaType.parse(getMediaType(file.getName())), file);
        builder.addFormDataPart("u_head", file.getName(), imageBody);//imgfile 后台接收图片流的参数名

        List<MultipartBody.Part> parts = builder.build().parts();

//        UploadApi.uploadPhoto(parts).enqueue(new retrofit2.Callback<UploadContractModel>() {
//            @Override
//            public void onResponse(Call<UploadContractModel> call, Response<UploadContractModel> response) {
//                getV().uploadGzdxSuccess(response.body().getStatus(),response.body().getRes());
//            }
//            @Override
//            public void onFailure(Call<UploadContractModel> call, Throwable t) {
//                getV().uploadGzdxSuccess(0,null);
//            }
//        });

        UploadApi.uploadMemberIcon(parts).enqueue(new retrofit2.Callback<UserUploadModel>() {
            @Override
            public void onResponse(Call<UserUploadModel> call, Response<UserUploadModel> response) {
                getV().uploadIconSuccess(response.body().status,response.body().res);
            }
            @Override
            public void onFailure(Call<UserUploadModel> call, Throwable t) {
                getV().uploadIconSuccess(0,"1");
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
