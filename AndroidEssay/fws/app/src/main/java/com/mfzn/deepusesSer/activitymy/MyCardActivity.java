package com.mfzn.deepusesSer.activitymy;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.bean.UserResponse.BusinessCardResponse;
import com.mfzn.deepusesSer.common.share.WeixinShare;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.utils.UserHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

import static com.mfzn.deepusesSer.utils.UIUtils.showToast;


/**
 * @author yz @date 2020-02-22
 */
public class MyCardActivity extends BaseActivity {
    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.riv_card_tx)
    RoundImageView rivCardTx;
    @BindView(R.id.iv_card_code)
    ImageView ivCardCode;

    @BindView(R.id.card_container)
    RelativeLayout rlCardContainer;
    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.tv_card_work_year)
    TextView tvWorkYear;
    @BindView(R.id.tv_card_jz)
    TextView tvCarddJZ;
    @BindView(R.id.tv_card_gz)
    TextView tvCardGZ;
    @BindView(R.id.tv_card_project)
    TextView tvCardProjrct;
    @BindView(R.id.tv_card_phone)
    TextView tvCardPhone;
    @BindView(R.id.tv_card_email)
    TextView tvCardEmail;
    private Bitmap mShareBitmap;
    private BusinessCardResponse businessCardResponse;
    private int EDIT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_my_card));
        getBusinessCard();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_card;
    }

    private void getBusinessCard() {
        ApiHelper.getApiService().getBusinessCard(UserHelper.getUserId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .safeSubscribe(new ApiSubscriber<HttpResult<BusinessCardResponse>>() {
                    @Override
                    public void onNext(HttpResult<BusinessCardResponse> result) {
                        rlCardContainer.setVisibility(View.VISIBLE);
                        initBusinessCard(result.getRes());
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(MyCardActivity.this, "名片获取失败");
                    }
                });
    }

    private void initBusinessCard(BusinessCardResponse businessCardResponse) {
        this.businessCardResponse = businessCardResponse;
        Glide.with(context).load(ApiHelper.BASE_URL + businessCardResponse.getUserAvatar())
                .into(rivCardTx);
        Glide.with(context).load(ApiHelper.BASE_API_QR + "/" + businessCardResponse.getMyQrCode())
                .into(ivCardCode);
        tvCardName.setText(businessCardResponse.getUserName());
        tvWorkYear.setText(getString(R.string.card_word_year, businessCardResponse.getWorkYear()));
        tvCarddJZ.setText(getString(R.string.card_jz, businessCardResponse.getJzNum()));
        tvCardGZ.setText(getString(R.string.card_gz, businessCardResponse.getGzNum()));
        tvCardProjrct.setText(getString(R.string.card_project, businessCardResponse.getProNum()));
        tvCardPhone.setText(businessCardResponse.getCardPhone());
        tvCardEmail.setText(businessCardResponse.getUserEmail());
    }

    @OnClick({R.id.ll_card_xiazai, R.id.ll_card_weixin, R.id.tv_edit_card})
    public void onViewClicked(View view) {
        if (businessCardResponse == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.ll_card_xiazai:
                saveBitmapToLocal();
                break;
            case R.id.ll_card_weixin:
                WeixinShare.getInstance(this).shareImageToFriend(getViewBitmap());
                break;
            case R.id.tv_edit_card:
                startActivityForResult(MyCardEditActivity.newIntent(this, businessCardResponse), EDIT_REQUEST_CODE);
                break;

        }
    }

    @OnClick(R.id.iv_back)
    public void goBack() {
        finish();
    }

    private void saveBitmapToLocal() {
        try {
            File imageDir = new File(Constants.IMAGE_DIR);
            if (!imageDir.exists()) {
                imageDir.mkdir();
            }
            String imageName = businessCardResponse.getUserID() + ".png";
            File file = new File(imageDir, imageName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream os = new FileOutputStream(file);
            getViewBitmap().compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
            if (file.exists()) {
                showToast("保存成功");
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
                finish();
            } else {
                showToast("保存是阿碧");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getViewBitmap() {
        if (mShareBitmap == null) {
            try {
                mShareBitmap = Bitmap.createBitmap(rlCardContainer.getWidth(), rlCardContainer.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(mShareBitmap);
                rlCardContainer.draw(canvas);
            } catch (Exception e) {

            }
        }
        return mShareBitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_REQUEST_CODE && data != null) {
            BusinessCardResponse businessCardResponse = (BusinessCardResponse) data.getSerializableExtra(MyCardEditActivity.CARD_INFO);
            if (businessCardResponse != null) {
                initBusinessCard(businessCardResponse);
            }
        }
    }
}
