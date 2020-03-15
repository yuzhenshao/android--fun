package com.mfzn.deepuses.activity.khgl;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.FollowPhotoAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.customer.AddFollowPresnet;
import com.mfzn.deepuses.qiniu.Auth;
import com.mfzn.deepuses.qiniu.Config;
import com.mfzn.deepuses.qiniu.QiNiuInitialize;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.ImageCompressUtil;
import com.mfzn.deepuses.utils.ObtainTime;
import com.mfzn.deepuses.utils.PhotographDialog;
import com.mfzn.deepuses.utils.ToastUtil;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadOptions;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.GenjinTypeDialog;
import com.wevey.selector.dialog.bean.SelectModel;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AddFollowActivity extends BaseMvpActivity<AddFollowPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.et_fo_type)
    EditText etFoType;
    @BindView(R.id.et_fo_fs)
    EditText etFoFs;
    @BindView(R.id.et_fo_time)
    EditText etFoTime;
    @BindView(R.id.et_fo_xq)
    EditText etFoXq;
    @BindView(R.id.fo_recycleview)
    RecyclerView foRecycleview;
    @BindView(R.id.but_fo_commit)
    Button butFoCommit;

    private SelectModel model;
    private OptionsPickerView pickerView;
    private List<String> listType = new ArrayList<>();

    private String statusID = "";//跟进状态ID
    private String communicationTypeID = "";//沟通方式

    private TimePickerView pvTime;

    //图片列表
    private List<File> files = new ArrayList<>();
    private FollowPhotoAdapter recycleAdapter;

    private List<Bitmap> bmp = new ArrayList<Bitmap>();
    private List<String> drr = new ArrayList<String>();

    private String dataid;
    private Bitmap bitmap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_follow;
    }

    @Override
    public AddFollowPresnet newP() {
        return new AddFollowPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText("新增跟进记录");

        dataid = getIntent().getStringExtra(Constants.ADD_FOLL);
        String name = getIntent().getStringExtra(Constants.ADD_FOLL_NAME);
        tv_name.setText(name);

        getP().getSelect();

        initTimePicker();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        foRecycleview.setLayoutManager(layoutManager);

        //多选图片
        gridviewInit();
    }

    @OnClick({R.id.iv_login_back, R.id.et_fo_type, R.id.et_fo_fs, R.id.et_fo_time, R.id.but_fo_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_fo_type:
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
                                    etFoType.setText(model.getFollowStatus().get(position).getName());
                                    statusID = String.valueOf(model.getFollowStatus().get(position).getData_id());
                                    dialog.dismiss();
                                }
                            })
                            .build()
                            .show();
                }
                break;
            case R.id.et_fo_fs:
                if(model.getCommunicationType() == null || model.getCommunicationType().size() == 0) {
                    ToastUtil.showToast(this,"暂不可筛选，请填写其它");
                    return;
                }else {
                    listType.clear();
                    for(int i = 0; i < model.getCommunicationType().size(); i++) {
                        listType.add(model.getCommunicationType().get(i).getName());
                    }
                }
                initPartmentPicker();
                pickerView.show(view);
                break;
            case R.id.et_fo_time:
                pvTime.show(view);
                break;
            case R.id.but_fo_commit:
                String sss = "";
                for(int i1 = 0; i1 < drr.size(); i1++) {
                    if(TextUtils.isEmpty(sss)) {
                        sss = drr.get(i1);
                    }else {
                        sss = sss + "," + drr.get(i1);
                    }

                }
                getP().addFollow(communicationTypeID,statusID,dataid,etFoXq.getText().toString().trim(),
                        sss,DateUtils.getStringToDate2(etFoTime.getText().toString()));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REAL_NAME_PAIZHAO) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
//            clipPhotoBySelf(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
            Bitmap bitmaps = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmaps != null) {
                bitmap = ImageCompressUtil.compressBySize(bitmaps, 800, 1000);

                uploadImage();
//                File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
//                getP().upLoadFile(file);
            }
        } else if (requestCode == Constants.REAL_NAME_XIANGCE) {

            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = ImageCompressUtil.getRealPathFromURI(this, uri);//获取选中图片的路径
//                    clipPhotoBySelf(path);
                    Bitmap bitmaps = BitmapFactory.decodeFile(path);
                    bitmap = ImageCompressUtil.compressBySize(bitmaps, 800, 1000);
//                    String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
//                    File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
//                    getP().upLoadFile(file);
                    uploadImage();
                }
            }
        }
    }

    public void addFollowSuccess() {
        ToastUtil.showToast(this,"创建成功");
        Intent intent = new Intent();
        intent.putExtra("saas","fdsadf");
        setResult(Constants.ADD_FOLLOW,intent);
        finish();
    }

    //获取token(开发中放到业务服务器)
    public String getUpToken() {
        return Auth.create(Config.ACCESS_KEY, Config.SECRET_KEY).uploadToken(Config.BUCKET_NAME);
    }

    public void uploadImage() {
        //上传到七牛后保存的文件名
//    String key = "myjava.jpg";
        String key = "prochange/files/" + ObtainTime.endDataTime() + ".jpg";

        //定义数据上传结束后的处理动作
        final UpCompletionHandler upCompletionHandler = new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                bmp.add(bitmap);
                drr.add(key);
                gridviewInit();
            }
        };
        final UploadOptions uploadOptions = new UploadOptions(null, null, false, new UpProgressHandler() {
            @Override
            public void progress(String key, final double percent) {
                //百分数格式化
                NumberFormat fmt = NumberFormat.getPercentInstance();
                fmt.setMaximumFractionDigits(2);//最多两位百分小数，如25.23%
//                tv.setText("图片已经上传:" + fmt.format(percent));
            }
        }, new UpCancellationSignal() {
            @Override
            public boolean isCancelled() {
                return false;
            }
        });
        try {
            //上传图片jjj
            QiNiuInitialize.getSingleton().put(getByte(), key, getUpToken(), upCompletionHandler, uploadOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取资源文件中的图片
    public byte[] getByte() {
//        Resources res = getResources();
//        Bitmap bm = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        return baos.toByteArray();
    }

    public void getSelectSuccess(SelectModel model) {
        this.model = model;
    }

    public void gridviewInit() {
        getResources().getDimension(R.dimen.app_10dp);

        recycleAdapter = new FollowPhotoAdapter(this,bmp);
        foRecycleview.setAdapter(recycleAdapter);

        recycleAdapter.setOnAddClickListener(new FollowPhotoAdapter.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                if (position == 0 && bmp.size() != 9) {
//                    PhotographDialog.photographDialog(AddFollowActivity.this,bmp);
                    PhotographDialog.photographDialog2(AddFollowActivity.this);
                }
            }
        });
        recycleAdapter.setOnDeleteClickListener(new FollowPhotoAdapter.OnDeleteClickListener() {
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

    //售后类型
    private void initPartmentPicker(){
        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                communicationTypeID = String.valueOf(model.getCommunicationType().get(options1).getData_id());
                etFoFs.setText(listType.get(options1));
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

    private void initTimePicker() {
        //时间选择器
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                etFoTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
