package com.mfzn.deepusesSer.present.myteam;

import com.mfzn.deepusesSer.activity.myteam.CompanyLogoActivity;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;

import cn.droidlover.xdroidmvp.mvp.XPresent;

public class CompanyLogoPresent extends XPresent<CompanyLogoActivity> {

    /**
     * 上传图片
     */
    public void upLoadFile(final File file) {

//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM);//表单类型
////                .addFormDataPart("token", token);//ParamKey.TOKEN 自定义参数key常量类，即参数名
//        RequestBody imageBody = RequestBody.create(MediaType.parse(getMediaType(file.getName())), file);
//        builder.addFormDataPart("u_head", file.getName(), imageBody);//imgfile 后台接收图片流的参数名
//
//        List<MultipartBody.Part> parts = builder.build().parts();
//        UploadApi.uploadMemberIcon(parts).enqueue(new retrofit2.Callback<UserUploadModel>() {
//            @Override
//            public void onResponse(Call<UserUploadModel> call, Response<UserUploadModel> response) {
//                getV().uploadIconSuccess(response.body().status,response.body().res);
//            }
//            @Override
//            public void onFailure(Call<UserUploadModel> call, Throwable t) {
//                getV().uploadIconSuccess(0,"1");
//            }
//        });
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
