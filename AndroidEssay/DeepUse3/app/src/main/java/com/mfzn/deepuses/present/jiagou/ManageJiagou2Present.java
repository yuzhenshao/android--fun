package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.ManageJiagou2Activity;
import com.mfzn.deepuses.bean.request.AddDepartmentRequest;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ManageJiagou2Present extends XPresent<ManageJiagou2Activity> {

    public void jiagouList() {
        ApiServiceManager.getDepartments()
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
        ApiHelper.getApiService().deleteBm(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(), departmentID)
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

    public void modifyBmName(String departmentID, String departName) {
        ApiHelper.getApiService().modifyBmName(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(), departmentID, departName)
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

    public void addSonbm(String departmentID, String departmentName) {
        ApiServiceManager.addDepartment(new AddDepartmentRequest(departmentID, departmentName))
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
                        getV().addSonbmSuccess();
                    }
                });
    }
}
