package com.mfzn.deepusesSer.present.foundxm;

import com.mfzn.deepusesSer.activityxm.shgd.WorkorderAcceptActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;

public class WorkorderAcceptPresnet extends XPresent<WorkorderAcceptActivity> {

//    public void foundProject(String pro_name,String latitude,String longitude,String detailAddress,String customName,String customTel,
//                             String sales_people,String contractMoney,String customLevel,String start_time,String end_time,String areaName) {
//        ApiHelper.getApiService().foundProject(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),pro_name,latitude
//                ,longitude,detailAddress,customName,customTel,sales_people,contractMoney,customLevel,start_time,end_time,areaName)
//                .compose(XApi.getApiTransformer())
//                .compose(XApi.getScheduler())
//                .compose(getV().bindToLifecycle())
//                .subscribe(new ApiSubscriber<HttpResult<FoundProjectModel>>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        getV().showError(error);
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<FoundProjectModel> reuslt) {
//                        getV().foundProjectSuccess(reuslt.getRes());
//                    }
//                });
//    }
}
