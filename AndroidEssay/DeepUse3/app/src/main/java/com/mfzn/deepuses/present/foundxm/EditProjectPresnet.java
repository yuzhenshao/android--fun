package com.mfzn.deepuses.present.foundxm;

import com.mfzn.deepuses.activity.project.EditProjectActivity;
import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.model.xiangmu.FoundProjectModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class EditProjectPresnet extends XPresent<EditProjectActivity> {

    public void editProject(String proID,String pro_name,String latitude,String longitude,String detailAddress,
                             String sales_people,String contractMoney,String customLevel,String areaName,String qualityTime,
                            String qualityBegin,String qualityEnd,String qualityRing,String customerIDs) {
        ApiHelper.getApiService().editProject(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),proID,pro_name,latitude
                ,longitude,detailAddress,sales_people,contractMoney,customLevel,areaName,
                qualityTime,qualityBegin,qualityEnd,qualityRing,"2",customerIDs)
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
                        getV().editProjectSuccess();
                    }
                });
    }
}
