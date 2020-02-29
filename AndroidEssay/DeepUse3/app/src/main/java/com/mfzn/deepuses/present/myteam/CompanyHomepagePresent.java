package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.activity.myteam.CompanyHomepageActivity;
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

public class CompanyHomepagePresent extends XPresent<CompanyHomepageActivity> {

    public void teamManage() {
        ApiServiceManager.getCompanyInfo(new CompanyInfoRequest())
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
