package com.mfzn.deepuses.activity.khgl;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.MyTaskModel;
import com.mfzn.deepuses.present.customer.AddTaskPresnet;
import com.mfzn.deepuses.present.customer.EditTaskPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.ToastUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class EditTaskActivity extends BaseMvpActivity<EditTaskPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_ta_name)
    TextView etTaName;
    @BindView(R.id.et_ta_phone)
    TextView etTaPhone;
    @BindView(R.id.et_ta_time)
    EditText etTaTime;
    @BindView(R.id.et_ta_tq)
    EditText etTaTq;
    @BindView(R.id.et_ta_remark)
    EditText etTaRemark;
    @BindView(R.id.but_ta_commit)
    Button butTaCommit;

    private TimePickerView pvTime;

    private String taskid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_task;
    }

    @Override
    public EditTaskPresnet newP() {
        return new EditTaskPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText("编辑任务");

        MyTaskModel.DataBean dataBean = (MyTaskModel.DataBean) getIntent().getSerializableExtra(Constants.EDIT_TASK);
        etTaName.setText(dataBean.getCustomerName());
        etTaPhone.setText(dataBean.getCustomerPhone());
        etTaTime.setText(DateUtils.stampToDateTime(String.valueOf(dataBean.getTaskTime())));
        etTaTq.setText(String.valueOf(dataBean.getNoticeTime()));
        etTaRemark.setText(dataBean.getRemark());

        taskid = String.valueOf(dataBean.getData_id());

        initTimePicker();

        etTaTime.addTextChangedListener(mOnInputChangeListener);
        etTaTq.addTextChangedListener(mOnInputChangeListener);
        etTaRemark.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.et_ta_time, R.id.but_ta_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_ta_time:
                pvTime.show(view);
                break;
            case R.id.but_ta_commit:
                String time = etTaTime.getText().toString().trim();
                String stringToDate2 = DateUtils.getStringToDate2(time);
                getP().editTask(taskid,stringToDate2,etTaTq.getText().toString().trim(),etTaRemark.getText().toString().trim());
                break;
        }
    }

    public void editTaskSuccess() {
        ToastUtil.showToast(this,"编辑成功");
        Intent intent = new Intent();
        intent.putExtra("ddd","gfgf");
        setResult(Constants.EDIT_TASKS,intent);
        finish();
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String time = etTaTime.getText().toString().trim();
            String td = etTaTq.getText().toString().trim();
            String remark = etTaRemark.getText().toString().trim();
            if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(td) && !TextUtils.isEmpty(remark)) {
                butTaCommit.setEnabled(true);
                butTaCommit.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butTaCommit.setEnabled(false);
                butTaCommit.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                etTaTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
