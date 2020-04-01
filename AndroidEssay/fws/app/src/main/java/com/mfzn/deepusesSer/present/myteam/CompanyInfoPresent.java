package com.mfzn.deepusesSer.present.myteam;

import com.mfzn.deepusesSer.activity.myteam.CompanyInfoActivity;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CompanyInfoPresent extends XPresent<CompanyInfoActivity> {

    public void companyInfo(String companyName,String shortName,String companyAddress,String companyWebsite,String businessScope,String scaleID) {
        ApiHelper.getApiService().companyInfo(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),
                companyName ,shortName,companyAddress,companyWebsite,businessScope,scaleID)
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
                        getV().companyInfo();
                    }
                });
    }
}
