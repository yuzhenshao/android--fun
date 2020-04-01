package com.mfzn.deepusesSer.present.jiagou;

import com.mfzn.deepusesSer.activity.jiagou.SearchKeywordActivity;
import com.mfzn.deepusesSer.model.jiagou.SearchKeywordModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SearchKeywordPresent extends XPresent<SearchKeywordActivity> {

    public void searchKeyword(String keywords) {
        ApiHelper.getApiService().jiagouList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),keywords)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<SearchKeywordModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult<List<SearchKeywordModel>> result) {
                        getV().searchKeywordSuccess(result.getRes());
                    }
                });
    }
}
