package com.mfzn.deepuses.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.UploadPhotoDialog;

import java.io.File;
import java.util.Date;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;


/**
 * Created by sun on 2018/5/29.
 */

public class PhotographDialog {

    public static Uri pictureUri;
    public static SharedPreferences mSp;
    public static String Image_SAVEDIR = Environment
            .getExternalStorageDirectory().getAbsolutePath();



    public static void photographDialog(final Activity activity, List<Bitmap> bmp) {
        photographDialog(activity,bmp,9);
    }

    /**
     * 选择相机或相册上传照片
     *
     * 选择多张上传
     * @param activity
     * @param bmp
     */
    public static void photographDialog(final Activity activity, List<Bitmap> bmp,int size) {

        mSp = activity.getSharedPreferences("cam", activity.MODE_PRIVATE);//初始化相机拍照

        UploadPhotoDialog dialog1 = new UploadPhotoDialog.Builder(activity)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setCanceledOnTouchOutside(true)//设置是否可点击其他地方取消dialog
                .setOnclickListener(new DialogInterface.OnShangchuanZhaopianClickListener<UploadPhotoDialog>() {
                    @Override
                    public void clickPaizhaoButton(UploadPhotoDialog dialog, View view) {
                        String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                        mSp.edit().putString("img", cameraFile).commit();
                        File file = new File(Image_SAVEDIR);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File pictureFile = new File(Image_SAVEDIR, cameraFile);
                        Intent intent;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put(MediaStore.Images.Media.DATA, pictureFile.getAbsolutePath());
                            pictureUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        } else {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            pictureUri = Uri.fromFile(pictureFile);
                        }
                        if (intent != null) {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    pictureUri);
                            activity.startActivityForResult(intent, Constants.REAL_NAME_PAIZHAO);
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void clickXiangceButton(UploadPhotoDialog dialog, View view) {
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        activity.startActivityForResult(intent, Constants.REAL_NAME_XIANGCE);

                        String sdcardState = Environment.getExternalStorageState();
                        if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                            int selectedMode;
                            selectedMode = MultiImageSelectorActivity.MODE_MULTI;
                            int maxNum = size - bmp.size();
                            Intent intent2 = new Intent(activity, MultiImageSelectorActivity.class);
                            // 是否显示拍摄图片
                            intent2.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                            // 最大可选择图片数量
                            intent2.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
                            // 选择模式
                            intent2.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);

                            activity.startActivityForResult(intent2, Constants.RESULT_LOAD_IMAGE);
                        } else {
                            Toast.makeText(activity, "sdcard已拔出，不能选择照片",
                                    Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }

                    @Override
                    public void clickQuxiaoButton(UploadPhotoDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build();
        dialog1.show();
    }
    /**
     * 选择相机或相册上传照片
     * @param activity
     * 单张上传
     */
    public static void photographDialog2(final Fragment activity) {

        mSp = activity.getActivity().getSharedPreferences("cam", activity.getActivity().MODE_PRIVATE);//初始化相机拍照

        UploadPhotoDialog dialog1 = new UploadPhotoDialog.Builder(activity.getActivity())
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setCanceledOnTouchOutside(false)//设置是否可点击其他地方取消dialog
                .setOnclickListener(new DialogInterface.OnShangchuanZhaopianClickListener<UploadPhotoDialog>() {
                    @Override
                    public void clickPaizhaoButton(UploadPhotoDialog dialog, View view) {
                        String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                        mSp.edit().putString("img", cameraFile).commit();
                        File file = new File(Image_SAVEDIR);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File pictureFile = new File(Image_SAVEDIR, cameraFile);
                        Intent intent;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put(MediaStore.Images.Media.DATA, pictureFile.getAbsolutePath());
                            pictureUri = activity.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        } else {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            pictureUri = Uri.fromFile(pictureFile);
                        }
                        if (intent != null) {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    pictureUri);
                            activity.startActivityForResult(intent, Constants.REAL_NAME_PAIZHAO);
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void clickXiangceButton(UploadPhotoDialog dialog, View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        activity.startActivityForResult(intent, Constants.REAL_NAME_XIANGCE);

                        dialog.dismiss();
                    }

                    @Override
                    public void clickQuxiaoButton(UploadPhotoDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build();
        dialog1.show();
    }

    /**
     * 选择相机或相册上传照片
     * @param activity
     * 单张上传
     */
    public static void photographDialog2(final Activity activity) {

        mSp = activity.getSharedPreferences("cam", activity.MODE_PRIVATE);//初始化相机拍照

        UploadPhotoDialog dialog1 = new UploadPhotoDialog.Builder(activity)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setCanceledOnTouchOutside(false)//设置是否可点击其他地方取消dialog
                .setOnclickListener(new DialogInterface.OnShangchuanZhaopianClickListener<UploadPhotoDialog>() {
                    @Override
                    public void clickPaizhaoButton(UploadPhotoDialog dialog, View view) {
                        String cameraFile = DateFormat.format("yy-MM-dd-hh-mm-ss", new Date()) + ".jpg";
                        mSp.edit().putString("img", cameraFile).commit();
                        File file = new File(Image_SAVEDIR);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File pictureFile = new File(Image_SAVEDIR, cameraFile);
                        Intent intent;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put(MediaStore.Images.Media.DATA, pictureFile.getAbsolutePath());
                            pictureUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        } else {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            pictureUri = Uri.fromFile(pictureFile);
                        }
                        if (intent != null) {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    pictureUri);
                            activity.startActivityForResult(intent, Constants.REAL_NAME_PAIZHAO);
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void clickXiangceButton(UploadPhotoDialog dialog, View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        activity.startActivityForResult(intent, Constants.REAL_NAME_XIANGCE);

                        dialog.dismiss();
                    }

                    @Override
                    public void clickQuxiaoButton(UploadPhotoDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build();
        dialog1.show();
    }
}
