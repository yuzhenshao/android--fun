package com.mfzn.deepusesSer.activityxm;

import android.Manifest;
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
import com.mfzn.deepusesSer.model.xiangmu.FoundProjectModel;
import com.mfzn.deepusesSer.present.foundxm.FoundProjectPresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.RxBus;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.ProjectConfirmDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class FoundProjectActivity extends BaseMvpActivity<FoundProjectPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_fou_jb)
    TextView tvFouJb;
    @BindView(R.id.et_fou_proname)
    EditText etFouProname;
    @BindView(R.id.et_fou_address)
    EditText etFouAddress;
    @BindView(R.id.et_fou_detail)
    EditText etFouDetail;
    @BindView(R.id.et_fou_name)
    EditText etFouName;
    @BindView(R.id.et_fou_phone)
    EditText etFouPhone;
    @BindView(R.id.et_fou_level)
    EditText etFouLevel;
    @BindView(R.id.tv_fou_ht)
    TextView tvFouHt;
    @BindView(R.id.et_fou_gw)
    EditText etFouGw;
    @BindView(R.id.et_fou_money)
    EditText etFouMoney;
    @BindView(R.id.tv_fou_money)
    TextView tvFouMoney;
    @BindView(R.id.et_fou_time)
    EditText etFouTime;
    @BindView(R.id.et_fou_time2)
    EditText etFouTime2;
    @BindView(R.id.but_fou_commit)
    Button butFouCommit;

    private int levelPosition = -1;
    private String levelID;//等级ID
    private String gwID;//销售顾问ID
    private String longitude;//经度
    private String latitude;//纬度

    private TimePickerView pvTime;

    private String startDate;
    private String endDate;
    private int timeType = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_found_project;
    }

    @Override
    public FoundProjectPresnet newP() {
        return new FoundProjectPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_found_project));
        tvFouJb.getPaint().setFakeBoldText(true);
        tvFouHt.getPaint().setFakeBoldText(true);
        tvFouMoney.getPaint().setFakeBoldText(true);

        initTimePicker();

        etFouProname.addTextChangedListener(mOnInputChangeListener);
        etFouAddress.addTextChangedListener(mOnInputChangeListener);
        etFouDetail.addTextChangedListener(mOnInputChangeListener);
        etFouName.addTextChangedListener(mOnInputChangeListener);
        etFouPhone.addTextChangedListener(mOnInputChangeListener);
        etFouLevel.addTextChangedListener(mOnInputChangeListener);
        etFouGw.addTextChangedListener(mOnInputChangeListener);
        etFouMoney.addTextChangedListener(mOnInputChangeListener);
        etFouTime.addTextChangedListener(mOnInputChangeListener);
        etFouTime2.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.et_fou_address, R.id.et_fou_level, R.id.et_fou_gw, R.id.et_fou_time, R.id.but_fou_commit, R.id.et_fou_time2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_fou_address:
                getRxPermissions()
                        .request(Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    startActivityForResult(new Intent(context, MapLocationActivity.class), Constants.MAP_LOCATION);
                                } else {
                                    getvDelegate().toastShort("亲，请先开启定位权限");
                                }
                            }
                        });
                break;
            case R.id.et_fou_level:
                Intent intent = new Intent(this, ProjectLevelActivity.class);
                intent.putExtra(Constants.PROJECT_LEVEL_POSITION, levelPosition);
                startActivityForResult(intent, Constants.FOUND_PROJECT_LEVEL);
                break;
            case R.id.et_fou_gw:
                startActivityForResult(new Intent(this, SelectPersonActivity.class), Constants.SELECT_PERSON);
                break;
            case R.id.et_fou_time:
                timeType = 1;
                pvTime.show(view);
                break;
            case R.id.et_fou_time2:
                if (TextUtils.isEmpty(etFouTime.getText().toString())) {
                    ToastUtil.showToast(this, "请先选择开始时间");
                    return;
                }
                timeType = 2;
                pvTime.show(view);
                break;
            case R.id.but_fou_commit:
                commitData();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.FOUND_PROJECT_LEVEL == requestCode) {
            if (data != null) {
                levelPosition = data.getIntExtra(Constants.PROJECT_LEVEL_POSITION, -1);
                levelID = data.getStringExtra(Constants.PROJECT_LEVEL_ID);
                String name = data.getStringExtra(Constants.PROJECT_LEVEL_NAME);
                etFouLevel.setText(name);
            }
        } else if (Constants.SELECT_PERSON == requestCode) {
            if (data != null) {
                gwID = data.getStringExtra(Constants.SELECT_PERSON_ID);
                String name = data.getStringExtra(Constants.SELECT_PERSON_NAME);
                etFouGw.setText(name);
            }
        } else if (Constants.MAP_LOCATION == requestCode) {
            if (data != null) {
                String weizhi = data.getStringExtra(Constants.MAP_LOCATION_WEIZHI);
                longitude = data.getStringExtra(Constants.MAP_LOCATION_JINGDU);
                latitude = data.getStringExtra(Constants.MAP_LOCATION_WEIDU);
                String address = data.getStringExtra(Constants.MAP_LOCATION_ADDRESS);
                etFouAddress.setText(weizhi);
                etFouDetail.setText(address);
            }
        }
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String proname = etFouProname.getText().toString().trim();
            String quyu = etFouAddress.getText().toString().trim();
            String address = etFouDetail.getText().toString().trim();
            String name = etFouName.getText().toString().trim();
            String phone = etFouPhone.getText().toString().trim();
            String level = etFouLevel.getText().toString().trim();
            String gw = etFouGw.getText().toString().trim();
            String money = etFouMoney.getText().toString().trim();
            String startTime = etFouTime.getText().toString().trim();
            String endTime = etFouTime2.getText().toString().trim();
            if (!TextUtils.isEmpty(proname) && !TextUtils.isEmpty(quyu) && !TextUtils.isEmpty(address) &&
                    !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(level) &&
                    !TextUtils.isEmpty(gw) && !TextUtils.isEmpty(money) && !TextUtils.isEmpty(startTime) &&
                    !TextUtils.isEmpty(endTime)) {
                butFouCommit.setEnabled(true);
                butFouCommit.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butFouCommit.setEnabled(false);
                butFouCommit.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    private void commitData() {
        String proname = etFouProname.getText().toString().trim();
        String details = etFouDetail.getText().toString().trim();
        String name = etFouName.getText().toString().trim();
        String phone = etFouPhone.getText().toString().trim();
        String money = etFouMoney.getText().toString().trim();
        String startTime = etFouTime.getText().toString().trim();
        String endTime = etFouTime2.getText().toString().trim();
        String address = etFouAddress.getText().toString().trim();

        new ProjectConfirmDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setStartTime(startDate)
                .setEndTime(endDate)
                .setProName(proname)
                .setCanceledOnTouchOutside(true)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<ProjectConfirmDialog>() {
                    @Override
                    public void clickLeftButton(ProjectConfirmDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(ProjectConfirmDialog dialog, View view) {
                        getP().foundProject(proname,latitude,longitude,details,name,phone,gwID,money,levelID,startTime,endTime,address);
                    }
                })
                .build()
                .show();
    }

    public void foundProjectSuccess(FoundProjectModel model) {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.FOUNDPROJECT);
        RxBus.getInstance().post(eventMsg);
        Intent intent = new Intent(this, FoundSuccessActivity.class);
        intent.putExtra(Constants.FOUND_PROJECT_PROID,model.getProID());
        startActivity(intent);
        finish();
    }

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timeType == 1) {
                    startDate = getTime1(date);
                    etFouTime.setText(getTime(date));
                } else if (timeType == 2) {
                    endDate = getTime1(date);
                    etFouTime2.setText(getTime(date));
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
