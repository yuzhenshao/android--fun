package com.mfzn.deepuses.present.my;

import com.mfzn.deepuses.activitymy.EnginerMaillistActivity;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EnginerMaillistPresent extends XPresent<EnginerMaillistActivity> {

    public void enginerList() {
        ApiServiceManager.getEngineerList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<EnginerListModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<EnginerListModel>> reuslt) {
                        getV().enginerListSuccess(reuslt.getRes());
                    }
                });
    }
}
