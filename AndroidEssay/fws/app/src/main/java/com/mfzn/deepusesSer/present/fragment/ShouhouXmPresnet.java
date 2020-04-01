package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.xm.ShouhouXmFragment;
import com.mfzn.deepusesSer.model.xiangmu.XiangmuModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShouhouXmPresnet extends XPresent<ShouhouXmFragment> {

    public void xiangmuList(Integer page) {
        String companyId = UserHelper.getCompanyId();
        ApiHelper.getApiService().xiangmuList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"10",page)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<XiangmuModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<XiangmuModel> reuslt) {
                        getV().xiangmuListSuccess(reuslt.getRes());
                    }
                });
    }
}
