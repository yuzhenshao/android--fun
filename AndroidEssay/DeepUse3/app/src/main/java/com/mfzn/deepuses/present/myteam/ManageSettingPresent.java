package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.activity.myteam.ManageSettingActivity;
import com.mfzn.deepuses.activity.myteam.TeamManageActivity;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ManageSettingPresent extends XPresent<ManageSettingActivity> {

    public void manageSetting() {
        ApiHelper.getApiService().manageSetting(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<ManageSettingModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<ManageSettingModel>> result) {
                        getV().manageSetting(result.getRes());
                    }
                });
    }

    public void deleteManage(String managerID) {
        ApiHelper.getApiService().deleteManage(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),managerID)
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
                        getV().deleteManage(result.getMsg());
                    }
                });
    }

    public void addAuthority(String userID,String managerRoleID,String departIDs,String authCreate,
                             String authData,String authManage,String rechargeAuth,String crmAuth) {
        ApiHelper.getApiService().addAuthority(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),
                userID,managerRoleID,departIDs,authCreate,authData,authManage,rechargeAuth,crmAuth)
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
                        getV().addAuthority(result.getMsg());
                    }
                });
    }
}
