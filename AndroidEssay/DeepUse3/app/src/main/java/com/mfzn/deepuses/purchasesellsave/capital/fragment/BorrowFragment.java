package com.mfzn.deepuses.purchasesellsave.capital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.bass.BasicListFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.BorrowListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.capital.activity.ActivityBorrowDetail;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.BorrowAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class BorrowFragment extends BasicListFragment<BorrowListResponse.ListBean.BorrowResponse> {

    private int type;

    public static BorrowFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(ParameterConstant.CAPITAL_TYPE, type);
        BorrowFragment fragment = new BorrowFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        type = getArguments().getInt(ParameterConstant.CAPITAL_TYPE);
        ApiServiceManager.getBorrowList(type)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<BorrowListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<BorrowListResponse> reuslt) {
                        BorrowListResponse response = reuslt.getRes();
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
        BorrowAdapter adapter= new BorrowAdapter(getActivity(), mSourceList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {

                Intent intent=new Intent(getActivity(), ActivityBorrowDetail.class);
                intent.putExtra(ParameterConstant.ORDER_ID,mSourceList.get(i).getBorrowID());
                startActivity(intent);
            }
        });
        return adapter;
    }
}
