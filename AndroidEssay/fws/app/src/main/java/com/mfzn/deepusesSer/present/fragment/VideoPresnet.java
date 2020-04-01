package com.mfzn.deepusesSer.present.fragment;

import com.mfzn.deepusesSer.fragment.fx.VideoFragmentNew;
import com.mfzn.deepusesSer.fragment.fx.ZixunFragmentNew;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.model.faxian.Videos;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class VideoPresnet extends XPresent<VideoFragmentNew> {

    public void videoList(Integer page) {
        ApiHelper.getApiService().videoList(UserHelper.getToken(), UserHelper.getUid(),"20",page,null)
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
