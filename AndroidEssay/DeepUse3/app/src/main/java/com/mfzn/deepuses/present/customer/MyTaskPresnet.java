package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.MultipleSelectActivity;
import com.mfzn.deepuses.activity.khgl.MyTaskActivity;
import com.mfzn.deepuses.model.khgl.MyTaskModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MyTaskPresnet extends XPresent<MyTaskActivity> {

    public void myTask(Integer page) {
        ApiHelper.getApiService().myTask(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"10",page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MyTaskModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<MyTaskModel> reuslt) {
                        getV().myTaskSuccess(reuslt.getRes());
                    }
                });
    }

    public void delTask(String taskID) {
        ApiHelper.getApiService().delTask(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),taskID)
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
                        getV().delTaskSuccess();
                    }
                });
    }
}
