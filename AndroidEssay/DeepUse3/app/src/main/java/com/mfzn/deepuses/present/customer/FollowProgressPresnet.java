package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.fragment.khxq.BasicInfoFragment;
import com.mfzn.deepuses.fragment.khxq.FollowProgressFragment;
import com.mfzn.deepuses.model.khgl.FollowProModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.bean.DetailsModel;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class FollowProgressPresnet extends XPresent<FollowProgressFragment> {

    public void followPro(String companyCustomerID) {
        ApiHelper.getApiService().followPro(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),companyCustomerID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<FollowProModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<FollowProModel>> reuslt) {
                        getV().followProSuccess(reuslt.getRes());
                    }
                });
    }
}
