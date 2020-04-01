package com.mfzn.deepusesSer.present.jiagou;

import com.mfzn.deepusesSer.activity.jiagou.EditStaffActivity;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EditStaffPresent extends XPresent<EditStaffActivity> {

    public void editStaff(String staffUID,String positionName,String oldDepartmentID,String newDepartmentID,String joinTime,String staffName) {
        ApiHelper.getApiService().editStaff(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),
                staffUID,positionName,oldDepartmentID,newDepartmentID,joinTime,staffName)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().editStaffSuccess();
                    }
                });
    }

    public void deleteStaff(String staffUID,String departmentID) {
        ApiHelper.getApiService().deleteStaff(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),
                staffUID,departmentID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().deleteStaffSuccess();
                    }
                });
    }
}
