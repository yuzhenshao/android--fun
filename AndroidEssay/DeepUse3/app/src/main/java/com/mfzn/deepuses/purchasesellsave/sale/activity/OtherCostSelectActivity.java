package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.settings.OtherCostResponse;
import com.mfzn.deepuses.bean.response.settings.RateResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.purchasesellsave.sale.Module.OtherCostModule;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.OtherCostSelectAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.activity.CommodityCreateActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.OtherCostActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OtherCostSelectActivity extends BasicActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private OtherCostSelectAdapter adapter;
    private List<OtherCostModule> otherCostList = new ArrayList<>();
    private static int COST_TYPE = 100;
    private int curIndex = 0;
    private List<RateResponse> mRateResponseList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("设置费用");
        otherCostList.addAll(JXCDataManager.getInstance().getOtherCostList());
        adapter = new OtherCostSelectAdapter(this, otherCostList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                curIndex = i;
                OtherCostModule otherCostModule = otherCostList.get(i);
                EditText costType = view.findViewById(R.id.cost_type);
                ImageView costTypeSelect = view.findViewById(R.id.cost_type_select);
                EditText costPrice = view.findViewById(R.id.cost_price);
                CheckBox hasTaxRate = view.findViewById(R.id.switch_button);
                EditText taxRate = view.findViewById(R.id.tax_rate);
                ImageView taxRateSelect = view.findViewById(R.id.tax_rate_select);


                switch (view.getId()) {
                    case R.id.cost_type_select:
                        Intent intent = new Intent(OtherCostSelectActivity.this, OtherCostActivity.class);
                        intent.putExtra(ParameterConstant.COST_TYPE_SELECTED, true);
                        startActivityForResult(intent, COST_TYPE);
                        break;
                    case R.id.switch_button:
                        otherCostModule.setTaxRate(!otherCostModule.isTaxRate());
                        hasTaxRate.setChecked(otherCostModule.isTaxRate());
                        break;
                    case R.id.tax_rate_select:
                        if (otherCostModule.isTaxRate()) {
                            showTaxDialog(otherCostModule);
                        }
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    private void showTaxDialog(OtherCostModule item) {
        ApiServiceManager.getTaxRateList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<RateResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<RateResponse>> reuslt) {
                        mRateResponseList = reuslt.getRes();
                        if (ListUtil.isEmpty(mRateResponseList)) {
                            showToast("没有相关税率");
                        } else {
                            List<String> rateList = new ArrayList<>();
                            for (RateResponse rateResponse : mRateResponseList) {
                                rateList.add(rateResponse.getRate());
                            }
                            PickerDialogView.showGoodSPosition(OtherCostSelectActivity.this,
                                    rateList, new OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                            double rate = getPrice(rateList.get(options1)) / 100;
                                            item.setTaxRate(rate);
                                            adapter.notifyItemChanged(curIndex);
                                        }
                                    });
                        }
                    }
                });


    }

    private double getPrice(String rate) {
        try {
            if (!TextUtils.isEmpty(rate)) {
                return Double.parseDouble(rate);
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_other_cost_list;
    }


    @OnClick({R.id.icon_add_other_cost, R.id.btn_commit})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.icon_add_other_cost:
                otherCostList.add(new OtherCostModule());
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_commit:
                JXCDataManager.getInstance().addOtherCostModule(otherCostList);
                Intent intent = new Intent();
                intent.putExtra("data", getData());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    /*
     * 其他费用信息：costType1（其他费用类型ID）,costMoney1（其他费用金额）,taxRate1（税率）;costType2...;*/
    private String getData() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!ListUtil.isEmpty(otherCostList)) {
            for (OtherCostModule costModule : otherCostList) {
                stringBuffer.append(costModule.getCostType()).append(",")
                        .append(costModule.getCostMoney()).append(",")
                        .append(costModule.getTaxRate()).append(";");
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == COST_TYPE) {
                OtherCostResponse otherCostResponse = (OtherCostResponse) data.getSerializableExtra("data");
                OtherCostModule otherCost = otherCostList.get(curIndex);
                otherCost.setCostType(otherCostResponse.getOtherCostTypeID());
                otherCost.setCostName(otherCostResponse.getOtherCostTypeName());
                adapter.notifyItemChanged(curIndex);
            }
        }
    }
}
