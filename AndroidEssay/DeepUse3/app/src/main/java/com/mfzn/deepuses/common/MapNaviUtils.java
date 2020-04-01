package com.mfzn.deepuses.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.amap.api.maps2d.model.LatLng;
import com.mfzn.deepuses.utils.ToastUtil;

import java.io.File;

/**
 * @author syz @date 2020-03-19
 */
public class MapNaviUtils {

    public static final String PN_GAODE_MAP = "com.autonavi.minimap"; // 高德地图包名
    public static final String PN_BAIDU_MAP = "com.baidu.BaiduMap"; // 百度地图包名
    public static final String DOWNLOAD_GAODE_MAP = "http://www.autonavi.com/"; // 高德地图下载地址

    public static boolean isGdMapInstalled() {
        return isInstallPackage(PN_GAODE_MAP);
    }

    public static boolean isBaiduMapInstalled() {
        return isInstallPackage(PN_BAIDU_MAP);
    }

    private static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    public static LatLng GCJ02ToBD09(LatLng latLng) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double z = Math.sqrt(latLng.longitude * latLng.longitude + latLng.latitude * latLng.latitude) + 0.00002 * Math.sin(latLng.latitude * x_pi);
        double theta = Math.atan2(latLng.latitude, latLng.longitude) + 0.000003 * Math.cos(latLng.longitude * x_pi);
        double bd_lat = z * Math.sin(theta) + 0.006;
        double bd_lng = z * Math.cos(theta) + 0.0065;
        return new LatLng(bd_lat, bd_lng);
    }

    public static void openGaoDeNavi(Context context, double dlat, double dlon, String dname) {
        StringBuilder builder = new StringBuilder("amapuri://route/plan?sourceApplication=maxuslife");
        builder.append("&dlat=").append(dlat)
                .append("&dlon=").append(dlon)
                .append("&dname=").append(dname)
                .append("&dev=0")
                .append("&t=0");
        String uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_GAODE_MAP);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }

    public static void openBaiDuNavi(Context context, double dlat, double dlon, String dname) {
        //终点坐标转换
        LatLng destination = new LatLng(dlat, dlon);
        LatLng destinationLatLng = GCJ02ToBD09(destination);
        dlat = destinationLatLng.latitude;
        dlon = destinationLatLng.longitude;
        StringBuilder builder = new StringBuilder("baidumap://map/direction?mode=driving&");
        builder.append("&destination=latlng:")
                .append(dlat)
                .append(",")
                .append(dlon)
                .append("|name:")
                .append(dname);
        String uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_BAIDU_MAP);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }


    public static void goToMapNavi(Context mContext, double dlat, double dlon, String dname) {
        if (MapNaviUtils.isGdMapInstalled()) {
            MapNaviUtils.openGaoDeNavi(mContext, dlat, dlon, dname);
            return;
        }

        if (MapNaviUtils.isBaiduMapInstalled()) {
            MapNaviUtils.openBaiDuNavi(mContext, dlat, dlon, dname);
            return;
        }

        ToastUtil.showToast(mContext, "您还未安装高德地图,请去下载");
        new AlertDialog.Builder(mContext)
                .setMessage("您还未安装高德地图,是否下载？")
                .setPositiveButton("下载", (dialog1, which1) -> mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MapNaviUtils.DOWNLOAD_GAODE_MAP))))
                .setNegativeButton("取消", null)
                .show();
    }
}
