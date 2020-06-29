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

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseAddCustomerAndGoodsActivity extends BasicActivity {

    private final static int USER = 1;
    private final static int GOODS = 2;
    private final static int COST = 3;
    protected String otherCostStr;
    protected String orderGoodsStr;
    protected String companyCustomerID;

    private List<GoodsInfoResponse> goodsSelectedList = new ArrayList<>();
    private GoodsAdapter adapter;
    @BindView(R.id.customer)
    EditText customerEdit;
    @BindView(R.id.goods)
    EditText goods;
    @BindView(R.id.other_cost)
    EditText otherCostEdit;
    @BindView(R.id.goods_price_container)
    RelativeLayout goodsPriceContainer;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.goods_recyleview)
    RecyclerView goodsRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoodsAdapter(this, goodsSelectedList);
        goodsRecyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.customer_select, R.id.goods_select, R.id.other_cost_select, R.id.btn_commit})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.customer_select:
                intent.setClass(this, MyCustomerSelectActivity.class);
                startActivityForResult(intent, USER);
                break;
            case R.id.goods_select:
                intent.setClass(this, GoodsSelectListActivity.class);
                startActivityForResult(intent, GOODS);
                break;
            case R.id.other_cost_select:
                intent.setClass(this, OtherCostSelectActivity.class);
                startActivityForResult(intent, COST);
                break;
            case R.id.btn_commit:
                commitAction();
                break;
        }
    }

    protected abstract void commitAction();


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == USER) {
                companyCustomerID=data.getStringExtra("Id");
                customerEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == GOODS) {
                goodsPriceContainer.setVisibility(View.VISIBLE);
                orderGoodsStr=data.getStringExtra("orderGoodsStr");
                goodsSelectedList.clear();
                goodsSelectedList.addAll((List<GoodsInfoResponse>) data.getSerializableExtra("data"));
                adapter.notifyDataSetChanged();
                number.setText(data.getStringExtra("goodsSize"));
                price.setText(data.getStringExtra("totalPrice"));

            } else if (requestCode == COST) {
                otherCostStr = data.getStringExtra("data");
                otherCostEdit.setText(TextUtils.isEmpty(otherCostStr) ? "" : "已填写");
            }
        }
    }

}
