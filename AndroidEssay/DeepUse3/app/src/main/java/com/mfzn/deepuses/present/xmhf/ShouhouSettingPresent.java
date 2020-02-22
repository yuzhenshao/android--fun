package com.mfzn.deepuses.present.xmhf;

import com.mfzn.deepuses.activityxm.shgd.ShouhuSettingActivity;
import com.mfzn.deepuses.activityxm.shhf.VisitRecordActivity;
import com.mfzn.deepuses.model.xiangmu.SettingInfoModel;
import com.mfzn.deepuses.model.xmhf.VisitRrcordModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShouhouSettingPresent extends XPresent<ShouhuSettingActivity> {

    public void settingInfo(String proId) {
        ApiHelper.getApiService().settingInfo(UserHelper.getToken(), UserHelper.getUid(),proId)
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

    public void shouhouSetting(String proId,String qualityTime,String qualityBegin,String qualityEnd,String nextVisitTime,
                               String visitSpace,String qualityRing,String wbRing,String visitRing,String ybEnd,String ybTime) {
        ApiHelper.getApiService().shouhouSetting(UserHelper.getToken(), UserHelper.getUid(),proId,qualityTime,qualityBegin,qualityEnd,nextVisitTime,
                visitSpace,qualityRing,wbRing,visitRing,ybEnd,ybTime)
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
