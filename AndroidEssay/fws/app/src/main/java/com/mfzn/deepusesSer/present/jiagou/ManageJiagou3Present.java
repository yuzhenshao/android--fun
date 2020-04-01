package com.mfzn.deepusesSer.present.jiagou;

import com.mfzn.deepusesSer.activity.jiagou.ManageJiagou3Activity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ManageJiagou3Present extends XPresent<ManageJiagou3Activity> {

    public void jiagouList() {
        ApiHelper.getApiService().jiagouList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ZuzhiJiagouModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ZuzhiJiagouModel> result) {
                        getV().jiagouListSuccess(result.getRes());
                    }
                });
    }

    public void deleteBm(String departmentID) {
        ApiHelper.getApiService().deleteBm(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),departmentID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().deleteBmSuccess();
                    }
                });
    }

    public void modifyBmName(String departmentID,String departName) {
        ApiHelper.getApiService().modifyBmName(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),departmentID,departName)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().modifyBmNameSuccess();
                    }
                });
    }
}
