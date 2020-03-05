package com.mfzn.deepuses.present.project;

import com.mfzn.deepuses.activityxm.shgd.CheckAppraiseActivity;
import com.mfzn.deepuses.model.xiangmu.CheckAppraiseModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CheckAppraisePresent extends XPresent<CheckAppraiseActivity> {

    public void checkAppraise(String orderNo) {
        ApiServiceManager.lookAsOrderComment(orderNo)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CheckAppraiseModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<CheckAppraiseModel> reuslt) {
                        getV().checkAppraiseSuccess(reuslt.getRes());
                    }
                });
    }
}
