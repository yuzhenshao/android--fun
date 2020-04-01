package com.mfzn.deepusesSer.present.shouhou;

import com.mfzn.deepusesSer.fragment.shouhou.GongdanShuxingFragment;
import com.mfzn.deepusesSer.model.shouhou.GongdanShuxingModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GongdanShuxingPresnet extends XPresent<GongdanShuxingFragment> {

    public void gongdanShuxing(String orderNo) {
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
