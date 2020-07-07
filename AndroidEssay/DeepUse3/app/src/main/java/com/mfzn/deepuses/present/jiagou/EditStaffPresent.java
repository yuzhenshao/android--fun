package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.EditStaffActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepuses.bean.request.EditStaffRequest;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EditStaffPresent extends XPresent<EditStaffActivity> {

    public void editStaff(String staffUID,String positionName,String oldDepartmentID,String newDepartmentID,String joinTime,String staffName) {
        EditStaffRequest staffBean=new EditStaffRequest();
        staffBean.setStaffUID(staffUID);
        staffBean.setOldDepartmentID(oldDepartmentID);
        staffBean.setNewDepartmentID(newDepartmentID);
        staffBean.setJoinTime(joinTime);
        staffBean.setStaffName(staffName);
        staffBean.setCompanyID(UserHelper.getCompanyId());
        ApiServiceManager.editStaff(staffBean)
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

    public void deleteStaff(String staffUID) {
        ApiHelper.getApiService().deleteStaff(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),
                staffUID)
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
