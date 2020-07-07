package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.slidemenu.MenuItemClickListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.IncomeExpenseTypeResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.IncomeExpenseAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author syz @date 2020-05-03
 */
public class IncomeExpenseActivity extends BasicListActivity<IncomeExpenseTypeResponse> {
    private boolean isAddIncome =true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("收支类别", R.mipmap.icon_titlebar_add);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getIncomeExpenseTypeListt()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<IncomeExpenseTypeResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<IncomeExpenseTypeResponse>> reuslt) {
                        refreshSource(reuslt.getRes());
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        IncomeExpenseAdapter mAdapter = new IncomeExpenseAdapter(mSourceList);
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
        DialogUtils.showEditDialog(this, "修改收支类别", "请输入类别名称", new
                OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                        EditText editText = viewHolder.getView(R.id.message);
                        IncomeExpenseTypeResponse otherCostResponse = mSourceList.get(index);
                        otherCostResponse.setTypeName(editText.getText().toString());
                        editIncomeExpenseType(otherCostResponse);
                    }
                });
    }


    private void showDeleteDialog(int index){
        DialogUtils.showConfirmDialog(this,"确定删除该类别",new OnViewClickListener(){
            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                deleteIncomeExpenseType(index);
            }
        });
    }
    protected void rightPressedAction() {

        new CustomDialog.Builder().setLayoutRes(R.layout.income_expense_dialog)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setWidth((int) (0.8 * DialogUtils.getDisplayMetrics(this).widthPixels))
                .addOnClickListener(R.id.cancel_btn, R.id.confirm_btn, R.id.income,R.id.expense)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment customDialog, BindViewHolder bindViewHolder, View view) {
                        if(view.getId()==R.id.cancel_btn){
                            if (customDialog != null) {
                                customDialog.dismiss();
                            }
                            return;
                        }

                        if(view.getId()==R.id.confirm_btn){
                            if (customDialog != null) {
                                customDialog.dismiss();
                            }
                            EditText editText = view.findViewById(R.id.message);
                            if(TextUtils.isEmpty(editText.getText())){
                                ToastUtil.showToast(IncomeExpenseActivity.this,"请输入收支类别名称");
                                return;
                            }
                            addIncomeExpenseType(editText.getText().toString());
                            return;
                        }

                        isAddIncome =view.getId()==R.id.income;
                        TextView income=view.findViewById(R.id.income);
                        TextView expense=view.findViewById(R.id.expense);
                        income.setTextColor(Color.parseColor(isAddIncome ?"#4A90E2":"#BFC2CC"));
                        GradientDrawable incomeBg=(GradientDrawable)income.getBackground();
                        incomeBg.setStroke(1,Color.parseColor(isAddIncome ?"#4A90E2":"#BFC2CC"));
                        expense.setTextColor(Color.parseColor(isAddIncome ?"#BFC2CC":"#4A90E2"));
                        GradientDrawable expenseBg=(GradientDrawable)expense.getBackground();
                        expenseBg.setStroke(1,Color.parseColor(isAddIncome ?"#BFC2CC":"#4A90E2"));

                    }
                }).create().show(getSupportFragmentManager(), getClass().getName());
    }

    public void deleteIncomeExpenseType(int index) {
        IncomeExpenseTypeResponse incomeExpenseResponse = mSourceList.get(index);
        if (incomeExpenseResponse != null) {
            ApiServiceManager.delIncomeExpenseType(incomeExpenseResponse.getTypeID())
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(IncomeExpenseActivity.this, "删除失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(IncomeExpenseActivity.this, "删除成功");
                            adapter.notifyItemRemoved(index);
                        }
                    });
        }
    }

    public void editIncomeExpenseType(IncomeExpenseTypeResponse otherCostResponse) {
        if (otherCostResponse != null) {
            ApiServiceManager.editIncomeExpenseType( otherCostResponse)
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(IncomeExpenseActivity.this, "修改失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(IncomeExpenseActivity.this, "修改成功");
                            getResourceList();
                        }
                    });
        }
    }

    private void addIncomeExpenseType(String name){
        ApiServiceManager.addIncomeExpenseType(isAddIncome?IncomeExpenseTypeResponse.INCOME:IncomeExpenseTypeResponse.EXPENSE, name)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(IncomeExpenseActivity.this, "新增失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(IncomeExpenseActivity.this, "新增成功");
                        getResourceList();
                    }
                });
    }
}
