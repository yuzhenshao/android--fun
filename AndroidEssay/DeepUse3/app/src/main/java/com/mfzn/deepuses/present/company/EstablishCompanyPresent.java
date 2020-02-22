package com.mfzn.deepuses.present.company;

import com.mfzn.deepuses.activity.company.EstablishCompanyActivity;
import com.mfzn.deepuses.activity.company.SelectLableActivity;
import com.mfzn.deepuses.model.company.CityModel;
import com.mfzn.deepuses.model.company.SelectLableModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EstablishCompanyPresent extends XPresent<EstablishCompanyActivity> {

    public void establishCompany(String companyName, String areaID, String businessScope,String longitude,String latitude,String companyAddress) {
        ApiHelper.getApiService().establishCompany(UserHelper.getToken(), UserHelper.getUid(),companyName,areaID,businessScope,
                longitude,latitude,companyAddress)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().establishCompanySuccess(result.getRes().toString());
                    }
                });
    }

    //获取省市区ID
    public void commitAddress(String province_name, String city_name, String area_name) {
        ApiHelper.getApiService().getAreaID(province_name, city_name,area_name)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CityModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<CityModel> result) {
                        getV().setAddress(result.getRes().getAreaid());
                    }
                });
    }
}
