package com.mfzn.deepuses.activityxm.shgd;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.WebviewX5Activity;
import com.mfzn.deepuses.adapter.xiangmu.AddPhotoAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.request.CreateAfterSaleOrderRequest;
import com.mfzn.deepuses.model.UploadContractModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.xmgd.AddWorkorderPresent;
import com.mfzn.deepuses.utils.Bimp;
import com.mfzn.deepuses.utils.BitmapFileSetting;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.FileUtils;
import com.mfzn.deepuses.utils.ImageCompressUtil;
import com.mfzn.deepuses.utils.PhotographDialog;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.YuyueTimeUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AddWorkorderActivity extends BaseMvpActivity<AddWorkorderPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_or_name)
    TextView tvOrName;
    @BindView(R.id.tv_or_phone)
    TextView tvOrPhone;
    @BindView(R.id.tv_or_address)
    TextView tvOrAddress;
    @BindView(R.id.tv_bao_type)
    TextView tv_bao_type;
    @BindView(R.id.et_or_type)
    EditText etOrType;
    @BindView(R.id.et_or_lxr)
    EditText etOrLxr;
    @BindView(R.id.et_or_lxrphone)
    EditText etOrLxrphone;
    @BindView(R.id.et_or_time)
    EditText etOrTime;
    @BindView(R.id.et_or_ms)
    EditText etOrMs;
    @BindView(R.id.or_recycleview)
    RecyclerView orRecycleview;

    //图片列表
    private List<File> files = new ArrayList<>();
    private AddPhotoAdapter recycleAdapter;

    private List<Bitmap> bmp = new ArrayList<Bitmap>();
    private List<String> drr = new ArrayList<String>();
    private ArrayList<String> mSelectPath;
    private float dp;
    private int i = 0;

    private OptionsPickerView pickerView;
    private OptionsPickerView pickerView2;
    private List<String> listType;//工单类型

    private String pro_id;

    //售后类型 1故障保修 2维护升级
    private String shType;

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

        XiangmuModel.DataBean dataBean = (XiangmuModel.DataBean) getIntent().getSerializableExtra(Constants.WORK_ORDER);
        pro_id = String.valueOf(dataBean.getData_id());
        int zhib = dataBean.getQualityIsGB();
        int yanb = dataBean.getYbIsGB();
        tvOrName.setText(dataBean.getCustomName());
        tvOrPhone.setText(dataBean.getCustomTel());
        tvOrAddress.setText(dataBean.getAreaName() + dataBean.getDetailAddress());
        etOrLxr.setText(dataBean.getContacter());
        etOrLxrphone.setText(dataBean.getContacterPhone());

        tv_bao_type.setText(setbx(zhib,yanb));

        //售后类型
        listType = new ArrayList<>();
        listType.add("故障报修");
        listType.add("维护升级");
        initPartmentPicker();

        initTimePicker();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        orRecycleview.setLayoutManager(layoutManager);

        //多选图片
        gridviewInit();
    }

    @OnClick({R.id.iv_login_back, R.id.et_or_type, R.id.et_or_time, R.id.but_or_commit, R.id.ll_or_sf, R.id.ll_or_gz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_or_type:
                pickerView.show(view);
                break;
            case R.id.et_or_time:
                pickerView2.show(view);
                break;
            case R.id.but_or_commit:
                commitData();
                break;
            case R.id.ll_or_sf:
                Intent intent = new Intent(context, WebviewX5Activity.class);
                intent.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/app_sh/problem.html");
                intent.putExtra(Constants.WEBVIEW_NAME, "收费标准");
                startActivity(intent);
                break;
            case R.id.ll_or_gz:
                Intent intent1 = new Intent(context, WebviewX5Activity.class);
                intent1.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/app_sh/standard.html");
                intent1.putExtra(Constants.WEBVIEW_NAME, "常见故障");
                startActivity(intent1);
                break;
        }
    }

    private void commitData() {
        String type = etOrType.getText().toString().trim();
        if(TextUtils.isEmpty(type)) {
            ToastUtil.showToast(this,"请选择售后类型");
            return;
        }
        String lxr = etOrLxr.getText().toString().trim();
        if(TextUtils.isEmpty(lxr)) {
            ToastUtil.showToast(this,"请输入联系人");
            return;
        }
        String phone = etOrLxrphone.getText().toString().trim();
        if(TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(this,"请输入联系电话");
            return;
        }
        String startTime = etOrTime.getText().toString().trim();
        if(TextUtils.isEmpty(startTime)) {
            ToastUtil.showToast(this,"请选择时间");
            return;
        }
        String ms = etOrMs.getText().toString().trim();
        if(TextUtils.isEmpty(ms)) {
            ToastUtil.showToast(this,"请输入故障描述");
            return;
        }
//        if(bmp.size() == 0) {
//            ToastUtil.showToast(this,"请添加相关图片");
//            return;
//        }
        files.clear();
        for (int i = 0 ; i < bmp.size() ; i++){
            String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss-" + i, new Date()) + ".jpg";
            files.add(BitmapFileSetting.saveBitmapFile(bmp.get(i), PhotographDialog.Image_SAVEDIR + "/" + cameraFile));
        }
        getP().upLoadFile("15",pro_id,etOrMs.getText().toString().trim(),files);
    }

    public void addWorkorderSuccess() {
        ToastUtil.showToast(this,"创建成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拍照，相册返回
        if (requestCode == Constants.REAL_NAME_PAIZHAO) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if(bitmap != null){
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 480,480);
                bmp.add(newbitmap);
                //把图片数量添加进集合,方便删除，统计数量
                drr.add(FileUtils.SDPATH + ".JPEG");
                gridviewInit();
            }
        } else if(requestCode == Constants.RESULT_LOAD_IMAGE){
            if (drr.size() < 9 && resultCode == RESULT_OK && null != data) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Bitmap bitmap;
                try {
                    for (String p : mSelectPath) {
                        bitmap = Bimp.revitionImageSize(p.toString());
//                            PhotoActivity.bitmap.add(bitmap);
                        bitmap = Bimp.createFramedPhoto(480, 480, bitmap, (int) (dp * 1.6f));
                        bmp.add(bitmap);
                        gridviewInit();
                        //把图片数量添加进集合,方便删除，统计数量
                        drr.add(FileUtils.SDPATH + ".JPEG");
                        //把位图转化为字符流
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        baos.close();
                        byte[] buffer = baos.toByteArray();
                        String[] photoImgliu = new String[1024 * 1024];
                        photoImgliu[i] = Base64.encodeToString(buffer, 0, buffer.length, Base64.DEFAULT);
                        i++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void gridviewInit() {
        getResources().getDimension(R.dimen.app_10dp);

        recycleAdapter = new AddPhotoAdapter(this,bmp);
        orRecycleview.setAdapter(recycleAdapter);

        recycleAdapter.setOnAddClickListener(new AddPhotoAdapter.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                if (position == 0 && bmp.size() != 9) {
                    PhotographDialog.photographDialog(AddWorkorderActivity.this,bmp);
                }
            }
        });
        recycleAdapter.setOnDeleteClickListener(new AddPhotoAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                if(bmp.size() == 9) {
                    bmp.get(position).recycle();
                    bmp.remove(position);
                    drr.remove(position);
                }else {
                    bmp.get(position - 1).recycle();
                    bmp.remove(position - 1);
                    drr.remove(position - 1);
                }
                recycleAdapter.notifyDataSetChanged();
            }
        });
    }

    //上传头像成功返回
    public void uploadIconSuccess(int status, UploadContractModel.ResBean res) {
        if(status == 1){
            String lxr = etOrLxr.getText().toString().trim();
            String phone = etOrLxrphone.getText().toString().trim();
            String startTime = etOrTime.getText().toString().trim();
            String ms = etOrMs.getText().toString().trim();

            CreateAfterSaleOrderRequest request=new CreateAfterSaleOrderRequest();
            request.setProID(pro_id);
            request.setAsType(shType);
            request.setContactName(lxr);
            request.setContactPhone(phone);
            request.setWishTime(startTime);
            request.setContent(ms);
            request.setFileUrls(res.getFileID());
            getP().addWorkorder(request);
//            recycleAdapter.notifyDataSetChanged();
//
//            ToastUtil.showToast(this,"图片上传成功");
        }else {
            ToastUtil.showToast(this,"图片上传失败，请稍后重试");
        }
    }

    //售后类型
    private void initPartmentPicker(){
        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                if(options1 == 0) {
                    shType = "1";
                }else {
                    shType = "2";
                }
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
        List<String> strings = YuyueTimeUtils.fillData();
        List<List<String>> lists = YuyueTimeUtils.timeData();

        pickerView2 = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                etOrTime.setText(strings.get(options1) + " " + lists.get(options1).get(option2));
            }
        }).setSubCalSize(15).setSubmitColor(R.color.color_303133)//确定按钮文字颜色
                .setCancelColor(R.color.color_606266)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCyclic(false,false,false)//设置是否循环
                .build();
        pickerView2.setPicker(strings, lists,null);
        Dialog mDialog = pickerView2.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            params.width = getWindowManager().getDefaultDisplay().getWidth();//设置这个才可以全屏展示
            pickerView2.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    public String setbx(int zhib, int yanb){
        if (zhib == 1) {
            return "质保期内";
        }
        if (zhib == 2 && yanb == 0) {
            return "已过质保期";
        }
        if (zhib == 2 && yanb == 1){
            return "延保期内";
        }
        if (yanb == 2){
            return "已过延保期";
        }
        return "未设置";
    }
}
