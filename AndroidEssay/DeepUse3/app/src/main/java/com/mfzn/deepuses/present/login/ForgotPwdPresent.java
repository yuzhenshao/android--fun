package com.mfzn.deepuses.present.login;


import com.mfzn.deepuses.activity.login.ForgotNewPwdActivity;
import com.mfzn.deepuses.activity.login.RegisterPwdActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public class ForgotPwdPresent extends XPresent<ForgotNewPwdActivity> {

    public void login(String u_phone, String pwd) {
        ApiHelper.getApiService().appLogin(u_phone, pwd)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<UserModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().loginErr();
                    }

                    @Override
                    public void onNext(HttpResult<UserModel> result) {
                        getV().loginSuccess(result.getRes());
                    }
                });
    }

    public void forgetPwd(String u_phone, String smscode, String u_pwd, String u_re_pwd) {
        getV().showDialog();
        ApiHelper.getApiService().forgetPwd(u_phone, smscode, u_pwd, u_re_pwd)
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
                        getV().showSuccessMsg(result.getMsg());
                        getV().forgetPwdSuccess();
                    }

                });
    }
}
