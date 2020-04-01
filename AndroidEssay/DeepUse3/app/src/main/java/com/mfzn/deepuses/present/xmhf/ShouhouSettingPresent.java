package com.mfzn.deepuses.present.xmhf;

import com.mfzn.deepuses.activityxm.shgd.ShouhuSettingActivity;
import com.mfzn.deepuses.bean.request.AsSetRequest;
import com.mfzn.deepuses.model.xiangmu.SettingInfoModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShouhouSettingPresent extends XPresent<ShouhuSettingActivity> {

    public void settingInfo(String proId) {
        ApiServiceManager.lookAsSet(proId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<SettingInfoModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<SettingInfoModel> reuslt) {
                        getV().settingInfoSuccess(reuslt.getRes());
                    }
                });
    }

    public void shouhouSetting(AsSetRequest request) {
        ApiServiceManager.asSet(request)
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
                        getV().shouhouSettingSuccess(reuslt.getMsg());
                    }
                });
    }
}
