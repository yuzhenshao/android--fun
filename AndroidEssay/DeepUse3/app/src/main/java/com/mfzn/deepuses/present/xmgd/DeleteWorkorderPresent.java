package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.CancalAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.StayAcceptActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class DeleteWorkorderPresent extends XPresent<StayAcceptActivity> {

    public void deleteWorkorder(String orderNo,String delNote) {
        ApiHelper.getApiService().deleteWorkorder(UserHelper.getToken(), UserHelper.getUid(),orderNo,delNote)
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
                        getV().deleteWorkorderSuccess();
                    }
                });
    }
}
