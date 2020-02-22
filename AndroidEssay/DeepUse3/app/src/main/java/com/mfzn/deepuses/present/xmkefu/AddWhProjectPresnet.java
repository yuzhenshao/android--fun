package com.mfzn.deepuses.present.xmkefu;

import com.mfzn.deepuses.activityxm.kf.AddCustomActivity;
import com.mfzn.deepuses.activityxm.kf.AddWhProjectActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddWhProjectPresnet extends XPresent<AddWhProjectActivity> {

    public void addWhProject(String proId,String title,String nextDate,String spaceDate) {
        ApiHelper.getApiService().addWhProject(UserHelper.getToken(), UserHelper.getUid(),proId,title,nextDate,spaceDate)
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
                        getV().addWhProjectSuccess(reuslt.getMsg());
                    }
                });
    }
}
