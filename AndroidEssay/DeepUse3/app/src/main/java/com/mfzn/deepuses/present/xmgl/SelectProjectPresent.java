package com.mfzn.deepuses.present.xmgl;

import com.mfzn.deepuses.activity.xmgl.SelectProjectActivity;
import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectProjectPresent extends XPresent<SelectProjectActivity> {

    public void getProjectList(int page, int type) {
        ApiServiceManager.getProjectList(new ProjectListRequest(page, type))
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
