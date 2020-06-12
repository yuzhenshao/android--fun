package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.MyCustomerActivity;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.request.sale.OrderOfferRequest;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.OtherCostActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SetCostActivity;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class AddOrderOfferActivity extends BasicActivity {

    private final static int USER = 1;
    private final static int GOODS = 2;
    private final static int COST = 3;

    @BindView(R.id.customer)
    EditText customer;
    @BindView(R.id.goods)
    EditText goods;
    @BindView(R.id.other_cost)
    EditText otherCost;
    @BindView(R.id.discount_price)
    EditText discountPrice;
    @BindView(R.id.total_price)
    EditText totalPrice;
    @BindView(R.id.date)
    EditText orderTime;
    @BindView(R.id.out_num)
    EditText outNum;
    @BindView(R.id.remark)
    EditText remark;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.goods_price_container)
    RelativeLayout goodsPriceContainer;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.price)
    TextView price;

    private OrderOfferRequest orderOfferRequest = new OrderOfferRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建报价单");
    }

    @OnClick({R.id.customer_select, R.id.goods_select, R.id.other_cost_select, R.id.date_select})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.customer_select:
                intent.setClass(AddOrderOfferActivity.this, MyCustomerActivity.class);
                startActivityForResult(intent, USER);
                break;
            case R.id.goods_select:
                intent.setClass(AddOrderOfferActivity.this, GoodsSelectListActivity.class);
                startActivityForResult(intent, GOODS);
                break;
            case R.id.other_cost_select:
                intent.setClass(AddOrderOfferActivity.this, OtherCostActivity.class);
                startActivityForResult(intent, COST);
                break;
            case R.id.date_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        orderTime.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == USER) {
                orderOfferRequest.setCompanyCustomerID(data.getStringExtra("Id"));
                customer.setText(data.getStringExtra("Name"));
            } else if (requestCode == GOODS) {
                orderOfferRequest.setOrderGoodsStr(data.getStringExtra("Name"));
            } else if (requestCode == COST) {
                orderOfferRequest.setOtherCostStr(data.getStringExtra("Name"));
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_offer_create;
    }

}
