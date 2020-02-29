package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.activity.myteam.SelectManage3Activity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectManage3Present extends XPresent<SelectManage3Activity> {

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
                        getV().jiagouListSuccess(result.getRes());
                    }
                });
    }
}
