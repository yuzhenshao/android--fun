package com.mfzn.deepusesSer.net;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public class HttpResult<T> implements IModel {
    private Integer status;
    private String msg;
    private T res;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

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
