package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.MyFragment;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.model.person.UserInfoModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MyPresnet extends XPresent<MyFragment> {

    //用户信息
    public void userInfo() {
        ApiHelper.getApiService().appUserInfo(UserHelper.getToken(), UserHelper.getUid())
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
                        getV().userInfoSuccess(result.getRes());
                    }

                    @Override
                    public void onComplete() {
//                        getV().hideDialog();
                    }
                });
    }
}
