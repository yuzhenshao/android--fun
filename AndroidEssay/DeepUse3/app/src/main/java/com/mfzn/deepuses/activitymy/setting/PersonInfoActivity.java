package com.mfzn.deepuses.activitymy.setting;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.myteam.ClipCircularActivity;
import com.mfzn.deepuses.activitymy.ModifyCallActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.my.UserInfoPresent;
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
import butterknife.OnClick;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class PersonInfoActivity extends BaseMvpActivity<UserInfoPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_info_icon)
    ImageView ivInfoIcon;
    @BindView(R.id.tv_info_company)
    TextView tvCompany;
    @BindView(R.id.tv_info_nic)
    TextView tvInfoNic;

    private File headClipFile = null;// 裁剪后的头像
    private final int CLIP_PHOTO_BY_SELF_REQUEST_CODE = 3;
    public static final String HEAD_ICON_DIC = Environment
            .getExternalStorageDirectory()
            + File.separator + "headIcon";
    private String clipFileNameStr = "clipIcon.jpg";

    @Override
    public int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    public UserInfoPresent newP() {
        return new UserInfoPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        myRequetPermission();
        tvBassTitle.setText(getString(R.string.app_person_info));
        getP().userInfo();

        tvCompany.setText(UserHelper.getCompanyName());
        tvInfoNic.setText(UserHelper.getU_name());
    }

    @OnClick({R.id.iv_login_back, R.id.ll_info_icon, R.id.ll_info_nic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_info_icon:
                PhotographDialog.photographDialog2(this);
                break;
            case R.id.ll_info_nic:
                Intent intent = new Intent(this, ModifyCallActivity.class);
                intent.putExtra(Constants.MODIFU_NICK, tvInfoNic.getText());
                startActivityForResult(intent, Constants.MODIFU_NICHENG);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.MODIFU_NICHENG == requestCode) {
            if (data != null) {
                String nick = data.getStringExtra(Constants.MODIFU_NICK_RETURN);
                tvInfoNic.setText(nick);
            }
        } else if (requestCode == Constants.REAL_NAME_PAIZHAO) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
//            clipPhotoBySelf(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmap != null) {
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 800, 1000);
                File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                uploadImage(file);
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
                    uploadImage(file);
                }
            }
        } else if (requestCode == CLIP_PHOTO_BY_SELF_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap bm = BitmapFactory.decodeFile(headClipFile.getAbsolutePath());
                String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                File file = BitmapFileSetting.saveBitmapFile(bm, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                uploadImage(file);
            }
        }
    }

    private void uploadImage(File file ){
        showDialog();
        getP().upLoadFile(file);
    }

    //用户信息成功返回
    public void userInfoSuccess(UserResponse result) {
        if (!TextUtils.isEmpty(result.getUserAvatar())) {
            Glide.with(context).load(ApiHelper.BASE_URL + result.getUserAvatar()).into(ivInfoIcon);
        }
    }

    //上传头像成功返回
    public void uploadIconSuccess(String res) {
        ToastUtil.showToast(this, "图片上传成功");
        Glide.with(this).load(ApiHelper.BASE_URL + res).into(ivInfoIcon);
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.MODIFY_ICON);
        RxBus.getInstance().post(eventMsg);
        hideDialog();
    }

    private void myRequetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
//            Toast.makeText(this,"您已经申请了权限!",Toast.LENGTH_SHORT).show();
        }
    }

}