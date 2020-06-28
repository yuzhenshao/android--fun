package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.fragment.CustomDialog;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.InventorySetWarnAdapter;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-30
 */
public class InventoryWarnSetActivity extends BasicListActivity<StoreResponse> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("设置库存预警");
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getStoreList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<StoreResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<StoreResponse>> reuslt) {
                        refreshSource(reuslt.getRes());
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        InventorySetWarnAdapter mAdapter = new InventorySetWarnAdapter(mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showStoreNumberSetDialog(mSourceList.get(position).getStoreID());
            }
        });
        return mAdapter;
    }

    public void showStoreNumberSetDialog(String storeId) {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_set_warn_inventory)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
                .setDialogAnimationRes(R.style.ActionSheetDialogAnimation)
                .addOnClickListener(R.id.btn_commit)
                .setOnViewClickListener((customDialog, bindViewHolder, view) -> {
                    EditText minEditText = bindViewHolder.getView(R.id.store_min_edit);
                    EditText maxEditText = bindViewHolder.getView(R.id.store_max_edit);
                    if (TextUtils.isEmpty(minEditText.getText()) || TextUtils.isEmpty(maxEditText.getText())) {
                        ToastUtil.showToast(InventoryWarnSetActivity.this, "请设置库存量");
                    } else {
                        JXCDataManager.getInstance().addStoreSet(storeId, maxEditText.getText().toString(),
                                minEditText.getText().toString());
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                        adapter.notifyDataSetChanged();
                    }

                }).create().show(getSupportFragmentManager(), getClass().getName());
    }
}
