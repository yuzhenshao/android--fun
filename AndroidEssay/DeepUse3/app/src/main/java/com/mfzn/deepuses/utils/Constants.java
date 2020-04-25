package com.mfzn.deepuses.utils;

import android.os.Environment;

import java.io.File;

public class Constants {

    //拍照相机
    public static final int REAL_NAME_PAIZHAO = 1901;
    public static final int REAL_NAME_XIANGCE = 1902;
    public static final int RESULT_LOAD_IMAGE = 1;

    public static final String GD_DETAILS = "gd_details";
    public static final String GD_DETAILS_ID = "gd_details_id";

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
    public static final String COMPANY_LOGO = "company_logo";
    public static final String COMPANY_INFO = "company_info";
    public static final String COMLOGO = "comlogo";

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
    public static final String FOUND_PROJECT_NAME = "found_project_name";
    public static final String FOUNDPROJECT = "foundproject";
    public static final String EDITPROJECT = "editproject";

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
    public static final String SHOUHOU_JOBID = "shouhou_jobid";
    public static final String SHOUHOU_PROIDS = "shouhou_proids";
    public static final String GONGDAN = "gongdan";
    public static final String SHOUHOU_NAME = "shouhou_name";
    public static final String SHOUHOU_PHONE = "shouhou_phone";
    public static final String SHOUHOU_ADDRESS = "shouhou_address";
    public static final String SHOUHOU_ZHIB = "shouhou_zhib";
    public static final String SHOUHOU_YANB = "shouhou_yanb";

    //是否受理
    public static final int IF_ACCEPT = 1010;
    public static final int ACCEPT_GONGDAN = 1011;
    public static final int SELECT_CUSTOM = 1022;
    public static final String IF_ACCEPT_TYPE = "if_accept_type";
    public static final String IF_ACCEPT_CONTENT = "if_accept_content";
    public static final String SELECT_CUSTOMID = "select_customid";

    //工程师
    public static final int ENGINER_LIST = 1012;
    public static final int SELECT_ENGINER = 1013;
    public static final String ENGINER_ID = "enginer_id";
    public static final String ENGINER_NAME = "enginer_name";
    public static final String ENGINER_PHONE = "enginer_phone";

    //客服
    public static final int KEFU_TYPE = 1014;
    public static final int ADD_KEFU = 1015;
    public static final String EDIT_KEFU_MODEL = "edie_kefu_model";
    public static final String KEFU_TYPE_ID = "kefu_type_id";
    public static final String KEFU_TYPE_NAME = "kefu_type_name";
    public static final String KEFU_TYPE_POSI = "kefu_type_posi";
    public static final String ADD_KEFU_SUCC = "add_kefu_succ";

    //维保设置
    public static final int WB_SETTING = 1016;

    //权限设置
    public static final int QUANXIAN_SET = 1017;
    public static final int SELECT_BU = 1018;
    public static final int ADD_MANAGE = 1019;
    public static final int ADD_MANAGE2 = 1020;
    public static final int ADD_MANAGE3 = 1021;
    public static final String QX_SET_MODEL = "qx_set_model";
    public static final String QX_SET_TEXT = "qx_set_text";
    public static final String ADD_MANAGE_TEXT = "add_manage_text";
    public static final String ADD_MANAGE_ID = "add_manage_id";
    public static final String SELECT_BU_TEXT = "select_bu_text";
    public static final String SELECT_BU_NAME = "select_bu_name";

    // webview
    public static final String WEBVIEW_URL = "webview_url";
    public static final String WEBVIEW_NAME = "webview_name";

    // 搜索
    public static final String XM_SEARCH = "xm_search";

    // 支付
    public static final String PAY_MONEY = "pay_money";
    public static final String PAY_TYPE = "pay_type";



    public static final int SELECT_BC = 1023;
    public static final String SELECT_DJ = "select_dj";
    public static final String SELECT_ZT = "select_zt";
    public static final String SELECT_DJID = "select_djid";
    public static final String SELECT_ZTID = "select_ztid";
    public static final String SELECT_JV = "select_jv";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String USER_ID = "user_id";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUS_DETA_ID = "cus_deta_id";
    public static final String CUS_DETA_NAME = "cus_deta_name";
    public static final String EDIT_SUCC = "edit_succ";
    public static final String PRO_DETA = "pro_deta";
    public static final String CREAT_SUCC = "creat_succ";
    public static final int SELECT_KH = 1024;
    public static final String CREAT_ID = "creat_id";
    public static final String CREAT_NAME = "creat_name";
    public static final String CREAT_PHONE = "creat_phone";
    public static final int SELECT_MUL = 1025;
    public static final String MULTI_NAME = "nulti_name";
    public static final String MULTI_PHONE = "nulti_phone";
    public static final String MULTI_ID = "nulti_id";
    public static final String ADD_FOLL = "add_foll";
    public static final String ADD_FOLL_NAME = "add_foll_name";
    public static final String ADD_FOLL_STATUS = "add_foll_status";
    public static final int SELECT_PRO = 1026;
    public static final int ADD_FOLLOW = 1027;
    public static final int ADD_TASK = 1028;
    public static final int EDIT_TASKS = 1029;
    public static final String EDIT_TASK = "edit_task";
    public static final int SELECT_COM = 1030;
    public static final String COM_ID = "com_id";
    public static final String COM_LEVEL = "com_level";
    public static final String COM_NAME = "com_name";
    public static final String CUS_ID = "cus_id";
    public static final String CUS_NAME = "cus_name";
    public static final String CUS_LEVEL = "cus_level";
    public static final String CUS_PRO = "cus_pro";
    public static final int SELECT_CUS = 1031;
    public static final int ADD_SHARE = 1032;
    public static final String ADD_NAME = "add_name";
    public static final String ADD_PHONE = "add_phone";
    public static final String ADD_ID = "add_id";
    public static final int LOOK_NEWS = 1033;


    public static final String COMPANY_CODE = "company_code";
    public static final String COMPANY_CODE_TEXT = "company_code_text";
    public static final String XIAOXI = "xiaoxi";
    public static final String COMPANY_NAME = "company_name";
    public static final String COMPANYJC = "companyjc";


    public static final String MODIFY_ICON = "modify_icon";
    public static final String MODIFY_NAME = "modify_name";
    public static final String SELECT_PROJECT = "select_project";
    public static final String PAYMENT = "paymnet";


    public static final String IMAGE_DIR = Environment.getExternalStorageDirectory() + File.separator + "Android截屏";
    public static final String SCREEN_SHOT ="code_me_s.png";
}
