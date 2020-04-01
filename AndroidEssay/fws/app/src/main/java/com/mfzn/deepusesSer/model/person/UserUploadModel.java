package com.mfzn.deepusesSer.model.person;

import cn.droidlover.xdroidmvp.net.IModel;

public class UserUploadModel implements IModel {


    /**
     * status : 1
     * msg : 上传成功
     * res : /public/images/u_head/1/20190226/53690be8675d9331410750a9506f48e3.png
     */

    public int status;
    public String msg;
    public String res;

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return status == -1;
    }

    @Override
    public boolean isBizError() {
        return status == 0;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
