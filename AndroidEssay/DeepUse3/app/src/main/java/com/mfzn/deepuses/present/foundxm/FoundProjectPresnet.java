package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.fragment.XiangmuFragment;
import com.mfzn.deepuses.model.xiangmu.FoundProjectModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class FoundProjectPresnet extends XPresent<FoundProjectActivity> {

    public void foundProject(String pro_name,String latitude,String longitude,String detailAddress,String customName,String customTel,
                             String sales_people,String contractMoney,String customLevel,String areaName,String qualityTime,
                             String qualityBegin,String qualityEnd,String qualityRing,String companyCustomerID) {
        ApiHelper.getApiService().foundProject(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),pro_name,latitude
                ,longitude,detailAddress,customName,customTel,sales_people,contractMoney,customLevel,areaName,
                qualityTime,qualityBegin,qualityEnd,qualityRing,"2",companyCustomerID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<FoundProjectModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<FoundProjectModel> reuslt) {
                        getV().foundProjectSuccess(reuslt.getRes());
                    }
                });
    }
}
