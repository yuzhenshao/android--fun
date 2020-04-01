package com.mfzn.deepusesSer.utils;


import com.mfzn.deepusesSer.BaseApplication;
import com.mfzn.deepusesSer.model.login.UserModel;

import cn.droidlover.xdroidmvp.cache.SharedPref;

/**
 * 用户信息辅助类
 *
 * @author zhangbostay
 * @date 2019/4/10
 */
public class UserHelper {

//    public static void regist() {
//        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("regist", true);
//    }
//
//    public static boolean isRegist() {
//        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("regist", false);
//    }

    public static void login(UserModel userModel,String pwd) {
//        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("news_kaiguan", false);
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isLogin", true);
        SharedPref.getInstance(BaseApplication.getContext()).putString("token", userModel.getUserToken());
        SharedPref.getInstance(BaseApplication.getContext()).putString("userid", userModel.getUserNo());
        SharedPref.getInstance(BaseApplication.getContext()).putString("uid", userModel.getData_en_id());
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_phone", userModel.getUserPhone());
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_pwd", pwd);
        SharedPref.getInstance(BaseApplication.getContext()).putInt("userId", userModel.getData_id());
    }

    public static void logout() {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isLogin", false);
        SharedPref.getInstance(BaseApplication.getContext()).putString("token", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("uid", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("userid", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_phone", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_pwd", "");

        SharedPref.getInstance(BaseApplication.getContext()).putString("companyId", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("companyName", "");
        SharedPref.getInstance(BaseApplication.getContext()).remove("userId");
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("regist", false);
    }

    //-------------------------------------------保存登录账号----------------------------------------------------
    public static void setLoginAccount(String account) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("login_account", account);
    }

    public static String getLoginAccount() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("login_account", "");
    }

    //-------------------------------------------设置指纹登录----------------------------------------------------
    public static void setZhiwen(boolean account) {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("zhiwen_login", account);
    }

    public static boolean getZhiwen() {
        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("zhiwen_login", false);
    }

    //-------------------------------------------设置消息推送----------------------------------------------------
    public static void setTuisong(boolean account) {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("news_tuisong", account);
    }

    public static boolean getTuisong() {
        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("news_tuisong", true);
    }

    //-------------------------------------------设置消息开关----------------------------------------------------
    public static void setNewsKaiguan(boolean account) {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("news_kaiguan", account);
    }

    public static boolean getNewsKaiguan() {
        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("news_kaiguan", true);
    }

    public static boolean isLogin() {
        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("isLogin", false);
//        return false;
    }

    public static String getToken() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("token", "");
    }

    public static String getUid() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("uid", "");
    }

    public static int getUserId() {
        return SharedPref.getInstance(BaseApplication.getContext()).getInt("userId", 0);
    }

    public static String getU_phone() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("u_phone", "");
    }

    public static String getUserid() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("userid", "");
    }

    public static String getUpwd() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("u_pwd", "");
    }

    public static void setCompany(String companyId, String companyName) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("companyId", companyId);
        SharedPref.getInstance(BaseApplication.getContext()).putString("companyName", companyName);
    }

    /**
     * 获取用户所在公司id
     *
     * @return 公司id
     */
    public static String getCompanyId() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("companyId", "");
    }

    public static String getCompanyName() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("companyName", "");
    }

    //-------------------------------------------是否手动退出----------------------------------------------------
    public static void setOut(boolean account) {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("news_out", account);
    }

    public static boolean getOut() {
        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("news_out", false);
    }

    //-------------------------------------------保存历史搜索----------------------------------------------------
    public static void setHistorySearch(String text) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("history_search", text);
    }

    public static String getHistorySearch() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("history_search", "");
    }

    public static void setHistorySearchCj(String text) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("history_search_cj", text);
    }

    public static String getHistorySearchCj() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("history_search_cj", "");
    }
}
