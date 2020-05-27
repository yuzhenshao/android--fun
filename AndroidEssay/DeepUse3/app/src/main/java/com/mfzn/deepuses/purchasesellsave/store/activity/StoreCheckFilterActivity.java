package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StatusCheckFilterAdapter;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StoreCheckFilterAdapter;
import com.mfzn.deepuses.purchasesellsave.store.model.StoreStatusFilter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreCheckFilterActivity extends BasicActivity {

    private String shopId;
    @BindView(R.id.date_edit)
    EditText dateEdit;
    @BindView(R.id.filter_container)
    LinearLayout filterView;
    @BindView(R.id.store_recyleview)
    RecyclerView storeRecyleview;
    @BindView(R.id.status_recyleview)
    RecyclerView statusRecyleview;
    private TimePickerView pvTime;
    private List<StoreResponse> mStoreList = new ArrayList<>();
    private List<StoreStatusFilter> mStatusList = new ArrayList<>();
    private StoreCheckFilterAdapter mStoreFilterAdapter;
    private StatusCheckFilterAdapter mStatusFilterAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    private void initData() {
        initTimePicker();
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
        mStoreFilterAdapter = new StoreCheckFilterAdapter(mStoreList);
        storeRecyleview.setLayoutManager(new GridLayoutManager(this, 3));
        storeRecyleview.setAdapter(mStoreFilterAdapter);

        mStatusFilterAdapter = new StatusCheckFilterAdapter(mStatusList);
        statusRecyleview.setLayoutManager(new GridLayoutManager(this, 3));
        statusRecyleview.setAdapter(mStatusFilterAdapter);

        for (int i = 0; i < 5; i++) {
            mStatusList.add(new StoreStatusFilter(i));
        }

        ApiServiceManager.getStoreList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<StoreResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        // showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<StoreResponse>> reuslt) {
                        mStoreList.clear();
                        mStoreList.addAll(reuslt.getRes());
                        mStoreFilterAdapter.notifyDataSetChanged();
                    }
                });
    }


    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                dateEdit.setText(format.format(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setSubCalSize(15)
                .setSubmitColor(R.color.color_4A90E2)
                .setCancelColor(R.color.color_4A90E2)
                .setOutSideCancelable(true)
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    @OnClick({R.id.ll_se_del, R.id.date_selected, R.id.save_btn, R.id.search_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_se_del:
                filterView.setVisibility(View.GONE);
                break;
            case R.id.date_selected:
                pvTime.show(view);
            case R.id.save_btn:

                break;
            case R.id.search_container:
                break;
        }
    }
}
