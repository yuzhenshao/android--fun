package com.mfzn.deepuses.purchasesellsave.capital.fragment;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.bass.BasicListFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.ShouldGatherePayAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class ShouldGatherePayFragment extends BasicListFragment<PayerPayeeListResponse.ListBean.PayerPayeeResponse> {

    private int type;

    public static ShouldGatherePayFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(ParameterConstant.CAPITAL_TYPE, type);
        ShouldGatherePayFragment fragment = new ShouldGatherePayFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        type = getArguments().getInt(ParameterConstant.CAPITAL_TYPE);
        Flowable<HttpResult<PayerPayeeListResponse>> todoFloeable;
        if (type == 0) {
            todoFloeable = ApiServiceManager.shouldGatheringList();
        } else {
            todoFloeable = ApiServiceManager.shouldPayList();
        }
        todoFloeable.compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<PayerPayeeListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<PayerPayeeListResponse> reuslt) {
                        PayerPayeeListResponse response = reuslt.getRes();
                        if (response != null && response.getList() != null) {
                            refreshSource(response.getList().getData());
                            return;
                        }
                        showNoDataView();
                    }
                });

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ShouldGatherePayAdapter(getActivity(), type, mSourceList);
    }
}
