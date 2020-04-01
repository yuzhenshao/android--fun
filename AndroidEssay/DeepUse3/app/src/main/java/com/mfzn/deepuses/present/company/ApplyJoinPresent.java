package com.mfzn.deepuses.present.company;

import com.mfzn.deepuses.activity.company.ApplyJoinActivity;
import com.mfzn.deepuses.bean.request.CompanyInfoRequest;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ApplyJoinPresent extends XPresent<ApplyJoinActivity> {

    public void applyJoin(String proid,String staffName, String reason) {
        ApiHelper.getApiService().joinWithQrCode(UserHelper.getToken(), UserHelper.getUid(),proid,staffName,reason)
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
                        getV().applyJoin();
                    }
                });
    }

//    public void xmDetails(String proID) {
//        ApiHelper.getApiService().xmDetails(UserHelper.getToken(), UserHelper.getUserID(),proID)
//                .compose(XApi.getApiTransformer())
//                .compose(XApi.getScheduler())
//                .compose(getV().bindToLifecycle())
//                .subscribe(new ApiSubscriber<HttpResult<ProjectDetailsModel>>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        getV().showError(error);
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<ProjectDetailsModel> result) {
//                        getV().xmDetails(result.getRes());
//                    }
//                });
//    }

    public void teamManage(String companyid) {
        ApiServiceManager.getCompanyInfo(new CompanyInfoRequest(companyid))
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<TeamManageModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<TeamManageModel> result) {
                        getV().companyHomepage(result.getRes());
                    }
                });
    }
}
