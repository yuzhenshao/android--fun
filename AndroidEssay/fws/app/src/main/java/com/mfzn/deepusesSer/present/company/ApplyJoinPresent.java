package com.mfzn.deepusesSer.present.company;

import com.mfzn.deepusesSer.activity.company.ApplyJoinActivity;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ApplyJoinPresent extends XPresent<ApplyJoinActivity> {

    public void applyJoin(String code, String positionName, String reason) {
        ApiHelper.getApiService().applyJoin(UserHelper.getToken(), UserHelper.getUid(),code,positionName,reason)
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
                        getV().applyJoin();
                    }
                });
    }
}
