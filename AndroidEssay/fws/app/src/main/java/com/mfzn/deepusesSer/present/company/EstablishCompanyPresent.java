package com.mfzn.deepusesSer.present.company;

import com.mfzn.deepusesSer.activity.company.EstablishCompanyActivity;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EstablishCompanyPresent extends XPresent<EstablishCompanyActivity> {

    public void establishCompany(String companyName, String areaID, String businessScope) {
        ApiHelper.getApiService().establishCompany(UserHelper.getToken(), UserHelper.getUid(),companyName,areaID,businessScope)
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
                        getV().establishCompanySuccess();
                    }
                });
    }
}
