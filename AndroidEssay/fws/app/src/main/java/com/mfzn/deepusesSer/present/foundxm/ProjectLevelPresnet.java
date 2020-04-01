package com.mfzn.deepusesSer.present.foundxm;

import com.mfzn.deepusesSer.activityxm.ProjectLevelActivity;
import com.mfzn.deepusesSer.model.xiangmu.ProjectLevelModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectLevelPresnet extends XPresent<ProjectLevelActivity> {

    public void projectLevel() {
        ApiHelper.getApiService().projectLevel(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<ProjectLevelModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<ProjectLevelModel>> reuslt) {
                        getV().projectLevelSuccess(reuslt.getRes());
                    }
                });
    }
}
