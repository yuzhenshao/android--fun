package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.fragment.GongzuoFragment;
import com.mfzn.deepuses.fragment.xm.GongdanShuxingFragment;
import com.mfzn.deepuses.model.home.JudgeLevelModel;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GongdanShuxingPresnet extends XPresent<GongdanShuxingFragment> {

    public void gongdanShuxing(String orderNo) {
        String token = UserHelper.getToken();
        String uid = UserHelper.getUid();
        ApiHelper.getApiService().gongdanShuxing(UserHelper.getToken(), UserHelper.getUid(),orderNo)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<GongdanShuxingModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<GongdanShuxingModel> result) {
                        getV().gongdanShuxingSuccess(result.getRes());
                    }
                });
    }
}
