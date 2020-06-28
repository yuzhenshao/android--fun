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
import com.mfzn.deepuses.activity.khgl.MyCustomerActivity;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.request.sale.OrderOfferRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.OtherCostActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SetCostActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SettingModuleAdapter;
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
    TextView orderTime;
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
    @BindView(R.id.goods_recyleview)
    RecyclerView goodsRecyclerView;

    private OrderOfferRequest orderOfferRequest = new OrderOfferRequest();
    private List<GoodsInfoResponse> goodsSelectedList = new ArrayList<>();
    private GoodsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建报价单");
        userName.setText(UserHelper.getU_name());
        orderTime.setText(DateUtils.getDateFromMillsec(System.currentTimeMillis()));
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoodsAdapter(this, goodsSelectedList);
        goodsRecyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.customer_select, R.id.goods_select, R.id.other_cost_select, R.id.btn_commit})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.customer_select:
                intent.setClass(AddOrderOfferActivity.this, MyCustomerSelectActivity.class);
                startActivityForResult(intent, USER);
                break;
            case R.id.goods_select:
                intent.setClass(AddOrderOfferActivity.this, GoodsSelectListActivity.class);
                startActivityForResult(intent, GOODS);
                break;
            case R.id.other_cost_select:
                intent.setClass(AddOrderOfferActivity.this, OtherCostSelectActivity.class);
                startActivityForResult(intent, COST);
                break;
            case R.id.btn_commit:
                addOrderOffer();
                break;
        }
    }

    private void addOrderOffer() {
        String mTotalPrice = totalPrice.getText().toString();
        String mdiscountPrice = discountPrice.getText().toString();
        if (TextUtils.isEmpty(mTotalPrice)) {
            showToast("请输入单据总价格");
            return;
        }
        if (TextUtils.isEmpty(mdiscountPrice)) {
            showToast("请输入优惠金额");
            return;
        }
        orderOfferRequest.setDiscountAmount(mdiscountPrice);
        orderOfferRequest.setTotalMoney(mTotalPrice);
        orderOfferRequest.setRealMoney(Integer.parseInt(mTotalPrice) - Integer.parseInt(mdiscountPrice) + "");
        orderOfferRequest.setOrderTime(System.currentTimeMillis());
        orderOfferRequest.setOutNum(outNum.getText().toString());
        orderOfferRequest.setOrderMakerUserID(UserHelper.getUid());
        orderOfferRequest.setRemark(remark.getText().toString());
        if (TextUtils.isEmpty(outNum.getText().toString())) {
            showToast("请输入外部报价单号");
            return;
        }

        if (TextUtils.isEmpty(orderOfferRequest.getOrderMakerUserID())) {
            showToast("请输入公司客户");
            return;
        }
        if (TextUtils.isEmpty(orderOfferRequest.getOtherCostStr())) {
            showToast("请输入其他费用信息");
            return;
        }
        if (TextUtils.isEmpty(orderOfferRequest.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }
        ApiServiceManager.addOrderOffer(orderOfferRequest)
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
                        showToast("新建成功");
                        finish();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == USER) {
                orderOfferRequest.setCompanyCustomerID(data.getStringExtra("Id"));
                customer.setText(data.getStringExtra("Name"));
            } else if (requestCode == GOODS) {
                goodsPriceContainer.setVisibility(View.VISIBLE);
                orderOfferRequest.setOrderGoodsStr(data.getStringExtra("orderGoodsStr"));
                goodsSelectedList.clear();
                goodsSelectedList.addAll((List<GoodsInfoResponse>) data.getSerializableExtra("data"));
                adapter.notifyDataSetChanged();
                number.setText(data.getStringExtra("goodsSize"));
                price.setText(data.getStringExtra("totalPrice"));

            } else if (requestCode == COST) {
                String otherCostStr = data.getStringExtra("data");
                orderOfferRequest.setOtherCostStr(otherCostStr);
                otherCost.setText(TextUtils.isEmpty(otherCostStr) ? "" : "已填写");
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_offer_create;
    }
}
