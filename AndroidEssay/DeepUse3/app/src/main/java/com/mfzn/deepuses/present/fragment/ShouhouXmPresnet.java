package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.fragment.xm.ShouhouXmFragment;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShouhouXmPresnet extends XPresent<ShouhouXmFragment> {

    public void xiangmuList(Integer page) {
        ProjectListRequest request = new ProjectListRequest(page, ProjectListRequest.MY_PROJECTS);
        request.setCompanyID("");
        ApiServiceManager.getProjectList(request)
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
