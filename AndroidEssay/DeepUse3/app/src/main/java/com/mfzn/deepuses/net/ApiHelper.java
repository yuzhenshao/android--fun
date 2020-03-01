package com.mfzn.deepuses.net;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * API接口辅助类
 *
 * @author zhangbostay
 * @date 2019/4/10
 */
public class ApiHelper {

    public static final String BASE_URL = "https://api.useyzs.com/";

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (ApiHelper.class) {
                if (apiService == null) {
                    apiService = XApi.getInstance().getRetrofit(BASE_URL, true).create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
