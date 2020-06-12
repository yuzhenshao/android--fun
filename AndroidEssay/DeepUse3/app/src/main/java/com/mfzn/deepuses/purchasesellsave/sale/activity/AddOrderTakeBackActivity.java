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
import com.mfzn.deepuses.bean.request.sale.OrderTakeGoodsBackRequest;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderTakeBackActivity extends BasicActivity {

    private final static int USER = 1;
    private final static int GOODS = 2;
    private final static int COST = 3;

    @BindView(R.id.customer)
    EditText customer;
    @BindView(R.id.goods)
    EditText goods;
    @BindView(R.id.store)
    EditText store;
    @BindView(R.id.order_time)
    EditText orderTime;
    @BindView(R.id.out_num)
    EditText outNum;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.remark)
    EditText remark;

    @BindView(R.id.goods_price_container)
    RelativeLayout goodsPriceContainer;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.price)
    TextView price;

    private OrderTakeGoodsBackRequest request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建领货归还单");
    }

    @OnClick({R.id.customer_select, R.id.goods_select, R.id.order_time_select,
            R.id.store_select,  R.id.btn_commit})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.customer_select:
                intent.setClass(AddOrderTakeBackActivity.this, MyCustomerActivity.class);
                startActivityForResult(intent, USER);
                break;
            case R.id.goods_select:
                intent.setClass(AddOrderTakeBackActivity.this, GoodsSelectListActivity.class);
                startActivityForResult(intent, GOODS);
                break;
            case R.id.date_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        orderTime.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
            case R.id.store_select:
                intent.setClass(AddOrderTakeBackActivity.this, StoreListActivity.class);
                startActivityForResult(intent, USER);
                break;
            case R.id.btn_commit:
                ApiServiceManager.addOrderTakeGoodsBack(request)
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
                                showToast("创建成功");
                                finish();
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
                request.setTakerUserID(data.getStringExtra("Id"));
                customer.setText(data.getStringExtra("Name"));
            } else if (requestCode == GOODS) {
                request.setOrderGoodsStr(data.getStringExtra("Name"));
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_take_back_create;
    }

}
