package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.SearchOrderActivity;
import com.mfzn.deepuses.bean.request.AfterSaleOrderListRequest;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchOrderPresnet extends XPresent<SearchOrderActivity> {

    public void workorderList(String keyword) {
        AfterSaleOrderListRequest request=new AfterSaleOrderListRequest(100,1);
        request.setKeywords(keyword);
        ApiServiceManager.afterSaleOrderList(request)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<WorkorderListModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<WorkorderListModel> reuslt) {
                        getV().workorderListSuccess(reuslt.getRes());
                    }
                });
    }

    public void quanxian(String proID) {
        ApiHelper.getApiService().lookQuanxian(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(),proID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<LookQuanxianModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<LookQuanxianModel> result) {
                        getV().lookQxSuccess(result.getRes());
                    }
                });
    }
}
