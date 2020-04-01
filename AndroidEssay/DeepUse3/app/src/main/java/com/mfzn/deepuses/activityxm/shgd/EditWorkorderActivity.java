package com.mfzn.deepuses.activityxm.shgd;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.mfzn.deepuses.model.UploadContractModel;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.xmgd.EditWorkorderPresent;
import com.mfzn.deepuses.utils.Bimp;
import com.mfzn.deepuses.utils.BitmapFileSetting;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ImageCompressUtil;
import com.mfzn.deepuses.utils.PhotographDialog;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.YuyueTimeUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import retrofit2.http.Url;

public class EditWorkorderActivity extends BaseMvpActivity<EditWorkorderPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_edit_name)
    TextView tveditName;
    @BindView(R.id.tv_edit_phone)
    TextView tveditPhone;
    @BindView(R.id.tv_edit_address)
    TextView tveditAddress;
    @BindView(R.id.et_edit_type)
    EditText eteditType;
    @BindView(R.id.et_edit_lxr)
    EditText eteditLxr;
    @BindView(R.id.et_edit_lxrphone)
    EditText eteditLxrphone;
    @BindView(R.id.et_edit_time)
    EditText eteditTime;
    @BindView(R.id.et_edit_ms)
    EditText eteditMs;
    @BindView(R.id.edit_recycleview)
    RecyclerView editRecycleview;

    //图片列表
    private List<File> files = new ArrayList<>();
    private AddPhotoAdapter recycleAdapter;

    private List<String> beanList = new ArrayList<>();
    private List<Bitmap> bmp = new ArrayList<Bitmap>();
    private ArrayList<String> mSelectPath;
    private float dp;
    private int i = 0;

    private OptionsPickerView pickerView;
    private OptionsPickerView pickerView2;
    private List<String> listType;//工单类型

    //售后类型 1故障保修 2维护升级
    private String shType;
    private String proId;
    private String orderNo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_workorder;
    }

    @Override
    public EditWorkorderPresent newP() {
        return new EditWorkorderPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_edit_workorder));

        orderNo = getIntent().getStringExtra(Constants.SHOUHOU_ORDERNO);
        getP().gongdanShuxing(orderNo);

        //售后类型
        listType = new ArrayList<>();
        listType.add("故障报修");
        listType.add("维护升级");
        initPartmentPicker();

        initTimePicker();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        editRecycleview.setLayoutManager(layoutManager);
    }

    @OnClick({R.id.iv_login_back, R.id.et_edit_type, R.id.et_edit_time, R.id.but_edit_commit, R.id.ll_edit_sf, R.id.ll_edit_gz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_edit_type:
                pickerView.show(view);
                break;
            case R.id.et_edit_time:
                pickerView2.show(view);
                break;
            case R.id.but_edit_commit:
                commitData();
                break;
            case R.id.ll_edit_sf:
                Intent intent = new Intent(context, WebviewX5Activity.class);
                intent.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/app_sh/problem.html");
                intent.putExtra(Constants.WEBVIEW_NAME, "收费标准");
                startActivity(intent);
                break;
            case R.id.ll_edit_gz:
                Intent intent1 = new Intent(context, WebviewX5Activity.class);
                intent1.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/app_sh/standard.html");
                intent1.putExtra(Constants.WEBVIEW_NAME, "常见故障");
                startActivity(intent1);
                break;
        }
    }

    private void commitData() {
        String type = eteditType.getText().toString().trim();
        if (TextUtils.isEmpty(type)) {
            ToastUtil.showToast(this, "请选择售后类型");
            return;
        }
        String lxr = eteditLxr.getText().toString().trim();
        if (TextUtils.isEmpty(lxr)) {
            ToastUtil.showToast(this, "请输入联系人");
            return;
        }
        String phone = eteditLxrphone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(this, "请输入联系电话");
            return;
        }
        String startTime = eteditTime.getText().toString().trim();
        if (TextUtils.isEmpty(startTime)) {
            ToastUtil.showToast(this, "请选择时间");
            return;
        }
        String ms = eteditMs.getText().toString().trim();
        if (TextUtils.isEmpty(ms)) {
            ToastUtil.showToast(this, "请输入故障描述");
            return;
        }
        if (bmp.size() == 0) {
            ToastUtil.showToast(this, "请添加相关图片");
            return;
        }
        String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
        for (int i = 0; i < bmp.size(); i++) {
            files.add(BitmapFileSetting.saveBitmapFile(bmp.get(i), PhotographDialog.Image_SAVEDIR + "/" + cameraFile));
        }
        getP().upLoadFile(files);
    }

    public void editWorkorderSuccess() {
        ToastUtil.showToast(this, "编辑成功");
        Intent intent = new Intent();
        intent.putExtra("fdsaf", "Fafas");
        setResult(Constants.ACCEPT_GONGDAN, intent);
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
            if (bitmap != null) {
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 480, 480);
                bmp.add(newbitmap);
                gridviewInit();
            }
        } else if (requestCode == Constants.RESULT_LOAD_IMAGE) {
            if (bmp.size() < 9 && resultCode == RESULT_OK && null != data) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Bitmap bitmap;
                try {
                    for (String p : mSelectPath) {
                        bitmap = Bimp.revitionImageSize(p.toString());
//                            PhotoActivity.bitmap.add(bitmap);
                        bitmap = Bimp.createFramedPhoto(480, 480, bitmap, (int) (dp * 1.6f));
                        bmp.add(bitmap);
                        gridviewInit();
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

    //上传头像成功返回
    public void uploadIconSuccess(String urls) {
        if (!TextUtils.isEmpty(urls)) {
            String lxr = eteditLxr.getText().toString().trim();
            String phone = eteditLxrphone.getText().toString().trim();
            String startTime = eteditTime.getText().toString().trim();
            String ms = eteditMs.getText().toString().trim();
            getP().editWorkorder(orderNo, shType, lxr, phone, startTime, ms, urls);
        } else {
            ToastUtil.showToast(this, "图片上传失败，请稍后重试");
        }
    }

    public void gongdanShuxingSuccess(GongdanShuxingModel model) {
        proId = model.getProId() + "";

        tveditName.setText(model.getCustomName());
        tveditPhone.setText(model.getCustomTel());
        tveditAddress.setText(model.getAddress());

        shType = model.getAsType() + "";

        if (shType.equals("1")) {
            eteditType.setText("故障报修");
        } else if (shType.equals("2")) {
            eteditType.setText("维护升级");
        }

        eteditLxr.setText(model.getContactName());
        eteditLxrphone.setText(model.getContactPhone());
        eteditTime.setText(model.getWishTime());
        eteditMs.setText(model.getContent());

        List<Uri> fileInfo = model.getFileInfo();
//
        if (fileInfo != null && fileInfo.size() != 0) {

            for (int i = 0; i < fileInfo.size(); i++) {
                Bitmap bitmap = BitmapFileSetting.convertStringToIcon(fileInfo.get(i).getPath());
                bmp.add(bitmap);
            }

            //多选图片
            gridviewInit();
        }

    }

    public void gridviewInit() {
        getResources().getDimension(R.dimen.app_10dp);

        recycleAdapter = new AddPhotoAdapter(this, bmp);
        editRecycleview.setAdapter(recycleAdapter);

        recycleAdapter.setOnAddClickListener(new AddPhotoAdapter.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                if (position == 0 && bmp.size() != 9) {
                    PhotographDialog.photographDialog(EditWorkorderActivity.this, bmp);
                }
            }
        });
        recycleAdapter.setOnDeleteClickListener(new AddPhotoAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                if (bmp.size() == 9) {
                    bmp.get(position).recycle();
                    bmp.remove(position);
                } else {
                    bmp.get(position - 1).recycle();
                    bmp.remove(position - 1);
                }
                recycleAdapter.notifyDataSetChanged();
            }
        });
    }

    //售后类型
    private void initPartmentPicker() {
        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                if (options1 == 0) {
                    shType = "1";
                } else {
                    shType = "2";
                }
                eteditType.setText(listType.get(options1));
            }
        }).setSubCalSize(15).setSubmitColor(R.color.color_303133)//确定按钮文字颜色
                .setCancelColor(R.color.color_606266)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCyclic(false, false, false)//设置是否循环
                .build();
        pickerView.setPicker(listType, null, null);
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
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                eteditTime.setText(strings.get(options1) + " " + lists.get(options1).get(option2));
            }
        }).setSubCalSize(15).setSubmitColor(R.color.color_303133)//确定按钮文字颜色
                .setCancelColor(R.color.color_606266)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCyclic(false, false, false)//设置是否循环
                .build();
        pickerView2.setPicker(strings, lists, null);
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
}
