package com.mfzn.deepusesSer.activityxm.shgd;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.f1reking.signatureview.SignatureView;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import java.io.File;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DzqmActivity extends BaseActivity {

    private SignatureView mSignatureView;
    private Button btnClear;
    private Button btnSave;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dzqm;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
        mSignatureView = findViewById(R.id.view_signature);
        btnClear = findViewById(R.id.btn_clear);
        btnSave = findViewById(R.id.btn_save);
        myRequetPermission();
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignatureView.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSignatureView.getTouched()) {
                    try {
                        String galleryPath = Environment.getExternalStorageDirectory()
                                + File.separator + Environment.DIRECTORY_DCIM
                                + File.separator + "Camera" + File.separator;
                        mSignatureView.save(galleryPath+"sign.png", true, 10);
                        Intent intent = new Intent();
                        intent.putExtra("path", mSignatureView.getSavePath());
                        setResult(0, intent);
                        finish();
//                        Toast.makeText(DzqmActivity.this, "图片保存在："+mSignatureView.getSavePath(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(DzqmActivity.this, "请先签名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void myRequetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
//            Toast.makeText(this,"您已经申请了权限!",Toast.LENGTH_SHORT).show();
        }
    }
}
