package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.SearchCustomerActivity;
import com.mfzn.deepuses.activity.khgl.SearchMyCustomerActivity;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchMyCustomerPresnet extends XPresent<SearchMyCustomerActivity> {

    public void wholeCustomer(Integer page,String keywords, String customerLevelID, String followStatusID, String hasFollowRecords) {
        ApiHelper.getApiService().myCustomer(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"100",page,
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
