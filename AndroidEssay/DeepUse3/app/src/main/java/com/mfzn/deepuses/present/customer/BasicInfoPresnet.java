package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.fragment.khgl.CustomerTongjiFragment;
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

public class BasicInfoPresnet extends XPresent<BasicInfoFragment> {

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

    public void customerDetails(String companyCustomerID) {
        ApiHelper.getApiService().customerDetails(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),companyCustomerID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<DetailsModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<DetailsModel> reuslt) {
                        getV().customerDetailsSuccess(reuslt.getRes());
                    }
                });
    }

    public void editInfo(String companyCustomerID,String customerName,String customerPhone,String followStatusID,String customerLevelID,
                         String customerSourceID, String remark, String salesPersonUserID) {
        ApiHelper.getApiService().editInfo(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),companyCustomerID,
                customerName,customerPhone,followStatusID,customerLevelID,customerSourceID,remark,salesPersonUserID)
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
                        getV().editInfoSuccess();
                    }
                });
    }
}
