package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.AddFollowActivity;
import com.mfzn.deepuses.activity.khgl.MyShnewActivity;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.bean.SelectModel;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MyShnewPresnet extends XPresent<MyShnewActivity> {

    public void myShnew(String comID,String shareRecordID) {
        ApiHelper.getApiService().myShnew(UserHelper.getToken(), UserHelper.getUid(),comID,shareRecordID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MyShareModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<MyShareModel> reuslt) {
                        getV().myShnewSuccess(reuslt.getRes());
                    }
                });
    }

    public void setShareSel(String comID,String shareRecordID,String customers) {
        ApiHelper.getApiService().setShareSel(UserHelper.getToken(), UserHelper.getUid(),comID,shareRecordID,customers)
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
                        getV().setShareSelSuccess();
                    }
                });
    }
}
