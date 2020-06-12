package com.mfzn.deepuses.purchasesellsave.setting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.GoodsDetailResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yz @date 2020-05-04
 */
public class BasicAttrFragment extends BaseFragment {

    private GoodsDetailResponse.GoodsInfoResponse goodsInfoResponse;
    @BindView(R.id.goods_supplier)
    TextView supplierEdit;
    @BindView(R.id.cost_price)
    TextView costPrice;
    @BindView(R.id.remark)
    TextView remark;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        goodsInfoResponse = (GoodsDetailResponse.GoodsInfoResponse) getArguments().getSerializable(ParameterConstant.BASIC_ATTR);
        if (goodsInfoResponse == null) {
            showNoDataView();
        } else {
            StringBuffer supplierName = new StringBuffer();
            List<GoodsDetailResponse.GoodsInfoResponse.SuppliersBean> suppliersBeans = goodsInfoResponse.getSuppliers();
            if (!ListUtil.isEmpty(suppliersBeans)) {
                for (GoodsDetailResponse.GoodsInfoResponse.SuppliersBean suppler : suppliersBeans) {
                    supplierName.append(suppler.getSupplierName()).append(",");
                }
            }
            supplierEdit.setText(supplierName.substring(0, supplierName.length() - 1));
            costPrice.setText(goodsInfoResponse.getCostPrice()+"元");
            remark.setText(goodsInfoResponse.getRemark());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_attr;
    }

    @OnClick({R.id.shop_warning, R.id.store_warning})
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.shop_warning:
                break;
            case R.id.store_warning:
                break;
        }
    }
}
