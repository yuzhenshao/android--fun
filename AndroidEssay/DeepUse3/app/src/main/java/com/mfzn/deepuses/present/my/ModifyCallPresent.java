package com.mfzn.deepuses.present.my;

import com.mfzn.deepuses.activitymy.ModifyCallActivity;
import com.mfzn.deepuses.activitymy.setting.PersonInfoActivity;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.net.ApiHelper;
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

public class ModifyCallPresent extends XPresent<ModifyCallActivity> {

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
                });
    }
}
