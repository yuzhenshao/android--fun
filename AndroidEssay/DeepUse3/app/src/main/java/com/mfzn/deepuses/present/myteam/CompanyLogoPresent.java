package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.activity.myteam.CompanyInfoActivity;
import com.mfzn.deepuses.activity.myteam.CompanyLogoActivity;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.net.UploadApi;
import com.mfzn.deepuses.utils.UserHelper;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class CompanyLogoPresent extends XPresent<CompanyLogoActivity> {

    /**
     * 上传图片
     */
    public void upLoadFile(final File file) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("companyID", UserHelper.getCompanyId());

//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM);//表单类型
//                .addFormDataPart("token", token);//ParamKey.TOKEN 自定义参数key常量类，即参数名
        RequestBody imageBody = RequestBody.create(MediaType.parse(getMediaType(file.getName())), file);
        builder.addFormDataPart("logo", file.getName(), imageBody);//imgfile 后台接收图片流的参数名

        List<MultipartBody.Part> parts = builder.build().parts();

        UploadApi.uploadLogoIcon(parts).enqueue(new retrofit2.Callback<UserUploadModel>() {
            @Override
            public void onResponse(Call<UserUploadModel> call, Response<UserUploadModel> response) {
                getV().uploadIconSuccess(response.body().status,response.body().res);
            }
            @Override
            public void onFailure(Call<UserUploadModel> call, Throwable t) {
                getV().uploadIconSuccess(0,"1");
            }
        });
    }

    /**
     * 根据文件的名称判断文件的Mine值
     */
    private String getMediaType(String fileName) {
        FileNameMap map = URLConnection.getFileNameMap();
        String contentTypeFor = map.getContentTypeFor(fileName);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
