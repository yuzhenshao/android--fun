package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.IncomeExpenseFragment;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.IncomeExpenseTypeFragment;
import com.mfzn.deepuses.utils.ToastUtil;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;


public class IncomeExpenseTypeListActivity extends BaseTabActivity {
    private IncomeExpenseTypeFragment mIncomeExpenseTypeFragment;
    private IncomeExpenseTypeFragment mOutcomeExpenseTypeFragment;

    @Override
    protected void initTabLabelList() {
        mTitleBar.updateTitleBar("收支类别管理", R.mipmap.icon_titlebar_add);
        mTabLabelList.add(new TabLabel("收入"));
        mTabLabelList.add(new TabLabel("支出"));
        mIncomeExpenseTypeFragment = IncomeExpenseTypeFragment.newInstance(IncomeExpenseListResponse.INCOME);
        mOutcomeExpenseTypeFragment = IncomeExpenseTypeFragment.newInstance(IncomeExpenseListResponse.EXPENSE);
    }

    @Override
    protected BaseTabAdapter getAdapter() {
        return new BaseTabAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return i == 0 ? mIncomeExpenseTypeFragment : mOutcomeExpenseTypeFragment;
            }
        };
    }

    @Override
    protected void rightPressedAction() {
        DialogUtils.showEditDialog(this, "新增收入类别", "请输入类别名称", new
                OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                        EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                        addIncomeExpenseType(editText.getText().toString());
                    }
                });
    }


    private void addIncomeExpenseType(String name) {
        ApiServiceManager.addIncomeExpenseType(curPosition == 0 ? IncomeExpenseListResponse.INCOME : IncomeExpenseListResponse.EXPENSE, name)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(IncomeExpenseTypeListActivity.this, "新增失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(IncomeExpenseTypeListActivity.this, "新增成功");
                        if (curPosition == 0) {
                            mIncomeExpenseTypeFragment.refresh();
                        } else {
                            mOutcomeExpenseTypeFragment.refresh();
                        }
                    }
                });
    }
}