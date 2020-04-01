package com.mfzn.deepusesSer.net;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * API接口辅助类
 *
 * @author zhangbostay
 * @date 2019/4/10
 */
public class ApiHelper {

    //    public static final String BASE_URL = "https://du.itsurfing.cn";
    public static final String BASE_URL = "https://cdn.useyzs.com/";
    public static final String BASE_API_QR = "https://api.useyzs.com/";
    public static final String BASE_API_URL = "https://api.useyzs.com/api/";

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (ApiHelper.class) {
                if (apiService == null) {
                    apiService = XApi.getInstance().getRetrofit(BASE_API_URL, true).create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
