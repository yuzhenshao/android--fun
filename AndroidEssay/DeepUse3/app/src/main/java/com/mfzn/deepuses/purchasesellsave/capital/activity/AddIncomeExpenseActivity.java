package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.capital.AddIncomeExpenseRequest;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.IncomeExpenseTypeSelectedActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddIncomeExpenseActivity extends BasicActivity {

    @BindView(R.id.income_expense_type)
    EditText incomeExpenseTypeEdit;
    @BindView(R.id.price)
    EditText priceEdit;
    @BindView(R.id.account)
    EditText accountEdit;
    @BindView(R.id.type)
    EditText typeEdit;
    @BindView(R.id.order_time)
    EditText orderTimeEdit;
    @BindView(R.id.data_user_name)
    EditText dataUserNameEdit;
    @BindView(R.id.project)
    EditText projectEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;

    private final static int ACCOUNT = 101;
    private final static int PROJECT = 102;
    private final static int TYPE = 103;
    private AddIncomeExpenseRequest request = new AddIncomeExpenseRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新增收支");
        dataUserNameEdit.setText(UserHelper.getU_name());
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_income_expense;
    }


    @OnClick({R.id.income_expense_type_select, R.id.type_select, R.id.account_select, R.id.order_time_select, R.id.btn_commit, R.id.project_select})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.income_expense_type_select:
                List<String> typeList = new ArrayList<>();
                typeList.add("收入");
                typeList.add("支出");
                PickerDialogView.showGoodSPosition(this,
                        typeList, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                incomeExpenseTypeEdit.setText(typeList.get(options1));
                                request.setType(options1 + 1);
                            }
                        });
                break;
            case R.id.account_select:
                intent.setClass(this, MoneyAccountListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, ACCOUNT);
                break;
            case R.id.type_select:
                if(request.getType()==0){
                    showToast("请选择新增收入或支出");
                    return;
                }
                intent.setClass(this, IncomeExpenseTypeSelectedActivity.class);
                intent.putExtra(ParameterConstant.CAPITAL_TYPE,request.getType());
                startActivityForResult(intent, TYPE);
                break;
            case R.id.order_time_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        request.setDataTime((date.getTime() / 1000));
                        orderTimeEdit.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
            case R.id.project_select:
                intent.setClass(this, ProjectManageActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, PROJECT);
                break;
            case R.id.btn_commit:
                onCommitAction();
                break;
        }
    }

    private void onCommitAction() {
        request.setMoney(priceEdit.getText().toString());
        request.setDataUserID(UserHelper.getUserId());
        request.setRemark(remarkEdit.getText().toString());

        if (request.getType() == 0) {
            showToast("请输入收支类型");
            return;
        }
        if (TextUtils.isEmpty(request.getMoney())) {
            showToast("请输入收支金额");
            return;
        }
        if (TextUtils.isEmpty(request.getTypeID())) {
            showToast("请输入收支类别");
            return;
        }
        if (TextUtils.isEmpty(request.getMoneyAccountID())) {
            showToast("请输入收支账户");
            return;
        }
        if (request.getDataTime() == 0) {
            showToast("请输入单据日期");
            return;
        }
        ApiServiceManager.addIncomeExpense(request)
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
                        showToast("新增成功");
                        finish();
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == ACCOUNT) {
                request.setMoneyAccountID(data.getStringExtra("Id"));
                accountEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == TYPE) {
                request.setTypeID(data.getStringExtra("Id"));
                typeEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == PROJECT) {
                request.setProID(data.getStringExtra("Id"));
                projectEdit.setText(data.getStringExtra("Name"));
            }
        }
    }
}
