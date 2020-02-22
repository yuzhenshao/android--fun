package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderAcceptActivity;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.FoundProjectModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WorkorderAcceptPresnet extends XPresent<WorkorderAcceptActivity> {

    public void workorderAccept(String orderNo,String isAccept,String result,String recommendContact) {
        ApiHelper.getApiService().workorderAccept(UserHelper.getToken(), UserHelper.getUid(),orderNo,isAccept,result,recommendContact)
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
                        getV().workorderAcceptSuccess();
                    }
                });
    }

    public void customList() {
        ApiHelper.getApiService().customList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<CustomListModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<CustomListModel>> reuslt) {
                        getV().customListSuccess(reuslt.getRes());
                    }
                });
    }
}
