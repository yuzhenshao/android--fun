package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.WorkorderDispatchActivity;
import com.mfzn.deepuses.bean.request.SendAsOrderRequest;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WorkorderDispatchPresent extends XPresent<WorkorderDispatchActivity> {

    public void workorderDispatch(SendAsOrderRequest request) {
        ApiServiceManager.sendAsOrder(request)
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
                        getV().workorderDispatchSuccess();
                    }
                });
    }
}
