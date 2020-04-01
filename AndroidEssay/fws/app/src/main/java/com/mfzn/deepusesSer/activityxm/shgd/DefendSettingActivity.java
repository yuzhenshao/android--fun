package com.mfzn.deepusesSer.activityxm.shgd;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.DefendSettingAdapter;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.model.xiangmu.DefendSettingModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DefendSettingActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.def_listview)
    ListView defListview;

    private List<DefendSettingModel> models;
    private DefendSettingAdapter adapter;

    private TimePickerView pvTime;
    private int positions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_defend_setting;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_defend_setting));

        models = new ArrayList<>();
        models.add(new DefendSettingModel());

        initTimePicker();

        adapter = new DefendSettingAdapter(this,models);
        defListview.setAdapter(adapter);
        adapter.setOnDeleteitemclickLisenter(new DefendSettingAdapter.OnDeleteitemclickLisenter() {
            @Override
            public void onDeteleItemClick(View view, int position) {
                models.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setOnTimeitemclickLisenter(new DefendSettingAdapter.OnTimeitemclickLisenter() {
            @Override
            public void onTimeItemClick(View view, int position) {
                positions = position;
                pvTime.show(view);
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_def_tj, R.id.but_def_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_def_tj:
                models.add(new DefendSettingModel());
                adapter.notifyDataSetChanged();
                break;
            case R.id.but_def_commit:
                break;
        }
    }

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                DefendSettingModel defendSettingModel = models.get(positions);
                defendSettingModel.setTimes(getTime(date));
                adapter.notifyDataSetChanged();
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }
}
