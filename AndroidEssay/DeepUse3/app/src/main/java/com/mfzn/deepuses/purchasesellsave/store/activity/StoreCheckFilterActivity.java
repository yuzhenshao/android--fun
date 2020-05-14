package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class StoreCheckFilterActivity extends BasicActivity {

    @BindView(R.id.date_edit)
    EditText dateEdit;
    @BindView(R.id.filter_container)
    LinearLayout filterView;
    @BindView(R.id.store_recyleview)
    RecyclerView storeRecyleview;
    @BindView(R.id.status_recyleview)
    RecyclerView statusRecyleview;
    private TimePickerView pvTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    private void initData() {
        initTimePicker();

    }

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                dateEdit.setText(format.format(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setSubCalSize(15)
                .setSubmitColor(R.color.color_4A90E2)
                .setCancelColor(R.color.color_4A90E2)
                .setOutSideCancelable(true)
                .isDialog(true)
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

    @OnClick({R.id.ll_se_del, R.id.date_selected, R.id.save_btn, R.id.search_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_se_del:
                filterView.setVisibility(View.GONE);
                break;
            case R.id.date_selected:
                pvTime.show(view);
            case R.id.save_btn:

                break;
            case R.id.search_container:
                break;
        }
    }
}
