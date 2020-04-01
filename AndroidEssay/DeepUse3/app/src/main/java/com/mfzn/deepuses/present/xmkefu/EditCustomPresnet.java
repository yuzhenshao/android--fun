package com.mfzn.deepuses.present.xmkefu;

import com.mfzn.deepuses.activityxm.kf.EditCustomActivity;
import com.mfzn.deepuses.bean.request.EditAsServicePeopleRequest;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EditCustomPresnet extends XPresent<EditCustomActivity> {

    public void editCustom(String kfID,String kfTypeID,String name,String phone) {
        ApiServiceManager.editAsServicePeople(new EditAsServicePeopleRequest(kfID,kfTypeID,name,phone))
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
                        getV().editCustomSuccess(reuslt.getMsg());
                    }
                });
    }
}
