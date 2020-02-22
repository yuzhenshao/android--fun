package com.mfzn.deepuses.activityxm.shhf;

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

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xmhf.VisitRrcordModel;
import com.mfzn.deepuses.present.xmhf.AddReturnVisitPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReturnVisitActivity extends BaseMvpActivity<AddReturnVisitPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
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

    private String pro_id;

    private OptionsPickerView pickerView;
    private List<String> listType;//工单类型

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

        butVisCommit.setEnabled(true);

        pro_id = getIntent().getStringExtra(Constants.SHOUHOU_PROID);

        etVisProblem.setText("无回访问题");

        //售后类型
        listType = new ArrayList<>();
        listType.add("无回访问题");
        listType.add("有回访问题");
        initPartmentPicker();
        initTimePicker();

        etVisTiem.addTextChangedListener(mOnInputChangeListener);
        etVisContent.addTextChangedListener(mOnInputChangeListener);
        etVisNexttiem.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.et_vis_problem, R.id.et_vis_tiem, R.id.et_vis_nexttiem, R.id.but_vis_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_vis_problem:
                pickerView.show(view);
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
                String title = etVisProblem.getText().toString().trim();
                String time = etVisTiem.getText().toString().trim();
                String content = etVisContent.getText().toString().trim();
                String nexttime = etVisNexttiem.getText().toString().trim();
                getP().addReturnVisit(pro_id,title,time,content,nexttime);
                break;
        }
    }

    public void addReturnVisitSuccess() {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.ADD_KEFU_SUCC);
        RxBus.getInstance().post(eventMsg);
        startActivity(new Intent(this, AddVisitSuccessActivity.class));
        finish();
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

    private void initPartmentPicker(){
        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                etVisProblem.setText(listType.get(options1));
            }
        }).setSubCalSize(15).setSubmitColor(R.color.color_303133)//确定按钮文字颜色
                .setCancelColor(R.color.color_606266)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCyclic(false,false,false)//设置是否循环
                .build();
        pickerView.setPicker(listType, null,null);
        Dialog mDialog = pickerView.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            params.width = getWindowManager().getDefaultDisplay().getWidth();//设置这个才可以全屏展示
            pickerView.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timeType == 1) {
//                    etVisTiem.setText(getTime(date));
                    judgeTime2(getTime(date));
                } else if (timeType == 2) {
//                    etVisNexttiem.setText(getTime(date));
                    judgeTime(getTime(date));
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

    private void judgeTime(String time) {
        String startTime = etVisTiem.getText().toString();
        if(!TextUtils.isEmpty(startTime)){
            int s1 = Integer.parseInt(startTime.substring(0, 4));
            int s2 = Integer.parseInt(startTime.substring(5, 7));
            int s3 = Integer.parseInt(startTime.substring(8, startTime.length()));
            int s4 = Integer.parseInt(time.substring(0, 4));
            int s5 = Integer.parseInt(time.substring(5, 7));
            int s6 = Integer.parseInt(time.substring(8, time.length()));
            if(s4 < s1){
                ToastUtil.showToast(this,"结束日期不能小于开始日期");
                return;
            }else if(s4 == s1 && s5 < s2){
                ToastUtil.showToast(this,"结束日期不能小于开始日期");
                return;
            }else if(s4 == s1 && s5 == s2 && s6 < s3){
                ToastUtil.showToast(this,"结束日期不能小于开始日期");
                return;
            }else {
                etVisNexttiem.setText(time);
            }
        }else {
            etVisNexttiem.setText(time);
        }
    }
    private void judgeTime2(String time) {
        String endtTime = etVisNexttiem.getText().toString();
        if(!TextUtils.isEmpty(endtTime)){
            int s1 = Integer.parseInt(endtTime.substring(0, 4));
            int s2 = Integer.parseInt(endtTime.substring(5, 7));
            int s3 = Integer.parseInt(endtTime.substring(8, endtTime.length()));
            int s4 = Integer.parseInt(time.substring(0, 4));
            int s5 = Integer.parseInt(time.substring(5, 7));
            int s6 = Integer.parseInt(time.substring(8, time.length()));
            if(s1 < s4){
                ToastUtil.showToast(this,"开始日期不能大于结束日期");
                return;
            }else if(s4 == s1 && s2 < s5){
                ToastUtil.showToast(this,"开始日期不能大于结束日期");
                return;
            }else if(s4 == s1 && s5 == s2 && s3 < s6){
                ToastUtil.showToast(this,"开始日期不能大于结束日期");
                return;
            }else {
                etVisTiem.setText(time);
            }
        }else {
            etVisTiem.setText(time);
        }
    }
}
