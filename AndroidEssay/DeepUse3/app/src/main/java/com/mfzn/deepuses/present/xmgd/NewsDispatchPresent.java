package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.NewsDispatchActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderDispatchActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class NewsDispatchPresent extends XPresent<NewsDispatchActivity> {

    public void newsDispatch(String orderNo,String enginerID,String name,String phone,String note,String shJobID) {
        ApiHelper.getApiService().newsDispatch(UserHelper.getToken(), UserHelper.getUid(),orderNo,enginerID,name,phone,note,shJobID)
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
                        getV().newsDispatchSuccess();
                    }
                });
    }
}
