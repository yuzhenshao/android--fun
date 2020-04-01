package com.mfzn.deepusesSer.present.shouhou;

import com.mfzn.deepusesSer.activityxm.shgd.WaitReceiveActivity;
import com.mfzn.deepusesSer.activityxm.shgd.WaitReceiveFromMsgActivity;
import com.mfzn.deepusesSer.bean.request.AcceptSendOrderRequest;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class JiedanFromMsgPresnet extends XPresent<WaitReceiveFromMsgActivity> {

    public void jiedan(AcceptSendOrderRequest request) {
        ApiHelper.getApiService().jiedan(UserHelper.getToken(), UserHelper.getUid(),request)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().jiedanResult(result);
                    }
                });
    }

    public void setRead(String msgId) {
        ApiHelper.getApiService().setRead(UserHelper.getToken(), UserHelper.getUid(),msgId)
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
                        getV().setReadSuccess(reuslt);
                    }
                });
    }
}
