package com.mfzn.deepusesSer.present.shouhou;

import com.mfzn.deepusesSer.activityxm.shgd.FwzHuizhiDetailActivity;
import com.mfzn.deepusesSer.activityxm.shgd.PingjiaDetailActivity;
import com.mfzn.deepusesSer.model.shouhou.ClzGongdanDetailModel;
import com.mfzn.deepusesSer.model.shouhou.PingjiaDetailModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PingjiaDetailPresnet extends XPresent<PingjiaDetailActivity> {

    public void pingjiaDetail(String orderNo) {
        ApiHelper.getApiService().lookAppraise(UserHelper.getToken(), UserHelper.getUid(),orderNo)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<PingjiaDetailModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<PingjiaDetailModel> result) {
                        getV().pingjiaDetailSuccess(result.getRes());
                    }
                });
    }
}
