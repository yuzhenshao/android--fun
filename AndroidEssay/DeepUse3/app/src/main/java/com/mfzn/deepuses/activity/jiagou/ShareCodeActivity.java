package com.mfzn.deepuses.activity.jiagou;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.company.CompanyRepository;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.jiagou.ShareCodeModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.jiagou.ShareCodePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.utils.Util;
import com.mfzn.deepuses.view.RoundImageView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareCodeActivity extends BaseMvpActivity<ShareCodePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_code_jiacu)
    TextView tvCodeJiacu;
    @BindView(R.id.tv_code_name)
    TextView tvCodeName;
    @BindView(R.id.iv_code_icon)
    RoundImageView ivCodeIcon;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.ll_code_main)
    LinearLayout llCodeMain;
    @BindView(R.id.tv_code_fail)
    TextView tvCodeFail;
    @BindView(R.id.iv_code_success)
    ImageView ivCodeSuccess;

    //待裁剪区域的绝对坐标
    private int[] mSavePositions = new int[2];
    //顶部裁剪坐标
    private int mCutTop;
    //左侧裁剪坐标
    private int mCutLeft;
    //最后的截图
    private Bitmap saveBitmap;
    private Bitmap bmp;

    private final int CHNEGGONG = 1;
    private final int SHIBAI = 2;

    //成功动画handler
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHNEGGONG:
                    ivCodeSuccess.setVisibility(View.GONE);
                    break;
                case SHIBAI:
                    tvCodeFail.setVisibility(View.GONE);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private int types;//1 fenxiang 2 保存
    private static final int THUMB_SIZE = 150;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;

    @Override
    public int getLayoutId() {
        return R.layout.activity_share_code;
    }

    @Override
    public ShareCodePresent newP() {
        return new ShareCodePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("团队二维码");
        tvCodeJiacu.getPaint().setFakeBoldText(true);

        tvCodeJiacu.setText(UserHelper.getU_name() + "诚邀您加入");

        int stringExtra = getIntent().getIntExtra(Constants.COMPANY_CODE, 0);
        String company = getIntent().getStringExtra(Constants.COMPANY_CODE_TEXT);
        if (stringExtra == 0) {
            getP().shareCode();
        } else if (stringExtra == 1) {
            getP().shareCode(company);
        }
    }

    @OnClick({R.id.iv_login_back, R.id.but_bc, R.id.but_fx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_bc:
                types = 2;
                jietu();
                break;
            case R.id.but_fx:
                types = 1;
                jietu();
                break;
        }
    }

    public void shareCodeSuccess(ShareCodeModel model) {
        tvCodeName.setText(model.getCompanyName());
        String userAvatar = model.getUserAvatar();
        SelectCompanyModel companyModel = CompanyRepository.getInstance().getCurCompany();
        if (companyModel != null && !TextUtils.isEmpty(companyModel.getLogo())) {
            Glide.with(this).load(ApiHelper.BASE_URL + companyModel.getLogo()).into(ivCodeIcon);
        }
        Glide.with(this).load(ApiHelper.BASE_API_URL + model.getQrCodeUrl()).into(ivCode);
    }

    private void jietu() {

        llCodeMain.getLocationOnScreen(mSavePositions);
        mCutLeft = mSavePositions[0];
        mCutTop = mSavePositions[1];

        // 获取屏幕
        View dView = getWindow().getDecorView();
        dView.destroyDrawingCache();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        bmp = dView.getDrawingCache();

        new Thread(new Runnable() {
            @Override
            public void run() {
                screenshot();
            }
        }).start();
    }

    private void screenshot() {

        if (bmp != null) {
            try {
                //二次截图
                saveBitmap = Bitmap.createBitmap(llCodeMain.getWidth(), llCodeMain.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(saveBitmap);
                Paint paint = new Paint();
                canvas.drawBitmap(bmp, new Rect(mCutLeft + 10, mCutTop + 10, mCutLeft + llCodeMain.getWidth() - 10,
                                mCutTop + llCodeMain.getHeight() - 10),
                        new Rect(0, 0, llCodeMain.getWidth(), llCodeMain.getHeight()), paint);

                File imageDir = new File(Constants.IMAGE_DIR);
                if (!imageDir.exists()) {
                    imageDir.mkdir();
                }
                String imageName = Constants.SCREEN_SHOT;
                File file = new File(imageDir, imageName);
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream os = new FileOutputStream(file);
                saveBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();

                //将截图保存至相册并广播通知系统刷新
                MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), imageName, null);
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file));
                sendBroadcast(intent);

                this.runOnUiThread(new Runnable() {//成功
                    @Override
                    public void run() {
                        if (types == 1) {
                            String path = Constants.IMAGE_DIR + "/" + Constants.SCREEN_SHOT;
                            File file = new File(path);
                            if (!file.exists()) {
//                    String tip = SendToWXActivity.this.getString(R.string.send_img_file_not_exist);
//                    Toast.makeText(SendToWXActivity.this, tip + " path = " + path, Toast.LENGTH_LONG).show();
                                return;
                            }

                            WXImageObject imgObj = new WXImageObject();
                            imgObj.setImagePath(path);

                            WXMediaMessage msg = new WXMediaMessage();
                            msg.mediaObject = imgObj;

                            Bitmap bmp = BitmapFactory.decodeFile(path);
                            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
                            bmp.recycle();
                            msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

                            SendMessageToWX.Req req = new SendMessageToWX.Req();
                            req.transaction = buildTransaction("img");
                            req.message = msg;
                            req.scene = mTargetScene;
                            BaseApplication.api.sendReq(req);
                        } else {
                            ivCodeSuccess.setVisibility(View.VISIBLE);
                            handler.sendEmptyMessageDelayed(CHNEGGONG, 1500);
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.runOnUiThread(new Runnable() {//失败
                @Override
                public void run() {
                    if (types == 1) {
                        ToastUtil.showToast(ShareCodeActivity.this, "请重新分享");
                    } else {
                        tvCodeFail.setVisibility(View.VISIBLE);
                        handler.sendEmptyMessageDelayed(SHIBAI, 1500);
                    }
                }
            });
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
