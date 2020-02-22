package com.mfzn.deepuses.present.jiagou;

import com.mfzn.deepuses.activity.jiagou.SearchKeywordActivity;
import com.mfzn.deepuses.activity.jiagou.ZuzhiJiagouActivity;
import com.mfzn.deepuses.model.jiagou.SearchKeywordModel;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

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
