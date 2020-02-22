package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.shgd.SelectCustomActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderAcceptActivity;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SelectCustomPresnet extends XPresent<SelectCustomActivity> {

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
