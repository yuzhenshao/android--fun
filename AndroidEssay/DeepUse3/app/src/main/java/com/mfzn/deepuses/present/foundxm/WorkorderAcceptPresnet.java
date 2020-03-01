package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.shgd.WorkorderAcceptActivity;
import com.mfzn.deepuses.bean.request.AcceptAsOrderRequest;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WorkorderAcceptPresnet extends XPresent<WorkorderAcceptActivity> {

    public void workorderAccept(String orderNo, int isAccept, String result, String recommendContact) {
        ApiServiceManager.acceptAsOrder(new AcceptAsOrderRequest(orderNo, isAccept, result, recommendContact))
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
                        getV().workorderAcceptSuccess();
                    }
                });
    }

    public void customList() {
        ApiServiceManager.asServicePeopleList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<CustomListModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<CustomListModel>> reuslt) {
                        getV().customListSuccess(reuslt.getRes());
                    }
                });
    }
}
