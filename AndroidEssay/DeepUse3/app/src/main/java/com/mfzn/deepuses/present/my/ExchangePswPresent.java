package com.mfzn.deepuses.present.my;

import com.mfzn.deepuses.activitymy.setting.SettingNewsPwdActivity;
import com.mfzn.deepuses.bean.request.ChangePwdRequest;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ExchangePswPresent extends XPresent<SettingNewsPwdActivity> {

    public void modifyPsw(String oldPwd, String newPwd, String reNewPwd) {
        getV().showDialog();
        ApiServiceManager.changePwd(new ChangePwdRequest(oldPwd, newPwd,reNewPwd))
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
                        getV().modifyPswSuccess();
                    }

                    @Override
                    public void onComplete() {
                        getV().hideDialog();
                    }
                });
    }
}
