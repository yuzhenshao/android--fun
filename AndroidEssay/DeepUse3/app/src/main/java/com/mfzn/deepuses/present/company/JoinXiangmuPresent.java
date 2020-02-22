package com.mfzn.deepuses.present.company;

import com.mfzn.deepuses.activity.company.ApplyJoinActivity;
import com.mfzn.deepuses.activity.company.JoinXiangmuActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectDetailsModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class JoinXiangmuPresent extends XPresent<JoinXiangmuActivity> {

    public void joinXiangmu(String proID, String label, String remark) {
        ApiHelper.getApiService().joinXiangmu(UserHelper.getToken(), UserHelper.getUid(),proID,label,remark)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().joinXiangmu(result.getMsg());
                    }
                });
    }

    public void xmDetails(String proID) {
        ApiHelper.getApiService().xmDetails(UserHelper.getToken(), UserHelper.getUid(),proID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ProjectDetailsModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ProjectDetailsModel> result) {
                        getV().xmDetails(result.getRes());
                    }
                });
    }
}
