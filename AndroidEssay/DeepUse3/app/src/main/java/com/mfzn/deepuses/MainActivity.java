package com.mfzn.deepuses;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mfzn.deepuses.activity.khgl.BulidCustomerActivity;
import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.fragment.BaikeFragment;
import com.mfzn.deepuses.fragment.GongzuoFragment;
import com.mfzn.deepuses.fragment.MyFragment;
import com.mfzn.deepuses.fragment.XiangmuFragment;
import com.mfzn.deepuses.fragment.XiaoxiFragment;
import com.mfzn.deepuses.model.xx.MsgMainModel;
import com.mfzn.deepuses.present.login.LoginPresent;
import com.mfzn.deepuses.present.login.MainPresent;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderOfferActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderSalesActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.OrderSalesListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.CommodityPhotoCreateActivity;
import com.mfzn.deepuses.utils.FullScreen;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseMvpActivity<MainPresent> {

    @BindView(R.id.iv_main_gongzuo)
    ImageView ivMainGongzuo;
    @BindView(R.id.tv_main_gongzuo)
    TextView tvMainGongzuo;
    @BindView(R.id.iv_main_xiangmu)
    ImageView ivMainXiangmu;
    @BindView(R.id.tv_main_xiangmu)
    TextView tvMainXiangmu;
    @BindView(R.id.icon_main_add)
    ImageView ivMainAdd;
    @BindView(R.id.iv_main_xiaoxi)
    ImageView ivMainXiaoxi;
    @BindView(R.id.tv_main_xiaoxi)
    TextView tvMainXiaoxi;
    @BindView(R.id.iv_main_me)
    ImageView ivMainMe;
    @BindView(R.id.tv_main_me)
    TextView tvMainMe;
    @BindView(R.id.add_container)
    RelativeLayout mainAddView;

    private GongzuoFragment mTab01;
    private XiangmuFragment mTab02;
    //private BaikeFragment mTab03;
    private XiaoxiFragment mTab03;
    private MyFragment mTab04;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @Override
    public MainPresent newP() {
        return new MainPresent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        FullScreen.fullScreen(this);
        FullScreen.setStatusBarMode(this);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        AppManager.getInstance().addActivity(this);

        JPushInterface.setAlias(MainActivity.this, UserHelper.getUid(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });

        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getP().getMSg();
    }

    public void getMsgSuccess(MsgMainModel model) {
//        if (model.getShowRedPoint() > 0){
        Badge badge = new QBadgeView(this).bindTarget(ivMainXiaoxi).setBadgeNumber(model.getShowRedPoint());
        badge.setShowShadow(false);
//        }
    }

    @OnClick({R.id.ll_main_gongzuo, R.id.ll_main_xiangmu, R.id.icon_main_add, R.id.ll_main_xiaoxi, R.id.ll_main_me, R.id.add_container,
            R.id.new_bjd, R.id.new_xsdd, R.id.new_ckd, R.id.add_lsd, R.id.new_goods, R.id.new_project, R.id.new_customer, R.id.new_jz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_gongzuo:
                setTabSelection(0);
                break;
            case R.id.ll_main_xiangmu:
                setTabSelection(1);
                break;

            case R.id.ll_main_xiaoxi:
                getP().getMSg();
                setTabSelection(2);
                break;
            case R.id.ll_main_me:
                setTabSelection(3);
                break;
            case R.id.add_container:
                mainAddView.setVisibility(View.GONE);
                ivMainAdd.setImageResource(R.mipmap.icon_main_add);
                break;
            case R.id.icon_main_add:
                if (mainAddView.getVisibility() == View.VISIBLE) {
                    mainAddView.setVisibility(View.GONE);
                    ivMainAdd.setImageResource(R.mipmap.icon_main_add);
                } else {
                    mainAddView.setVisibility(View.VISIBLE);
                    ivMainAdd.setImageResource(R.mipmap.icon_main_close);
                }
                break;
            case R.id.new_bjd:
                turnToActivity(AddOrderOfferActivity.class);
                break;
            case R.id.new_xsdd:
                turnToSalesActivity(false);
                break;
            case R.id.new_ckd:
            case R.id.new_jz:
                mainAddView.setVisibility(View.GONE);
                ivMainAdd.setImageResource(R.mipmap.icon_main_add);
                ToastUtil.showToast(this,"敬请期待");
                break;
            case R.id.add_lsd:
                turnToSalesActivity(true);
                break;
            case R.id.new_goods:
                turnToActivity(CommodityPhotoCreateActivity.class);
                break;
            case R.id.new_project:
                turnToActivity(FoundProjectActivity.class);
                break;
            case R.id.new_customer:
                turnToActivity(BulidCustomerActivity.class);
                break;
        }
    }

    private void turnToActivity(Class<?> turnActivity) {
        mainAddView.setVisibility(View.GONE);
        ivMainAdd.setImageResource(R.mipmap.icon_main_add);
        Intent intent = new Intent();
        intent.setClass(this, turnActivity);
        startActivity(intent);
    }

    private void turnToSalesActivity(boolean isRetail) {
        mainAddView.setVisibility(View.GONE);
        ivMainAdd.setImageResource(R.mipmap.icon_main_add);
        Intent intent = new Intent();
        intent.putExtra(ParameterConstant.IS_RETAIL_CREATE, isRetail);
        intent.setClass(this, AddOrderSalesActivity.class);
        startActivity(intent);
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    private void setTabSelection(int index) {
        resetBtn();//清除状态
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ivMainGongzuo.setImageResource(R.mipmap.bass_work2);
                tvMainGongzuo.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab01 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTab01 = new GongzuoFragment();
                    transaction.add(R.id.fl_content, mTab01);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTab01);
                }
                break;
            case 1:
                ivMainXiangmu.setImageResource(R.mipmap.bass_project2);
                tvMainXiangmu.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab02 == null) {
                    mTab02 = new XiangmuFragment();
                    transaction.add(R.id.fl_content, mTab02);
                } else {
                    transaction.show(mTab02);
                }
                break;
