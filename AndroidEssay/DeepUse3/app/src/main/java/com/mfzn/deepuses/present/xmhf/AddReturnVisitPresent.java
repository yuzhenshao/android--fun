package com.mfzn.deepuses.present.xmhf;

import com.mfzn.deepuses.activityxm.kf.AddCustomActivity;
import com.mfzn.deepuses.activityxm.shhf.AddReturnVisitActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddReturnVisitPresent extends XPresent<AddReturnVisitActivity> {

    public void addReturnVisit(String proID,String title,String nowDate,String content,String nextDate) {
        ApiHelper.getApiService().addReturnVisit(UserHelper.getToken(), UserHelper.getUid(),proID,title,nowDate,content,nextDate)
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
                        getV().addReturnVisitSuccess();
                    }
                });
    }
}
