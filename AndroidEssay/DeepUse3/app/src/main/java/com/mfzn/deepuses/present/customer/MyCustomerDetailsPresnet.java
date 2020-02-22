package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.CustomerDetailsActivity;
import com.mfzn.deepuses.activity.khgl.MyCustomerDetailsActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.bean.DetailsModel;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MyCustomerDetailsPresnet extends XPresent<MyCustomerDetailsActivity> {

    public void customerDetails(String companyCustomerID) {
        ApiHelper.getApiService().customerDetails(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),companyCustomerID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<DetailsModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<DetailsModel> reuslt) {
                        getV().customerDetailsSuccess(reuslt.getRes());
                    }
                });
    }
}
