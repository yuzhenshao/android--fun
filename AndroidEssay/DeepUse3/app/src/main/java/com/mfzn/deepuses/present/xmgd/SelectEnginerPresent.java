package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.EnginerListActivity;
import com.mfzn.deepuses.activityxm.shgd.SelectEnginerActivity;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.model.xiangmu.SelectEnginerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectEnginerPresent extends XPresent<SelectEnginerActivity> {

    public void selectEnginer(String phone) {
        ApiServiceManager.searchEngineer(phone)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<SelectEnginerModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<SelectEnginerModel> reuslt) {
                        getV().selectEnginerSuccess(reuslt.getRes());
                    }
                });
    }

    public void addEnginer(String enginerID) {
        ApiServiceManager.addEngineer(enginerID,"通过搜索手机号添加")
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
                        getV().addEnginerSuccess();
                    }
                });
    }
}
