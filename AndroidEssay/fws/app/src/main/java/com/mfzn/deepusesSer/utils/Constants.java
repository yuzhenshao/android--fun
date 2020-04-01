package com.mfzn.deepusesSer.utils;

import android.os.Environment;

import java.io.File;

public class Constants {

    //拍照相机
    public static final int REAL_NAME_PAIZHAO = 1901;
    public static final int REAL_NAME_XIANGCE = 1902;
    public static final int RESULT_LOAD_IMAGE = 1;

    //项目等级
    public static final String CUSTOMER_LEVEL = "customer_level";
    public static final String CUSTOMER_LEVEL_RETURN = "customer_level_return";
    public static final int PROJECT_LEVEL = 1001;

    //修改昵称
    public static final String MODIFU_NICK = "modify_nick";
    public static final String MODIFU_NICK_RETURN = "modify_nick_return";
    public static final int MODIFU_NICHENG = 1002;

    //注册
    public static final String REGISTER = "register";
    public static final String REGISTER_PHONE = "register_phone";
    public static final String REGISTER_CODE = "register_code";
    public static final String REGISTER_NICK = "register_nick";
    public static final String REGISTER_PWD = "register_pwd";

    //注册
    public static final String FORGOT = "forgot";
    public static final String FORGOT_PHONE = "forgot_phone";
    public static final String FORGOT_CODE = "forgot_code";

    //选择标签
    public static final int SELECT_LAVLE = 1003;
    public static final String ESTABLSISH = "establish";
    public static final String SELECT_LAVLE_NAME = "select_lable_name";
    public static final String SELECT_LAVLE_ID = "select_lable_id";

    //扫码
    public static final String SCAN_CODE = "scan_code";

    //公司规模
    public static final int COMPANY_SCALE = 1004;
    public static final String COMPANY_SCALE_NAME = "company_scale_name";
    public static final String COMPANY_SCALE_ID = "company_scale_id";

    //企业信息设置
    public static final int COMPANY_INFO_SET = 1005;

    //组织架构
    public static final String ZUZHI_JIAGOU = "zuzhi_jiagou";
    public static final String PERSONAL_INFO = "personal_info";
    public static final String PERSONAL_INFO_TYPE = "personal_info_type";
    public static final String REMARKS_UID = "remarks_uid";
    public static final String EDIT_STAFF = "edit_staff";
    public static final String EDIT_STAFF_TYPE = "edit_staff_type";
    public static final String EDIT_STAFF_POSITION = "edit_staff_position";
    public static final String EDIT_STAFF_POSITION2 = "edit_staff_position2";
    public static final String EDIT_STAFF_POSITION3 = "edit_staff_position3";
    public static final String MOVE_STAFF_TYPE1 = "move_staff_type1";
    public static final String MOVE_STAFF_POSITION2 = "move_staff_position2";
    public static final String MOVE_STAFF_POSITION3 = "move_staff_position3";

    //企业信息设置
    public static final int EDIT_STAFF_BM = 1006;
    public static final String EDIT_STAFF_ID = "edit_staff_id";
    public static final String EDIT_STAFF_NAME = "edit_staff_name";
    public static final String EDIT_STAFF_BM_POSI = "edit_staff_bm_posi";
    public static final String EDIT_STAFF_BM_POSI2 = "edit_staff_bm_posi2";
    public static final String EDITSTAFF = "editstaff";

    //创建项目
    public static final int FOUND_PROJECT_LEVEL = 1007;
    public static final String PROJECT_LEVEL_POSITION = "project_level_position";
    public static final String PROJECT_LEVEL_ID = "project_level_id";
    public static final String PROJECT_LEVEL_NAME = "project_level_name";
    public static final int SELECT_PERSON = 1008;
    public static final String SELECT_PERSON_ID = "select_person_id";
    public static final String SELECT_PERSON_NAME = "select_person_name";
    public static final String FOUND_PROJECT_PROID = "found_project_proid";
    public static final String FOUNDPROJECT = "foundproject";

    //定图定位
    public static final int MAP_LOCATION = 1009;
    public static final String MAP_LOCATION_WEIZHI = "map_location_weizhi";
    public static final String MAP_LOCATION_CODE = "map_location_code";
    public static final String MAP_LOCATION_ADDRESS = "map_location_address";
    public static final String MAP_LOCATION_JINGDU = "map_location_jingdu";
    public static final String MAP_LOCATION_WEIDU = "map_location_weidu";

    //售后工单
    public static final String WORK_ORDER = "work_order";
    public static final String SHOUHOU_PROID = "shouhou_proid";
    public static final String SHOUHOU_DETAILS = "shouhou_details";
    public static final String SHOUHOU_ORDERNO = "shouhou_orderno";
    public static final String GONGDAN = "gongdan";
    public static final String SHOUHOU_NAME = "shouhou_name";
    public static final String SHOUHOU_PHONE = "shouhou_phone";
    public static final String SHOUHOU_ADDRESS = "shouhou_address";

    public static final int XGTP_GZFX_CAMERA = 1955;
    public static final int XGTP_GZFX_ALBUM = 1958;
    public static final int XGTP_JJFA_CAMERA = 1956;
    public static final int XGTP_JJFA_ALBUM = 1959;
    public static final int XGTP_CLNR_CAMERA = 1957;
    public static final int XGTP_CLNR_ALBUM = 1960;

    //是否受理
    public static final int IF_ACCEPT = 1010;
    public static final String IF_ACCEPT_TYPE = "if_accept_type";
    public static final String IF_ACCEPT_CONTENT = "if_accept_content";

    // webview
    public static final String WEBVIEW_URL = "webview_url";
    public static final String WEBVIEW_NAME = "webview_name";

    public static final String IMAGE_DIR = Environment.getExternalStorageDirectory() + File.separator + "Android截屏";
    public static final String SCREEN_SHOT ="code_me_s.png";

    public static final String MODIFY_ICON = "modify_icon";
}
