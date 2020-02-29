package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.fragment.BaikeFragment;
import com.mfzn.deepuses.fragment.fx.ZixunFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;

public class ZixunPresnet extends XPresent<ZixunFragment> {

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
