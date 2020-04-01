package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.GongzuoFragment;
import com.mfzn.deepusesSer.model.home.JudgeLevelModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GongzuoPresnet extends XPresent<GongzuoFragment> {

    public void judgeLevel() {
        ApiHelper.getApiService().judgeLevel(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<JudgeLevelModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<JudgeLevelModel> result) {
                        getV().judgeLevelSuccess(result.getRes());
                    }
                });
    }
}
