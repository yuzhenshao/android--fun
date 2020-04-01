package com.mfzn.deepusesSer.present.person;

import com.mfzn.deepusesSer.activitymy.FeedbackActivity;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class FeedbackPresent extends XPresent<FeedbackActivity> {

    public void feedBack(String content) {
        getV().showDialog();
        ApiHelper.getApiService().appFeedback(UserHelper.getToken(), UserHelper.getUid(),content)
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
                        getV().feedbackSuccess();
                    }

                    @Override
                    public void onComplete() {
                        getV().hideDialog();
                    }
                });
    }
}
