package com.mfzn.deepuses.fragment.khxq;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.SelectPersonActivity;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.present.customer.BasicInfoPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.GenjinTypeDialog;
import com.wevey.selector.dialog.bean.DetailsModel;
import com.wevey.selector.dialog.bean.SelectModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BasicInfoFragment extends BaseMvpFragment<BasicInfoPresnet> {

    @BindView(R.id.ll_ba_bianji)
    LinearLayout llBaBianji;
    @BindView(R.id.tv_ba_wc)
    TextView tvBaWc;
    @BindView(R.id.et_ba_name)
    EditText etBaName;
    @BindView(R.id.et_ba_phone)
    EditText etBaPhone;
    @BindView(R.id.et_ba_type)
    EditText etBaType;
    @BindView(R.id.iv_ba_type1)
    ImageView ivBaType1;
    @BindView(R.id.iv_ba_type2)
    View ivBaType2;
    @BindView(R.id.et_ba_level)
    EditText etBaLevel;
    @BindView(R.id.iv_ba_level1)
    ImageView ivBaLevel1;
    @BindView(R.id.iv_ba_level2)
    View ivBaLevel2;
    @BindView(R.id.et_ba_laiy)
    EditText etBaLaiy;
    @BindView(R.id.iv_ba_laiy1)
    ImageView ivBaLaiy1;
    @BindView(R.id.iv_ba_laiy2)
    View ivBaLaiy2;
    @BindView(R.id.et_ba_bz)
    EditText etBaBz;
    @BindView(R.id.et_ba_gw)
    EditText etBaGw;
    @BindView(R.id.iv_ba_gw1)
    ImageView iv_ba_gw1;
    @BindView(R.id.iv_ba_gw2)
    View iv_ba_gw2;

    private String statusID = "";//跟进状态ID
    private String levelID = "";//客户等级ID
    private String sourceID = "";//客户来源ID
    private String gwID = "";//销售顾问ID
    private int selectType;

    private SelectModel model;
    private OptionsPickerView pickerView;
    private List<String> listType = new ArrayList<>();

    private String dataid;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basic_info;
    }

    @Override
    public BasicInfoPresnet newP() {
        return new BasicInfoPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        dataid = getArguments().getString(Constants.CUS_DETA_ID);
        getP().customerDetails(dataid);
        getP().getSelect();
    }

    @OnClick({R.id.ll_ba_bianji, R.id.tv_ba_wc, R.id.et_ba_type, R.id.et_ba_level, R.id.et_ba_laiy, R.id.et_ba_gw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ba_bianji:
                bianji();
                break;
            case R.id.tv_ba_wc:
                String name = etBaName.getText().toString().trim();
                if(TextUtils.isEmpty(name)) {
                    ToastUtil.showToast(getActivity(),"请输入客户姓名");
                    return;
                }
                String phone = etBaPhone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)) {
                    ToastUtil.showToast(getActivity(),"请输入联系电话");
                    return;
                }
                success();
                break;
            case R.id.et_ba_type:
                if(model.getFollowStatus() == null || model.getFollowStatus().size() == 0) {
                    ToastUtil.showToast(getActivity(),"暂不可筛选，请填写其它");
                }else {
                    new GenjinTypeDialog.Builder(getActivity())
                            .setHeight(1f)  //屏幕高度*0.23
                            .setWidth(1f)  //屏幕宽度*0.65
                            .setListModel(model.getFollowStatus())
                            .setCanceledOnTouchOutside(true)
                            .setOnclickListener(new DialogInterface.OnItemClickListener<GenjinTypeDialog>() {
                                @Override
                                public void onItemClick(GenjinTypeDialog dialog, View button, int position) {
                                    etBaType.setText(model.getFollowStatus().get(position).getName());
                                    statusID = String.valueOf(model.getFollowStatus().get(position).getData_id());
                                    dialog.dismiss();
                                }
                            })
                            .build()
                            .show();
                }
                break;
            case R.id.et_ba_level:
                if(model.getCustomerLevel() == null || model.getCustomerLevel().size() == 0) {
                    ToastUtil.showToast(getActivity(),"暂不可筛选，请填写其它");
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
            case R.id.et_ba_laiy:
                if(model == null || model.getCustomerSource() == null || model.getCustomerSource().size() == 0) {
                    ToastUtil.showToast(getActivity(),"暂不可筛选，请填写其它");
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
            case R.id.et_ba_gw:
                UserHelper.setSelectNmae("1");
                startActivityForResult(new Intent(getActivity(), SelectPersonActivity.class), Constants.SELECT_PERSON);
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
                etBaGw.setText(name);
            }
        }
    }

    public void editInfoSuccess() {
        ToastUtil.showToast(getActivity(),"编辑成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDIT_SUCC);
        RxBus.getInstance().post(eventMsg);
        getP().customerDetails(dataid);
    }

    public void customerDetailsSuccess(DetailsModel models) {
        etBaName.setText(models.getCustomerName());
        etBaPhone.setText(models.getCustomerPhone());
        etBaBz.setText(models.getNote());

        etBaType.setText(models.getFollowStatusName());
        statusID = String.valueOf(models.getFollowStatusID());

        etBaLevel.setText(models.getLevelName());
        levelID = String.valueOf(models.getCustomerLevelID());

        etBaLaiy.setText(models.getCustomerSourceName());
        sourceID = String.valueOf(models.getCustomerSourceID());

        etBaGw.setText(models.getSalesPersonUserName());
        gwID = String.valueOf(models.getSalesPersonUserID());
    }

    public void getSelectSuccess(SelectModel model) {
        this.model = model;
    }

    //售后类型
    private void initPartmentPicker(){
        pickerView = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                if(selectType == 1) {
                    levelID = String.valueOf(model.getCustomerLevel().get(options1).getData_id());
                    etBaLevel.setText(listType.get(options1));
                }else if(selectType == 2){
                    sourceID = String.valueOf(model.getCustomerSource().get(options1).getData_id());
                    etBaLaiy.setText(listType.get(options1));
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
            params.width = getActivity().getWindowManager().getDefaultDisplay().getWidth();//设置这个才可以全屏展示
            pickerView.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    private void bianji() {
        llBaBianji.setVisibility(View.GONE);
        tvBaWc.setVisibility(View.VISIBLE);
        etBaName.setFocusableInTouchMode(true);
        etBaPhone.setFocusableInTouchMode(true);
        etBaBz.setFocusableInTouchMode(true);
        etBaName.setFocusable(true);
        etBaPhone.setFocusable(true);
        etBaBz.setFocusable(true);
        etBaType.setEnabled(true);
        etBaLevel.setEnabled(true);
        etBaLaiy.setEnabled(true);
        etBaGw.setEnabled(true);
        ivBaType1.setVisibility(View.VISIBLE);
        iv_ba_gw1.setVisibility(View.VISIBLE);
        ivBaLevel1.setVisibility(View.VISIBLE);
        ivBaLaiy1.setVisibility(View.VISIBLE);
        ivBaType2.setVisibility(View.GONE);
        ivBaLevel2.setVisibility(View.GONE);
        ivBaLaiy2.setVisibility(View.GONE);
        iv_ba_gw2.setVisibility(View.GONE);
    }

    private void success() {
        llBaBianji.setVisibility(View.VISIBLE);
        tvBaWc.setVisibility(View.GONE);
        etBaName.setFocusableInTouchMode(false);
        etBaPhone.setFocusableInTouchMode(false);
        etBaBz.setFocusableInTouchMode(false);
        etBaName.setFocusable(false);
        etBaPhone.setFocusable(false);
        etBaBz.setFocusable(false);
        etBaType.setEnabled(false);
        etBaLevel.setEnabled(false);
        etBaLaiy.setEnabled(false);
        etBaGw.setEnabled(false);
        ivBaType1.setVisibility(View.GONE);
        iv_ba_gw1.setVisibility(View.GONE);
        ivBaLevel1.setVisibility(View.GONE);
        ivBaLaiy1.setVisibility(View.GONE);
        ivBaType2.setVisibility(View.VISIBLE);
        ivBaLevel2.setVisibility(View.VISIBLE);
        ivBaLaiy2.setVisibility(View.VISIBLE);
        iv_ba_gw2.setVisibility(View.VISIBLE);

        String name = etBaName.getText().toString().trim();
        String phone = etBaPhone.getText().toString().trim();
        String bz = etBaBz.getText().toString().trim();
        getP().editInfo(dataid,name,phone,statusID,levelID,sourceID,bz,gwID);
    }
}
