package com.mfzn.deepuses.utils;


import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.login.UserModel;

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

    public static void login(UserResponse userResponse, String pwd) {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isLogin", true);
        SharedPref.getInstance(BaseApplication.getContext()).putString("token", userResponse.getUserToken());
        SharedPref.getInstance(BaseApplication.getContext()).putString("uid", userResponse.getData_en_id());
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_phone", userResponse.getUserPhone());
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_pwd", pwd);
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_name", userResponse.getUserName());
    }

    public static void login(UserModel userModel, String pwd) {
//        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("news_kaiguan", false);
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isLogin", true);
        SharedPref.getInstance(BaseApplication.getContext()).putString("token", userModel.getToken());
        SharedPref.getInstance(BaseApplication.getContext()).putString("uid", userModel.getData_en_id());
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_phone", userModel.getU_phone());
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_pwd", pwd);
        SharedPref.getInstance(BaseApplication.getContext()).putInt("is_shiming", userModel.getIs_shiming());
        SharedPref.getInstance(BaseApplication.getContext()).putInt("is_yajin", userModel.getIs_cost());
        SharedPref.getInstance(BaseApplication.getContext()).putInt("is_personal_b", userModel.getIs_personal_b());
        SharedPref.getInstance(BaseApplication.getContext()).putInt("u_type", userModel.getU_type());
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_name", userModel.getU_name());
    }

    public static void logout() {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isLogin", false);
        SharedPref.getInstance(BaseApplication.getContext()).putString("token", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("uid", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_phone", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_pwd", "");
        SharedPref.getInstance(BaseApplication.getContext()).remove("is_shiming");
        SharedPref.getInstance(BaseApplication.getContext()).remove("is_yajin");
        SharedPref.getInstance(BaseApplication.getContext()).remove("is_personal_b");
        SharedPref.getInstance(BaseApplication.getContext()).remove("u_type");
        SharedPref.getInstance(BaseApplication.getContext()).remove("u_name");

        SharedPref.getInstance(BaseApplication.getContext()).putString("companyId", "");
        SharedPref.getInstance(BaseApplication.getContext()).putString("companyName", "");

        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("regist", false);
    }

    //-------------------------------------------保存登录账号----------------------------------------------------
    public static void setLoginAccount(String account) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("login_account", account);
    }

    public static String getLoginAccount() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("login_account", "");
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

    public static String getU_phone() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("u_phone", "");
    }

    public static String getUpwd() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("u_pwd", "");
    }

    public static int getIs_shiming() {
        return SharedPref.getInstance(BaseApplication.getContext()).getInt("is_shiming", 0);
    }

    public static int getIs_yajin() {
        return SharedPref.getInstance(BaseApplication.getContext()).getInt("is_yajin", 0);
    }

    public static int getIs_personal_b() {
        return SharedPref.getInstance(BaseApplication.getContext()).getInt("is_personal_b", 0);
    }

    public static void setCompany(String companyId, String companyName) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("companyId", companyId);
        SharedPref.getInstance(BaseApplication.getContext()).putString("companyName", companyName);
    }

    public static void setCompanyNmae(String companyName) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("companyName", companyName);
    }

    public static void setSelectNmae(String name) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("selectname", name);
    }

    public static String getSelectNmae() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("selectname", "");
    }

    /**
     * 获取用户所在公司id
     *
     * @return 公司id
     */
    public static String getCompanyId() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("companyId", "");
//        return "13";
    }

    public static String getCompanyName() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("companyName", "");
//        return "1";
    }

    public static String getU_name() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("u_name", "");
    }

    public static void setU_name(String name) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("u_name", name);
    }

    //-------------------------------------------是否手动退出----------------------------------------------------
    public static void setOut(boolean account) {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("news_out", account);
    }

    public static boolean getOut() {
        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("news_out", false);
    }


    public static int getU_type() {
        return SharedPref.getInstance(BaseApplication.getContext()).getInt("u_type", 0);
    }

    //操作指南
    public static void setCzzn(boolean account) {
        SharedPref.getInstance(BaseApplication.getContext()).putBoolean("news_czzn", account);
    }

    public static boolean getCzzn() {
        return SharedPref.getInstance(BaseApplication.getContext()).getBoolean("news_czzn", false);
    }

    public static void setRoleID(String roleID) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("roleID", roleID);
    }

    public static String getRoleID() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("roleID", "");
    }

    public static void setAuthCreate(String roleID) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("authCreate", roleID);
    }

    public static String getAuthCreate() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("authCreate", "");
    }

    //-------------------------------------------判断提示权限----------------------------------------------------
    public static void setLookqx(String account) {
        SharedPref.getInstance(BaseApplication.getContext()).putString("look_qx", account);
    }

    public static String getLookqx() {
        return SharedPref.getInstance(BaseApplication.getContext()).getString("look_qx", "");
    }
}
