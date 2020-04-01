package com.mfzn.deepusesSer.present.jiagou;

import com.mfzn.deepusesSer.activity.jiagou.RemarksAtivity;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class RemarksPresent extends XPresent<RemarksAtivity> {

    public void remarks(String remarkName, String staffUID) {
        ApiHelper.getApiService().remarks(UserHelper.getToken(), UserHelper.getUid(),remarkName,UserHelper.getCompanyId(),staffUID)
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
                        getV().remarksSuccess();
                    }
                });
    }
}
