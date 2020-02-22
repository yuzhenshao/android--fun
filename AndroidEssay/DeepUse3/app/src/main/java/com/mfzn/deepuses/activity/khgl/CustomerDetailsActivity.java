package com.mfzn.deepuses.activity.khgl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.fragment.khgl.CustomerTongjiFragment;
import com.mfzn.deepuses.fragment.khgl.WholeCustomerFragment;
import com.mfzn.deepuses.fragment.khxq.BasicInfoFragment;
import com.mfzn.deepuses.fragment.khxq.FollowProgressFragment;
import com.mfzn.deepuses.fragment.khxq.ProjectInfoFragment;
import com.mfzn.deepuses.fragment.xm.GongdanFuwuFragment;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.CustomerDetailsPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.view.MyDuiZhangPagerAdapter;
import com.wevey.selector.dialog.bean.DetailsModel;

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
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class CustomerDetailsActivity extends BaseMvpActivity<CustomerDetailsPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_de_name)
    TextView tvDeName;
    @BindView(R.id.tv_de_type)
    TextView tvDeType;
    @BindView(R.id.iv_de_type)
    ImageView ivDeType;
    @BindView(R.id.tv_de_phone)
    TextView tvDePhone;
    @BindView(R.id.tr_indicator)
    MagicIndicator trIndicator;
    @BindView(R.id.tr_viewpager)
    ViewPager trViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_details;
    }

    @Override
    public CustomerDetailsPresnet newP() {
        return new CustomerDetailsPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_customer_details));

        String dataid = getIntent().getStringExtra(Constants.CUSTOMER_ID);
        String name = getIntent().getStringExtra(Constants.CUSTOMER_NAME);
        getP().customerDetails(dataid);

        List<String> mDataList = new ArrayList<>();
        mDataList.add("基本信息");
        mDataList.add("跟进进度");
        mDataList.add("项目信息");

        List<Fragment> list = new ArrayList<>();

        BasicInfoFragment basicInfoFragment = new BasicInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CUS_DETA_ID, dataid);
        basicInfoFragment.setArguments(bundle);//数据传递到fragment中
        list.add(basicInfoFragment);

        FollowProgressFragment followProgressFragment = new FollowProgressFragment();
        bundle.putString(Constants.CUS_DETA_ID, dataid);
        bundle.putString(Constants.CUS_DETA_NAME, name);
        followProgressFragment.setArguments(bundle);//数据传递到fragment中
        list.add(followProgressFragment);

        ProjectInfoFragment projectInfoFragment = new ProjectInfoFragment();
        bundle.putString(Constants.CUS_DETA_ID, dataid);
        projectInfoFragment.setArguments(bundle);//数据传递到fragment中
        list.add(projectInfoFragment);

        trViewpager.setAdapter(new MyDuiZhangPagerAdapter(getSupportFragmentManager(), list));

        initMagicIndicator(mDataList);

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.EDIT_SUCC)) {
                        getP().customerDetails(dataid);
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
        }
    }

    public void customerDetailsSuccess(DetailsModel models) {
        tvDeName.setText(models.getCustomerName());
        tvDePhone.setText(models.getCustomerPhone());
        tvDeType.setText(models.getFollowStatusName());

        int customerLevelID = models.getCustomerLevelID();
        if(customerLevelID == 4) {
            ivDeType.setImageResource(R.mipmap.xm_yixing);
        }else if(customerLevelID == 5) {
            ivDeType.setImageResource(R.mipmap.xm_erxing);
        }else if(customerLevelID == 6) {
            ivDeType.setImageResource(R.mipmap.xm_sanxing);
        }else if(customerLevelID == 7) {
            ivDeType.setImageResource(R.mipmap.xm_sixing);
        }else if(customerLevelID == 8) {
            ivDeType.setImageResource(R.mipmap.xm_wuxing);
        }else {
            ivDeType.setImageResource(0);
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
                        titleText.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.postInvalidate();
                        titleText.getPaint().setFakeBoldText(true);
                        titleText.setTextColor(context.getResources().getColor(R.color.color_606266));
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
                linePagerIndicator.setLineWidth(170);
                linePagerIndicator.setLineHeight(5);
                linePagerIndicator.setColors(getResources().getColor(R.color.color_3D7EFF));
                return linePagerIndicator;
            }
        });
        trIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(trIndicator, trViewpager);
    }
}
