package com.mfzn.deepusesSer.present.login;

import com.mfzn.deepusesSer.MainActivity;
import com.mfzn.deepusesSer.model.msg.Message;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

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
        ApiHelper.getApiService().getSerMsgList(UserHelper.getToken(), UserHelper.getUid(),"100000",1)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<Message>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<Message> reuslt) {
                        getV().getMsgSuccess(reuslt.getRes());
                    }
                });
    }
}
