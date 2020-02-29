package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.ProDetailsActivity;
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.xiangmu.ProjectStagingModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProDetailsPresnet extends XPresent<ProDetailsActivity> {

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
        ApiServiceManager.getProMemberList(proID)
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

    public void quanxian(String proID) {
        ApiHelper.getApiService().lookQuanxian(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(),proID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<LookQuanxianModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<LookQuanxianModel> result) {
                        getV().lookQxSuccess(result.getRes());
                    }
                });
    }
}
