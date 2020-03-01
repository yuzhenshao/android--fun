package com.mfzn.deepuses.present.xmgd;

import com.mfzn.deepuses.activityxm.shgd.AddWorkorderActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderListActivity;
import com.mfzn.deepuses.bean.request.CreateAfterSaleOrderRequest;
import com.mfzn.deepuses.model.UploadContractModel;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
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

public class AddWorkorderPresent extends XPresent<AddWorkorderActivity> {

    /**
     * 上传图片
     */
    public void upLoadFile(String type, String pro_id, String imageNote, List<File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("type", type)
                .addFormDataPart("pro_id", pro_id)
                .addFormDataPart("imageNote", imageNote);//ParamKey.TOKEN 自定义参数key常量类，即参数名

        for (int i = 0; i < files.size(); i++) {
            RequestBody imageBody = RequestBody.create(MediaType.parse(getMediaType(files.get(i).getName())), files.get(i));
            builder.addFormDataPart("images[]", files.get(i).getName(), imageBody);//imgfile 后台接收图片流的参数名
        }

        List<MultipartBody.Part> parts = builder.build().parts();
        UploadApi.uploadPhoto(parts).enqueue(new retrofit2.Callback<UploadContractModel>() {
            @Override
            public void onResponse(Call<UploadContractModel> call, Response<UploadContractModel> response) {
                getV().uploadIconSuccess(response.body().getStatus(), response.body().getRes());
            }

            @Override
            public void onFailure(Call<UploadContractModel> call, Throwable t) {
                getV().uploadIconSuccess(0, null);
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

    public void addWorkorder(CreateAfterSaleOrderRequest request) {
        ApiServiceManager.createAfterSaleOrder(request)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        getV().addWorkorderSuccess();
                    }
                });
    }
}
