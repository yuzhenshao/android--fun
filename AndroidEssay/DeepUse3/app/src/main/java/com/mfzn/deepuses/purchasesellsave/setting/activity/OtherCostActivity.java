package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.slidemenu.MenuHelper;
import com.libcommon.slidemenu.MenuItemClickListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.OtherCostResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.OtherCostAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author syz @date 2020-05-03
 */
public class OtherCostActivity extends BasicListActivity<OtherCostResponse> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("其他费用", R.mipmap.icon_titlebar_add);
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getOtherCostList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<OtherCostResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<OtherCostResponse>> reuslt) {
                        refreshSource(reuslt.getRes());
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        OtherCostAdapter mAdapter = new OtherCostAdapter(mSourceList);

        MenuHelper.attach(recyclerView, new MenuHelper.MenuEnableDecider() {
            @Override
            public boolean enable(int position) {
                return true;
            }
        });

        mAdapter.setOnMenuItemClickListener(new MenuItemClickListener() {
            @Override
            public void onClick(int index, View view) {
                if (index >= 0 && index < mSourceList.size()) {
                    switch (view.getId()) {
                        case R.id.delete:
                            showDeleteDialog(index);
                            break;
                        case R.id.edit:
                            showEditDialog(index);
                            break;
                    }
                }
            }
        }, R.id.edit, R.id.delete);

        return mAdapter;
    }

    private void showEditDialog(int index) {
        DialogUtils.showEditDialog(this, "修改其他费用", "请输入费用名称", new
                OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                        EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                        editOtherCost(index, editText.getText().toString());
                    }
                });
    }


    private void showDeleteDialog(int index){
        DialogUtils.showConfirmDialog(this,"确定删除该费用",new OnViewClickListener(){
            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                deleteOtherCost(index);
            }
        });
    }

    protected void rightPressedAction() {
        DialogUtils.showEditDialog(this, "新增其他费用", "请输入费用名称", new
                OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                        EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                        addOtherCost(editText.getText().toString());
                    }
                });
    }

    public void deleteOtherCost(int index) {
        OtherCostResponse otherCostResponse = mSourceList.get(index);
        if (otherCostResponse != null) {
            ApiServiceManager.deleteOtherCost(otherCostResponse.getOtherCostTypeID())
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(OtherCostActivity.this, "删除失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(OtherCostActivity.this, "删除成功");
                            adapter.notifyItemRemoved(index);
                        }
                    });
        }
    }

    public void editOtherCost(int index, String name) {
        OtherCostResponse otherCostResponse = mSourceList.get(index);
        if (otherCostResponse != null) {
            ApiServiceManager.editOtherCost(name, otherCostResponse.getOtherCostTypeID())
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(OtherCostActivity.this, "修改失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(OtherCostActivity.this, "修改成功");
                            otherCostResponse.setOtherCostTypeName(name);
                            adapter.notifyItemRemoved(index);
                        }
                    });
        }
    }

    private void addOtherCost(String name){
        ApiServiceManager.addOtherCost(name)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(OtherCostActivity.this, "新增失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(OtherCostActivity.this, "新增成功");
                        getResourceList();
                    }
                });
    }

}
