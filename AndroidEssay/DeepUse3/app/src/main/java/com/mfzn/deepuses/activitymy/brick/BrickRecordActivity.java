package com.mfzn.deepuses.activitymy.brick;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.fragment.brick.ConsumeBrickFragment;
import com.mfzn.deepuses.fragment.brick.ConsumeRecordFragment;
import com.mfzn.deepuses.fragment.brick.RechargeBrickFragment;
import com.mfzn.deepuses.fragment.brick.TransactionRecordFragment;
import com.mfzn.deepuses.fragment.brick.WholeBrickFragment;
import com.mfzn.deepuses.fragment.brick.WholeRecordFragment;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.present.brick.BrickRecordPresnet;
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

public class BrickRecordActivity extends BaseMvpActivity<BrickRecordPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_br_sumz)
    TextView tvBrSumz;
    @BindView(R.id.tv_br_sumq)
    TextView tvBrSumq;
    @BindView(R.id.tr_indicator)
    MagicIndicator trIndicator;
    @BindView(R.id.tr_viewpager)
    ViewPager trViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_brick_record;
    }

    @Override
    public BrickRecordPresnet newP() {
        return new BrickRecordPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_brick_record));

        getP().brickRecord();

        List<String> mDataList = new ArrayList<>();
        mDataList.add("全部");
        mDataList.add("充值");
        mDataList.add("消费");

        List<Fragment> list = new ArrayList<>();

        list.add(new WholeBrickFragment());
        list.add(new RechargeBrickFragment());
        list.add(new ConsumeBrickFragment());

        trViewpager.setAdapter(new MyDuiZhangPagerAdapter(getSupportFragmentManager(), list));

        initMagicIndicator(mDataList);
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void brickRecordSuccess(BrickRecordModel model){
        tvBrSumz.setText(model.getBrickMoney());
        tvBrSumq.setText(model.getSumMoney());
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
                        titleText.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.postInvalidate();
                        titleText.getPaint().setFakeBoldText(false);
                        titleText.setTextColor(context.getResources().getColor(R.color.color_909399));
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
                        trViewpager.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(2);//自己设置线的宽度
                linePagerIndicator.setLineWidth(100);
                linePagerIndicator.setLineHeight(5);
                linePagerIndicator.setColors(getResources().getColor(R.color.color_3D7EFF));
                return linePagerIndicator;
            }
        });
        trIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(trIndicator, trViewpager);
    }
}
