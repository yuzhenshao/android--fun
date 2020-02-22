package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.activity.myteam.CompanyInfoActivity;
import com.mfzn.deepuses.activity.myteam.TeamManageActivity;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CompanyInfoPresent extends XPresent<CompanyInfoActivity> {

    public void companyInfo(String companyName,String shortName,String companyAddress,String latitude,String longitude,
                            String companyWebsite,String businessScope,String scaleID) {
        ApiHelper.getApiService().companyInfo(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),
                companyName ,shortName,companyAddress,latitude,longitude,companyWebsite,businessScope,scaleID)
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
