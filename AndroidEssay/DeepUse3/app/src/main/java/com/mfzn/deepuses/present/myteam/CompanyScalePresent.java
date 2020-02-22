package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.activity.myteam.CompanyScaleActivity;
import com.mfzn.deepuses.activity.myteam.TeamManageActivity;
import com.mfzn.deepuses.model.myTeam.CompanyScaleModel;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CompanyScalePresent extends XPresent<CompanyScaleActivity> {

    public void companyScale() {
        ApiHelper.getApiService().companyScale(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<CompanyScaleModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<CompanyScaleModel>> result) {
                        getV().companyScale(result.getRes());
                    }
                });
    }
}
