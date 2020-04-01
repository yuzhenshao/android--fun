package com.mfzn.deepusesSer.activityxm.shhf;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.xmhf.AddReturnVisitPresent;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class AddReturnVisitActivity extends BaseMvpActivity<AddReturnVisitPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;
    @BindView(R.id.et_vis_problem)
    EditText etVisProblem;
    @BindView(R.id.et_vis_tiem)
    EditText etVisTiem;
    @BindView(R.id.et_vis_content)
    EditText etVisContent;
    @BindView(R.id.et_vis_nexttiem)
    EditText etVisNexttiem;
    @BindView(R.id.but_vis_commit)
    Button butVisCommit;

    private TimePickerView pvTime;
    private int timeType = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_return_visit;
    }

    @Override
    public AddReturnVisitPresent newP() {
        return new AddReturnVisitPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_add_return_visit));
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("回访记录");

        butVisCommit.setEnabled(true);

        etVisTiem.addTextChangedListener(mOnInputChangeListener);
        etVisContent.addTextChangedListener(mOnInputChangeListener);
        etVisNexttiem.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_content, R.id.et_vis_problem, R.id.et_vis_tiem, R.id.et_vis_nexttiem, R.id.but_vis_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content:
                startActivity(new Intent(this, VisitRecordActivity.class));
                break;
            case R.id.et_vis_problem:
                break;
            case R.id.et_vis_tiem:
                timeType = 1;
                pvTime.show(view);
                break;
            case R.id.et_vis_nexttiem:
                timeType = 2;
                pvTime.show(view);
                break;
            case R.id.but_vis_commit:
                startActivity(new Intent(this, AddVisitSuccessActivity.class));
                break;
        }
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(etVisTiem.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etVisContent.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etVisNexttiem.getText().toString().trim())) {
                butVisCommit.setEnabled(true);
                butVisCommit.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butVisCommit.setEnabled(false);
                butVisCommit.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timeType == 1) {
//                    startDate = getTime1(date);
                    etVisTiem.setText(getTime(date));
                } else if (timeType == 2) {
//                    endDate = getTime1(date);
                    etVisNexttiem.setText(getTime(date));
                }
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

    private String getTime1(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }
}
