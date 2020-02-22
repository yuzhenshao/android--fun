package com.mfzn.deepuses.present.xmkefu;

import com.mfzn.deepuses.activityxm.ProjectCodeActivity;
import com.mfzn.deepuses.activityxm.kf.AddCustomActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddCustomPresnet extends XPresent<AddCustomActivity> {

    public void addCustom(String kfTypeID,String name,String phone) {
        ApiHelper.getApiService().addCustom(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),kfTypeID,name,phone)
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
                        getV().addCustomSuccess(reuslt.getMsg());
                    }
                });
    }
}
