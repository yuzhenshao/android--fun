package com.mfzn.deepusesSer.activityxm.shgd;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.AddPhotoAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.xiangmu.AddPhotoModel;
import com.mfzn.deepusesSer.present.xmgd.AddWorkorderPresent;
import com.mfzn.deepusesSer.utils.BitmapFileSetting;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.ImageCompressUtil;
import com.mfzn.deepusesSer.utils.PhotographDialog;
import com.mfzn.deepusesSer.utils.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddWorkorderActivity extends BaseMvpActivity<AddWorkorderPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_or_name)
    TextView tvOrName;
    @BindView(R.id.tv_or_phone)
    TextView tvOrPhone;
    @BindView(R.id.tv_or_address)
    TextView tvOrAddress;
    @BindView(R.id.et_or_type)
    EditText etOrType;
    @BindView(R.id.et_or_lxr)
    EditText etOrLxr;
    @BindView(R.id.et_or_lxrphone)
    EditText etOrLxrphone;
    @BindView(R.id.et_or_time)
    EditText etOrTime;
    @BindView(R.id.et_or_time2)
    EditText etOrTime2;
    @BindView(R.id.et_or_ms)
    EditText etOrMs;
    @BindView(R.id.or_recycleview)
    RecyclerView orRecycleview;

    //图片列表
    private List<AddPhotoModel> beanList = new ArrayList<>();
    private int positions;//图片列表位置
    private String photoID;//图片ID
    private AddPhotoAdapter recycleAdapter;

    private OptionsPickerView pickerView;
    private List<String> listType;//工单类型

    private TimePickerView pvTime;
    private int typeTime = 1;// 1 开始时间 2 结束时间

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_workorder;
    }

    @Override
    public AddWorkorderPresent newP() {
        return new AddWorkorderPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_add_workorder));

        AddPhotoModel bean = new AddPhotoModel();
        beanList.add(bean);

        //售后类型
        listType = new ArrayList<>();
        listType.add("故障报修");
        listType.add("维护升级");
        initPartmentPicker();

        initTimePicker();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        orRecycleview.setLayoutManager(layoutManager);
//        recycleAdapter = new AddPhotoAdapter(this,beanList);
        orRecycleview.setAdapter(recycleAdapter);

        recycleAdapter.setOnAddClickListener(new AddPhotoAdapter.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                AddWorkorderActivity.this.startActivityForResult(intent, Constants.REAL_NAME_XIANGCE);
            }
        });
        recycleAdapter.setOnDeleteClickListener(new AddPhotoAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                beanList.remove(position);
                recycleAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.et_or_type, R.id.et_or_time, R.id.et_or_time2, R.id.but_or_commit, R.id.ll_or_sf, R.id.ll_or_gz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_or_type:
                pickerView.show(view);
                break;
            case R.id.et_or_time:
                typeTime = 1;
                pvTime.show(view);
                break;
            case R.id.et_or_time2:
                typeTime = 2;
                pvTime.show(view);
                break;
            case R.id.but_or_commit:
                break;
            case R.id.ll_or_sf:
                break;
            case R.id.ll_or_gz:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拍照，相册返回
        if (requestCode == Constants.REAL_NAME_XIANGCE) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = ImageCompressUtil.getRealPathFromURI(this, uri);//获取选中图片的路径
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 800, 1000);
                    String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                    File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                    AddPhotoModel bean = new AddPhotoModel();
                    bean.setBitmap(file);
                    beanList.add(bean);
                    recycleAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    //售后类型
    private void initPartmentPicker(){
        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                etOrType.setText(listType.get(options1));
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

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if(typeTime == 1){
                    judgeTime2(getTime(date));
                }else if(typeTime == 2){
                    judgeTime(getTime(date));
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

    private void judgeTime(String time) {
        String startTime = etOrTime.getText().toString();
        if(!TextUtils.isEmpty(startTime)){
            int s1 = Integer.parseInt(startTime.substring(0, 4));
            int s2 = Integer.parseInt(startTime.substring(5, 7));
            int s3 = Integer.parseInt(startTime.substring(8, startTime.length()));
            int s4 = Integer.parseInt(time.substring(0, 4));
            int s5 = Integer.parseInt(time.substring(5, 7));
            int s6 = Integer.parseInt(time.substring(8, time.length()));
            if(s4 < s1){
                ToastUtil.showToast(this,"结束日期不能小于开始日期");
                return;
            }else if(s4 == s1 && s5 < s2){
                ToastUtil.showToast(this,"结束日期不能小于开始日期");
                return;
            }else if(s4 == s1 && s5 == s2 && s6 < s3){
                ToastUtil.showToast(this,"结束日期不能小于开始日期");
                return;
            }else {
                etOrTime2.setText(time);
            }
        }else {
            etOrTime2.setText(time);
        }
    }
    private void judgeTime2(String time) {
        String endtTime = etOrTime2.getText().toString();
        if(!TextUtils.isEmpty(endtTime)){
            int s1 = Integer.parseInt(endtTime.substring(0, 4));
            int s2 = Integer.parseInt(endtTime.substring(5, 7));
            int s3 = Integer.parseInt(endtTime.substring(8, endtTime.length()));
            int s4 = Integer.parseInt(time.substring(0, 4));
            int s5 = Integer.parseInt(time.substring(5, 7));
            int s6 = Integer.parseInt(time.substring(8, time.length()));
            if(s1 < s4){
                ToastUtil.showToast(this,"开始日期不能大于结束日期");
                return;
            }else if(s4 == s1 && s2 < s5){
                ToastUtil.showToast(this,"开始日期不能大于结束日期");
                return;
            }else if(s4 == s1 && s5 == s2 && s3 < s6){
                ToastUtil.showToast(this,"开始日期不能大于结束日期");
                return;
            }else {
                etOrTime.setText(time);
            }
        }else {
            etOrTime.setText(time);
        }
    }
}
