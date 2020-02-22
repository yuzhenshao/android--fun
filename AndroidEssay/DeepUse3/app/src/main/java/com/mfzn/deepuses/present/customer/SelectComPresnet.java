package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.SelectComActivity;
import com.mfzn.deepuses.activitymy.setting.SwitchCompanyActivity;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.khgl.SearchComModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectComPresnet extends XPresent<SelectComActivity> {

    public void searchCom(String keywords) {
        ApiHelper.getApiService().searchCom(UserHelper.getToken(), UserHelper.getUid(),keywords)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<SearchComModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<SearchComModel>> result) {
                        getV().companyListSuccess(result.getRes());
                    }
                });
    }
}
