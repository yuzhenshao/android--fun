package com.mfzn.deepuses.present.xmhf;

import com.mfzn.deepuses.activityxm.shhf.AddReturnVisitActivity;
import com.mfzn.deepuses.activityxm.shhf.VisitRecordActivity;
import com.mfzn.deepuses.model.xmhf.VisitRrcordModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class VisitRecordPresent extends XPresent<VisitRecordActivity> {

    public void visitRecord(String proID,Integer page) {
        ApiHelper.getApiService().visitRecord(UserHelper.getToken(), UserHelper.getUid(),proID,"10",page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<VisitRrcordModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<VisitRrcordModel> reuslt) {
                        getV().visitRecordSuccess(reuslt.getRes());
                    }
                });
    }
}
