package com.mfzn.deepuses.present.xmkefu;

import com.mfzn.deepuses.activityxm.kf.CustomTypeActivity;
import com.mfzn.deepuses.model.xiangmu.CustomTypeModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CustomTypePresnet extends XPresent<CustomTypeActivity> {

    public void customType() {
        ApiServiceManager.getAsServicePeopleTypeList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<CustomTypeModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<CustomTypeModel>> reuslt) {
                        getV().customTypeSuccess(reuslt.getRes());
                    }
                });
    }
}
