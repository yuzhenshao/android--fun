package com.mfzn.deepusesSer.present.shouhou;

import com.mfzn.deepusesSer.fragment.shouhou.ChuliGuochengFragment;
import com.mfzn.deepusesSer.model.shouhou.ChuliGuochengModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ChuliGuochengPresnet extends XPresent<ChuliGuochengFragment> {

    public void chuliGuocheng(String orderNo) {
        ApiHelper.getApiService().chuliGuocheng(UserHelper.getToken(), UserHelper.getUid(),orderNo)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<ChuliGuochengModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<ChuliGuochengModel>> result) {
                        getV().chuliGuochengSuccess(result.getRes());
                    }
                });
    }
}
