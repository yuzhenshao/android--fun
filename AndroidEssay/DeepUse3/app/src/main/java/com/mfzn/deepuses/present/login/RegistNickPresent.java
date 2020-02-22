package com.mfzn.deepuses.present.login;


import com.mfzn.deepuses.activity.login.RegisterPwdActivity;
import com.mfzn.deepuses.activity.login.SettingNicknameActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public class RegistNickPresent extends XPresent<SettingNicknameActivity> {

    public void appRegister(String u_phone, String smscode, String u_pwd, String u_re_pwd, String name) {
        getV().showDialog();
        ApiHelper.getApiService().appRegister(u_phone, smscode, u_pwd, u_re_pwd,name)
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
                        getV().registSuccess();
                    }
                });
    }

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
}
