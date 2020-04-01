package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.shouhou.WaitJiedanFragment;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShouhouGongdanPresnet extends XPresent<WaitJiedanFragment> {

    public void workorderList(Integer page,String status) {
        ApiHelper.getApiService().workorderList(UserHelper.getToken(), UserHelper.getUid(),"0",status,"10",page)
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
