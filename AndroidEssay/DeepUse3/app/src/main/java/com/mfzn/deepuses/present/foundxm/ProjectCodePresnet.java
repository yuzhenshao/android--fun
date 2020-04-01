package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.activityxm.ProjectCodeActivity;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.model.xiangmu.FoundProjectModel;
import com.mfzn.deepuses.model.xiangmu.ProjectCodeModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectCodePresnet extends XPresent<ProjectCodeActivity> {

    public void projectCode(String proID) {
        ApiHelper.getApiService().projectCode(UserHelper.getToken(), UserHelper.getUid(),proID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ProjectCodeModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ProjectCodeModel> reuslt) {
                        getV().projectCodeSuccess(reuslt.getRes());
                    }
                });
    }

    //用户信息
    public void userInfo() {
        ApiHelper.getApiService().appUserInfo(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<UserResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<UserResponse> result) {
                        getV().userInfoSuccess(result.getRes());
                    }
                });
    }
}
