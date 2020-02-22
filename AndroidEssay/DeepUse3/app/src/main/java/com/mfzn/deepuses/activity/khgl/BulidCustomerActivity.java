package com.mfzn.deepuses.activity.khgl;

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
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.jiagou.BatchAddStaffActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagou2Activity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.wevey.selector.dialog.BotomListviewDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.GenjinTypeDialog;
import com.wevey.selector.dialog.bean.SelectModel;
import com.mfzn.deepuses.present.customer.BuildCustomerPresnet;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BulidCustomerActivity extends BaseMvpActivity<BuildCustomerPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_bu_name)
    EditText etBuName;
    @BindView(R.id.et_bu_phone)
    EditText etBuPhone;
    @BindView(R.id.et_bu_type)
    EditText etBuType;
    @BindView(R.id.et_bu_level)
    EditText etBuLevel;
    @BindView(R.id.et_bu_laiy)
    EditText etBuLaiy;
    @BindView(R.id.et_bu_bz)
    EditText etBuBz;
    @BindView(R.id.but_bu_commit)
    Button butBuCommit;

    private String statusID = "";//跟进状态ID
    private String levelID = "";//客户等级ID
    private String sourceID = "";//客户来源ID
    private int selectType;

    private SelectModel model;
    private OptionsPickerView pickerView;
    private List<String> listType = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_bulid_customer;
    }

    @Override
    public BuildCustomerPresnet newP() {
        return new BuildCustomerPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_build_customer));

        getP().getSelect();

        etBuName.addTextChangedListener(mOnInputChangeListener);
        etBuPhone.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.et_bu_type, R.id.et_bu_level, R.id.et_bu_laiy, R.id.but_bu_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_bu_type:
                if(model.getFollowStatus() == null || model.getFollowStatus().size() == 0) {
                    ToastUtil.showToast(this,"暂不可筛选，请填写其它");
                }else {
                    new GenjinTypeDialog.Builder(this)
                            .setHeight(1f)  //屏幕高度*0.23
                            .setWidth(1f)  //屏幕宽度*0.65
                            .setListModel(model.getFollowStatus())
                            .setCanceledOnTouchOutside(true)
                            .setOnclickListener(new DialogInterface.OnItemClickListener<GenjinTypeDialog>() {
                                @Override
                                public void onItemClick(GenjinTypeDialog dialog, View button, int position) {
                                    etBuType.setText(model.getFollowStatus().get(position).getName());
                                    statusID = String.valueOf(model.getFollowStatus().get(position).getData_id());
                                    dialog.dismiss();
                                }
                            })
                            .build()
                            .show();
                }
                break;
            case R.id.et_bu_level:
                if(model.getCustomerLevel() == null || model.getCustomerLevel().size() == 0) {
                    ToastUtil.showToast(this,"暂不可筛选，请填写其它");
                    return;
                }else {
                    selectType = 1;
                    listType.clear();
                    for(int i = 0; i < model.getCustomerLevel().size(); i++) {
                        listType.add(model.getCustomerLevel().get(i).getLevelName());
                    }
                }
                initPartmentPicker();
                pickerView.show(view);
                break;
            case R.id.et_bu_laiy:
                if(model.getCustomerSource() == null || model.getCustomerSource().size() == 0) {
                    ToastUtil.showToast(this,"暂不可筛选，请填写其它");
                    return;
                }else {
                    selectType = 2;
                    listType.clear();
                    for(int i = 0; i < model.getCustomerSource().size(); i++) {
                        listType.add(model.getCustomerSource().get(i).getName());
                    }
                }
                initPartmentPicker();
                pickerView.show(view);
                break;
            case R.id.but_bu_commit:
                String name = etBuName.getText().toString().trim();
                String phone = etBuPhone.getText().toString().trim();
                String bz = etBuBz.getText().toString().trim();
                getP().buildCustomer(name,phone,statusID,levelID,sourceID,bz);
                break;
        }
    }

    public void buildCustomerSuccess() {
        ToastUtil.showToast(this,"创建成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.CREAT_SUCC);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void getSelectSuccess(SelectModel model) {
        this.model = model;
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = etBuName.getText().toString().trim();
            String phone = etBuPhone.getText().toString().trim();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
                butBuCommit.setEnabled(true);
                butBuCommit.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butBuCommit.setEnabled(false);
                butBuCommit.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    //售后类型
    private void initPartmentPicker(){
        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                if(selectType == 1) {
                    levelID = String.valueOf(model.getCustomerLevel().get(options1).getData_id());
                    etBuLevel.setText(listType.get(options1));
                }else if(selectType == 2){
                    sourceID = String.valueOf(model.getCustomerSource().get(options1).getData_id());
                    etBuLaiy.setText(listType.get(options1));
                }
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
}
