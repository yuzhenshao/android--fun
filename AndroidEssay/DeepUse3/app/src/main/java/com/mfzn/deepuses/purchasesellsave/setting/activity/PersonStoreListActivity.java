package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.sale.PersonalStoreListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.PersonStoreAdapter;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PersonStoreListActivity extends BasicListActivity<PersonalStoreListResponse.PersonalStoreResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("个人仓库");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getPersonalStoreList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<PersonalStoreListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<PersonalStoreListResponse> result) {
                        if (result.getRes() != null) {
                            if (!ListUtil.isEmpty(result.getRes().getData())) {
                                refreshSource(result.getRes().getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        PersonStoreAdapter mAdapter = new PersonStoreAdapter(this, mSourceList);
        return mAdapter;
    }
}
