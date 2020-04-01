package com.mfzn.deepusesSer.present.foundxm;

import com.mfzn.deepusesSer.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepusesSer.model.xiangmu.ProjectStagingModel;
import com.mfzn.deepusesSer.model.xiangmu.StagingListModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectStagingPresnet extends XPresent<ProjectStagingActivity> {

    public void projectStaging(String proID) {
        ApiHelper.getApiService().projectStaging(UserHelper.getToken(), UserHelper.getUid(),proID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ProjectStagingModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ProjectStagingModel> reuslt) {
                        getV().projectStagingSuccess(reuslt.getRes());
                    }
                });
    }

    public void stagingList(String proID) {
        ApiHelper.getApiService().stagingList(UserHelper.getToken(), UserHelper.getUid(),proID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<StagingListModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<StagingListModel> reuslt) {
                        getV().stagingListSuccess(reuslt.getRes());
                    }
                });
    }
}
