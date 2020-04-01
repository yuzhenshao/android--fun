package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.FwzHuizhiDetailActivity;
import com.mfzn.deepuses.model.xiangmu.ClzGongdanDetailModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ClzHuidanDetailPresnet extends XPresent<FwzHuizhiDetailActivity> {

    public void huidanDetail(String receiptId) {
        ApiServiceManager.lookReceipt(receiptId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ClzGongdanDetailModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ClzGongdanDetailModel> result) {
                        getV().huidanDetailSuccess(result.getRes());
                    }
                });
    }
}
