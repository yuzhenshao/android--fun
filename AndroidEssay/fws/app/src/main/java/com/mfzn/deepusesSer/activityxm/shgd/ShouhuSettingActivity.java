package com.mfzn.deepusesSer.activityxm.shgd;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class ShouhuSettingActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_shou_name)
    TextView tvShouName;
    @BindView(R.id.tv_shou_phone)
    TextView tvShouPhone;
    @BindView(R.id.tv_shou_address)
    TextView tvShouAddress;
    @BindView(R.id.et_shou_zbqx)
    EditText etShouZbqx;
    @BindView(R.id.tv_shou_start)
    TextView tvShouStart;
    @BindView(R.id.et_shou_end)
    EditText etShouEnd;
    @BindView(R.id.et_shou_nexttime)
    EditText etShouNexttime;
    @BindView(R.id.et_shou_hfjg)
    EditText etShouHfjg;
    @BindView(R.id.ib_shou_zbyj)
    ImageButton ibShouZbyj;
    @BindView(R.id.ib_shou_wbyj)
    ImageButton ibShouWbyj;
    @BindView(R.id.ib_shou_hftx)
    ImageButton ibShouHftx;
    @BindView(R.id.tv_shou_ybdq)
    TextView tvShouYbdq;
    @BindView(R.id.et_shou_ybjz)
    EditText etShouYbjz;

    private TimePickerView pvTime;
    private int typeTime = 1;// 1 质保截止日期 2 下次回访时间 3 延保截止日期

    @Override
    public int getLayoutId() {
        return R.layout.activity_shouhu_setting;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_shouhu_setting));

        initTimePicker();
    }

    @OnClick({R.id.iv_login_back, R.id.et_shou_end, R.id.ll_shou_whsz, R.id.et_shou_nexttime, R.id.ib_shou_zbyj,
            R.id.ib_shou_wbyj, R.id.ib_shou_hftx, R.id.et_shou_ybjz, R.id.but_shou_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_shou_end:
                typeTime = 1;
                pvTime.show(view);
                break;
            case R.id.ll_shou_whsz:
                Intent intent = new Intent(this, DefendSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.et_shou_nexttime:
                typeTime = 2;
                pvTime.show(view);
                break;
            case R.id.ib_shou_zbyj:
                break;
            case R.id.ib_shou_wbyj:
                break;
            case R.id.ib_shou_hftx:
                break;
            case R.id.et_shou_ybjz:
                typeTime = 3;
                pvTime.show(view);
                break;
            case R.id.but_shou_qr:
                break;
        }
    }

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if(typeTime == 1){
                    etShouEnd.setText(getTime(date));
                }else if(typeTime == 2){
                    etShouNexttime.setText(getTime(date));
                }else if(typeTime == 3){
                    etShouYbjz.setText(getTime(date));
                }
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setSubCalSize(15)
                .setSubmitColor(R.color.color_303133)//确定按钮文字颜色
                .setCancelColor(R.color.color_606266)//取消按钮文字颜色
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
