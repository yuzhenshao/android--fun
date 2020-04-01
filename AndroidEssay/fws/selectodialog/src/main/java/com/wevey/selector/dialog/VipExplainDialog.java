package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;
import com.wevey.selector.dialog.adapter.GuideViewPager3Adapter;
import com.wevey.selector.dialog.bean.VipExplainBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weavey on 2016/9/3.
 */
public class VipExplainDialog {

    private TextView tv_vip_payment;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;
    private LinearLayout ll_vip_out;
    private LinearLayout ll_vip_dian;
    private ViewPager vip_viewpage;

    private TextView g_mianhuo;
    private TextView g_tuihuo;
    private TextView g_chihuo;
    private TextView g_kefu;
    private TextView g_moban;
    private TextView g_fuli;
    private TextView g_zhanshi;

    private TextView h_xiaofei;
    private TextView h_mianhuo;
    private TextView h_tuihuo;
    private TextView h_chihuo;
    private TextView h_kefu;
    private TextView h_moban;
    private TextView h_fuli;
    private TextView h_zhanshi;

    // 引导页图片资源
    private static final int[] pics = {R.layout.viewpage_vip_haungjin, R.layout.viewpage_vip_hehuo};
    // 底部小点图片
    private ImageView[] dots;
    // 记录当前选中位置
    private int currentIndex;
    private List<View> views;

    private String gMoney;//黄金
    private String hMoney;//合伙人
    private String sumMoney;//需要购买时的金额

    private int level_id1;//黄金的id
    private int level_id2;//合伙人的id
    private int level_id;//需要购买时的id

    private String sumName;//需要购买时的名字

    public VipExplainDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.vip_explain_dialog, null);
        tv_vip_payment = (TextView) mDialogView.findViewById(R.id.tv_vip_payment);
        ll_vip_out = (LinearLayout) mDialogView.findViewById(R.id.ll_vip_out);
        ll_vip_dian = (LinearLayout) mDialogView.findViewById(R.id.ll_vip_dian);
        vip_viewpage = (ViewPager) mDialogView.findViewById(R.id.vip_viewpage);

        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        lp.y = 500;

        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        views = new ArrayList<View>();
        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(mDialog.getContext()).inflate(pics[i], null);
            views.add(view);
        }

        GuideViewPager3Adapter adapter = new GuideViewPager3Adapter(views);
        vip_viewpage.setAdapter(adapter);
        vip_viewpage.addOnPageChangeListener(new PageChangeListener());

        initDots();

        g_mianhuo = views.get(0).findViewById(R.id.g_mianhuo);
        g_tuihuo = views.get(0).findViewById(R.id.g_tuihuo);
        g_chihuo = views.get(0).findViewById(R.id.g_chihuo);
        g_kefu = views.get(0).findViewById(R.id.g_kefu);
        g_moban = views.get(0).findViewById(R.id.g_moban);
        g_fuli = views.get(0).findViewById(R.id.g_fuli);
        g_zhanshi = views.get(0).findViewById(R.id.g_zhanshi);

        h_xiaofei = views.get(1).findViewById(R.id.h_xiaofei);
        h_mianhuo = views.get(1).findViewById(R.id.h_mianhuo);
        h_tuihuo = views.get(1).findViewById(R.id.h_tuihuo);
        h_chihuo = views.get(1).findViewById(R.id.h_chihuo);
        h_kefu = views.get(1).findViewById(R.id.h_kefu);
        h_moban = views.get(1).findViewById(R.id.h_moban);
        h_fuli = views.get(1).findViewById(R.id.h_fuli);
        h_zhanshi = views.get(1).findViewById(R.id.h_zhanshi);

        VipExplainBean vipExplainBean = builder.getVipExplainBean();
        VipExplainBean.Level2Bean level2 = vipExplainBean.level2;
        g_mianhuo.setText(level2.freeZhuan + "砖");
        g_tuihuo.setText(level2.straitBonus + "%");
//        g_chihuo.setText(level2.continueBonusTime);
        g_kefu.setText(level2.customService.trim());
//        g_moban.setText(level2.moduleNum);
        g_fuli.setText(level2.recommendZhuan + "砖");
//        g_zhanshi.setText(level2.showMarket);
        gMoney = level2.money;
        level_id1 = level2.level_id;

        VipExplainBean.Level3Bean level3 = vipExplainBean.level3;
        h_xiaofei.setText(level3.costRakeBack3 + "%");
        h_mianhuo.setText(level3.freeZhuan + "砖");
        h_tuihuo.setText(level3.straitBonus + "%");
//        h_chihuo.setText(level3.continueBonusTime);
        h_kefu.setText(level3.customService.trim());
//        h_moban.setText(level3.moduleNum);
        h_fuli.setText(level3.recommendZhuan + "砖");
//        h_zhanshi.setText(level3.showMarket);
        hMoney = level3.money;
        level_id2 = level3.level_id;

        level_id = level_id1;
        sumMoney = gMoney;
        sumName = "黄金会员";
        tv_vip_payment.setText("立即以" + gMoney + "元购买");

        tv_vip_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getSingleListener() != null) {
                    mBuilder.getSingleListener().clickSingleButton(VipExplainDialog.this,
                            tv_vip_payment, sumMoney, level_id, sumName);
                }
            }
        });

        ll_vip_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initDots() {
        dots = new ImageView[pics.length];
        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) ll_vip_dian.getChildAt(i);
            dots[i].setEnabled(false);// 都设为灰色
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {
        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            setCurDot(position);
            if (position == 0) {
                level_id = level_id1;
                sumMoney = gMoney;
                sumName = "黄金会员";
                tv_vip_payment.setText("立即以" + gMoney + "元购买");
            } else if (position == 1) {
                level_id = level_id2;
                sumMoney = hMoney;
                sumName = "黄金合伙人";
                tv_vip_payment.setText("立即以" + hMoney + "元购买");
            }
        }
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    public void show() {

        mDialog.show();
    }

    public void dismiss() {

        mDialog.dismiss();
    }

    public Dialog getDialog() {

        return mDialog;
    }

    public static class Builder {

        private DialogInterface.OnVipClickListener<VipExplainDialog> singleListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private VipExplainBean res;

        public Builder(Context context) {
            mContext = context;
            singleListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
        }

        public Context getContext() {

            return mContext;
        }

        public VipExplainBean getVipExplainBean() {
            return res;
        }

        public Builder setVipExplainBean(VipExplainBean res) {
            this.res = res;
            return this;
        }

        public DialogInterface.OnVipClickListener<VipExplainDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnVipClickListener<VipExplainDialog>
                                                 singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public VipExplainDialog build() {

            return new VipExplainDialog(this);
        }
    }


}
