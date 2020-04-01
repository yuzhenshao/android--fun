package com.mfzn.deepusesSer.present.login;

import com.mfzn.deepusesSer.activity.login.LoginActivity;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public class LoginPresent extends XPresent<LoginActivity> {

    public void login(String u_phone, String pwd) {
        getV().showDialog();
        ApiHelper.getApiService().appLogin(u_phone, pwd)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<UserModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<UserModel> result) {
                        getV().showSuccessMsg(result.getMsg());
                        getV().loginSuccess(result.getRes());
                    }
                });
    }
}
