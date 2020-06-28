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
import com.mfzn.deepuses.purchasesellsave.setting.adapter.InventorySetAdapter;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-30
 */
public class InventorySetActivity extends BasicListActivity<StoreResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("库存");
    }

    @Override
    protected void getResourceList() {
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
        InventorySetAdapter mAdapter = new InventorySetAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                setStoreNumber(mSourceList.get(i));
            }
        });
        return mAdapter;
    }

    public void setStoreNumber(StoreResponse storeResponse) {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_set_inventory)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
                .setDialogAnimationRes(R.style.ActionSheetDialogAnimation)
                .addOnClickListener(R.id.btn_commit)
                .setOnViewClickListener((customDialog, bindViewHolder, view) -> {
                    EditText editText = bindViewHolder.getView(R.id.store_number_edit);
                    if (TextUtils.isEmpty(editText.getText())) {
                        ToastUtil.showToast(InventorySetActivity.this, "请输入库存");
                    } else {
                        JXCDataManager.getInstance().addStoreSet(storeResponse.getStoreID(), editText.getText().toString());
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                        adapter.notifyDataSetChanged();
                    }

                }).create().show(getSupportFragmentManager(), getClass().getName());
    }
}
