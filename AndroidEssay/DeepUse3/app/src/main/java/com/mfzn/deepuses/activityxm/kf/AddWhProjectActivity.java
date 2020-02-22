package com.mfzn.deepuses.activityxm.kf;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.xmkefu.AddCustomPresnet;
import com.mfzn.deepuses.present.xmkefu.AddWhProjectPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWhProjectActivity extends BaseMvpActivity<AddWhProjectPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_wb_name)
    EditText etWbName;
    @BindView(R.id.et_wb_time)
    EditText etWbTime;
    @BindView(R.id.et_wb_day)
    EditText etWbDay;
    @BindView(R.id.but_wb_qr)
    Button butWbQr;

    private TimePickerView pvTime;

    private String pro_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_wh_project;
    }

    @Override
    public AddWhProjectPresnet newP() {
        return new AddWhProjectPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_add_wb_project));

        pro_id = getIntent().getStringExtra(Constants.SHOUHOU_PROID);

        initTimePicker();

        etWbName.addTextChangedListener(mOnInputChangeListener);
        etWbTime.addTextChangedListener(mOnInputChangeListener);
        etWbDay.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.et_wb_time, R.id.but_wb_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_wb_time:
                pvTime.show(view);
                break;
            case R.id.but_wb_qr:
                String name = etWbName.getText().toString().trim();
                String time = etWbTime.getText().toString().trim();
                String day = etWbDay.getText().toString().trim();
                getP().addWhProject(pro_id,name,time,day);
                break;
        }
    }

    public void addWhProjectSuccess(String models) {
        ToastUtil.showToast(this,models);
        Intent intent = new Intent();
        intent.putExtra("xzcz", "cxzc");
        setResult(Constants.WB_SETTING,intent);
        finish();
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(etWbName.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etWbDay.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etWbTime.getText().toString().trim())) {
                butWbQr.setEnabled(true);
                butWbQr.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butWbQr.setEnabled(false);
                butWbQr.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                etWbTime.setText(getTime(date));
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
