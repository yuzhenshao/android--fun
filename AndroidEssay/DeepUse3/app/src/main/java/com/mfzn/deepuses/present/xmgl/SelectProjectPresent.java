package com.mfzn.deepuses.present.xmgl;

import com.mfzn.deepuses.activity.xmgl.MyProjectActivity;
import com.mfzn.deepuses.activity.xmgl.SelectProjectActivity;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectProjectPresent extends XPresent<SelectProjectActivity> {

    public void xiangmuList(Integer page) {
        ApiHelper.getApiService().xiangmuList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"10",page,"")
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

    public void xiangmuList2(Integer page) {
        ApiHelper.getApiService().xiangmuList2(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),"10",page,"")
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
