package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.SearchProjectActivity;
import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchProjectPresnet extends XPresent<SearchProjectActivity> {

    public void xiangmuList(String search) {
        ProjectListRequest request = new ProjectListRequest(1, ProjectListRequest.MY_PROJECTS);
        request.setCompanyID("");
        request.setPer(1000);
        request.setKeywords(search);
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
