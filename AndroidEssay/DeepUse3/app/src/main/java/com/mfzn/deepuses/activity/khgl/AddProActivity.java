package com.mfzn.deepuses.activity.khgl;

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
import android.widget.ImageButton;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.MapLocationActivity;
import com.mfzn.deepuses.activityxm.SelectPersonActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.FoundProjectModel;
import com.mfzn.deepuses.present.customer.AddProPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ObtainTime;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.ProjectConfirmDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class AddProActivity extends BaseMvpActivity<AddProPresnet> {

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
    TextView etFouName;
    @BindView(R.id.et_fou_phone)
    TextView etFouPhone;
    @BindView(R.id.tv_fou_ht)
    TextView tvFouHt;
    @BindView(R.id.et_fou_gw)
    EditText etFouGw;
    @BindView(R.id.et_fou_money)
    EditText etFouMoney;
    @BindView(R.id.tv_fou_money)
    TextView tvFouMoney;
    @BindView(R.id.et_fou_zbqx)
    EditText etFouZbqx;
    @BindView(R.id.tv_fou_start)
    EditText etFouStart;
    @BindView(R.id.et_fou_end)
    EditText etFouEnd;
    @BindView(R.id.but_fou_commit)
    Button butFouCommit;
    @BindView(R.id.ib_shou_zbyj)
    ImageButton ib_shou_zbyj;

    private int levelPosition = -1;
    private String levelID = "";//等级ID
    private String gwID = "";//销售顾问ID
    private String longitude;//经度
    private String latitude;//纬度

    private TimePickerView pvTime;

    private int zbType = 0;// 0提醒 1不提醒
    private String kehuID;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_pro;
    }

    @Override
    public AddProPresnet newP() {
        return new AddProPresnet();
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

        Intent intent = getIntent();
        String name = intent.getStringExtra(Constants.ADD_NAME);
        String phone = intent.getStringExtra(Constants.ADD_PHONE);
        kehuID = intent.getStringExtra(Constants.ADD_ID);
        etFouName.setText(name);
        etFouPhone.setText(phone);

        initTimePicker();

        etFouProname.addTextChangedListener(mOnInputChangeListener);
        etFouAddress.addTextChangedListener(mOnInputChangeListener);
        etFouDetail.addTextChangedListener(mOnInputChangeListener);
        etFouZbqx.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num = etFouZbqx.getText().toString().trim();
                if(TextUtils.isEmpty(num)) {
                    etFouEnd.getText().clear();
                }else {
                    String time = etFouStart.getText().toString().trim();
                    if(!TextUtils.isEmpty(time)) {
                        String[] split = time.split("/");

                        int yb = Integer.parseInt(num);
                        int year = Integer.parseInt(split[0]);
                        int month = Integer.parseInt(split[1]);
                        int day = Integer.parseInt(split[2]);

                        int i = month + yb;

                        while (i > 12) {
                            i = i - 12;
                            year++;
                        }
                        etFouEnd.setText(ObtainTime.showTime(year,i,day));
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.et_fou_address, R.id.et_fou_gw,
            R.id.tv_fou_start, R.id.but_fou_commit, R.id.ib_shou_zbyj})
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
            case R.id.et_fou_gw:
                UserHelper.setSelectNmae("1");
                startActivityForResult(new Intent(this, SelectPersonActivity.class), Constants.SELECT_PERSON);
                break;
            case R.id.tv_fou_start:
                pvTime.show(view);
                break;
            case R.id.ib_shou_zbyj:
                if(TextUtils.isEmpty(etFouZbqx.getText().toString().trim()) || TextUtils.isEmpty(etFouStart.getText().toString().trim())) {
                    ToastUtil.showToast(this,"请先完成质保设置");
                    return;
                }
                if(zbType == 0) {
                    ib_shou_zbyj.setImageResource(R.mipmap.shou_open);
                    zbType = 1;
                }else {
                    ib_shou_zbyj.setImageResource(R.mipmap.shou_close);
                    zbType = 0;
                }
                break;
            case R.id.but_fou_commit:
                commitData();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_PERSON == requestCode) {
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
            if (!TextUtils.isEmpty(proname) && !TextUtils.isEmpty(quyu) && !TextUtils.isEmpty(address)) {
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
        String address = etFouAddress.getText().toString().trim();
        String zbqx = etFouZbqx.getText().toString().trim();
        String startTime = etFouStart.getText().toString().trim();
        String start = "";
        String start2 = "";
        if(TextUtils.isEmpty(startTime)) {
            start = "暂无";
            start2 = "";
        }else {
            start = startTime;
            start2 = DateUtils.getStringToDate(startTime);
        }
        String endTime = etFouEnd.getText().toString().trim();
        String end = "";
        String end2 = "";
        if(TextUtils.isEmpty(endTime)) {
            end = "暂无";
            end2 = "";
        }else {
            end = endTime;
            end2 = DateUtils.getStringToDate(endTime);
        }

        String finalStart = start2;
        String finalEnd = end2;
        new ProjectConfirmDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setStartTime(start)
                .setEndTime(end)
                .setProName(proname)
                .setCanceledOnTouchOutside(true)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<ProjectConfirmDialog>() {
                    @Override
                    public void clickLeftButton(ProjectConfirmDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(ProjectConfirmDialog dialog, View view) {
                        getP().foundProject(proname,latitude,longitude,details,name,phone,gwID,money,levelID,
                                address, zbqx,finalStart, finalEnd,zbType + "",kehuID);
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    public void foundProjectSuccess(FoundProjectModel model) {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.FOUNDPROJECT);
        RxBus.getInstance().post(eventMsg);
//        Intent intent = new Intent(this, FoundSuccessActivity.class);
//        intent.putExtra(Constants.FOUND_PROJECT_PROID,model.getProID());
//        startActivity(intent);
        ToastUtil.showToast(this,"创建成功");
        finish();
    }

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String num = etFouZbqx.getText().toString().trim();
                if(TextUtils.isEmpty(num)) {
                    etFouStart.setText(getTime(date));
                }else {
                    String time = getTime(date);
                    String[] split = time.split("/");

                    int yb = Integer.parseInt(num);
                    int year = Integer.parseInt(split[0]);
                    int month = Integer.parseInt(split[1]);
                    int day = Integer.parseInt(split[2]);

                    int i = month + yb;
                    while (i > 12) {
                        i = i - 12;
                        year++;
                    }
                    etFouEnd.setText(ObtainTime.showTime(year,i,day));
                    etFouStart.setText(getTime(date));
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
}
