package com.mfzn.deepusesSer.present.xmgd;

import com.mfzn.deepusesSer.activityxm.shgd.WorkorderListActivity;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WorkorderListPresent extends XPresent<WorkorderListActivity> {

    public void workorderList(String shType,Integer page,String status) {
        ApiHelper.getApiService().workorderList(UserHelper.getToken(), UserHelper.getUid(),shType,status,"10",page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<WorkorderListModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<WorkorderListModel> reuslt) {
                        getV().workorderListSuccess(reuslt.getRes());
                    }
                });
    }
}
