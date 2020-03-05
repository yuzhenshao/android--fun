package com.mfzn.deepuses.present.xx;

import com.mfzn.deepuses.activitynews.ProjectApplyActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectApplyPresent extends XPresent<ProjectApplyActivity> {

    public void projectNews(Integer page) {
        ApiServiceManager.appliesList("", "", 10, page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ProjectNewsModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ProjectNewsModel> reuslt) {
                        getV().projectNewsSuccess(reuslt.getRes());
                    }
                });
    }

    public void joinProject(String applyID, String status, String checkRemark) {
        ApiServiceManager.doApplyCheck(applyID, status, checkRemark)
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
                        getV().joinProjectSuccess();
                    }
                });
    }
}
