package com.mfzn.deepuses.present.brick;

import com.mfzn.deepuses.activitymy.brick.BrickActivity;
import com.mfzn.deepuses.fragment.brick.WholeRecordFragment;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class BrickPresnet extends XPresent<BrickActivity> {

    public void getCompanyInfo() {
        ApiHelper.getApiService().getCompany(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CompanyInfoModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<CompanyInfoModel> reuslt) {
                        getV().getCompanySuccess(reuslt.getRes());
                    }
                });
    }
}
