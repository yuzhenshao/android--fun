package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.fragment.BaikeFragment;
import com.mfzn.deepuses.fragment.XiangmuFragment;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class FaxianPresnet extends XPresent<BaikeFragment> {

//    public void xiangmuList(Integer page) {
//        String companyId = UserHelper.getCompanyId();
//        ApiHelper.getApiService().xiangmuList(UserHelper.getToken(), UserHelper.getUserID(),UserHelper.getCompanyId(),"10",page)
//                .compose(XApi.getApiTransformer())
//                .compose(XApi.getScheduler())
//                .compose(getV().bindToLifecycle())
//                .subscribe(new ApiSubscriber<HttpResult<XiangmuModel>>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        getV().showError(error);
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<XiangmuModel> reuslt) {
//                        getV().xiangmuListSuccess(reuslt.getRes());
//                    }
//                });
//    }
}
