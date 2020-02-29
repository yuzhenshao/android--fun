package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.SelectPerson3Activity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectPerson3Present extends XPresent<SelectPerson3Activity> {

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
                        getV().selectPersonSuccess(result.getRes());
                    }
                });
    }
}
