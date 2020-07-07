package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.purchasesellsave.shop.activity.ShopListManagerActivity;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreAllCheckSponsorActivity extends BasicActivity {

    private final static int SHOP = 4;
    private final static int STORE = 5;

    @BindView(R.id.shop)
    EditText shopEdit;
    @BindView(R.id.store_check)
    EditText storeCheckEdit;
    @BindView(R.id.start_time)
    EditText startTimeEdit;
    @BindView(R.id.endtime)
    EditText endtimeEdit;

    private String storeId;
    private String shopId;
    private long beginDate;
    private long endDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建全库盘点");
    }

    @OnClick({R.id.shop_select, R.id.store_check_select, R.id.start_time_select, R.id.endtime_select, R.id.btn_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_select:
                Intent shopIntent = new Intent();
                shopIntent.setClass(StoreAllCheckSponsorActivity.this, ShopListManagerActivity.class);
                shopIntent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(shopIntent, SHOP);
                break;
            case R.id.store_check_select:
                Intent storeIntent = new Intent();
                storeIntent.setClass(StoreAllCheckSponsorActivity.this, StoreListActivity.class);
                storeIntent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(storeIntent, STORE);
                break;
            case R.id.start_time_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        beginDate = (int) (date.getTime() / 1000);
                        startTimeEdit.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
            case R.id.endtime_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        endDate = (int) (date.getTime() / 1000);
                        endtimeEdit.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
            case R.id.btn_commit:
                commitAction();
                break;
        }

    }

    protected void commitAction() {
        if (TextUtils.isEmpty(shopId)) {
            showToast("请输入公司/门店");
            return;
        }
        if (TextUtils.isEmpty(storeId)) {
            showToast("请输入盘点仓库");
            return;
        }
        if (beginDate == 0) {
            showToast("盘点开始日期");
            return;
        }
        if (endDate == 0) {
            showToast("盘点结束日期");
            return;
        }

        ApiServiceManager.storeAllCheckSponsor(shopId, storeId, beginDate + "", endDate + "")
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
                        showToast("成功");
                        setResult(Activity.RESULT_OK);
                        finish();

                    }
                });

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_store_all_check_sponsor;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SHOP) {
                ShopListResponse shop = (ShopListResponse) data.getSerializableExtra(ParameterConstant.SHOP);
                shopId = shop.getShopID();
                shopEdit.setText(shop.getShopName());
            } else if (requestCode == STORE) {
                StoreResponse store = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                storeId = store.getStoreID();
                storeCheckEdit.setText(store.getStoreName());

            }
        }
    }
}
