package com.mfzn.deepuses.activityxm.shgd;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.SettingInfoModel;
import com.mfzn.deepuses.present.xmhf.ShouhouSettingPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ObtainTime;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouhuSettingActivity extends BaseMvpActivity<ShouhouSettingPresent> {

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
    EditText tvShouStart;
    @BindView(R.id.et_shou_end)
    EditText etShouEnd;
    @BindView(R.id.ib_shou_zbyj)
    ImageButton ibShouZbyj;

    @BindView(R.id.et_shou_ybqx)
    EditText etShouYbqx;
    @BindView(R.id.et_shou_ybjz)
    EditText etShouYbjz;
    @BindView(R.id.tv_shou_nexttime)
    EditText tvShouNexttime;
    @BindView(R.id.et_shou_hfjg)
    EditText etShouHfjg;
    @BindView(R.id.ib_shou_ybyj)
    ImageButton ibShouYbyj;
    @BindView(R.id.ib_shou_hftx)
    ImageButton ibShouHftx;

    private TimePickerView pvTime;
    private int typeTime = 1;// 1 质保起始日期 2 下次回访时间

    private String pro_id;

    private int zbType = 0;// 0不提醒 1提醒
    private int ybType = 0;
    private int hfType = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shouhu_setting;
    }

    @Override
    public ShouhouSettingPresent newP() {
        return new ShouhouSettingPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_shouhu_setting));

        Intent intent = getIntent();
        pro_id = intent.getStringExtra(Constants.SHOUHOU_PROID);
        String customName = intent.getStringExtra(Constants.SHOUHOU_NAME);
        String customTel = intent.getStringExtra(Constants.SHOUHOU_PHONE);
        String address = intent.getStringExtra(Constants.SHOUHOU_ADDRESS);
        tvShouName.setText(customName);
        tvShouPhone.setText(customTel);
        tvShouAddress.setText(address);

        getP().settingInfo(pro_id);

        initTimePicker();

        etShouZbqx.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num = etShouZbqx.getText().toString().trim();
                if (TextUtils.isEmpty(num)) {
                    etShouEnd.getText().clear();
                    etShouYbjz.getText().clear();
                } else {
                    String time = tvShouStart.getText().toString().trim();
                    if (!TextUtils.isEmpty(time)) {
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
                        etShouEnd.setText(ObtainTime.showTime(year,i,day));
                    }
                }
            }
        });
        etShouYbqx.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String zbjz = etShouEnd.getText().toString().trim();
                if (TextUtils.isEmpty(zbjz)) {
                    etShouYbjz.getText().clear();
                } else {
                    String ybqx = etShouYbqx.getText().toString().trim();
                    if (!TextUtils.isEmpty(ybqx)) {
                        String[] split = zbjz.split("/");

                        int yb = Integer.parseInt(ybqx);
                        int year = Integer.parseInt(split[0]);
                        int month = Integer.parseInt(split[1]);
                        int day = Integer.parseInt(split[2]);

                        int i = month + yb;

                        while (i > 12) {
                            i = i - 12;
                            year++;
                        }
                        etShouYbjz.setText(ObtainTime.showTime(year,i,day));
                    }else {
                        etShouYbjz.getText().clear();
                    }
                }
            }
        });
        etShouEnd.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    String ybqx = etShouYbqx.getText().toString().trim();
                    if (!TextUtils.isEmpty(ybqx)) {
                        String[] split = s.toString().split("/");

                        int yb = Integer.parseInt(ybqx);
                        int year = Integer.parseInt(split[0]);
                        int month = Integer.parseInt(split[1]);
                        int day = Integer.parseInt(split[2]);

                        int i = month + yb;

                        while (i > 12) {
                            i = i - 12;
                            year++;
                        }
                        etShouYbjz.setText(ObtainTime.showTime(year,i,day));
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.ib_shou_zbyj,R.id.tv_shou_nexttime, R.id.ib_shou_ybyj, R.id.ib_shou_hftx,
            R.id.but_shou_qr, R.id.tv_shou_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ib_shou_zbyj:
                if (TextUtils.isEmpty(etShouZbqx.getText().toString().trim()) || TextUtils.isEmpty(tvShouStart.getText().toString().trim())) {
                    ToastUtil.showToast(this, "请先完成质保设置");
                    return;
                }
                if (zbType == 0) {
                    ibShouZbyj.setImageResource(R.mipmap.shou_open);
                    zbType = 1;
                } else {
                    ibShouZbyj.setImageResource(R.mipmap.shou_close);
                    zbType = 0;
                }
                break;
            case R.id.tv_shou_nexttime:
                typeTime = 2;
                pvTime.show(view);
                break;
            case R.id.ib_shou_ybyj:
                if (TextUtils.isEmpty(etShouYbqx.getText().toString().trim()) || TextUtils.isEmpty(etShouYbjz.getText().toString().trim())) {
                    ToastUtil.showToast(this, "请先完成延保设置");
                    return;
                }
                if (ybType == 0) {
                    ibShouYbyj.setImageResource(R.mipmap.shou_open);
                    ybType = 1;
                } else {
                    ibShouYbyj.setImageResource(R.mipmap.shou_close);
                    ybType = 0;
                }
                break;
            case R.id.ib_shou_hftx:
                if (TextUtils.isEmpty(tvShouNexttime.getText().toString().trim()) || TextUtils.isEmpty(etShouHfjg.getText().toString().trim())) {
                    ToastUtil.showToast(this, "请先完成回访设置");
                    return;
                }
                if (hfType == 0) {
                    ibShouHftx.setImageResource(R.mipmap.shou_open);
                    hfType = 1;
                } else {
                    ibShouHftx.setImageResource(R.mipmap.shou_close);
                    hfType = 0;
                }
                break;
            case R.id.but_shou_qr:
                commitData();
                break;
            case R.id.tv_shou_start:
                typeTime = 1;
                pvTime.show(view);
                break;
        }
    }

    private void commitData() {
        String zbqx = etShouZbqx.getText().toString().trim();
        String startTime = tvShouStart.getText().toString().trim();
        String entTime = "";
        if (!TextUtils.isEmpty(zbqx) && !TextUtils.isEmpty(startTime)) {
//            zbqx = etShouZbqx.getText().toString().trim();
            startTime = DateUtils.getStringToDate(startTime);
            entTime = DateUtils.getStringToDate(etShouEnd.getText().toString().trim());
        }else  if (TextUtils.isEmpty(zbqx) && TextUtils.isEmpty(startTime)) {
        }else {
            ToastUtil.showToast(this, "请先完成质保设置");
            return;
        }

        String ybqx = etShouYbqx.getText().toString().trim();
        String ybtime = etShouYbjz.getText().toString().trim();
        if (!TextUtils.isEmpty(ybqx) && !TextUtils.isEmpty(ybtime)) {
            ybtime = DateUtils.getStringToDate(ybtime);
        }else  if (TextUtils.isEmpty(ybqx) && TextUtils.isEmpty(ybtime)) {
        }else {
            ToastUtil.showToast(this, "请先完成延保设置");
            return;
        }

        String hftime = tvShouNexttime.getText().toString().trim();
        String hfjg = etShouHfjg.getText().toString().trim();
        if (!TextUtils.isEmpty(hfjg) && !TextUtils.isEmpty(hftime)) {
            hftime = DateUtils.getStringToDate(hftime);
        }else  if (TextUtils.isEmpty(hfjg) && TextUtils.isEmpty(hftime)) {
        }else {
            ToastUtil.showToast(this, "请先完成回访设置");
            return;
        }


        getP().shouhouSetting(pro_id, zbqx, startTime, entTime, hftime, hfjg, zbType + "",
                ybType + "", hfType + "", ybtime,ybqx);
    }

    public void settingInfoSuccess(SettingInfoModel models) {
        String qualityBegin = models.getQualityBegin();
        if (!TextUtils.isEmpty(qualityBegin) && !qualityBegin.equals("0")) {
            tvShouStart.setText(DateUtils.stampDate(qualityBegin));
        }
        String qualityEnd = models.getQualityEnd();
        if (!TextUtils.isEmpty(qualityEnd) && !qualityEnd.equals("0")) {
            etShouEnd.setText(DateUtils.stampDate(qualityEnd));
        }
        int qualityTime = models.getQualityTime();
        if (qualityTime != 0) {
            etShouZbqx.setText(qualityTime + "");
        }

        String ybEnd = models.getYbEnd();
        if(!ybEnd.equals("null")) {
            if (!TextUtils.isEmpty(ybEnd) && !ybEnd.equals("0")) {
                etShouYbjz.setText(DateUtils.stampDate(ybEnd));
            }
        }
        int ybTime = models.getYbTime();
        if (ybTime != 0) {
            etShouYbqx.setText(ybTime + "");
        }

        String nextVisitTime = models.getNextVisitTime();
        if (!TextUtils.isEmpty(nextVisitTime) && !nextVisitTime.equals("0")) {
            tvShouNexttime.setText(DateUtils.stampDate(nextVisitTime));
        }
        int visitSpace = models.getVisitSpace();
        if (visitSpace != 0) {
            etShouHfjg.setText(visitSpace + "");
        }

        int qualityRing = models.getQualityRing();
        if (qualityRing == 1) {
            zbType = 1;
            ibShouZbyj.setImageResource(R.mipmap.shou_open);
        } else if (qualityRing == 0) {
            zbType = 0;
            ibShouZbyj.setImageResource(R.mipmap.shou_close);
        }
        int ybRing = models.getYbRing();
        if (ybRing == 1) {
            ybType = 1;
            ibShouYbyj.setImageResource(R.mipmap.shou_open);
        } else if (ybRing == 0) {
            ybType = 0;
            ibShouYbyj.setImageResource(R.mipmap.shou_close);
        }
        int visitRing = models.getVisitRing();
        if (visitRing == 1) {
            hfType = 1;
            ibShouHftx.setImageResource(R.mipmap.shou_open);
        } else if (visitRing == 0) {
            hfType = 0;
            ibShouHftx.setImageResource(R.mipmap.shou_close);
        }
    }

    public void shouhouSettingSuccess(String models) {
        ToastUtil.showToast(this, models);
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.FOUNDPROJECT);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if(typeTime == 1){
                    String num = etShouZbqx.getText().toString().trim();
                    if (TextUtils.isEmpty(num)) {
                        tvShouStart.setText(getTime(date));
                    } else {
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
                        etShouEnd.setText(ObtainTime.showTime(year,i,day));
                        tvShouStart.setText(getTime(date));
                    }
                }else if(typeTime == 2){
                    tvShouNexttime.setText(getTime(date));
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }
}
