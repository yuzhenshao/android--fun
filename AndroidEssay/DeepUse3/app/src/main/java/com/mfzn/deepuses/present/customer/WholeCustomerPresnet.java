package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.fragment.brick.WholeBrickFragment;
import com.mfzn.deepuses.fragment.khgl.WholeCustomerFragment;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WholeCustomerPresnet extends XPresent<WholeCustomerFragment> {

    public void wholeCustomer(Integer page,String keywords, String customerLevelID, String followStatusID, String hasFollowRecords) {
        ApiHelper.getApiService().wholeCustomer(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"10",page,
                keywords,customerLevelID,followStatusID,hasFollowRecords)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<WholeCustomerModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<WholeCustomerModel> reuslt) {
                        getV().brickRecordSuccess(reuslt.getRes());
                    }
                });
    }
}
