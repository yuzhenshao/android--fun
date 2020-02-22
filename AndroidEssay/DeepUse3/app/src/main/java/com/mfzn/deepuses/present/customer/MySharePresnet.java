package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.CustomerMangerActivity;
import com.mfzn.deepuses.activity.khgl.MyShareActivity;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MySharePresnet extends XPresent<MyShareActivity> {

    public void myShare(Integer page) {
        ApiHelper.getApiService().myShare(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"10",page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MyShareModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<MyShareModel> reuslt) {
                        getV().myShareSuccess(reuslt.getRes());
                    }
                });
    }

    public void delMyShare(String shareRecordID) {
        ApiHelper.getApiService().delMyShare(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),shareRecordID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        getV().delMyShareSuccess();
                    }
                });
    }
}
