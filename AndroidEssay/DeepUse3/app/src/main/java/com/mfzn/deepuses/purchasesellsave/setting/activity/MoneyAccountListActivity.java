package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
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
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import com.mfzn.deepuses.bean.response.settings.MoneyAccountListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.capital.activity.MoneyAccountFinancialLogActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.MoneyTransferActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.MoneyAccountAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MoneyAccountListActivity extends BasicListActivity<MoneyAccountListResponse.AccountResponse> {

    private boolean isSelected;
    private static int REFRESH_CODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isSelected = getIntent().getBooleanExtra(ParameterConstant.IS_SELECTED, false);
        if (isSelected) {
            mTitleBar.updateTitleBar("结算账户");
        } else {
            mTitleBar.updateTitleBar("结算账户", R.mipmap.icon_titlebar_add);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_list_view;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getMoneyAccountList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MoneyAccountListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<MoneyAccountListResponse> reuslt) {
                        MoneyAccountListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (!ListUtil.isEmpty(response.getList())) {
                                refreshSource(response.getList());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        MoneyAccountAdapter mAdapter = new MoneyAccountAdapter(mSourceList);
        MenuHelper.attach(recyclerView, new MenuHelper.MenuEnableDecider() {
            @Override
            public boolean enable(int position) {
                return !isSelected;
            }
        });

        mAdapter.setOnMenuItemClickListener(new MenuItemClickListener() {
            @Override
            public void onClick(int index, View view) {
                showDeleteDialog(index);
            }
        }, R.id.cancel);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                MoneyAccountListResponse.AccountResponse accountResponse = mSourceList.get(i);
                if (isSelected) {
                    Intent intent = new Intent();
                    intent.putExtra("Id", accountResponse.getAccountID());
                    intent.putExtra("Name", accountResponse.getAccountName());
                    intent.putExtra("Money", accountResponse.getNowMoney());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    if (view.getId() == R.id.financial_log) {
                        Intent intent = new Intent(MoneyAccountListActivity.this, MoneyAccountFinancialLogActivity.class);
                        intent.putExtra(ParameterConstant.ACCOUNT_ID, accountResponse.getAccountID());
                        intent.putExtra(ParameterConstant.ACCOUNT_NAME, accountResponse.getAccountName());
                        startActivity(intent);
                    } else if (view.getId() == R.id.money_transfer) {
                        Intent intent = new Intent(MoneyAccountListActivity.this, MoneyTransferActivity.class);
                        intent.putExtra(ParameterConstant.ACCOUNT_ID, accountResponse.getAccountID());
                        intent.putExtra(ParameterConstant.ACCOUNT_NAME, accountResponse.getAccountName());
                        intent.putExtra(ParameterConstant.ACCOUNT_MONEY, accountResponse.getNowMoney());
                        startActivity(intent);
                    }
                }
            }
        });


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                MoneyAccountListResponse.AccountResponse accountResponse = mSourceList.get(i);
                Intent intent = new Intent();
                intent.putExtra("Id", accountResponse.getAccountID());
                intent.putExtra("Name", accountResponse.getAccountName());
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
        return mAdapter;
    }


    private void showDeleteDialog(int index) {
        DialogUtils.showConfirmDialog(this, "确定删除该账户？", new OnViewClickListener() {
            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                deleteCategoryUnit(index);
            }
        });
    }

    public void deleteCategoryUnit(int index) {
        MoneyAccountListResponse.AccountResponse accountResponse = mSourceList.get(index);
        if (accountResponse != null) {
            ApiServiceManager.delMoneyAccount(accountResponse.getAccountID())
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

    @Override
    protected void rightPressedAction() {
        startActivityForResult(new Intent(MoneyAccountListActivity.this, AddMoneyAccountActivity.class), REFRESH_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REFRESH_CODE && resultCode == Activity.RESULT_OK) {
            getResourceList();
        }
    }
}
