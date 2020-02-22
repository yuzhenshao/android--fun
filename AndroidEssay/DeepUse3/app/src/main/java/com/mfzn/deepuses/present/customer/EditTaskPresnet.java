package com.mfzn.deepuses.present.customer;

import com.mfzn.deepuses.activity.khgl.AddTaskActivity;
import com.mfzn.deepuses.activity.khgl.EditTaskActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EditTaskPresnet extends XPresent<EditTaskActivity> {

    public void editTask(String taskID,String taskTime,String noticeTime,
                          String remark) {
        ApiHelper.getApiService().editTask(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),taskID,taskTime,
                noticeTime,remark)
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
                        getV().editTaskSuccess();
                    }
                });
    }
}
