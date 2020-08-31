package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse.ListBean.IncomeExpenseResponse;
import com.mfzn.deepuses.utils.DateUtils;

import butterknife.BindView;

public class IncomeExpenseDetailActivity extends BasicActivity {

    @BindView(R.id.order_num)
    EditText orderNumEdit;
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
    @BindView(R.id.remark)
    EditText remarkEdit;

    private IncomeExpenseResponse mIncomeExpenseResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIncomeExpenseResponse=(IncomeExpenseResponse)getIntent().getSerializableExtra(ParameterConstant.INCOME_EXPENSE_RESPONSE);
        mTitleBar.updateTitleBar("收支详情");
        orderNumEdit.setText(mIncomeExpenseResponse.getOrderNum());
        incomeExpenseTypeEdit.setText(mIncomeExpenseResponse.getDataType()==1?"收入":"支出");
        priceEdit.setText(mIncomeExpenseResponse.getMoney());
        accountEdit.setText(mIncomeExpenseResponse.getAccountName());
        typeEdit.setText(mIncomeExpenseResponse.getTypeName());
        orderTimeEdit.setText(DateUtils.longToString(mIncomeExpenseResponse.getDataTime()));
        dataUserNameEdit.setText(mIncomeExpenseResponse.getDataUserName());
        remarkEdit.setText(mIncomeExpenseResponse.getRemark());
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_income_expense_detail;
    }
}
