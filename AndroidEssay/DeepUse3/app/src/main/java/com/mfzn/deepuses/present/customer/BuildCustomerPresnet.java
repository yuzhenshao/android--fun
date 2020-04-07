package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.BulidCustomerActivity;
import com.mfzn.deepuses.bean.request.AddCustomerRequest;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.bean.SelectModel;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class BuildCustomerPresnet extends XPresent<BulidCustomerActivity> {

    public void buildCustomer(String customerName,String customerPhone,String followStatusID,String customerLevelID,String customerSourceID,
                              String remark) {
        AddCustomerRequest request=new AddCustomerRequest();
        request.setCompanyID(UserHelper.getCompanyId());
        request.setCustomerName(customerName);
        request.setCustomerPhone(customerPhone);
        request.setFollowStatusID(followStatusID);
        request.setCustomerLevelID(customerLevelID);
        request.setCustomerSourceID(customerSourceID);
        request.setRemark(remark);
        ApiHelper.getApiService().buildCustomer(UserHelper.getToken(), UserHelper.getUid(),request)
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
                        getV().buildCustomerSuccess();
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
