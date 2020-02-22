package com.mfzn.deepuses.present.brick;

import com.mfzn.deepuses.activitymy.brick.TransactionRecordActivity;
import com.mfzn.deepuses.fragment.brick.TransactionRecordFragment;
import com.mfzn.deepuses.model.brick.TransactionRecordModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class TransactionPresnet extends XPresent<TransactionRecordActivity> {

    public void transactionRecord() {
        ApiHelper.getApiService().transactionRecord(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"0","10",1)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<TransactionRecordModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<TransactionRecordModel> reuslt) {
                        getV().transactionRecord(reuslt.getRes());
                    }
                });
    }
}
