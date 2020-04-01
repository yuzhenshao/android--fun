package com.mfzn.deepusesSer.present.xmgd;

import com.mfzn.deepusesSer.activityxm.shgd.AddWorkorderActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;

public class AddWorkorderPresent extends XPresent<AddWorkorderActivity> {

//    public void projectCode(String proID) {
//        ApiHelper.getApiService().projectCode(UserHelper.getToken(), UserHelper.getUid(),proID)
//                .compose(XApi.getApiTransformer())
//                .compose(XApi.getScheduler())
//                .compose(getV().bindToLifecycle())
//                .subscribe(new ApiSubscriber<HttpResult>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        getV().showError(error);
//                    }
//
//                    @Override
//                    public void onNext(HttpResult reuslt) {
//                        getV().projectCodeSuccess();
//                    }
//                });
//    }
}
