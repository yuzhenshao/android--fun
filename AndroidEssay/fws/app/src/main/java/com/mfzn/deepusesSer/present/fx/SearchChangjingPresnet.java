package com.mfzn.deepusesSer.present.fx;

import com.mfzn.deepusesSer.activity.fx.SearchChangjingActivity;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.model.faxian.Videos;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchChangjingPresnet extends XPresent<SearchChangjingActivity> {

    public void searchZixun(String label) {
        ApiHelper.getApiService().videoList(UserHelper.getToken(), UserHelper.getUid(),"200",1,label)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<Videos>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<Videos> reuslt) {
                        getV().newsListSuccess(reuslt.getRes());
                    }
                });
    }
}
