package com.mfzn.deepuses.present.xx;

import com.mfzn.deepuses.activitynews.ProjectMsgActivity;
import com.mfzn.deepuses.activitynews.TeamMsgActivity;
import com.mfzn.deepuses.model.xx.MsgTdxxModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectMsgPresnet extends XPresent<ProjectMsgActivity> {
    public void getMsg(Integer page) {
        ApiHelper.getApiService().getMsgList(UserHelper.getToken(), UserHelper.getUid(),"1","10",page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MsgTdxxModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<MsgTdxxModel> reuslt) {
                        getV().getMsgListSuccess(reuslt.getRes());
                    }
                });
    }

    public void setRead(String msgId) {
        ApiHelper.getApiService().setRead(UserHelper.getToken(), UserHelper.getUid(),msgId)
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
                        getV().setReadSuccess(reuslt);
                    }
                });
    }

    public void delRead(String msgId) {
        ApiHelper.getApiService().delMsg(UserHelper.getToken(), UserHelper.getUid(),msgId)
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
                        getV().delMsgSuccess(reuslt);
                    }
                });
    }
}
