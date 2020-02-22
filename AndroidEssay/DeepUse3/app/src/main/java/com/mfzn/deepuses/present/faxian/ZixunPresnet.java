package com.mfzn.deepuses.present.faxian;

import com.mfzn.deepuses.fragment.fx.ZixunFragmentNew;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ZixunPresnet extends XPresent<ZixunFragmentNew> {

    public void newsList(Integer page) {
        ApiHelper.getApiService().newsList(UserHelper.getToken(), UserHelper.getUid(),"20",page,"")
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
