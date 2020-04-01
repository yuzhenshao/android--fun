package com.mfzn.deepusesSer.present.myteam;

import com.mfzn.deepusesSer.activity.myteam.CompanyHomepageActivity;
import com.mfzn.deepusesSer.model.myTeam.TeamManageModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CompanyHomepagePresent extends XPresent<CompanyHomepageActivity> {

    public void teamManage() {
        ApiHelper.getApiService().teamManage(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
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
