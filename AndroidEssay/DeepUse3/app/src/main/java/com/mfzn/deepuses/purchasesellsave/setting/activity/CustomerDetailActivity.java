package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.setting.AddSetCustomerRequest;
import com.mfzn.deepuses.bean.response.settings.CustomerDetailResponse;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;
import com.mfzn.deepuses.common.tab.TabAdapter;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.CustomerOrderFragment;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.CustomerPayLogFragment;
import com.mfzn.deepuses.utils.ToastUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-05-04
 */
public class CustomerDetailActivity extends BasicActivity {

    private static int EDIT_CODE = 1001;

    @BindView(R.id.customer_icon)
    ImageView customerAvatar;
    @BindView(R.id.customer_name)
    TextView nameView;
    @BindView(R.id.customer_type)
    TextView customerTypeView;
    @BindView(R.id.leavel_name)
    TextView leavelNameView;
    @BindView(R.id.leavel_icon)
    ImageView leavelIconView;
    @BindView(R.id.customer_phone)
    TextView customerPhone;

    @BindView(R.id.magic_indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.detail_view_pager)
    ViewPager detailPager;

    List<TabLabel> mTabLabelList = new ArrayList<>();
    private final static int SUPPLIER_ORDER_LIST = 1;
    private final static int SUPPLIER_PAY_LOG_LIST = 2;
    private CustomerDetailResponse mCustomerInfoResponse;
    private CustomerListResponse.CustomerResponse mCustomerResponse;
    private boolean isNeedRefresh = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("客户详情", R.mipmap.work_xie);
        mCustomerResponse = (CustomerListResponse.CustomerResponse) getIntent().getSerializableExtra(ParameterConstant.CUSTOMER);
        if (mCustomerResponse == null) {
            showToast("客户信息获取失败");
        } else {
            initCustomerInfo();
            initData();
        }
    }

    private void initCustomerInfo() {
        if (mCustomerResponse != null) {
            if (!TextUtils.isEmpty(mCustomerResponse.getUserAvatar())) {
                Glide.with(this).load(ApiHelper.BASE_URL + mCustomerResponse.getUserAvatar()).into(customerAvatar);
            }
            nameView.setText(mCustomerResponse.getCustomerName());
            customerPhone.setText(mCustomerResponse.getCustomerPhone());
            customerTypeView.setText(mCustomerResponse.getCustomerTypeName());
            int iconRes = mCustomerResponse.getLevelIcon();
            leavelIconView.setVisibility(iconRes == 0 ? View.GONE : View.VISIBLE);
            leavelNameView.setText(mCustomerResponse.getLevelName());
            if (iconRes != 0) {
                leavelIconView.setImageResource(iconRes);
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_detail;
    }

    private void initData() {
        showDialog();
        ApiServiceManager.getCustomerInfo(mCustomerResponse.getData_id())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CustomerDetailResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        ToastUtil.showToast(CustomerDetailActivity.this, error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<CustomerDetailResponse> reuslt) {
                        hideDialog();
                        mCustomerInfoResponse = reuslt.getRes();
                        initDetailPager();
                    }
                });
    }

    private void initDetailPager() {
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager());
        detailPager.setAdapter(detailAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.25f);
        mTabLabelList.add(new TabLabel(SUPPLIER_ORDER_LIST, "销售历史"));
        mTabLabelList.add(new TabLabel(SUPPLIER_PAY_LOG_LIST, "收付款历史"));
        commonNavigator.setAdapter(new TabAdapter(mTabLabelList) {
            public void setCurrentItem(int index) {
                detailPager.setCurrentItem(index);
            }
        });

        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, detailPager);
        detailAdapter.notifyDataSetChanged();
    }

    public class DetailAdapter extends FragmentPagerAdapter {
        public DetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (mTabLabelList.get(position).getId()) {
                case SUPPLIER_PAY_LOG_LIST:
                    CustomerPayLogFragment attrFragment = new CustomerPayLogFragment();
                    Bundle attrBundle = new Bundle();
                    attrBundle.putSerializable(ParameterConstant.SUPPLIER_PAY_LOG_LIST, (Serializable) mCustomerInfoResponse.getPayLogList());
                    attrFragment.setArguments(attrBundle);
                    return attrFragment;
                case SUPPLIER_ORDER_LIST:
                    CustomerOrderFragment storeFragment = new CustomerOrderFragment();
                    Bundle storeBundle = new Bundle();
                    storeBundle.putSerializable(ParameterConstant.SUPPLIER_ORDER_LIST, (Serializable) mCustomerInfoResponse.getOrderList());
                    storeFragment.setArguments(storeBundle);
                    return storeFragment;
            }
            return null;
        }

        @Override
        public String getPageTitle(int position) {
            return mTabLabelList.get(position).getName();
        }

        @Override
        public int getCount() {
            return mTabLabelList.size();
        }
    }

    protected void rightPressedAction() {
        Intent intent = new Intent(this, CreateSetCustomerActivity.class);
        intent.putExtra(ParameterConstant.CUSTOMER, mCustomerResponse);
        startActivityForResult(intent, EDIT_CODE);
    }

    @OnClick(R.id.customer_info_container)
    public void showCustomerDetail() {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_customer_info)
                .setHeight((int) (0.8 * DialogUtils.getDisplayMetrics(this).heightPixels))
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setGravity(Gravity.BOTTOM)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder holder) {

                        holder.setText(R.id.customer_name, mCustomerResponse.getCustomerName());
                        holder.setText(R.id.customer_phone, mCustomerResponse.getCustomerPhone());
                        holder.setText(R.id.charge_person_name, mCustomerResponse.getChargePersonName());
                        holder.setText(R.id.charge_person_phone, mCustomerResponse.getChargePersonPhone());
                        holder.setText(R.id.level_name, mCustomerResponse.getLevelName());
                        holder.setText(R.id.customer_type, mCustomerResponse.getCustomerTypeName());
                        holder.setText(R.id.address, mCustomerResponse.getAddress());
                        holder.setText(R.id.remark, mCustomerResponse.getNote());
                    }
                })
                .addOnClickListener(R.id.btn_commit)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment customDialog, BindViewHolder bindViewHolder, View view) {
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                    }
                }).create().show(getSupportFragmentManager(), getClass().getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_CODE && resultCode == RESULT_OK && data != null) {
            boolean isDelete = data.getBooleanExtra("IsDeleted", false);
            if (isDelete) {
                isNeedRefresh = true;
                finish();
            } else {
                AddSetCustomerRequest mCustomerRequest = (AddSetCustomerRequest) data.getSerializableExtra(ParameterConstant.CUSTOMER);
                if (mCustomerResponse != null && mCustomerRequest != null) {
                    mCustomerResponse.setCustomerName(mCustomerRequest.getCustomerName());
                    mCustomerResponse.setCustomerPhone(mCustomerRequest.getCustomerPhone());
                    mCustomerResponse.setChargePersonName(mCustomerRequest.getChargePersonName());
                    mCustomerResponse.setChargePersonPhone(mCustomerRequest.getChargePersonPhone());
                    mCustomerResponse.setLevelName(data.getStringExtra("Name"));
                    mCustomerResponse.setCustomerLevelID(mCustomerRequest.getCustomerLevelID());
                    mCustomerResponse.setCustomerType(Integer.parseInt(mCustomerRequest.getCustomerType()));
                    mCustomerResponse.setAddress(mCustomerRequest.getAddress());
                    mCustomerResponse.setNote(mCustomerRequest.getRemark());
                    initCustomerInfo();
                    isNeedRefresh = true;
                }
            }
        }
    }

    @Override
    public void finish() {
        if (isNeedRefresh) {
            setResult(Activity.RESULT_OK);
        }
        super.finish();
    }
}
