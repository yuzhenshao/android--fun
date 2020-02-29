package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.ZuzhiJiagou2Activity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ZuzhiJiagou2Present extends XPresent<ZuzhiJiagou2Activity> {

    public void jiagouList() {
        ApiServiceManager.getDepartments()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ZuzhiJiagouModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ZuzhiJiagouModel> result) {
                        getV().jiagouList2Success(result.getRes());
                    }
                });
    }
}
