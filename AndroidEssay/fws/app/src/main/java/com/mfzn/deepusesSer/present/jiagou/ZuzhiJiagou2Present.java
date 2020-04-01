package com.mfzn.deepusesSer.present.jiagou;

import com.mfzn.deepusesSer.activity.jiagou.ZuzhiJiagou2Activity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ZuzhiJiagou2Present extends XPresent<ZuzhiJiagou2Activity> {

    public void jiagouList() {
        ApiHelper.getApiService().jiagouList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
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
