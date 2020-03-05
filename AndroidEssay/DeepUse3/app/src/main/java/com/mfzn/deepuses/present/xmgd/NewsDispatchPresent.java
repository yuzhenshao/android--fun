package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.NewsDispatchActivity;
import com.mfzn.deepuses.bean.request.ReSendAsOrderRequest;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class NewsDispatchPresent extends XPresent<NewsDispatchActivity> {

    public void newsDispatch(ReSendAsOrderRequest request) {
        ApiServiceManager.reSendAsOrder(request)
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
                        getV().newsDispatchSuccess();
                    }
                });
    }
}
