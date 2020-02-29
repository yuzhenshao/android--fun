package com.mfzn.deepuses.present.login;


import com.mfzn.deepuses.activity.login.ForgotNewPwdActivity;
import com.mfzn.deepuses.activity.login.RegisterPwdActivity;
import com.mfzn.deepuses.bean.request.ForgetRequest;
import com.mfzn.deepuses.bean.request.LoginRequest;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
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

    public void login(LoginRequest request) {
        ApiServiceManager.appLogin(request)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<UserResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().loginErr();
                    }

                    @Override
                    public void onNext(HttpResult<UserResponse> result) {
                        getV().loginSuccess(result.getRes());
                    }
                });
    }

    public void forgetPwd(ForgetRequest request) {
        getV().showDialog();
        ApiServiceManager.forgetPwd(request)
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
