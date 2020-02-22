package com.mfzn.deepuses.present.myset;

import com.mfzn.deepuses.activitymy.setting.SwitchCompanyActivity;
import com.mfzn.deepuses.activityxm.kf.AddCustomActivity;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SwitchCompanyPresnet extends XPresent<SwitchCompanyActivity> {

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

    public void delCompany(String companyID) {
        ApiHelper.getApiService().delCompany(UserHelper.getToken(), UserHelper.getUid(),companyID)
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
                        getV().delCompanySuccess(result.getMsg());
                    }
                });
    }
}
