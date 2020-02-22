package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.fragment.GongzuoFragment;
import com.mfzn.deepuses.fragment.XiaoxiFragment;
import com.mfzn.deepuses.model.xx.MsgMainModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class XiaoxiPresnet extends XPresent<XiaoxiFragment> {
    public void getMsg() {
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
