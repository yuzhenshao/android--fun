package com.mfzn.deepuses.present.my;


import com.mfzn.deepuses.activitymy.MyPromotionActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MyPromotionPresent extends XPresent<MyPromotionActivity> {

    //获取图片
    public void myPromotion(String phone) {
        ApiHelper.getApiService().myPromotion(UserHelper.getToken(), UserHelper.getUid(),phone)
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
                        getV().myPromotionSuccess(result);
                    }
                });
    }
}
