package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.InvitationJoinActivity;
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

public class InvitationJoinPresent extends XPresent<InvitationJoinActivity> {

    public void shareCode() {
        ApiServiceManager.generateCompanyQRCode(new CompanyInfoRequest())
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
