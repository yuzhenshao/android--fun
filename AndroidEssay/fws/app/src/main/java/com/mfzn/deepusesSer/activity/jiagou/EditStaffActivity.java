package com.mfzn.deepusesSer.activity.jiagou;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.present.jiagou.EditStaffPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.RxBus;
import com.mfzn.deepusesSer.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class EditStaffActivity extends BaseMvpActivity<EditStaffPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.et_edit_name)
    EditText etEditName;
    @BindView(R.id.iv_edit_name)
    ImageView ivEditName;
    @BindView(R.id.tv_edit_phone)
    TextView tvEditPhone;
    @BindView(R.id.tv_edit_bm)
    EditText tvEditBm;
    @BindView(R.id.et_edit_time)
    EditText etEditTime;

    private String oldDepartmentID;
    private String newDepartmentID;
    private TimePickerView pvTime;
    private String uid;
    private String positionName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_staff;
    }

    @Override
    public EditStaffPresent newP() {
        return new EditStaffPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_edit_staff));
        tvBassComlate.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String type = intent.getStringExtra(Constants.EDIT_STAFF_TYPE);
        if(type.equals("1")){
            ZuzhiJiagouModel model = (ZuzhiJiagouModel) intent.getSerializableExtra(Constants.EDIT_STAFF);
            int extra = intent.getIntExtra(Constants.EDIT_STAFF_POSITION, 0);
            etEditName.setText(model.getStaff().get(extra).getU_name());
            tvEditPhone.setText(model.getStaff().get(extra).getU_phone());
            tvEditBm.setText(model.getDepartmentName());
            oldDepartmentID = model.getDepartmentID() + "";
            uid = model.getStaff().get(extra).getUid();
            positionName = model.getStaff().get(extra).getPositionName();
        }if(type.equals("2")){
            ZuzhiJiagouModel model = (ZuzhiJiagouModel) intent.getSerializableExtra(Constants.EDIT_STAFF);
            int extra = intent.getIntExtra(Constants.EDIT_STAFF_POSITION, 0);
            int extra2 = intent.getIntExtra(Constants.EDIT_STAFF_POSITION2, 0);
            ZuzhiJiagouModel.SonsBeanX.StaffBeanX staffBeanX = model.getSons().get(extra).getStaff().get(extra2);
            etEditName.setText(staffBeanX.getU_name());
            tvEditPhone.setText(staffBeanX.getU_phone());
            tvEditBm.setText(model.getSons().get(extra).getDepartmentName());
            oldDepartmentID = model.getSons().get(extra).getDepartmentID() + "";
            uid = staffBeanX.getUid();
            positionName = staffBeanX.getPositionName();
        }if(type.equals("3")){
            ZuzhiJiagouModel model = (ZuzhiJiagouModel) intent.getSerializableExtra(Constants.EDIT_STAFF);
            int extra = intent.getIntExtra(Constants.EDIT_STAFF_POSITION, 0);
            int extra2 = intent.getIntExtra(Constants.EDIT_STAFF_POSITION2, 0);
            int extra3 = intent.getIntExtra(Constants.EDIT_STAFF_POSITION3, 0);
            ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean staffBeanX = model.getSons().get(extra).getSons().get(extra2).getStaff().get(extra3);
            etEditName.setText(staffBeanX.getU_name());
            tvEditPhone.setText(staffBeanX.getU_phone());
            tvEditBm.setText(model.getSons().get(extra).getSons().get(extra2).getDepartmentName());
            oldDepartmentID = model.getSons().get(extra).getSons().get(extra2).getDepartmentID() + "";
            uid = staffBeanX.getUid();
            positionName = staffBeanX.getPositionName();
        }

    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.iv_edit_name, R.id.tv_edit_bm, R.id.et_edit_time, R.id.tv_edit_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:
                String name = etEditName.getText().toString().trim();
                if(TextUtils.isEmpty(name)) {
                    ToastUtil.showToast(this,"请输入姓名");
                    return;
                }
                String bm = tvEditBm.getText().toString().trim();
                if(TextUtils.isEmpty(bm)) {
                    ToastUtil.showToast(this,"请选择部门");
                    return;
                }
                String time = etEditTime.getText().toString().trim();
                if(TextUtils.isEmpty(time)) {
                    ToastUtil.showToast(this,"请选择入职时间");
                    return;
                }
                getP().editStaff(uid,positionName,oldDepartmentID,newDepartmentID,time,name);
                break;
            case R.id.iv_edit_name:
                etEditName.getText().clear();
                break;
            case R.id.tv_edit_bm:
                startActivityForResult(new Intent(this, DepartmentActivity.class),Constants.EDIT_STAFF_BM);
                break;
            case R.id.et_edit_time:
                initTimePicker();
                pvTime.show(view);
                break;
            case R.id.tv_edit_delete:
                getP().deleteStaff(uid,oldDepartmentID);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.EDIT_STAFF_BM == requestCode){
            if(data != null) {
                newDepartmentID = data.getStringExtra(Constants.EDIT_STAFF_ID);
                String name = data.getStringExtra(Constants.EDIT_STAFF_NAME);
                tvEditBm.setText(name);
            }
        }
    }

    public void editStaffSuccess() {
        ToastUtil.showToast(this, "编辑成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void deleteStaffSuccess() {
        ToastUtil.showToast(this, "删除成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                etEditTime.setText(getTime1(date));
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

    private String getTime1(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
