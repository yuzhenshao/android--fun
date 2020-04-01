package com.mfzn.deepuses.present.xx;

import com.mfzn.deepuses.activitynews.ProjectApplyActivity;
import com.mfzn.deepuses.activitynews.TeamApplyActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.model.xx.TeamApplyModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class TeamApplyPresent extends XPresent<TeamApplyActivity> {

    public void teamApply(Integer page) {
        ApiHelper.getApiService().teamApply(UserHelper.getToken(), UserHelper.getUid(),2,"10",page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<TeamApplyModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<TeamApplyModel> reuslt) {
                        getV().projectNewsSuccess(reuslt.getRes());
                    }
                });
    }

    public void joinTeam(String applyID,String type) {
        ApiHelper.getApiService().joinTeam(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),applyID,type,"0")
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
                        getV().joinProjectSuccess();
                    }
                });
    }
}
