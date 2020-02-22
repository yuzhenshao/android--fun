package com.mfzn.deepuses.present.fragment;

import com.mfzn.deepuses.activity.fx.ZixunDetailActivity;
import com.mfzn.deepuses.model.faxian.CommentBean;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ZixunDetailPresnet extends XPresent<ZixunDetailActivity> {

    public void addComment(String rowNum,String comment) {
        ApiHelper.getApiService().addComment(UserHelper.getToken(), UserHelper.getUid(),rowNum,comment)
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
                        getV().addCommentSuccess(reuslt);
                    }
                });
    }

    public void addhits(String rowNum) {
        ApiHelper.getApiService().addhits(UserHelper.getToken(), UserHelper.getUid(),rowNum)
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
//                        getV().addhitsSuccess(reuslt);
                    }
                });
    }

    public void commentList(String rowNum) {
        ApiHelper.getApiService().commentsList(UserHelper.getToken(), UserHelper.getUid(),rowNum)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CommentBean>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<CommentBean> reuslt) {
                        getV().commentListSuccess(reuslt);
                    }
                });
    }
}
