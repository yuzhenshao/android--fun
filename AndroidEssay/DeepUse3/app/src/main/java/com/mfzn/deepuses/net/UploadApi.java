package com.mfzn.deepuses.net;



import com.mfzn.deepuses.model.UploadContractModel;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sun on 2018/5/4.
 */

public class UploadApi {

    private static final String HOST = ApiHelper.BASE_URL;//换成你上传用的服务器地址
    private static Retrofit retrofit;
    private static final int DEFAULT_TIMEOUT = 10;//超时时长，单位：秒

    /**
     * 获取根服务地址
     */
    public static String getHOST() {
        return HOST;
    }

    /**
     * 初始化 Retrofit
     */
    private static Retrofit getApiRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            retrofit = new Retrofit.Builder()
                    .client(okHttpBuilder.build())
                    .baseUrl(HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * 创建数据请求服务
     */
    private static ApiService getApiService() {
        return UploadApi.getApiRetrofit().create(ApiService.class);
    }

//    /**
//     * 上传头像
//     */
//    public static Call<UserUploadModel> uploadMemberIcon(List<MultipartBody.Part> partList) {
//        return UploadApi.getApiService().uploadMemberIcon(UserHelper.getToken(), UserHelper.getUid(),partList);
//    }

    /**
     * 上传图片
     */
    public static Call<UploadContractModel> uploadPhoto(List<MultipartBody.Part> partList) {
        return UploadApi.getApiService().uploadPhotoIcon(UserHelper.getToken(), UserHelper.getUid(),partList);
    }

//    /**
//     * 上传身份证
//     */
//    public static Call<UploadIDModel> uploadIDFile(List<MultipartBody.Part> partList) {
//        return UploadApi.getApiService().uploadIDFile(UserHelper.getToken(), UserHelper.getUid(),partList);
//    }
//
//    /**
//     * 上传身份证
//     */
//    public static Call<UploadZhizhaoModel> uploadZhizhaoFile(List<MultipartBody.Part> partList) {
//        return UploadApi.getApiService().uploadZhizhaoFile(UserHelper.getToken(), UserHelper.getUid(),partList);
//    }

    /**
     * 上传头像
     */
    public static Call<UserUploadModel> uploadMemberIcon(List<MultipartBody.Part> partList) {
        return UploadApi.getApiService().uploadMemberIcon(UserHelper.getToken(), UserHelper.getUid(),partList);
    }

    /**
     * 企业LOGO上传
     */
    public static Call<UserUploadModel> uploadLogoIcon(List<MultipartBody.Part> partList) {
        return UploadApi.getApiService().uploadLogoIcon(UserHelper.getToken(), UserHelper.getUid(),partList);
    }
}
