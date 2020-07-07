package com.mfzn.deepuses.purchasesellsave.setting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.adapter.DialogAdapter;
import com.libcommon.dialog.fragment.CustomListDialog;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.StoresInfoResponse;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.StockLogAdapter;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author yz @date 2020-05-04
 */
public class StoresFragment extends BaseFragment {
    private List<StoresInfoResponse> mStoresInfoResponses;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mStoresInfoResponses = (List<StoresInfoResponse>) getArguments().getSerializable(ParameterConstant.STORES);
        if (ListUtil.isEmpty(mStoresInfoResponses)) {
            showNoDataView();
        } else {
            StockLogAdapter adapter = new StockLogAdapter(mStoresInfoResponses);
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                    if (view.getId() == R.id.log_btn) {
                        showStoreLogDialog(mStoresInfoResponses.get(i));
                    }
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void showStoreLogDialog(StoresInfoResponse response) {
        if(response==null||ListUtil.isEmpty(response.getStockLog())){
            ToastUtil.showToast(getActivity(),"暂无流水");
            return;
        }
        new CustomListDialog.Builder().setLayoutRes(R.layout.dialog_store_log_recycler)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setHeight((int) (0.6 * DialogUtils.getDisplayMetrics(getActivity()).heightPixels))
                .setGravity(Gravity.BOTTOM)
                .setAdapter(new DialogAdapter<StoresInfoResponse.StockLog>(R.layout.dialog_store_log_item, R.id.recycler_view, response.getStockLog()) {
                    @Override
                    protected void onBind(BindViewHolder holder, StoresInfoResponse.StockLog storeLog) {
                        holder.setText(R.id.date, DateUtils.longToString(storeLog.getAddTime()))
                                .setText(R.id.in_out_type_name, storeLog.getInOrOutTypeName())
                                .setText(R.id.size, "数量：" + storeLog.getNum());
                    }
                })
                .setLayoutManager(new LinearLayoutManager(getContext()))
                .addOnClickListener(R.id.confirm_btn)
                .setOnViewClickListener((dialog, holder, view) -> {
                    dialog.dismiss();
                })
                .create()
                .show(getActivity().getSupportFragmentManager(), getActivity().getSupportFragmentManager().getClass().getName());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_stores;
    }

}
