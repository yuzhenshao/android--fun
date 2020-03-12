package com.mfzn.deepuses.present.faxian;


import com.mfzn.deepuses.fragment.fx.VideoFragmentNew;
import com.mfzn.deepuses.model.faxian.Videos;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

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
