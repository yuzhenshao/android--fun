package com.mfzn.deepuses.present.work;


import com.mfzn.deepuses.fragment.work.B2bsmFragment;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SmPresent extends XPresent<B2bsmFragment> {

    //获取营销的地址
    public void getMarketUrl() {
        ApiHelper.getApiService().getMarketUrl(UserHelper.getToken(), UserHelper.getUid(),"3")
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
                        getV().getMarketUrlSuccess(result);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
