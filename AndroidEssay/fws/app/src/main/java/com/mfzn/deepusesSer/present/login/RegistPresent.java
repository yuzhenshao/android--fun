package com.mfzn.deepusesSer.present.login;


import com.mfzn.deepusesSer.activity.login.RegisterCodeActivity;
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
public class RegistPresent extends XPresent<RegisterCodeActivity> {

    public void getSmsCode(String phone) {
        getV().showDialog();
        ApiHelper.getApiService().getSmsCode(phone, "0")
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<String> result) {
                        getV().smsCodeSuccess(result.getRes());
                        getV().showSuccessMsg("短信验证码发送成功！");
                    }

                });
    }
}
