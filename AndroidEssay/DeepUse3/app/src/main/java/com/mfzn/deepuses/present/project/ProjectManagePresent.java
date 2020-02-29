package com.mfzn.deepuses.present.project;

import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectManagePresent extends XPresent<ProjectManageActivity> {

    public void xiangmuList(int page) {
        ApiServiceManager.getProjectList(new ProjectListRequest(page,ProjectListRequest.COMPANY_PROJECTS))
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<XiangmuModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<XiangmuModel> reuslt) {
                        getV().xiangmuListSuccess(reuslt.getRes());
                    }
                });
    }
}
