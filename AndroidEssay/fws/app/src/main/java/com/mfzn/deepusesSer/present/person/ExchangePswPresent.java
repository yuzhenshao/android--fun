package com.mfzn.deepusesSer.present.person;


import com.mfzn.deepusesSer.activitymy.setting.SettingNewsPwdActivity;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ExchangePswPresent extends XPresent<SettingNewsPwdActivity> {

    public void modifyPsw(String old_pwd, String new_pwd, String re_new_pwd) {
        getV().showDialog();
        ApiHelper.getApiService().appModifyPwd(UserHelper.getToken(), UserHelper.getUid(),old_pwd, new_pwd,re_new_pwd)
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
