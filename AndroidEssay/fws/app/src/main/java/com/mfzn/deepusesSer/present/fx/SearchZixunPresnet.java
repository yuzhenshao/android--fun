package com.mfzn.deepusesSer.present.fx;

import com.mfzn.deepusesSer.activity.fx.SearchZixunActivity;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchZixunPresnet extends XPresent<SearchZixunActivity> {

    // type 1 新闻 2 视频
    public void searchZixun(String label) {
        ApiHelper.getApiService().newsList(UserHelper.getToken(), UserHelper.getUid(), "200", 1, label)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<News>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<News> reuslt) {
                        getV().newsListSuccess(reuslt.getRes());
                    }
                });
    }
}
