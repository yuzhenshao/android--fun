package com.mfzn.deepuses.present.faxian;

import com.mfzn.deepuses.activity.fx.SearchZixunActivity;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchZixunPresnet extends XPresent<SearchZixunActivity> {

    // type 1 新闻 2 视频
    public void searchZixun(String label) {
        ApiServiceManager.newsList("200",1,label)
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
