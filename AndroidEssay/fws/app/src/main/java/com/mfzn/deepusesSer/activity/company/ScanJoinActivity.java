package com.mfzn.deepusesSer.activity.company;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.BitmapUtil;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.wevey.selector.dialog.TishiDialog;

import java.util.Hashtable;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import io.reactivex.functions.Consumer;

public class ScanJoinActivity extends BaseActivity implements QRCodeView.Delegate {

    @BindView(R.id.tv_black_title)
    TextView tvBlackTitle;
    @BindView(R.id.tv_black_photo)
    TextView tvBlackPhoto;
    @BindView(R.id.zxingview)
    ZXingView mQRCodeView;

    private String str2;
    private String companyName;
    private static final int REQUEST_CODE_SCAN_GALLERY = 0x008;

    private boolean flag = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_join;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBlackTitle.setText(getString(R.string.app_scan_join));
        tvBlackPhoto.setVisibility(View.VISIBLE);

        getPerssion();
    }

    @OnClick({R.id.iv_black_back, R.id.tv_black_photo, R.id.ll_scan_deng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_black_back:
                finish();
                break;
            case R.id.tv_black_photo:
                //打开手机中的相册
                Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                innerIntent.setType("image/*");
                startActivityForResult(innerIntent, REQUEST_CODE_SCAN_GALLERY);
                break;
            case R.id.ll_scan_deng:
                Camera m_Camera = null;
                if (flag) {
                    flag = false;
                    // 开闪光灯
                    try{
                        m_Camera = Camera.open();
                        Camera.Parameters mParameters;
                        mParameters = m_Camera.getParameters();
                        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        m_Camera.setParameters(mParameters);
                    } catch(Exception ex){}
                } else {
                    flag = true;
                    // 关闪光灯
                    try{
                        m_Camera = Camera.open();
                        Camera.Parameters mParameters;
                        mParameters = m_Camera.getParameters();
                        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        m_Camera.setParameters(mParameters);
                        m_Camera.release();
                    } catch(Exception ex){}
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mQRCodeView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQRCodeView.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Activity.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();

        showResult(result);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    private void showResult(String code) {
        mQRCodeView.startSpot();

        Intent intent = new Intent(this, ApplyJoinActivity.class);
        intent.putExtra(Constants.SCAN_CODE,code);
        startActivity(intent);
        finish();

//        if(code.contains("c=")) {
//            String[]  strs = code.split("c=");
//            String str = strs[1];
//            String[]  strs2 = str.split("&");
//            str2 = strs2[0];
//            if(TextUtils.isEmpty(str2)){
//                tishiDialog();
//            }else {
//                getP().companyInfo(str2);
//            }
//        }else {
//            tishiDialog();
//        }
    }

    private void getPerssion() {
        getRxPermissions()
                .request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
//
                                }
                            }, 2000);
                        } else {
                            getPerssion();
                            getvDelegate().toastShort("亲，同意了权限才能更好的使用软件哦");
                        }
                    }
                });
    }

    public void applyError(String msg) {
        new TishiDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setContentText(msg)
                .setTitle(companyName)
                .setbuttext("关闭")
                .setCanceledOnTouchOutside(false)
                .setSingleListener(new com.wevey.selector.dialog.DialogInterface.OnSingleClickListener<TishiDialog>() {
                    @Override
                    public void clickSingleButton(TishiDialog dialog, View view) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .build()
                .show();
    }

    private void tishiDialog(){
        new TishiDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setContentText("无效二维码")
                .setbuttext("知道了")
                .setCanceledOnTouchOutside(false)
                .setSingleListener(new com.wevey.selector.dialog.DialogInterface.OnSingleClickListener<TishiDialog>() {
                    @Override
                    public void clickSingleButton(TishiDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SCAN_GALLERY:
                    handleAlbumPic(data);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 处理选择的图片
     *
     * @param data
     */
    private void handleAlbumPic(Intent data) {
        //获取选中图片的路径
        final Uri uri = data.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Result result = scanningImage(uri);
                if (result != null) {
                    showResult(result.getText());
                } else {
                    ToastUtil.showToast(ScanJoinActivity.this,"识别失败");
                }
            }
        });
    }

    /**
     * 扫描二维码图片的方法
     *
     * @param uri
     * @return
     */
    public Result scanningImage(Uri uri) {
        if (uri == null) {
            return null;
        }
        Hashtable<DecodeHintType, String> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF8"); //设置二维码内容的编码

        Bitmap scanBitmap = BitmapUtil.decodeUri(this, uri, 500, 500);
        int[] data = new int[scanBitmap.getWidth() * scanBitmap.getHeight()];
        scanBitmap.getPixels(data, 0, scanBitmap.getWidth(), 0, 0, scanBitmap.getWidth(), scanBitmap.getHeight());
        RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap.getWidth(), scanBitmap.getHeight(), data);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        try {
            return reader.decode(bitmap1, hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
