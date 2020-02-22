package com.mfzn.deepuses.present.brick;

import com.mfzn.deepuses.activitymy.brick.PaymentModeActivity;
import com.mfzn.deepuses.model.brick.AppPaymentModel;
import com.mfzn.deepuses.model.brick.AppWXPaymentModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PaymentModePresent extends XPresent<PaymentModeActivity> {

    //ali 1
    public void appAliPayment(String comboID, String payType) {
        getV().showDialog();
        ApiHelper.getApiService().appAliPayment(UserHelper.getToken(), UserHelper.getUid(),comboID,payType,UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<AppPaymentModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<AppPaymentModel> result) {
                        getV().appAliPay(result.getRes().getOrderString());
                    }

                    @Override
                    public void onComplete() {
                        getV().hideDialog();
                    }
                });
    }

    public void appWXPayment(String comboID, String payType) {
        getV().showDialog();
        ApiHelper.getApiService().appWXPayment(UserHelper.getToken(), UserHelper.getUid(),comboID,payType,UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<AppWXPaymentModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<AppWXPaymentModel> result) {
                        getV().appwxpaySuccess(result.getRes());
                    }

                    @Override
                    public void onComplete() {
                        getV().hideDialog();
                    }
                });
    }
}
