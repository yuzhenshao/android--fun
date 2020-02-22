package com.mfzn.deepuses.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.mfzn.deepuses.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;

import io.reactivex.functions.Consumer;

/**
 * Created by sun on 2018/5/7.
 */

public class PhoneUtils {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public static void dialogPhone(final Activity activity, final String phone) {
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(activity)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.7f)  //屏幕宽度*0.65
                .setContentText(phone)
                .setContentTextColor(R.color.color_030303)
                .setContentTextSize(16)
                .setLeftButtonText("取消")
                .setLeftButtonTextColor(R.color.color_007AFF)
                .setRightButtonText("拨打")
                .setRightButtonTextColor(R.color.color_007AFF)
                .setButtonTextSize(17)
                .setTitleText("拨打")
                .setTitleTextColor(R.color.color_030303)
                .setTitleTextSize(20)
                .setCanceledOnTouchOutside(true)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlert2Dialog>() {
                    @Override
                    public void clickLeftButton(NormalAlert2Dialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(NormalAlert2Dialog dialog, View view) {
                        // 检查是否获得了权限（Android6.0运行时权限）
                        new RxPermissions(activity)
                                .request(Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (!aBoolean) {
                                            ToastUtil.showToast(activity, "请授权");
                                        } else {
                                            CallPhone(activity,phone);
                                            dialog.dismiss();
                                        }
                                    }
                                });

//                        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
//                            // 没有获得授权，申请授权
//                            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.CALL_PHONE)) {
//                                Toast.makeText(activity, "请授权！", Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
//                                intent.setData(uri);
//                                activity.startActivity(intent);
//                            }
//                            else {
//                                // 不需要解释为何需要该权限，直接请求授权
//                                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
//                            }
//                        } else {
//                            // 已经获得授权，可以打电话
//                            CallPhone(activity,phone);
//                            dialog.dismiss();
//                        }
                    }
                })
                .build();
        normalAlertDialog.show();
    }

    private static void CallPhone(Activity activity, String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + phone));
        //开启系统拨号器
        activity.startActivity(intent);
    }

    public static void dialogPhone2(final Activity activity, final String title, final String phone, final String phone2) {
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(activity)
                .setHeight(0.25f)  //屏幕高度*0.23
                .setWidth(0.8f)  //屏幕宽度*0.65
                .setContentText(phone)
                .setContentTextColor(R.color.color_030303)
                .setContentTextSize(15)
                .setLeftButtonText("取消")
                .setLeftButtonTextColor(R.color.color_007AFF)
                .setRightButtonText("拨打")
                .setRightButtonTextColor(R.color.color_007AFF)
                .setButtonTextSize(17)
                .setTitleText(title)
                .setTitleTextColor(R.color.color_030303)
                .setTitleTextSize(20)
                .setCanceledOnTouchOutside(true)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlert2Dialog>() {
                    @Override
                    public void clickLeftButton(NormalAlert2Dialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(NormalAlert2Dialog dialog, View view) {
                        // 检查是否获得了权限（Android6.0运行时权限）
                        new RxPermissions(activity)
                                .request(Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (!aBoolean) {
                                            ToastUtil.showToast(activity, "请授权");
                                        } else {
                                            CallPhone(activity,phone2);
                                            dialog.dismiss();
                                        }
                                    }
                                });
                    }
                })
                .build();
        normalAlertDialog.show();
    }
}
