package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseAddCustomerAndGoodsActivity extends BasicActivity {

    protected final static int USER = 1;
    protected final static int GOODS = 2;
    protected final static int COST = 3;
    protected String otherCostStr;
    protected String orderGoodsStr;
    protected String companyCustomerID;
    protected long orderTime;

    protected List<GoodsInfoResponse> goodsSelectedList = new ArrayList<>();
    protected GoodsAdapter adapter;

    @BindView(R.id.goods_price_container)
    public RelativeLayout goodsPriceContainer;
    @BindView(R.id.number)
    public TextView number;
    @BindView(R.id.price)
    public TextView price;
    @BindView(R.id.goods_recyleview)
    RecyclerView goodsRecyclerView;

    @BindView(R.id.order_time)
    public EditText orderTimeEdit;
    @BindView(R.id.user_name)
    public TextView userNameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userNameView.setText(UserHelper.getU_name());
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoodsAdapter(this, goodsSelectedList);
        goodsRecyclerView.setAdapter(adapter);
    }

    protected void turnToCustomer() {
        Intent intent = new Intent();
        intent.setClass(this, MyCustomerSelectActivity.class);
        startActivityForResult(intent, USER);
    }

    protected void turnToCostSelect() {
        Intent intent1 = new Intent();
        intent1.setClass(this, OtherCostSelectActivity.class);
        startActivityForResult(intent1, COST);
    }

    @OnClick(R.id.order_time_select)
    protected void showDatePickerView() {
        PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                orderTime = date.getTime();
                orderTimeEdit.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
            }
        });
    }


    @OnClick(R.id.goods_select)
    protected void turnToGoodsSelected() {
        Intent intent = new Intent();
        intent.setClass(this, GoodsSelectListActivity.class);
        startActivityForResult(intent, GOODS);
    }

    @OnClick(R.id.btn_commit)
    protected abstract void commitAction();


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == GOODS) {
                goodsPriceContainer.setVisibility(View.VISIBLE);
                goodsSelectedList.clear();
                goodsSelectedList.addAll((List<GoodsInfoResponse>) data.getSerializableExtra("data"));
                adapter.notifyDataSetChanged();
                number.setText(data.getStringExtra("goodsSize"));
                price.setText(data.getStringExtra("totalPrice"));

            }
        }
    }

    /*
     * goodsID1（商品ID）,goodsNum1（商品数量）,uniformSalePrice1（零售价）
     * ,$salePrice1（销售单价不含税）,$taxRate1（税率）,$salePriceWithTax1（销售价含税）,
     * $money1（金额：数量*销售单价）;*/
    protected String getOrderGoodsStr() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!ListUtil.isEmpty(goodsSelectedList)) {
            for (GoodsInfoResponse goods : goodsSelectedList) {
                stringBuffer.append(goods.getGoodsID()).append(",")
                        .append(goods.getGoodsCount()).append(",")
                        .append(goods.getSalePrice()).append(",")
                        .append(goods.getSalePrice()).append(",")
                        .append(goods.getTaxRate()).append(",")
                        .append(goods.getSalePriceWithTax()).append(",")
                        .append(goods.getGoodsCount() * getPrice(goods.getSalePrice())).append(";");
            }
        }
        return stringBuffer.toString();
    }


    protected double getPrice(String salePrice) {
        try {
            if (!TextUtils.isEmpty(salePrice)) {
                return Double.parseDouble(salePrice);
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

}
