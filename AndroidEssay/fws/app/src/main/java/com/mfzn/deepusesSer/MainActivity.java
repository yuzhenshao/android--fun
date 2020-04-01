package com.mfzn.deepusesSer;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.fragment.BaikeFragment;
import com.mfzn.deepusesSer.fragment.MyFragment;
import com.mfzn.deepusesSer.fragment.XiangmuFragment;
import com.mfzn.deepusesSer.fragment.XiaoxiFragment;
import com.mfzn.deepusesSer.jpushpackage.ExampleUtil;
import com.mfzn.deepusesSer.jpushpackage.LocalBroadcastManager;
import com.mfzn.deepusesSer.model.msg.Message;
import com.mfzn.deepusesSer.present.login.MainPresent;
import com.mfzn.deepusesSer.utils.FullScreen;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseMvpActivity<MainPresent> {

    @BindView(R.id.iv_main_xiangmu)
    ImageView ivMainXiangmu;
    @BindView(R.id.tv_main_xiangmu)
    TextView tvMainXiangmu;
    @BindView(R.id.iv_main_baike)
    ImageView ivMainBaike;
    @BindView(R.id.tv_main_baike)
    TextView tvMainBaike;
    @BindView(R.id.iv_main_xiaoxi)
    ImageView ivMainXiaoxi;
    @BindView(R.id.tv_main_xiaoxi)
    TextView tvMainXiaoxi;
    @BindView(R.id.iv_main_me)
    ImageView ivMainMe;
    @BindView(R.id.tv_main_me)
    TextView tvMainMe;

    private XiangmuFragment mTab02;
    private BaikeFragment mTab03;
    private XiaoxiFragment mTab04;
    private MyFragment mTab05;
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
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

//        JPushInterface.setAlias(MainActivity.this,0,UserHelper.getUid());
        JPushInterface.setAlias(BaseApplication.getContext(), UserHelper.getUid(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
            }
        });
//        JPushInterface.setAlias(MainActivity.this, 1, UserHelper.getUid());
        FullScreen.fullScreen(this);
        FullScreen.setStatusBarMode(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);

        //是否接受推送消息
        if (UserHelper.getTuisong()) {
            JPushInterface.resumePush(this);
        } else {
            JPushInterface.stopPush(this);
        }

        //是否开启推送消息声音
        if (UserHelper.getNewsKaiguan()) {
            setSoundAndVibrate(true, true);
        } else {
            setSoundAndVibrate(false, false);
        }
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public ImageView getImageView(){
        return ivMainXiaoxi;
    }
    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getP().getMSg();
    }

    public void getMsgSuccess(Message model) {
        new QBadgeView(this).bindTarget(ivMainXiaoxi).setBadgeNumber(model.getNotReadCount()).setShowShadow(false);
    }

    @OnClick({R.id.ll_main_xiangmu, R.id.ll_main_baike, R.id.ll_main_xiaoxi, R.id.ll_main_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_xiangmu:
                setTabSelection(0);
                break;
            case R.id.ll_main_baike:
                setTabSelection(1);
                break;
            case R.id.ll_main_xiaoxi:
                setTabSelection(2);
                break;
            case R.id.ll_main_me:
                setTabSelection(3);
                break;
        }
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
                ivMainXiangmu.setImageResource(R.mipmap.bass_project2);
                tvMainXiangmu.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab02 == null) {
                    mTab02 = new XiangmuFragment();
                    transaction.add(R.id.fl_content, mTab02);
                } else {
                    transaction.show(mTab02);
                }
                break;
            case 1:
                ivMainBaike.setImageResource(R.mipmap.bass_xiangmu2);
                tvMainBaike.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab03 == null) {
                    mTab03 = new BaikeFragment();
                    transaction.add(R.id.fl_content, mTab03);
                } else {
                    transaction.show(mTab03);
                }
                break;
            case 2:
                ivMainXiaoxi.setImageResource(R.mipmap.bass_faxian2);
                tvMainXiaoxi.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab04 == null) {
                    mTab04 = new XiaoxiFragment();
                    transaction.add(R.id.fl_content, mTab04);
                } else {
                    transaction.show(mTab04);
                }
                break;
            case 3:
                ivMainMe.setImageResource(R.mipmap.bass_me2);
                tvMainMe.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                if (mTab05 == null) {
                    mTab05 = new MyFragment();
                    transaction.add(R.id.fl_content, mTab05);
                } else {
                    transaction.show(mTab05);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {
        ivMainXiangmu.setImageResource(R.mipmap.bass_xiangmu);
        tvMainXiangmu.setTextColor(getResources().getColor(R.color.color_909399));
        ivMainBaike.setImageResource(R.mipmap.bass_baike1);
        tvMainBaike.setTextColor(getResources().getColor(R.color.color_909399));
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
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }
        if (mTab05 != null) {
            transaction.hide(mTab05);
        }
    }

    private long firstTime = 0;

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

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

    private void setSoundAndVibrate(boolean isOpenSound, boolean isOpenVibrate) {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.mipmap.ic_launcher;//设置推送的图标
        if (isOpenVibrate && !isOpenSound) {//只有振动
            builder.notificationDefaults = Notification.DEFAULT_VIBRATE;
        } else if (isOpenSound && !isOpenVibrate) {//只有声音;
            builder.notificationDefaults = Notification.DEFAULT_SOUND;
        } else if (isOpenSound && isOpenVibrate) {//两个都有
            builder.notificationDefaults = Notification.DEFAULT_ALL;
        } else {//只有呼吸灯
            builder.notificationDefaults = Notification.DEFAULT_LIGHTS;
        }
        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }

    private void setCostomMsg(String msg){

    }
}
