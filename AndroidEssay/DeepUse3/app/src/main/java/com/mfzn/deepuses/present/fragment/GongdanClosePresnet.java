package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.fragment.xm.GongdanCloseFragment;
import com.mfzn.deepuses.fragment.xm.GongdanFuwuFragment;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GongdanClosePresnet extends XPresent<GongdanCloseFragment> {

    public void gongdanShuxing(String orderNo) {
        ApiServiceManager.lookAsOrder(orderNo)
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
