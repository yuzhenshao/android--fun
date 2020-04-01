package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.BaikeFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;

public class FaxianPresnet extends XPresent<BaikeFragment> {

//    public void xiangmuList(Integer page) {
//        String companyId = UserHelper.getCompanyId();
//        ApiHelper.getApiService().xiangmuList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"10",page)
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
