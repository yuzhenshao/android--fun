package com.mfzn.deepuses.present.brick;

import com.mfzn.deepuses.fragment.brick.WholeRecordFragment;
import com.mfzn.deepuses.fragment.xm.ChuliGuochengFragment;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.model.brick.TransactionRecordModel;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WholeRecordPresnet extends XPresent<WholeRecordFragment> {

    public void transactionRecord(Integer page) {
        ApiHelper.getApiService().transactionRecord(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"0","10",page)
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
