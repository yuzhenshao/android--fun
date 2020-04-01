package com.mfzn.deepusesSer.activityxm.shgd;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.bean.request.AcceptSendOrderRequest;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.present.shouhou.JiedanHuizhiPresnet;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class JiedanHuizhiActivity extends BaseMvpActivity<JiedanHuizhiPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_if_bold)
    TextView tvIfBold;
    @BindView(R.id.et_fwsj)
    TextView etFwsj;
    @BindView(R.id.iv_if_sm)
    ImageView ivIfSm;
    @BindView(R.id.iv_if_yczd)
    ImageView ivIfYczd;

    private int type = 1;
    private TimePickerView pvTime;
    private String orderNo = "";
    @Override
    public int getLayoutId() {
        return R.layout.activity_jiedan_huizhi;
    }

    @Override
    public JiedanHuizhiPresnet newP() {
        return new JiedanHuizhiPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("接单回执");
        tvIfBold.getPaint().setFakeBoldText(true);
        orderNo = getIntent().getStringExtra("orderNo");
        initTimePicker();
        if(type == 1) {
            ivIfSm.setImageResource(R.mipmap.work_xuanzhong);
            ivIfYczd.setImageResource(R.mipmap.work_weixuan);
        }else  if(type == 2) {
            ivIfSm.setImageResource(R.mipmap.work_weixuan);
            ivIfYczd.setImageResource(R.mipmap.work_xuanzhong);
        }
    }

    @OnClick({R.id.iv_login_back, R.id.ll_if_sm, R.id.ll_if_yczd, R.id.but_if_qr,R.id.et_fwsj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_if_sm:
                type = 1;
                ivIfSm.setImageResource(R.mipmap.work_xuanzhong);
                ivIfYczd.setImageResource(R.mipmap.work_weixuan);
                break;
            case R.id.ll_if_yczd:
                type = 2;
                ivIfSm.setImageResource(R.mipmap.work_weixuan);
                ivIfYczd.setImageResource(R.mipmap.work_xuanzhong);
                break;
            case R.id.et_fwsj:
                pvTime.show(view);
                break;
            case R.id.but_if_qr:
                AcceptSendOrderRequest request=new AcceptSendOrderRequest();
                request.setOrderNo(orderNo);
                request.setIsAccept("1");
                request.setServiceType(type+"");
                request.setServiceTime(etFwsj.getText().toString());
                getP().jiedan(request);
                break;
        }
    }

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(JiedanHuizhiActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                etFwsj.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setSubCalSize(15)
                .setSubmitColor(R.color.color_4A90E2)//确定按钮文字颜色
                .setCancelColor(R.color.color_4A90E2)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
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

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }

    public void jiedanHuizhiResult(HttpResult result){
        showMessage(result.getMsg());
        if (result.getStatus() == 1){
            Intent intent = new Intent();
            setResult(0,intent);
            finish();
        }
//        else{
//            Intent intent = new Intent();
//            setResult(1,intent);
//            finish();
//        }
    }
}
