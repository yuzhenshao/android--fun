package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.fx.ZixunFragment;
import com.mfzn.deepusesSer.fragment.fx.ZixunFragmentNew;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ZixunPresnet extends XPresent<ZixunFragmentNew> {

    public void newsList(Integer page) {
        ApiHelper.getApiService().newsList(UserHelper.getToken(), UserHelper.getUid(),"20",page,null)
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
