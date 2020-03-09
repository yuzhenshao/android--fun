package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.fragment.GongzuoFragment;
import com.mfzn.deepuses.model.LookQuanxian2Model;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.home.JudgeLevelModel;
import com.mfzn.deepuses.model.home.KanbDataModel;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GongzuoPresnet extends XPresent<GongzuoFragment> {

    public void judgeLevel() {
        ApiHelper.getApiService().judgeLevel(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<JudgeLevelModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<JudgeLevelModel> result) {
                        getV().judgeLevelSuccess(result.getRes());
                    }
                });
    }

    public void kanbData() {
        ApiHelper.getApiService().kanbData(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<KanbDataModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<KanbDataModel> result) {
                        getV().kanbDataSuccess(result.getRes());
                    }
                });
    }

    public void companyList() {
        ApiHelper.getApiService().companyList(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<SelectCompanyModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<SelectCompanyModel>> result) {
                        getV().companyListSuccess(result.getRes());
                    }
                });
    }

    //用户信息
    public void userInfo() {
        ApiHelper.getApiService().appUserInfo(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<UserInfoModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<UserInfoModel> result) {
                        getV().userInfoSuccess(result.getRes());
                    }

                    @Override
                    public void onComplete() {
//                        getV().hideDialog();
                    }
                });
    }

    public void quanxian() {
        ApiHelper.getApiService().lookQuanxian2(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<LookQuanxian2Model>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<LookQuanxian2Model> result) {
                        getV().lookQxSuccess(result.getRes());
                    }
                });
    }

    public void quanxian2() {
        ApiHelper.getApiService().lookQuanxian2(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<LookQuanxian2Model>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<LookQuanxian2Model> result) {
                        getV().lookQxSuccess2(result.getRes());
                    }
                });
    }
}
