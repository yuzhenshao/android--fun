package com.mfzn.deepuses.present.myteam;

import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.activity.myteam.CompanyInfoActivity;
import com.mfzn.deepuses.activity.myteam.CompanyLogoActivity;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.net.ImageUploadManager;
import com.mfzn.deepuses.net.UploadApi;
import com.mfzn.deepuses.utils.ToastUtil;
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

        ImageUploadManager.uploadImage(file, new ImageUploadManager.ImageUploadCallback() {

            @Override
            public void uploadSuccess(String url) {
                uploadLogo(url);
            }

            @Override
            public void uoloadFailed(String error) {
                getV().uploadIconSuccess(null);
            }
        });
    }

    private void uploadLogo(String logoUrl) {
        ApiServiceManager.uploadLogo(logoUrl)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().uploadIconSuccess(null);
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        getV().uploadIconSuccess(logoUrl);
                    }
                });
    }
}
