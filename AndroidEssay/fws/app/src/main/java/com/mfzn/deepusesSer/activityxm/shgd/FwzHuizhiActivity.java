package com.mfzn.deepusesSer.activityxm.shgd;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.AddPhotoAdapterMuti;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.net.ImageUploadManager;
import com.mfzn.deepusesSer.present.shouhou.FwzHuizhiPresnet;
import com.mfzn.deepusesSer.utils.Bimp;
import com.mfzn.deepusesSer.utils.BitmapFileSetting;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.FileUtils;
import com.mfzn.deepusesSer.utils.ImageCompressUtil;
import com.mfzn.deepusesSer.utils.PhotographDialog;
import com.mfzn.deepusesSer.utils.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class FwzHuizhiActivity extends BaseMvpActivity<FwzHuizhiPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_if_bold)
    TextView tvIfBold;
    @BindView(R.id.iv_if_clz)
    ImageView ivIfClz;
    @BindView(R.id.iv_if_yjj)
    ImageView ivIfYjj;

    @BindView(R.id.et_gzfx)
    EditText etGzfx;
    @BindView(R.id.gzfx_xgtp)
    RecyclerView gzfx_xgtp;

    @BindView(R.id.et_jjfa)
    EditText etJjfa;

    @BindView(R.id.jjfa_xgtp)
    RecyclerView jjfa_xgtp;

    @BindView(R.id.et_clnr)
    EditText etClnr;

    @BindView(R.id.clnr_xgtp)
    RecyclerView clnr_xgtp;

    @BindView(R.id.clz_huizhi)
    LinearLayout llClz;

    @BindView(R.id.yjj_huizhi)
    LinearLayout llYjj;

    @BindView(R.id.ll_dzqm)
    LinearLayout llDzqm;

    @BindView(R.id.iv_qm)
    ImageView ivQm;

    private int type = 1;
    private String orderNo = "";
    private String pro_id = "";

    //故障分析图片列表
    private List<File> filesGzfx = new ArrayList<>();
    private AddPhotoAdapterMuti recycleAdapter_gzfx;
    private List<Bitmap> bmpGzfx = new ArrayList<Bitmap>();
    private List<String> drrGzfx = new ArrayList<String>();
    private ArrayList<String> mSelectPathGzfx;
    private float dpGzfx;
    private int i_gzfx = 0;
    private String gzfx_ids = "";
    //解决方案图片列表
    private List<File> filesJjfa = new ArrayList<>();
    private AddPhotoAdapterMuti recycleAdapter_jjfa;
    private List<Bitmap> bmpJjfa = new ArrayList<Bitmap>();
    private List<String> drrJjfa = new ArrayList<String>();
    private ArrayList<String> mSelectPathJjfa;
    private float dpJjfa;
    private int i_jjfa = 0;
    private String jjfa_ids = "";
    //处理内容图片列表
    private List<File> filesClnr = new ArrayList<>();
    private AddPhotoAdapterMuti recycleAdapter_clnr;

    private List<Bitmap> bmpClnr = new ArrayList<Bitmap>();
    private List<String> drrClnr = new ArrayList<String>();
    private ArrayList<String> mSelectPathClnr;
    private float dpClnr;
    private int i_clnr = 0;
    private String clnr_ids = "";

    private File filesQm;
    private String photoID_qm;

    private String skje = "0";
    private String skbz = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_fwz_huizhi;
    }

    @Override
    public FwzHuizhiPresnet newP() {
        return new FwzHuizhiPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("填写回单");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        tvIfBold.getPaint().setFakeBoldText(true);
        orderNo = getIntent().getStringExtra("orderNo");
        pro_id = getIntent().getStringExtra("pro_id");
        if (type == 1) {
            ivIfClz.setImageResource(R.mipmap.work_xuanzhong);
            ivIfYjj.setImageResource(R.mipmap.work_weixuan);
            llClz.setVisibility(View.VISIBLE);
            llYjj.setVisibility(View.GONE);
        } else if (type == 2) {
            ivIfClz.setImageResource(R.mipmap.work_weixuan);
            ivIfYjj.setImageResource(R.mipmap.work_xuanzhong);
            llClz.setVisibility(View.GONE);
            llYjj.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gzfx_xgtp.setLayoutManager(layoutManager);
        //多选图片
        gridviewInitGzfx();


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        jjfa_xgtp.setLayoutManager(layoutManager1);
        //多选图片
        gridviewInitJjfa();

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        clnr_xgtp.setLayoutManager(layoutManager2);
        //多选图片
        gridviewInitClnr();
    }

    public void gridviewInitGzfx() {
        getResources().getDimension(R.dimen.app_10dp);

        recycleAdapter_gzfx = new AddPhotoAdapterMuti(this, bmpGzfx);
        gzfx_xgtp.setAdapter(recycleAdapter_gzfx);

        recycleAdapter_gzfx.setOnAddClickListener(new AddPhotoAdapterMuti.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                if (position == 0 && bmpGzfx.size() != 9) {
                    PhotographDialog.photographDialogGzfx(FwzHuizhiActivity.this, bmpGzfx);
                }
            }
        });
        recycleAdapter_gzfx.setOnDeleteClickListener(new AddPhotoAdapterMuti.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                if (bmpGzfx.size() == 9) {
                    bmpGzfx.get(position).recycle();
                    bmpGzfx.remove(position);
                    drrGzfx.remove(position);
                } else {
                    bmpGzfx.get(position - 1).recycle();
                    bmpGzfx.remove(position - 1);
                    drrGzfx.remove(position - 1);
                }
                recycleAdapter_gzfx.notifyDataSetChanged();
            }
        });
    }

    public void gridviewInitJjfa() {
        getResources().getDimension(R.dimen.app_10dp);

        recycleAdapter_jjfa = new AddPhotoAdapterMuti(this, bmpJjfa);
        jjfa_xgtp.setAdapter(recycleAdapter_jjfa);

        recycleAdapter_jjfa.setOnAddClickListener(new AddPhotoAdapterMuti.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                if (position == 0 && bmpJjfa.size() != 9) {
                    PhotographDialog.photographDialogJjfa(FwzHuizhiActivity.this, bmpJjfa);
                }
            }
        });
        recycleAdapter_jjfa.setOnDeleteClickListener(new AddPhotoAdapterMuti.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                if (bmpJjfa.size() == 9) {
                    bmpJjfa.get(position).recycle();
                    bmpJjfa.remove(position);
                    drrJjfa.remove(position);
                } else {
                    bmpJjfa.get(position - 1).recycle();
                    bmpJjfa.remove(position - 1);
                    drrJjfa.remove(position - 1);
                }
                recycleAdapter_jjfa.notifyDataSetChanged();
            }
        });
    }

    public void gridviewInitClnr() {
        getResources().getDimension(R.dimen.app_10dp);

        recycleAdapter_clnr = new AddPhotoAdapterMuti(this, bmpClnr);
        clnr_xgtp.setAdapter(recycleAdapter_clnr);

        recycleAdapter_clnr.setOnAddClickListener(new AddPhotoAdapterMuti.OnAddItemClickListener() {
            @Override
            public void onItemAddClick(View view, int position) {
                if (position == 0 && bmpClnr.size() != 9) {
                    PhotographDialog.photographDialogClnr(FwzHuizhiActivity.this, bmpClnr);
                }
            }
        });
        recycleAdapter_clnr.setOnDeleteClickListener(new AddPhotoAdapterMuti.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                if (bmpClnr.size() == 9) {
                    bmpClnr.get(position).recycle();
                    bmpClnr.remove(position);
                    drrClnr.remove(position);
                } else {
                    bmpClnr.get(position - 1).recycle();
                    bmpClnr.remove(position - 1);
                    drrClnr.remove(position - 1);
                }
                recycleAdapter_clnr.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.iv_login_back, R.id.ll_if_clz, R.id.ll_if_yjj, R.id.but_if_qr, R.id.ll_dzqm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_if_clz:
                type = 1;
                ivIfClz.setImageResource(R.mipmap.work_xuanzhong);
                ivIfYjj.setImageResource(R.mipmap.work_weixuan);
                llClz.setVisibility(View.VISIBLE);
                llYjj.setVisibility(View.GONE);
                break;
            case R.id.ll_if_yjj:
                type = 2;
                ivIfClz.setImageResource(R.mipmap.work_weixuan);
                ivIfYjj.setImageResource(R.mipmap.work_xuanzhong);
                llClz.setVisibility(View.GONE);
                llYjj.setVisibility(View.VISIBLE);
//                new WeixinTishiDialog.Builder(this)
//                        .setHeight(1f)  //屏幕高度*0.23
//                        .setWidth(1f)  //屏幕宽度*0.65
//                        .setContentText("您尚未确认已收款，工单无法结束")
//                        .setTitle("提示")
//                        .setCanceledOnTouchOutside(false)
//                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<WeixinTishiDialog>() {
//                            @Override
//                            public void clickLeftButton(WeixinTishiDialog dialog, View view) {
//                                dialog.dismiss();
//                                FwzHuizhiActivity.this.finish();
//                            }
//
//                            @Override
//                            public void clickRightButton(WeixinTishiDialog dialog, View view) {
//                                dialog.dismiss();
//                                XxskDialog xxskialog = new XxskDialog.Builder(FwzHuizhiActivity.this)
//                                        .setHeight(1f)  //屏幕高度*0.23
//                                        .setWidth(1f)  //屏幕宽度*0.65
//                                        .setCanceledOnTouchOutside(true)
//                                        .setSingleListener(new DialogInterface.OnLeftAndRightClick2Listener<XxskDialog>() {
//                                            @Override
//                                            public void clickLeftButton(XxskDialog dialog, View view) {
//                                                dialog.dismiss();
//                                                FwzHuizhiActivity.this.finish();
//                                            }
//
//                                            @Override
//                                            public void clickRightButton(XxskDialog dialog, View view, EditText text) {
//
//                                            }
//
//                                            @Override
//                                            public void clickRightButton2(XxskDialog dialog, View view, EditText text1, EditText text2) {
//                                                if (TextUtils.isEmpty(text1.getText().toString())){
//                                                    showMessage("请填写金额");
//                                                    return;
//                                                }
//                                                if (TextUtils.isEmpty(text2.getText().toString())){
//                                                    showMessage("请填写收款备注");
//                                                    return;
//                                                }
//                                                skje = text1.getText().toString();
//                                                skbz = text2.getText().toString();
//                                                dialog.dismiss();
//                                                ivIfClz.setImageResource(R.mipmap.work_weixuan);
//                                                ivIfYjj.setImageResource(R.mipmap.work_xuanzhong);
//                                                llClz.setVisibility(View.GONE);
//                                                llYjj.setVisibility(View.VISIBLE);
//                                            }
//                                        })
//                                        .build();
//                                xxskialog.show();
//                            }
//                        })
//                        .build()
//                        .show();
                break;
            case R.id.but_if_qr:
                if (type == 1) {
                    if (TextUtils.isEmpty(etGzfx.getText().toString())) {
                        showMessage("故障分析不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(etJjfa.getText().toString())) {
                        showMessage("解决方案不能为空");
                        return;
                    }
                    filesGzfx.clear();
                    if (bmpGzfx.size() > 0) {
                        for (int i = 0; i < bmpGzfx.size(); i++) {
                            String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss-" + i, new Date()) + ".jpg";
                            filesGzfx.add(BitmapFileSetting.saveBitmapFile(bmpGzfx.get(i), PhotographDialog.Image_SAVEDIR + "/" + cameraFile));
                        }
                        ImageUploadManager.upLoadFile(filesGzfx, new ImageUploadManager.ImageUploadCallback() {

                            @Override
                            public void uploadSuccess(String url) {
                                gzfx_ids = url;
                                uploadJifa();
                            }

                            @Override
                            public void uoloadFailed(String error) {
                                ToastUtil.showToast(FwzHuizhiActivity.this, "图片上传失败，请稍后重试");
                            }
                        });
                    } else {
                        if (bmpJjfa.size() > 0) {
                            uploadJifa();
                        } else {
                            getP().createReceipt(orderNo, etGzfx.getText().toString(), gzfx_ids, etJjfa.getText().toString(), jjfa_ids, String.valueOf(type), "", "", "");
                        }
                    }

                } else {
                    if (TextUtils.isEmpty(etClnr.getText().toString())) {
                        showMessage("处理内容不能为空");
                        return;
                    }
                    if (filesQm == null) {
                        showMessage("签名不能为空");
                        return;
                    }
                    filesClnr.clear();
                    if (bmpClnr.size() > 0) {
                        for (int i = 0; i < bmpClnr.size(); i++) {
                            String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss-" + i, new Date()) + ".jpg";
                            filesClnr.add(BitmapFileSetting.saveBitmapFile(bmpClnr.get(i), PhotographDialog.Image_SAVEDIR + "/" + cameraFile));
                        }
                        ImageUploadManager.upLoadFile(filesClnr, new ImageUploadManager.ImageUploadCallback() {

                            @Override
                            public void uploadSuccess(String url) {
                                clnr_ids = url;
                                createReceipt();
                            }

                            @Override
                            public void uoloadFailed(String error) {
                                ToastUtil.showToast(FwzHuizhiActivity.this, "图片上传失败，请稍后重试");
                            }
                        });
                    } else {
                        createReceipt();
                    }
                }

                break;
            case R.id.ll_dzqm:
                Intent intent = new Intent(FwzHuizhiActivity.this, DzqmActivity.class);
                startActivityForResult(intent, 888);
                break;
        }
    }

    private void createReceipt() {
        getP().createReceipt(orderNo, "", "", etClnr.getText().toString(), clnr_ids, String.valueOf(type), photoID_qm, skje, skbz);
    }

    private void uploadJifa() {
        filesJjfa.clear();
        for (int i = 0; i < bmpJjfa.size(); i++) {
            String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss-" + i, new Date()) + ".jpg";
            filesJjfa.add(BitmapFileSetting.saveBitmapFile(bmpJjfa.get(i), PhotographDialog.Image_SAVEDIR + "/" + cameraFile));
        }
        ImageUploadManager.upLoadFile(filesJjfa, new ImageUploadManager.ImageUploadCallback() {

            @Override
            public void uploadSuccess(String url) {
                jjfa_ids = url;
                getP().createReceipt(orderNo, etGzfx.getText().toString(), gzfx_ids, etJjfa.getText().toString(), jjfa_ids, String.valueOf(type), "", "", "");
            }

            @Override
            public void uoloadFailed(String error) {
                ToastUtil.showToast(FwzHuizhiActivity.this, "图片上传失败，请稍后重试");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拍照，相册返回
        if (requestCode == Constants.XGTP_GZFX_CAMERA) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmap != null) {
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 480, 480);
                bmpGzfx.add(newbitmap);
                //把图片数量添加进集合,方便删除，统计数量
                drrGzfx.add(FileUtils.SDPATH + ".JPEG");
                gridviewInitGzfx();
            }
        } else if (requestCode == Constants.XGTP_GZFX_ALBUM) {
            if (drrGzfx.size() < 9 && resultCode == RESULT_OK && null != data) {
                mSelectPathGzfx = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Bitmap bitmap;
                try {
                    for (String p : mSelectPathGzfx) {
                        bitmap = Bimp.revitionImageSize(p.toString());
//                            PhotoActivity.bitmap.add(bitmap);
                        bitmap = Bimp.createFramedPhoto(480, 480, bitmap, (int) (dpGzfx * 1.6f));
                        bmpGzfx.add(bitmap);
                        gridviewInitGzfx();
                        //把图片数量添加进集合,方便删除，统计数量
                        drrGzfx.add(FileUtils.SDPATH + ".JPEG");
                        //把位图转化为字符流
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        baos.close();
                        byte[] buffer = baos.toByteArray();
                        String[] photoImgliu = new String[1024 * 1024];
                        photoImgliu[i_gzfx] = Base64.encodeToString(buffer, 0, buffer.length, Base64.DEFAULT);
                        i_gzfx++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == Constants.XGTP_JJFA_CAMERA) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmap != null) {
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 480, 480);
                bmpJjfa.add(newbitmap);
                //把图片数量添加进集合,方便删除，统计数量
                drrJjfa.add(FileUtils.SDPATH + ".JPEG");
                gridviewInitJjfa();
            }
        } else if (requestCode == Constants.XGTP_JJFA_ALBUM) {
            if (drrJjfa.size() < 9 && resultCode == RESULT_OK && null != data) {
                mSelectPathJjfa = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Bitmap bitmap;
                try {
                    for (String p : mSelectPathJjfa) {
                        bitmap = Bimp.revitionImageSize(p.toString());
//                            PhotoActivity.bitmap.add(bitmap);
                        bitmap = Bimp.createFramedPhoto(480, 480, bitmap, (int) (dpJjfa * 1.6f));
                        bmpJjfa.add(bitmap);
                        gridviewInitJjfa();
                        //把图片数量添加进集合,方便删除，统计数量
                        drrJjfa.add(FileUtils.SDPATH + ".JPEG");
                        //把位图转化为字符流
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        baos.close();
                        byte[] buffer = baos.toByteArray();
                        String[] photoImgliu = new String[1024 * 1024];
                        photoImgliu[i_jjfa] = Base64.encodeToString(buffer, 0, buffer.length, Base64.DEFAULT);
                        i_jjfa++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == Constants.XGTP_CLNR_CAMERA) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmap != null) {
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 480, 480);
                bmpClnr.add(newbitmap);
                //把图片数量添加进集合,方便删除，统计数量
                drrClnr.add(FileUtils.SDPATH + ".JPEG");
                gridviewInitClnr();
            }
        } else if (requestCode == Constants.XGTP_CLNR_ALBUM) {
            if (drrClnr.size() < 9 && resultCode == RESULT_OK && null != data) {
                mSelectPathClnr = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Bitmap bitmap;
                try {
                    for (String p : mSelectPathClnr) {
                        bitmap = Bimp.revitionImageSize(p.toString());
//                            PhotoActivity.bitmap.add(bitmap);
                        bitmap = Bimp.createFramedPhoto(480, 480, bitmap, (int) (dpClnr * 1.6f));
                        bmpClnr.add(bitmap);
                        gridviewInitClnr();
                        //把图片数量添加进集合,方便删除，统计数量
                        drrClnr.add(FileUtils.SDPATH + ".JPEG");
                        //把位图转化为字符流
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        baos.close();
                        byte[] buffer = baos.toByteArray();
                        String[] photoImgliu = new String[1024 * 1024];
                        photoImgliu[i_clnr] = Base64.encodeToString(buffer, 0, buffer.length, Base64.DEFAULT);
                        i_clnr++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 888) {
            if (resultCode == 0) {
                String path = data.getStringExtra("path");
                File file = new File(path);
                if (file.exists()) {
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    ivQm.setImageBitmap(bm);
                }
                filesQm = file;
                ImageUploadManager.uploadImage(filesQm, new ImageUploadManager.ImageUploadCallback() {

                    @Override
                    public void uploadSuccess(String url) {
                        photoID_qm=url;
                        ToastUtil.showToast(FwzHuizhiActivity.this, "签名上传成功");
                    }

                    @Override
                    public void uoloadFailed(String error) {
                        ToastUtil.showToast(FwzHuizhiActivity.this, "签名上传失败，请稍后重试");
                    }
                });
            }
        }
    }


    public void fwzHuizhiResult(HttpResult result) {
        ToastUtil.showToast(this, result.getMsg());
        if (result.getStatus() == 1) {
            Intent intent = new Intent();
            setResult(0, intent);
            finish();
        }
//        else{
//            Intent intent = new Intent();
//            setResult(1,intent);
//            finish();
//        }
    }
}
