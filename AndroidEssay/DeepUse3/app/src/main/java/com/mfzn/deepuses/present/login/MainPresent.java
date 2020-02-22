package com.mfzn.deepuses.present.login;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.model.xx.MsgMainModel;
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
public class MainPresent extends XPresent<MainActivity> {

    public void getMSg() {
        ApiHelper.getApiService().getMsg(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MsgMainModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<MsgMainModel> reuslt) {
                        getV().getMsgSuccess(reuslt.getRes());
                    }
                });
    }
}
