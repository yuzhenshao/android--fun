package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.AddFollowActivity;
import com.mfzn.deepuses.bean.request.AddFollowRecordRequest;
import com.mfzn.deepuses.fragment.khxq.BasicInfoFragment;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.bean.DetailsModel;
import com.wevey.selector.dialog.bean.SelectModel;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddFollowPresnet extends XPresent<AddFollowActivity> {

    public void addFollow(AddFollowRecordRequest request) {
        ApiHelper.getApiService().addFollow(UserHelper.getToken(), UserHelper.getUid(),request)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        getV().addFollowSuccess();
                    }
                });
    }

    public void getSelect() {
        ApiHelper.getApiService().getSelect(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<SelectModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<SelectModel> reuslt) {
                        getV().getSelectSuccess(reuslt.getRes());
                    }
                });
    }
}
