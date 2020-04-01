package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.RemarksAtivity;
import com.mfzn.deepuses.activity.jiagou.ShareCodeActivity;
import com.mfzn.deepuses.bean.request.CompanyInfoRequest;
import com.mfzn.deepuses.model.jiagou.ShareCodeModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShareCodePresent extends XPresent<ShareCodeActivity> {

    public void shareCode() {
        ApiServiceManager.generateCompanyQRCode(UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ShareCodeModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ShareCodeModel> result) {
                        getV().shareCodeSuccess(result.getRes());
                    }
                });
    }

    public void shareCode(String company) {
        ApiServiceManager.generateCompanyQRCode(company)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ShareCodeModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ShareCodeModel> result) {
                        getV().shareCodeSuccess(result.getRes());
                    }
                });
    }
}
