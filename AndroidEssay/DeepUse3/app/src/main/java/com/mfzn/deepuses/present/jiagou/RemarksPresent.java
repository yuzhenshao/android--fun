package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.RemarksAtivity;
import com.mfzn.deepuses.activity.jiagou.ZuzhiJiagouActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class RemarksPresent extends XPresent<RemarksAtivity> {

    public void remarks(String remarkName, String staffUID) {
        ApiHelper.getApiService().remarks(UserHelper.getToken(), UserHelper.getUid(),remarkName,UserHelper.getCompanyId(),staffUID)
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
                        getV().remarksSuccess();
                    }
                });
    }
}
