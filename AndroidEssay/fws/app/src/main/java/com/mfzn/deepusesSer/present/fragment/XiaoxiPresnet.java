package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.XiaoxiFragment;
import com.mfzn.deepusesSer.model.msg.Message;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class XiaoxiPresnet extends XPresent<XiaoxiFragment> {

    public void getMsg(int page) {
        ApiHelper.getApiService().getSerMsgList(UserHelper.getToken(), UserHelper.getUid(),"20",page)
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
                        getV().getMsgListSuccess(reuslt.getRes());
                    }
                });
    }

    public void setRead(String msgId) {
        ApiHelper.getApiService().setRead(UserHelper.getToken(), UserHelper.getUid(),msgId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        getV().setReadSuccess(reuslt);
                    }
                });
    }
}
