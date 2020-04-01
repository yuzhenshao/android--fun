package com.mfzn.deepuses.activity.project;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.mfzn.deepuses.activity.khgl.MultipleSelectActivity;
import com.mfzn.deepuses.activityxm.MapLocationActivity;
import com.mfzn.deepuses.activityxm.SelectPersonActivity;
import com.mfzn.deepuses.adapter.khgl.EditCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.request.EditProjectRequest;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.foundxm.EditProjectPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ObtainTime;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import retrofit2.http.Query;

public class EditProjectActivity extends BaseMvpActivity<EditProjectPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.tv_edit_jb)
    TextView tvEditJb;
    @BindView(R.id.et_edit_proname)
    EditText etEditProname;
    @BindView(R.id.et_edit_address)
    EditText etEditAddress;
    @BindView(R.id.et_edit_detail)
    EditText etEditDetail;
    @BindView(R.id.tv_edit_ht)
    TextView tvEditHt;
    @BindView(R.id.et_edit_gw)
    EditText etEditGw;
    @BindView(R.id.et_edit_money)
    EditText etEditMoney;
    @BindView(R.id.tv_edit_money)
    TextView tvEditMoney;
    @BindView(R.id.et_edit_zbqx)
    EditText etEditZbqx;
    @BindView(R.id.tv_edit_start)
    EditText etEditStart;
    @BindView(R.id.et_edit_end)
    EditText etEditEnd;
    @BindView(R.id.ib_edit_zbyj)
    ImageButton ibEditZbyj;
    @BindView(R.id.or_recycleview)
    RecyclerView orRecycleview;

    private int levelPosition = -1;
    private String levelID = "";//等级ID
    private String gwID = "";//销售顾问ID
    private String longitude;//经度
    private String latitude;//纬度

    private TimePickerView pvTime;

    private String proId;
    private int zbType = 0;// 1不提醒 1提醒
    private List<XiangmuModel.DataBean.CustomersBean> customers;
    private EditCustomerAdapter recycleAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_project;
    }

    @Override
    public EditProjectPresnet newP() {
        return new EditProjectPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_edit_project));
        tvEditJb.getPaint().setFakeBoldText(true);
        tvEditHt.getPaint().setFakeBoldText(true);
        tvEditMoney.getPaint().setFakeBoldText(true);
        tvBassComlate.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        orRecycleview.setLayoutManager(layoutManager);

        initTimePicker();

        XiangmuModel.DataBean dataBean = (XiangmuModel.DataBean) getIntent().getSerializableExtra(Constants.WORK_ORDER);

        proId = dataBean.getData_id() + "";

        etEditProname.setText(dataBean.getProName());
        etEditAddress.setText(dataBean.getAreaName());
        longitude = dataBean.getLongitude() + "";
        latitude = dataBean.getLatitude() + "";
        etEditDetail.setText(dataBean.getDetailAddress());
        levelID = dataBean.getCustomLevel() + "";
        etEditGw.setText(dataBean.getProSalesPersonInfo().getSalesName());
        gwID = dataBean.getProSalesPersonInfo().getSalesID();
        etEditMoney.setText(dataBean.getContractMoney() + "");
        String qualityBegin = dataBean.getQualityBegin();
        if(!TextUtils.isEmpty(qualityBegin) && !qualityBegin.equals("0")) {
            etEditStart.setText(DateUtils.stampDate(qualityBegin));
        }
        String qualityEnd = dataBean.getQualityEnd();
        if(!TextUtils.isEmpty(qualityEnd) && !qualityEnd.equals("0")) {
            etEditEnd.setText(DateUtils.stampDate(qualityEnd));
        }
        String qualityTime = dataBean.getQualityTime();
        if(!qualityTime.equals("0")) {
            etEditZbqx.setText(qualityTime);
        }

        customers = dataBean.getCustomersInfo();
        customers.add(0,new XiangmuModel.DataBean.CustomersBean());
        recycleAdapter = new EditCustomerAdapter(this, customers);
        orRecycleview.setAdapter(recycleAdapter);

        recycleAdapter.setOnDeleteClickListener(new EditCustomerAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                customers.remove(position);
                recycleAdapter.notifyDataSetChanged();
            }
        });

        recycleAdapter.setOnAddClickListener(new EditCustomerAdapter.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                startActivityForResult(new Intent(context, MultipleSelectActivity.class), Constants.SELECT_MUL);
            }
        });

        String qualityRing = dataBean.getQualityRing();
        if(qualityRing.equals("1")) {
            zbType = 1;
            ibEditZbyj.setImageResource(R.mipmap.shou_open);
        }else if(qualityRing.equals("0")){
            zbType = 0;
            ibEditZbyj.setImageResource(R.mipmap.shou_close);
        }

        etEditZbqx.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num = etEditZbqx.getText().toString().trim();
                if(TextUtils.isEmpty(num)) {
                    etEditEnd.getText().clear();
                }else {
                    String time = etEditStart.getText().toString().trim();
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
                        etEditEnd.setText(ObtainTime.showTime(year,i,day));
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.et_edit_address,
            R.id.et_edit_gw, R.id.tv_edit_start, R.id.ib_edit_zbyj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:
                commitData();
                break;
            case R.id.et_edit_address:
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
            case R.id.et_edit_gw:
                UserHelper.setSelectNmae("1");
                startActivityForResult(new Intent(this, SelectPersonActivity.class), Constants.SELECT_PERSON);
                break;
            case R.id.tv_edit_start:
                pvTime.show(view);
                break;
            case R.id.ib_edit_zbyj:
                if(TextUtils.isEmpty(etEditZbqx.getText().toString().trim()) || TextUtils.isEmpty(etEditStart.getText().toString().trim())) {
                    ToastUtil.showToast(this,"请先完成质保设置");
                    return;
                }
                if(zbType == 0) {
                    ibEditZbyj.setImageResource(R.mipmap.shou_open);
                    zbType = 1;
                }else {
                    ibEditZbyj.setImageResource(R.mipmap.shou_close);
                    zbType = 0;
                }
                break;
        }
    }

    private void commitData() {
        String proname = etEditProname.getText().toString().trim();
        if(TextUtils.isEmpty(proname)) {
            ToastUtil.showToast(this,getString(R.string.found_pro_proname));
            return;
        }
        String address = etEditAddress.getText().toString().trim();
        String details = etEditDetail.getText().toString().trim();
        if(TextUtils.isEmpty(address)) {
            ToastUtil.showToast(this,getString(R.string.found_pro_address));
            return;
        }
        if(customers.size() < 2) {
            ToastUtil.showToast(this,"请选择客户");
            return;
        }

        String money = etEditMoney.getText().toString().trim();
        String zbqx = etEditZbqx.getText().toString().trim();
        String startTime = etEditStart.getText().toString().trim();
        String start2 = "";
        if(TextUtils.isEmpty(startTime)) {
            start2 = "";
        }else {
            start2 = DateUtils.getStringToDate(startTime);
        }
        String endTime = etEditEnd.getText().toString().trim();
        String end2 = "";
        if(TextUtils.isEmpty(endTime)) {
            end2 = "";
        }else {
            end2 = DateUtils.getStringToDate(endTime);
        }

        String sss = null;
        for(int i = 1; i < customers.size(); i++) {
            if(TextUtils.isEmpty(sss)) {
                sss = customers.get(i).getCustomerID();
            }else {
                sss = sss + "," + customers.get(i).getCustomerID();
            }
        }

        EditProjectRequest request=new EditProjectRequest();
        request.setProID(proId);
        request.setProName(proname);
        request.setLatitude(latitude);
        request.setLongitude(longitude);
        request.setDetailAddress(details);
        request.setSalesPersonUserID(gwID);
        request.setContractMoney(money);
        request.setAreaName(address);
        request.setQualityTime(zbqx);
        request.setQualityBegin(start2);
        request.setQualityEnd(end2);
        request.setQualityRing(zbType);
        request.setCustomerIDs(sss);
        getP().editProject(request);
    }

    public void editProjectSuccess() {
        ToastUtil.showToast(this,"编辑成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.FOUNDPROJECT);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_PERSON == requestCode) {
            if (data != null) {
                gwID = data.getStringExtra(Constants.SELECT_PERSON_ID);
                String name = data.getStringExtra(Constants.SELECT_PERSON_NAME);
                etEditGw.setText(name);
            }
        } else if (Constants.MAP_LOCATION == requestCode) {
            if (data != null) {
                String weizhi = data.getStringExtra(Constants.MAP_LOCATION_WEIZHI);
                longitude = data.getStringExtra(Constants.MAP_LOCATION_JINGDU);
                latitude = data.getStringExtra(Constants.MAP_LOCATION_WEIDU);
                String address = data.getStringExtra(Constants.MAP_LOCATION_ADDRESS);
                etEditAddress.setText(weizhi);
                etEditDetail.setText(address);
            }
        } else if (Constants.SELECT_MUL == requestCode) {
            if (data != null) {
                customers.clear();
                customers.add(0,new XiangmuModel.DataBean.CustomersBean());
                String name = data.getStringExtra(Constants.MULTI_NAME);
                String phone = data.getStringExtra(Constants.MULTI_PHONE);
                String id = data.getStringExtra(Constants.MULTI_ID);
                String[] split = name.split(",");
                String[] split2 = phone.split(",");
                String[] split3 = id.split(",");
                for(int i = 0; i < split.length; i++) {
                    XiangmuModel.DataBean.CustomersBean bean = new XiangmuModel.DataBean.CustomersBean();
                    bean.setCustomerName(split[i]);
                    bean.setCustomerID(split3[i]);
                    customers.add(bean);
                }
                recycleAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String num = etEditZbqx.getText().toString().trim();
                if(TextUtils.isEmpty(num)) {
                    etEditStart.setText(getTime(date));
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
                    etEditEnd.setText(ObtainTime.showTime(year,i,day));
                    etEditStart.setText(getTime(date));
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
