package com.mfzn.deepusesSer.present.person;

import com.mfzn.deepusesSer.activitymy.ModifyCallActivity;
import com.mfzn.deepusesSer.activitymy.setting.PersonInfoActivity;
import com.mfzn.deepusesSer.model.person.UserInfoModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
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

public class UserInfoModifyNamePresent extends XPresent<ModifyCallActivity> {

    //用户信息
    public void modifyName(String u_name) {
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
                        getV().modifyName(result.getMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
