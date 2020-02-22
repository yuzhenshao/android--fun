package com.mfzn.deepuses.qiniu;

import com.mfzn.deepuses.net.ApiHelper;

import java.nio.charset.Charset;


public final class Config {

    public static final Charset UTF_8 = Charset.forName("UTF-8");
    /**
     * 设置好账号的ACCESS_KEY和SECRET_KEY
     */
    public static final String ACCESS_KEY = "WY80vhZ47c4q4WfEWUj4GGpkX8HM3nVkwZNVKkUP";
    public static final String SECRET_KEY = "x1PCLxa6EXbW2w8U_F1-mOhA_LFYWxKn4VrB7c9r";
    /**
     * 新建七牛空间的名字
     */
    public static final String BUCKET_NAME = "useyzs";
    /**
     * 测试域名(我没有充值,只有一个测试域名)
     */
    public static String TEST_DOMAIN = ApiHelper.BASE_URL;

}