//            case 2:
//                ivMainBaike.setImageResource(R.mipmap.bass_xiangmu2);
//                tvMainBaike.setTextColor(getResources().getColor(R.color.color_3D7EFF));
//                if (mTab03 == null) {
//                    mTab03 = new BaikeFragment();
//                    transaction.add(R.id.fl_content, mTab03);
//                } else {
//                    transaction.show(mTab03);
//                }
//                break;
            case 2:
                ivMainXiaoxi.setImageResource(R.mipmap.bass_faxian2);
                tvMainXiaoxi.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab03 == null) {
                    mTab03 = new XiaoxiFragment();
                    transaction.add(R.id.fl_content, mTab03);
                } else {
                    transaction.show(mTab03);
                }
                break;
            case 3:
                ivMainMe.setImageResource(R.mipmap.bass_me2);
                tvMainMe.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab04 == null) {
                    mTab04 = new MyFragment();
                    transaction.add(R.id.fl_content, mTab04);
                } else {
                    transaction.show(mTab04);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {
        ivMainGongzuo.setImageResource(R.mipmap.bass_gongzuo1);
        tvMainGongzuo.setTextColor(getResources().getColor(R.color.color_909399));
        ivMainXiangmu.setImageResource(R.mipmap.bass_xiangmu);
        tvMainXiangmu.setTextColor(getResources().getColor(R.color.color_909399));
        //  ivMainBaike.setImageResource(R.mipmap.bass_baike1);
        //  tvMainBaike.setTextColor(getResources().getColor(R.color.color_909399));
        ivMainXiaoxi.setImageResource(R.mipmap.bass_xiaoxi);
        tvMainXiaoxi.setTextColor(getResources().getColor(R.color.color_909399));
        ivMainMe.setImageResource(R.mipmap.bass_me);
        tvMainMe.setTextColor(getResources().getColor(R.color.color_909399));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
//    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }
    }

    private long firstTime = 0;

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtil.showToast(this, "再按一次退出程序");
            firstTime = secondTime;
        } else {
            AppManager.getInstance().appExit();
        }
    }
}
