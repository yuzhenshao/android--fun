package com.mfzn.deepusesSer.present.company;

import com.mfzn.deepusesSer.activity.company.SelectLableActivity;
import com.mfzn.deepusesSer.model.company.SelectLableModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectLablePresent extends XPresent<SelectLableActivity> {

    public void companyLable() {
        ApiHelper.getApiService().companyLable(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<SelectLableModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<SelectLableModel>> result) {
                        getV().companyLableSuccess(result.getRes());
                    }
                });
    }
}
