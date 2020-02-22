package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.EnginerListActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderDispatchActivity;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WorkorderDispatchPresent extends XPresent<WorkorderDispatchActivity> {

    public void workorderDispatch(String proId,String orderNo,String enginerID,String name,String phone,String note) {
        ApiHelper.getApiService().workorderDispatch(UserHelper.getToken(), UserHelper.getUid(),proId,orderNo,enginerID,name,phone,note)
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
                        getV().workorderDispatchSuccess();
                    }
                });
    }
}
