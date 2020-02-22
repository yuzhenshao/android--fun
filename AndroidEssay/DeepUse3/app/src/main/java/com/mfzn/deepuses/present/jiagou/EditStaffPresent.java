package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.EditStaffActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

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
