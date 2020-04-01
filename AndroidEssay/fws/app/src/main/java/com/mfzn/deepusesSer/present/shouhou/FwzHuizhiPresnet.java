package com.mfzn.deepusesSer.present.shouhou;

import com.mfzn.deepusesSer.activityxm.shgd.FwzHuizhiActivity;
import com.mfzn.deepusesSer.model.UploadContractModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.net.UploadApi;
import com.mfzn.deepusesSer.utils.UserHelper;

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

public class FwzHuizhiPresnet extends XPresent<FwzHuizhiActivity> {

    public void createReceipt(String orderNo,String faultText,String faultFileId,String content,String contentFileId,String status,String sign,String money,String moneyNote) {
        ApiHelper.getApiService().createReceipt(UserHelper.getToken(), UserHelper.getUid(),orderNo,faultText,faultFileId,content,contentFileId,status,sign,money,moneyNote)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        getV().fwzHuizhiResult(result);
                    }
                });
    }
}
