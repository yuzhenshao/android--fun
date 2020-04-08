package com.mfzn.deepusesSer.activitymy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.BaseApplication;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.bean.UserResponse.BusinessCardResponse;
import com.mfzn.deepusesSer.bean.request.EditBusinessCardRequest;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.net.ImageUploadManager;
import com.mfzn.deepusesSer.utils.BitmapFileSetting;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.ImageCompressUtil;
import com.mfzn.deepusesSer.utils.PhotographDialog;
import com.mfzn.deepusesSer.utils.RxBus;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.utils.UserHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import java.io.File;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.mfzn.deepusesSer.utils.UIUtils.showToast;

/**
 * @author yz @date 2020-02-22
 */
public class MyCardEditActivity extends BaseActivity {

    public static String CARD_INFO = "CardInfo";
    @BindView(R.id.photo)
    RoundImageView userPhoto;
    @BindView(R.id.name)
    TextView tvCardName;
    @BindView(R.id.phone)
    TextView tvCardPhone;
    @BindView(R.id.email)
    EditText etCardEmail;

    @BindView(R.id.work_year)
    EditText etWorkYear;
    @BindView(R.id.jz_number)
    EditText etCarddJZ;
    @BindView(R.id.gz_number)
    EditText etCardGZ;
    @BindView(R.id.project)
    TextView tvCardProjrct;
    private BusinessCardResponse mBusinessCardResponse;

    public static Intent newIntent(Context context, BusinessCardResponse businessCardResponse) {
        Intent intent = new Intent(context, MyCardEditActivity.class);
        intent.putExtra(CARD_INFO, businessCardResponse);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        mBusinessCardResponse = (BusinessCardResponse) getIntent().getSerializableExtra(CARD_INFO);
        if (mBusinessCardResponse != null) {
            Glide.with(context).load(ApiHelper.BASE_URL + mBusinessCardResponse.getUserAvatar())
                    .into(userPhoto);
            tvCardName.setText(mBusinessCardResponse.getUserName());
            tvCardPhone.setText(mBusinessCardResponse.getCardPhone());
            tvCardProjrct.setText(mBusinessCardResponse.getProNum());
            etCardEmail.setText(mBusinessCardResponse.getUserEmail());
            if (!TextUtils.isEmpty(mBusinessCardResponse.getUserEmail())) {
                etCardEmail.setSelection(mBusinessCardResponse.getUserEmail().length());
            }
            etWorkYear.setText(mBusinessCardResponse.getWorkYear());
            etCarddJZ.setText(mBusinessCardResponse.getJzNum());
            etCardGZ.setText(mBusinessCardResponse.getGzNum());
        }
    }

    @OnClick({R.id.iv_back, R.id.photo_icon, R.id.compelete,})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.photo_icon:
                PhotographDialog.photographDialog2(this);
                break;
            case R.id.compelete:
                uploadEditCard();
                break;
        }
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        intent.putExtra(CARD_INFO, mBusinessCardResponse);
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }

    private void uploadEditCard() {
        EditBusinessCardRequest request = new EditBusinessCardRequest();
        request.setUserEmail(etCardEmail.getText().toString());
        request.setWorkYear(etWorkYear.getText().toString());
        request.setJzNum(etCarddJZ.getText().toString());
        request.setGzNum(etCardGZ.getText().toString());
        request.setShowCompany(mBusinessCardResponse.getShowCompany());
        request.setShowProNum(mBusinessCardResponse.getShowProNum());
        request.setCardPhone(mBusinessCardResponse.getCardPhone());
        request.setCardCompanyID(mBusinessCardResponse.getCardCompanyID());
        ApiHelper.getApiService().editBusinessCard(UserHelper.getToken(), UserHelper.getUid(), request)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .safeSubscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult result) {
                        mBusinessCardResponse.setUserEmail(request.getUserEmail());
                        mBusinessCardResponse.setUserPosition(request.getUserPosition());
                        mBusinessCardResponse.setWorkYear(request.getWorkYear());
                        mBusinessCardResponse.setJzNum(request.getJzNum());
                        mBusinessCardResponse.setGzNum(request.getGzNum());
                        mBusinessCardResponse.setShowCompany(request.getShowCompany());
                        mBusinessCardResponse.setShowProNum(request.getShowProNum());
                        finish();
                    }

                    @Override
                    protected void onFail(NetError error) {
                        showToast("更新名片失败，请稍后重试");
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_edit_card;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REAL_NAME_PAIZHAO) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmap != null) {
                Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 800, 1000);
                File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                upLoadFile(file);
            }
        } else if (requestCode == Constants.REAL_NAME_XIANGCE) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = ImageCompressUtil.getRealPathFromURI(this, uri);//获取选中图片的路径
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    Bitmap newbitmap = ImageCompressUtil.compressBySize(bitmap, 800, 1000);
                    String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                    File file = BitmapFileSetting.saveBitmapFile(newbitmap, PhotographDialog.Image_SAVEDIR + "/" + cameraFile);
                    upLoadFile(file);
                }
            }
        }
    }

    public void upLoadFile(final File file) {
        ImageUploadManager.uploadImage(file, new ImageUploadManager.ImageUploadCallback() {

            @Override
            public void uploadSuccess(String url) {
                uploadAvatar(url);
            }

            @Override
            public void uoloadFailed(String error) {
                ToastUtil.showToast(BaseApplication.getContext(), error);
            }
        });
    }

    private void uploadAvatar(String userAvatar) {
        ApiHelper.getApiService().uploadAvatar(UserHelper.getToken(), UserHelper.getUid(), userAvatar)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(BaseApplication.getContext(), "图片上传失败，请稍后重试");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(MyCardEditActivity.this, "图片上传成功");
                        mBusinessCardResponse.setUserAvatar(userAvatar);
                        Glide.with(MyCardEditActivity.this).load(ApiHelper.BASE_URL + userAvatar).into(userPhoto);
                        EventMsg eventMsg = new EventMsg();
                        eventMsg.setMsg(Constants.MODIFY_ICON);
                        RxBus.getInstance().post(eventMsg);
                    }
                });
    }
}
