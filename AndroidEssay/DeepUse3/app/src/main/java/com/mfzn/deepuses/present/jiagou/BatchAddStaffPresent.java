package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.BatchAddStaffActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class BatchAddStaffPresent extends XPresent<BatchAddStaffActivity> {

    public void getDepartments() {
        ApiServiceManager.getDepartments()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ZuzhiJiagouModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<ZuzhiJiagouModel> result) {
                        getV().jiagouListSuccess(result.getRes());
                    }
                });
    }

    public void moveStaff(String userIDs, String oldDepartmentID, String newDepartmentID) {
        ApiHelper.getApiService().moveStaff(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),userIDs,oldDepartmentID,newDepartmentID)
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
                        getV().moveStaffSuccess();
                    }
                });
    }
}
