package com.mfzn.deepuses.present.project;

import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.activity.project.ProjectNewsActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectNewsPresent extends XPresent<ProjectNewsActivity> {

    public void projectNews(String proID,Integer page) {
        ApiHelper.getApiService().projectNews(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),proID,"10",page)
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

    public void joinProject(String applyID,String status,String checkRemark) {
        ApiHelper.getApiService().joinProject(UserHelper.getToken(), UserHelper.getUid(),applyID,status,checkRemark)
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
