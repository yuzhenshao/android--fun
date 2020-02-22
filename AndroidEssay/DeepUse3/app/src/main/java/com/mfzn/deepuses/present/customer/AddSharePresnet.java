package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.AddShareActivity;
import com.mfzn.deepuses.activity.khgl.MyShareActivity;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddSharePresnet extends XPresent<AddShareActivity> {

    public void addShare(String customers,String toCompany) {
        ApiHelper.getApiService().addShare(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),customers,toCompany)
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
                        getV().addShareSuccess();
                    }
                });
    }
}
