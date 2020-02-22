package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.fragment.brick.RechargeBrickFragment;
import com.mfzn.deepuses.fragment.khgl.CustomerTongjiFragment;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CustomerTongjiPresnet extends XPresent<CustomerTongjiFragment> {

//    public void brickRecord(Integer page) {
//        ApiHelper.getApiService().brickRecord(UserHelper.getToken(), UserHelper.getUid(),"1","10",page)
//                .compose(XApi.getApiTransformer())
//                .compose(XApi.getScheduler())
//                .compose(getV().bindToLifecycle())
//                .subscribe(new ApiSubscriber<HttpResult<BrickRecordModel>>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        getV().showError(error);
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<BrickRecordModel> reuslt) {
//                        getV().brickRecordSuccess(reuslt.getRes());
//                    }
//                });
//    }
}
