package com.mfzn.deepuses.present.project;

import com.mfzn.deepuses.activity.project.ProjectDetailsActivity;
import com.mfzn.deepuses.bean.request.ProMemberRequest;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectDetailsPresent extends XPresent<ProjectDetailsActivity> {

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

    public void addStaging(String proID, String userID) {
        ApiServiceManager.addProMember(new ProMemberRequest(proID, userID, "1"))
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
                        getV().addStagingSuccess();
                    }
                });
    }

    public void deleteStaging(String proID, String userID) {
        ApiServiceManager.deleteProMember(new ProMemberRequest(proID, userID, "1"))
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
                        getV().deleteStagingSuccess();
                    }
                });
    }

    public void deleteProject(String proID) {
        ApiHelper.getApiService().deleteProject(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(), proID)
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
                        getV().deleteProjectSuccess();
                    }
                });
    }

    public void getBrick() {
        ApiHelper.getApiService().getCompany(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CompanyInfoModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<CompanyInfoModel> reuslt) {
                        getV().getCompanySuccess(reuslt.getRes());
                    }
                });
    }

    //moduleType  1售后 2流程 3进销存
    public void openBk(String proId, String moduleType, String zhuanNum) {
        ApiHelper.getApiService().openBk(UserHelper.getToken(), UserHelper.getUid(), proId, moduleType, zhuanNum)
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
                        getV().openBk();
                    }
                });
    }
}
