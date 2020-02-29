package com.mfzn.deepuses.present.my;


import com.mfzn.deepuses.activitymy.setting.ReplacePhone2Activity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
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
public class ReplacePhoneCodePresent extends XPresent<ReplacePhone2Activity> {

    public void getSmsCode(String phone) {
        getV().showDialog();
        ApiServiceManager.getSmsCode(phone, "2")
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

    public void modifyPhone(String nowPwd, String u_phone, String smscode) {
        getV().showDialog();
        ApiHelper.getApiService().appModifyPhone(UserHelper.getToken(), UserHelper.getUid(),nowPwd, u_phone,smscode)
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
                        getV().modifyPhoneSuccess();
                    }

                    @Override
                    public void onComplete() {
                        getV().hideDialog();
                    }
                });
    }
}
