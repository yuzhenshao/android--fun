package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.activity.myteam.AuthoritySettingActivity;
import com.mfzn.deepuses.activity.myteam.ManageSettingActivity;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AuthoritySettingPresent extends XPresent<AuthoritySettingActivity> {

    public void authoritySetting(String userID,ManageSettingModel model) {
        ApiHelper.getApiService().updateManager(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),
                userID,model.getProCreateAuth(),model.getAdminCreateAuth(),model.getRechargeAuth(),model.getCrmAuth())
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
                        getV().authoritySetting(result.getMsg());
                    }
                });
    }
}
