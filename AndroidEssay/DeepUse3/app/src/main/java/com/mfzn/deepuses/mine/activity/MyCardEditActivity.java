package com.mfzn.deepuses.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.setting.SwitchCompanyActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bean.request.EditBusinessCardRequest;
import com.mfzn.deepuses.bean.response.BusinessCardResponse;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.net.ImageUploadManager;
import com.mfzn.deepuses.net.UploadApi;
import com.mfzn.deepuses.utils.BitmapFileSetting;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ImageCompressUtil;
import com.mfzn.deepuses.utils.PhotographDialog;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.mfzn.deepuses.utils.UIUtils.showToast;

/**
 * @author yz @date 2020-02-22
 */
public class MyCardEditActivity extends BaseActivity {

    public static String CARD_INFO = "CardInfo";
    public static String ONLY_SELECTED;
    @BindView(R.id.photo)
    RoundImageView userPhoto;
    @BindView(R.id.name)
    TextView tvCardName;
    @BindView(R.id.phone)
    TextView tvCardPhone;
    @BindView(R.id.email)
    EditText etCardEmail;
    @BindView(R.id.select_company)
    TextView tvCompanySelect;
    @BindView(R.id.company_position)
    EditText etCompanyPosition;
    @BindView(R.id.work_year)
    EditText etWorkYear;
    @BindView(R.id.jz_number)
    EditText etCarddJZ;
    @BindView(R.id.gz_number)
    EditText etCardGZ;
    @BindView(R.id.project)
    TextView tvCardProjrct;
    @BindView(R.id.company_name)
    TextView tvCompanyName;
    @BindView(R.id.project_number)
    TextView tvProjectNumber;

    private static int SELECTED = 1;
    private BusinessCardResponse mBusinessCardResponse;
    private boolean isShowCompany;
    private boolean isShowProNum;

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
            tvCompanySelect.setText(mBusinessCardResponse.getCompanyName());
            etCardEmail.setText(mBusinessCardResponse.getUserEmail());
            if (!TextUtils.isEmpty(mBusinessCardResponse.getUserEmail())) {
                etCardEmail.setSelection(mBusinessCardResponse.getUserEmail().length());
            }
            etCompanyPosition.setText(mBusinessCardResponse.getUserPosition());
            if (!TextUtils.isEmpty(mBusinessCardResponse.getUserPosition())) {
                etCompanyPosition.setSelection(mBusinessCardResponse.getUserPosition().length());
            }
            etWorkYear.setText(mBusinessCardResponse.getWorkYear());
            etCarddJZ.setText(mBusinessCardResponse.getJzNum());
            etCardGZ.setText(mBusinessCardResponse.getGzNum());

            setCompanyNameStatus(mBusinessCardResponse.getShowCompany() != 0);
            setProjectStatus(mBusinessCardResponse.getShowProNum() != 0);
        }
    }

    private void setCompanyNameStatus(boolean isShowCompany) {
        this.isShowCompany = isShowCompany;
        tvCompanyName.setTextColor(Color.parseColor(isShowCompany ? "#3D7EFF" : "#BFC2CC"));
        tvCompanyName.setBackgroundResource(isShowCompany ? R.drawable.flowing_type_tv_bule_shape :
                R.drawable.flowing_type_tv_while_shape);
    }

    private void setProjectStatus(boolean isShowProNum) {
        this.isShowProNum = isShowProNum;
        tvProjectNumber.setTextColor(Color.parseColor(isShowProNum ? "#3D7EFF" : "#BFC2CC"));
        tvProjectNumber.setBackgroundResource(isShowProNum ? R.drawable.flowing_type_tv_bule_shape :
                R.drawable.flowing_type_tv_while_shape);

    }

    @OnClick({R.id.iv_back, R.id.photo_icon, R.id.select_company_icon, R.id.compelete, R.id.company_name, R.id.project_number})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.photo_icon:
                PhotographDialog.photographDialog2(this);
                break;
            case R.id.select_company_icon:
                Intent intent = new Intent(this, CompanyListActivity.class);
                startActivityForResult(intent, SELECTED);
                break;
            case R.id.compelete:
                uploadEditCard();
                break;
            case R.id.company_name:
                setCompanyNameStatus(!isShowCompany);
                break;
            case R.id.project_number:
                setProjectStatus(!isShowProNum);
                break;
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(CARD_INFO, mBusinessCardResponse);
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }

    private void uploadEditCard() {
        EditBusinessCardRequest request = new EditBusinessCardRequest();
        request.setUserEmail(etCardEmail.getText().toString());
        request.setUserPosition(etCompanyPosition.getText().toString());
        request.setWorkYear(etWorkYear.getText().toString());
        request.setJzNum(etCarddJZ.getText().toString());
        request.setGzNum(etCardGZ.getText().toString());
        request.setShowCompany(mBusinessCardResponse.getShowCompany());
        request.setShowProNum(mBusinessCardResponse.getShowProNum());
        request.setCardPhone(mBusinessCardResponse.getCardPhone());
        request.setCardCompanyID(mBusinessCardResponse.getCardCompanyID());
        request.setShowCompany(isShowCompany ? 1 : 0);
        request.setShowProNum(isShowProNum ? 1 : 0);
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
        if (requestCode == SELECTED) {
            if (data != null) {
                tvCompanySelect.setText(data.getStringExtra("companyName"));
            }
        } else if (requestCode == Constants.REAL_NAME_PAIZHAO) {
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
        ApiServiceManager.uploadAvatar(userAvatar)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(MyCardEditActivity.this, "图片上传失败，请稍后重试");
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
