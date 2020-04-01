package com.mfzn.deepuses.activity.jiagou;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.MyPromotionActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ShareCodeModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.jiagou.InvitationJoinPresent;
import com.mfzn.deepuses.present.jiagou.ShareCodePresent;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.Util;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import okhttp3.Call;

public class InvitationJoinActivity extends BaseMvpActivity<InvitationJoinPresent> implements TbsReaderView.ReaderCallback{

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_team_sousuo)
    TextView tvTeamSousuo;
    @BindView(R.id.tv_team_code)
    TextView tvTeamCode;

    private String urlIcon;

    private static final int THUMB_SIZE = 150;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private String download = Environment.getExternalStorageDirectory() + "/download/test/document/";
    private TbsReaderView mTbsReaderView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invitation_join;
    }

    @Override
    public InvitationJoinPresent newP() {
        return new InvitationJoinPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("邀请加入公司/团队");

        tvTeamSousuo.getPaint().setFakeBoldText(true);
        tvTeamCode.getPaint().setFakeBoldText(true);

        mTbsReaderView = new TbsReaderView(this, this);

        getP().shareCode();
    }

    public void shareCodeSuccess(ShareCodeModel model) {
        urlIcon = ApiHelper.BASE_API_URL + model.getQrCodeUrl();
        String path = download + "text.png";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        OkGo.<File>get(urlIcon)//
                .tag(this)//
                .execute(new FileCallback(download, "text.png") {
                    @Override
                    public void onSuccess(Response<File> response) {//文件下载时，可以指定下载的文件目录和文件名
                        displayFile(download + "text.png", "text.png");
                    }
                });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_join_weixin, R.id.ll_join_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_join_weixin:
                String path = download + "text.png";
                File file = new File(path);
                if (!file.exists()) {
//                    String tip = SendToWXActivity.this.getString(R.string.send_img_file_not_exist);
//                    Toast.makeText(SendToWXActivity.this, tip + " path = " + path, Toast.LENGTH_LONG).show();
                    break;
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
                break;
            case R.id.ll_join_code:
                startActivity(new Intent(this, ShareCodeActivity.class));
                break;
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private String tbsReaderTemp = Environment.getExternalStorageDirectory() + "/TbsReaderTemp";

    private void displayFile(String filePath, String fileName) {
        try {

            //增加下面一句解决没有TbsReaderTemp文件夹存在导致加载文件失败
            String bsReaderTemp = tbsReaderTemp;
            File bsReaderTempFile = new File(tbsReaderTemp);
            if (!bsReaderTempFile.exists()) {
                boolean mkdir = bsReaderTempFile.mkdir();
                if (!mkdir) {
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString("filePath", filePath);
            bundle.putString("tempPath", tbsReaderTemp);
            boolean result = mTbsReaderView.preOpen(getFileType(fileName), false);
            if (result) {
                mTbsReaderView.openFile(bundle);
            } else {
//                this.filePath = filePath;
//                this.fileName = fileName;
//                String substring = fileName.substring(fileName.lastIndexOf('/') + 1);
//                ll_tbs_hide.setVisibility(View.VISIBLE);
//                tv_tbs_text.setText(substring);
            }
        } catch (Exception ignored) {

        }
    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";
        if (TextUtils.isEmpty(paramString)) {
            return str;
        }
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            return str;
        }
        str = paramString.substring(i + 1);
        return str;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }
}
