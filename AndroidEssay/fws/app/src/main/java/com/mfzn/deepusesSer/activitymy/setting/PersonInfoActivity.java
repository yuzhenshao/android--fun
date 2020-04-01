package com.mfzn.deepusesSer.activitymy.setting;

import android.Manifest;
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
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.myteam.ClipCircularActivity;
import com.mfzn.deepusesSer.activitymy.ModifyCallActivity;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.model.person.UserInfoModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.present.person.UserInfoPresent;
import com.mfzn.deepusesSer.present.shouhou.WaitJiedanPresnet;
import com.mfzn.deepusesSer.utils.BitmapFileSetting;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.ImageCompressUtil;
import com.mfzn.deepusesSer.utils.ImageLoadHelper;
import com.mfzn.deepusesSer.utils.PhotographDialog;
import com.mfzn.deepusesSer.utils.ToastUtil;

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
    }

    @OnClick({R.id.iv_login_back, R.id.ll_info_icon, R.id.ll_info_nic, R.id.ll_info_company})
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
            case R.id.ll_info_company:
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
            clipPhotoBySelf(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
        } else if (requestCode == Constants.REAL_NAME_XIANGCE) {

            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = ImageCompressUtil.getRealPathFromURI(this, uri);//获取选中图片的路径
                    clipPhotoBySelf(path);
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

    //用户信息成功返回
    public void userInfoSuccess(UserModel result) {
        if (!TextUtils.isEmpty(result.getUserAvatar())) {
            Glide.with(context).load(ApiHelper.BASE_URL + result.getUserAvatar()).into(ivInfoIcon);
        }
        tvInfoNic.setText(result.getUserName());
    }

    //上传头像成功返回
    public void uploadIconSuccess(String res) {
        ToastUtil.showToast(this, "图片上传成功");
        Glide.with(this).load(ApiHelper.BASE_URL + res).into(ivInfoIcon);
    }

    private void myRequetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
//            Toast.makeText(this,"您已经申请了权限!",Toast.LENGTH_SHORT).show();
        }
    }

}
