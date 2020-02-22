package com.mfzn.deepuses.activityxm.shgd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.fragment.xm.ChuliGuochengFragment;
import com.mfzn.deepuses.fragment.xm.GongdanFuwuFragment;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.xmgd.GongdanDetailsPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.view.MyDuiZhangPagerAdapter;

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

public class GongdanDetailsActivity extends BaseMvpActivity<GongdanDetailsPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_ser_type)
    TextView tvserType;
    @BindView(R.id.tv_ser_typename)
    TextView tvserTypename;
    @BindView(R.id.tv_ser_address)
    TextView tvserAddress;
    @BindView(R.id.tv_ser_name)
    TextView tvserName;
    @BindView(R.id.tv_ser_phone)
    TextView tvserPhone;
    @BindView(R.id.tv_acc_sl)
    TextView tv_acc_sl;
    @BindView(R.id.iv_acc_sl)
    ImageView iv_acc_sl;

    @BindView(R.id.gd_indicator)
    MagicIndicator gdIndicator;
    @BindView(R.id.gd_viewpager)
    ViewPager gdViewpager;

    private String contactPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gongdan_details;
    }

    @Override
    public GongdanDetailsPresent newP() {
        return new GongdanDetailsPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_stay_accept));
        tvserTypename.getPaint().setFakeBoldText(true);

        String order = getIntent().getStringExtra(Constants.GD_DETAILS);

        getP().gongdanShuxing(order);

        tvserType.setText(order);

        List<String> mDataList = new ArrayList<>();
        mDataList.add("工单属性");
        mDataList.add("处理过程");

        List<Fragment> list = new ArrayList<>();

        GongdanFuwuFragment shuxingFragment = new GongdanFuwuFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SHOUHOU_ORDERNO, order);
        shuxingFragment.setArguments(bundle);//数据传递到fragment中
        list.add(shuxingFragment);

        ChuliGuochengFragment guochengFragment = new ChuliGuochengFragment();
        bundle.putString(Constants.SHOUHOU_ORDERNO, order);
        guochengFragment.setArguments(bundle);//数据传递到fragment中
        list.add(guochengFragment);

        gdViewpager.setAdapter(new MyDuiZhangPagerAdapter(getSupportFragmentManager(), list));

        initMagicIndicator(mDataList);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_ser_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                Intent intent = new Intent();
                intent.putExtra("sadsa", "saf");
                setResult(Constants.LOOK_NEWS,intent);
                finish();
                break;
            case R.id.ll_ser_phone:
                if (!TextUtils.isEmpty(contactPhone)) {
                    PhoneUtils.dialogPhone(this, contactPhone);
                }
                break;
        }
    }

    //点击返回键返回到桌面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("sadsa", "saf");
            setResult(Constants.LOOK_NEWS,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void gongdanShuxingSuccess(GongdanShuxingModel model) {
        int status = model.getStatus();
        if(status == 1) { //待受理
            iv_acc_sl.setVisibility(View.GONE);
            tv_acc_sl.setVisibility(View.VISIBLE);
            tv_acc_sl.setBackgroundResource(R.drawable.yuanjiao_xianbg_d0f9fd_shape);
        }else if(status == 2) {//待派工
            iv_acc_sl.setVisibility(View.VISIBLE);
            tv_acc_sl.setVisibility(View.GONE);
            iv_acc_sl.setImageResource(R.mipmap.work_paigong);
        }else if(status == 3) {//待接单
            iv_acc_sl.setVisibility(View.VISIBLE);
            tv_acc_sl.setVisibility(View.GONE);
            iv_acc_sl.setImageResource(R.mipmap.work_daijiedan);
        }else if(status == 4) {//服务中
            iv_acc_sl.setVisibility(View.VISIBLE);
            tv_acc_sl.setVisibility(View.GONE);
            iv_acc_sl.setImageResource(R.mipmap.work_fuwuzhong);
        }else if(status == 5) {//待评价
            iv_acc_sl.setVisibility(View.VISIBLE);
            tv_acc_sl.setVisibility(View.GONE);
            iv_acc_sl.setImageResource(R.mipmap.work_daipingjia);
        }else if(status == 6 || status == 7 || status == 8) {//已评价  已取消  已关闭
            iv_acc_sl.setVisibility(View.GONE);
            tv_acc_sl.setVisibility(View.VISIBLE);
            tv_acc_sl.setBackgroundResource(R.drawable.yuanjiao_xianbg_edeff2_shape);
        }
        tv_acc_sl.setText(model.getStatusTypeName());

        int shType = model.getShType();
        if(shType == 1) {//0全部  1故障保修  2维护升级
            tvserTypename.setTextColor(getResources().getColor(R.color.color_3D7EFF));
        }else if(shType == 2) {
            tvserTypename.setTextColor(getResources().getColor(R.color.color_62C33A));
        }
        tvserTypename.setText(model.getShTypeName());
        tvserAddress.setText(model.getDetailAddress());
        tvserName.setText(model.getContactName());
        contactPhone = model.getContactPhone();
        tvserPhone.setText(contactPhone);
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
