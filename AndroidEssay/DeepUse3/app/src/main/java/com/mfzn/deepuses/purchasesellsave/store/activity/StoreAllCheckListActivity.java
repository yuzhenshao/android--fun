package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.slidemenu.MenuHelper;
import com.libcommon.slidemenu.MenuItemClickListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.store.StoreAllCheckListResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsListActivity;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StoreAllCheckAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreAllCheckListActivity extends BasicListActivity<StoreCheckResponse> {
    private static int REQUESTCODE = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("全库盘点", R.mipmap.icon_titlebar_add);
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getSoreAllCheckList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<StoreAllCheckListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<StoreAllCheckListResponse> reuslt) {
                        StoreAllCheckListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (response.getData() != null) {
                                refreshSource(response.getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        StoreAllCheckAdapter mAdapter = new StoreAllCheckAdapter(this, mSourceList);
        MenuHelper.attach(recyclerView, new MenuHelper.MenuEnableDecider() {
            @Override
            public boolean enable(int position) {
                return true;
            }
        });

        mAdapter.setOnMenuItemClickListener(new MenuItemClickListener() {
            @Override
            public void onClick(int index, View view) {
                showDeleteDialog(index);
            }
        }, R.id.cancel);
        return mAdapter;
    }

    private void showDeleteDialog(int index) {
        DialogUtils.showConfirmDialog(this, "确定删除该商品？", new OnViewClickListener() {
            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                deleteGoods(index);
            }
        });
    }

    public void deleteGoods(int index) {
        StoreCheckResponse storeCheckResponse = mSourceList.get(index);
        if (storeCheckResponse != null) {
            ApiServiceManager.storeAllCheckDel(storeCheckResponse.getOrderID())
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            showToast(error.getMessage());
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            showToast("删除成功");
                            mSourceList.remove(index);
                            adapter.notifyItemRemoved(index);
                        }
                    });
        }
    }

    protected void rightPressedAction() {
        startActivityForResult(new Intent(this, StoreAllCheckSponsorActivity.class), REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && requestCode == RESULT_OK) {
            getResourceList();
        }
    }
}