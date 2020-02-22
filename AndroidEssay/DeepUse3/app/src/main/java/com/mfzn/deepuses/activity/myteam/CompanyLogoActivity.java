package com.mfzn.deepuses.activity.myteam;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.myteam.CompanyLogoPresent;
import com.mfzn.deepuses.utils.BitmapFileSetting;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ImageCompressUtil;
import com.mfzn.deepuses.utils.PhotographDialog;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import java.io.File;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;

public class CompanyLogoActivity extends BaseMvpActivity<CompanyLogoPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_logo_icon)
    ImageView iv_logo_icon;

    private File headClipFile = null;// 裁剪后的头像
    private final int CLIP_PHOTO_BY_SELF_REQUEST_CODE = 3;
    public static final String HEAD_ICON_DIC = Environment
            .getExternalStorageDirectory()
            + File.separator + "headIcon";
    private String clipFileNameStr = "clipIcon.jpg";

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_logo;
    }

    @Override
    public CompanyLogoPresent newP() {
        return new CompanyLogoPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_company_logo));

        String logo = getIntent().getStringExtra(Constants.COMPANY_LOGO);
        if(!TextUtils.isEmpty(logo)) {
            Glide.with(this).load(ApiHelper.BASE_URL + logo).into(iv_logo_icon);
        }
    }

    @OnClick({R.id.iv_login_back, R.id.ll_logo_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_logo_setting:
                getPerssion();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拍照，相册返回
        if (requestCode == Constants.REAL_NAME_PAIZHAO) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
//            clipPhotoBySelf(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmap != null) {
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 800, 1000);
                File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                getP().upLoadFile(file);
            }
        } else if (requestCode == Constants.REAL_NAME_XIANGCE) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = ImageCompressUtil.getRealPathFromURI(this, uri);//获取选中图片的路径
//                    clipPhotoBySelf(path);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 800, 1000);
                    String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                    File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                    getP().upLoadFile(file);
                }
            }
        } else if (requestCode == CLIP_PHOTO_BY_SELF_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap bm = BitmapFactory.decodeFile(headClipFile.getAbsolutePath());
                String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                File file = BitmapFileSetting.saveBitmapFile(bm, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                getP().upLoadFile(file);
            }
        }
    }

    /**
     * 调用自定义切图方法
     *
     * @param filePath
     */
    protected void clipPhotoBySelf(String filePath) {
//        Log.i(TAG, "通过自定义方式去剪辑这个照片");
        headClipFile = new File(HEAD_ICON_DIC, clipFileNameStr);
        //进入裁剪页面,此处用的是自定义的裁剪页面而不是调用系统裁剪
        Intent intent = new Intent(this, ClipCircularActivity.class);
        intent.putExtra(ClipCircularActivity.IMAGE_PATH_ORIGINAL, filePath);
        intent.putExtra(ClipCircularActivity.IMAGE_PATH_AFTER_CROP,
                headClipFile.getAbsolutePath());
        startActivityForResult(intent, CLIP_PHOTO_BY_SELF_REQUEST_CODE);
    }

    //上传头像成功返回
    public void uploadIconSuccess(int status, String res) {
        if (status == 1) {
            ToastUtil.showToast(this, "logo上传成功");
            Glide.with(this).load(ApiHelper.BASE_URL + res).into(iv_logo_icon);
            EventMsg eventMsg = new EventMsg();
            eventMsg.setMsg(Constants.COMLOGO);
            RxBus.getInstance().post(eventMsg);
        } else {
            ToastUtil.showToast(this, "logo上传失败，请稍后重试");
        }
    }

    private void getPerssion() {
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.ACCESS_FINE_LOCATION,//定位
//                        Manifest.permission.ACCESS_COARSE_LOCATION,//定位
//                        Manifest.permission.READ_CONTACTS,//通讯录
//                        Manifest.permission.WRITE_CONTACTS,//通讯录
                        Manifest.permission.CAMERA
//                        Manifest.permission.READ_PHONE_STATE//打电话
                )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                  PhotographDialog.photographDialog2(CompanyLogoActivity.this);
                                }
                            }, 2000);
                        } else {
                            getvDelegate().toastShort("亲，同意了权限才能更好的使用软件哦");
                        }
                    }
                });
    }
}
