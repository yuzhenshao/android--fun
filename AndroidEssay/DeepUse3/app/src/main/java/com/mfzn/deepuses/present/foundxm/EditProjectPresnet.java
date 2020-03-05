package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activity.project.EditProjectActivity;
import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.bean.request.EditProjectRequest;
import com.mfzn.deepuses.model.xiangmu.FoundProjectModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EditProjectPresnet extends XPresent<EditProjectActivity> {

    public void editProject(EditProjectRequest request) {
        ApiServiceManager.editProject(request)
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
                        getV().editProjectSuccess();
                    }
                });
    }
}
