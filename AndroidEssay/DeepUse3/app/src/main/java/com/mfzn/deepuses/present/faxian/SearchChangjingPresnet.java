package com.mfzn.deepuses.present.faxian;

import com.mfzn.deepuses.activity.fx.SearchChangjingActivity;
import com.mfzn.deepuses.activity.fx.SearchZixunActivity;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.model.faxian.Videos;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchChangjingPresnet extends XPresent<SearchChangjingActivity> {

    // type 1 新闻 2 视频
    public void searchZixun(String label) {
        ApiHelper.getApiService().searchZixun(UserHelper.getToken(), UserHelper.getUid(),"200",1,label,"2")
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
