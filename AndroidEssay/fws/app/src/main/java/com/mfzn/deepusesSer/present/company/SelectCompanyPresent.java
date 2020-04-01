package com.mfzn.deepusesSer.present.company;

import com.mfzn.deepusesSer.activity.company.SelectCompanyActivity;
import com.mfzn.deepusesSer.model.company.SelectCompanyModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectCompanyPresent extends XPresent<SelectCompanyActivity> {

    public void companyList() {
        ApiHelper.getApiService().companyList(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<SelectCompanyModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<SelectCompanyModel>> result) {
                        getV().companyListSuccess(result.getRes());
                    }
                });
    }
}
