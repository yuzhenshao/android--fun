package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.WorkorderListActivity;
import com.mfzn.deepuses.activityxm.shhf.VisitRecordActivity;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class WorkorderListPresent extends XPresent<WorkorderListActivity> {

    public void workorderList(String proID, String shType,Integer page) {
        ApiHelper.getApiService().workorderList(UserHelper.getToken(), UserHelper.getUid(),proID,shType,"10",page,"")
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

    public void getBrick() {
        ApiHelper.getApiService().getCompany(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CompanyInfoModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<CompanyInfoModel> reuslt) {
                        getV().getCompanySuccess(reuslt.getRes());
                    }
                });
    }

    //moduleType  1售后 2流程 3进销存
    public void openBk(String proId,String moduleType,String zhuanNum) {
        ApiHelper.getApiService().openBk(UserHelper.getToken(), UserHelper.getUid(),proId,moduleType,zhuanNum)
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
                        getV().openBk();
                    }
                });
    }
}
