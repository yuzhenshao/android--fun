package com.mfzn.deepusesSer.present.myteam;

import com.mfzn.deepusesSer.activity.myteam.CompanyScaleActivity;
import com.mfzn.deepusesSer.model.myTeam.CompanyScaleModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CompanyScalePresent extends XPresent<CompanyScaleActivity> {

    public void companyScale() {
        ApiHelper.getApiService().companyScale(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<CompanyScaleModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<CompanyScaleModel>> result) {
                        getV().companyScale(result.getRes());
                    }
                });
    }
}
