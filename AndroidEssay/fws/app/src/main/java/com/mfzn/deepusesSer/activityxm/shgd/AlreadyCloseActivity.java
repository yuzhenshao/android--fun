package com.mfzn.deepusesSer.activityxm.shgd;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.fragment.shouhou.ChuliGuochengFragment;
import com.mfzn.deepusesSer.fragment.shouhou.GongdanShuxingFragment;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.present.shouhou.JiedanPresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.PhoneUtils;
import com.mfzn.deepusesSer.view.MyDuiZhangPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AlreadyCloseActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_select)
    LinearLayout llBassSelect;
    @BindView(R.id.tv_acc_type)
    TextView tvAccType;
    @BindView(R.id.tv_acc_typename)
    TextView tvAccTypename;
    @BindView(R.id.tv_acc_address)
    TextView tvAccAddress;
    @BindView(R.id.tv_acc_name)
    TextView tvAccName;
    @BindView(R.id.tv_acc_phone)
    TextView tvAccPhone;

    @BindView(R.id.gd_indicator)
    MagicIndicator gdIndicator;
    @BindView(R.id.gd_viewpager)
    ViewPager gdViewpager;

    private String contactPhone;
    private String orderNo;
    private String pro_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_already_close;
    }

    @Override
    public JiedanPresnet newP() {
        return new JiedanPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_stay_accept));
        llBassSelect.setVisibility(View.GONE);
        tvAccTypename.getPaint().setFakeBoldText(true);

        WorkorderListModel.DataBean dataBean = (WorkorderListModel.DataBean) getIntent().getSerializableExtra(Constants.SHOUHOU_DETAILS);

        orderNo = dataBean.getOrderNo();
        pro_id = String.valueOf(dataBean.getProID());

        tvAccType.setText(orderNo);
        int shType = dataBean.getAsType();
        if(shType == 1) {//0全部  1故障保修  2维护升级
            tvAccTypename.setTextColor(getResources().getColor(R.color.color_3D7EFF));
        }else if(shType == 2) {
            tvAccTypename.setTextColor(getResources().getColor(R.color.color_62C33A));
        }
        tvAccTypename.setText(dataBean.getShTypeName());
        tvAccAddress.setText(dataBean.getDetailAddress());
        tvAccName.setText(dataBean.getContactName());
        contactPhone = dataBean.getContactPhone();
        tvAccPhone.setText(contactPhone);

        List<String> mDataList = new ArrayList<>();
        mDataList.add("工单属性");
        mDataList.add("处理过程");

        List<Fragment> list = new ArrayList<>();

        GongdanShuxingFragment shuxingFragment = new GongdanShuxingFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SHOUHOU_ORDERNO, orderNo);
        shuxingFragment.setArguments(bundle);//数据传递到fragment中
        list.add(shuxingFragment);

        ChuliGuochengFragment guochengFragment = new ChuliGuochengFragment();
        bundle.putString(Constants.SHOUHOU_ORDERNO, orderNo);
        guochengFragment.setArguments(bundle);//数据传递到fragment中
        list.add(guochengFragment);

        gdViewpager.setAdapter(new MyDuiZhangPagerAdapter(getSupportFragmentManager(), list));

        initMagicIndicator(mDataList);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_acc_phone,R.id.but_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_acc_phone:
                if (!TextUtils.isEmpty(contactPhone)) {
                    PhoneUtils.dialogPhone(this, contactPhone);
                }
                break;
            case R.id.but_look:
                Intent intent = new Intent(AlreadyCloseActivity.this,PingjiaDetailActivity.class);
                intent.putExtra("orderNo",orderNo);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 998) {
            if (requestCode == 0){
                finish();
            }
        }
    }

    private void initMagicIndicator(List<String> mDataList) {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);//设置为充满屏幕
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout, null);
                final TextView titleText = (TextView) customLayout.findViewById(R.id.title_text);
                titleText.setText(mDataList.get(index));
                commonPagerTitleView.setContentView(customLayout);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.postInvalidate();
                        titleText.getPaint().setFakeBoldText(true);
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.postInvalidate();
                        titleText.getPaint().setFakeBoldText(false);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gdViewpager.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(2);//自己设置线的宽度
                linePagerIndicator.setLineWidth(180);
                linePagerIndicator.setLineHeight(5);
                linePagerIndicator.setColors(getResources().getColor(R.color.color_3D7EFF));
                return linePagerIndicator;
            }
        });
        gdIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(gdIndicator, gdViewpager);
    }

}