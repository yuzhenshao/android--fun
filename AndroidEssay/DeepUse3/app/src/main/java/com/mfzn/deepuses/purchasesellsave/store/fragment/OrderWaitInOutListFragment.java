package com.mfzn.deepuses.purchasesellsave.store.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bass.BasicListFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.store.WaitingInOutListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.activity.AddOrderStockCheckActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.WaitingInOutDetailActivity;
import com.mfzn.deepuses.purchasesellsave.store.adapter.OrderWaitInOutAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class OrderWaitInOutListFragment extends BasicListFragment<WaitingInOutListResponse.WaitingInOutResponse> {

    private int type;

    public static OrderWaitInOutListFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(ParameterConstant.CAPITAL_TYPE, type);
        OrderWaitInOutListFragment fragment = new OrderWaitInOutListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        type = getArguments().getInt(ParameterConstant.CAPITAL_TYPE);
        Flowable<HttpResult<WaitingInOutListResponse>> todoFloeable;
        if (type == 0) {
            todoFloeable = ApiServiceManager.getWaitingOutList();
        } else {
            todoFloeable = ApiServiceManager.getWaitingInList();
        }
        todoFloeable.compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<WaitingInOutListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<WaitingInOutListResponse> reuslt) {
                        WaitingInOutListResponse response = reuslt.getRes();
                        if (response != null && !ListUtil.isEmpty(response.getData())) {
                            refreshSource(response.getData());
                            return;
                        }
                        showNoDataView();
                    }
                });

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        OrderWaitInOutAdapter adapter = new OrderWaitInOutAdapter(getActivity(), mSourceList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(getActivity(), WaitingInOutDetailActivity.class);
                intent.putExtra(ParameterConstant.ORDER_ID, mSourceList.get(i).getDataID());
                intent.putExtra(ParameterConstant.GOODS_ID, mSourceList.get(i).getOrderNum());
                intent.putExtra(ParameterConstant.CAPITAL_TYPE, type);
                startActivity(intent);
            }
        });
        return adapter;
    }
}
