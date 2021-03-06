package com.mfzn.deepuses.net;


import com.mfzn.deepuses.bean.request.AddDepartmentRequest;
import com.mfzn.deepuses.bean.request.ChangePwdRequest;
import com.mfzn.deepuses.bean.request.ProMemberRequest;
import com.mfzn.deepuses.bean.request.EditBusinessCardRequest;
import com.mfzn.deepuses.bean.request.ForgetRequest;
import com.mfzn.deepuses.bean.request.LoginRequest;
import com.mfzn.deepuses.bean.request.RegisterRequest;
import com.mfzn.deepuses.bean.response.BusinessCardResponse;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.LookQuanxian2Model;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.UploadContractModel;
import com.mfzn.deepuses.model.brick.AppPaymentModel;
import com.mfzn.deepuses.model.brick.AppWXPaymentModel;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.brick.LevelRightsModel;
import com.mfzn.deepuses.model.brick.RechargeComboModel;
import com.mfzn.deepuses.model.brick.TransactionRecordModel;
import com.mfzn.deepuses.model.company.CityModel;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.company.SelectLableModel;
import com.mfzn.deepuses.model.faxian.CommentBean;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.model.faxian.Videos;
import com.mfzn.deepuses.model.home.JudgeLevelModel;
import com.mfzn.deepuses.model.home.KanbDataModel;
import com.mfzn.deepuses.model.jiagou.SearchKeywordModel;
import com.mfzn.deepuses.model.jiagou.ShareCodeModel;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.khgl.FollowProModel;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.MyTaskModel;
import com.mfzn.deepuses.model.khgl.SearchComModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.model.my.UserUploadModel;
import com.mfzn.deepuses.model.myTeam.CompanyScaleModel;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.model.xiangmu.CheckAppraiseModel;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.model.xiangmu.ClzGongdanDetailModel;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.CustomTypeModel;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.model.xiangmu.FoundProjectModel;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.model.xiangmu.ProjectCodeModel;
import com.mfzn.deepuses.model.xiangmu.ProjectDetailsModel;
import com.mfzn.deepuses.model.xiangmu.ProjectLevelModel;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.model.xiangmu.ProjectStagingModel;
import com.mfzn.deepuses.model.xiangmu.SelectEnginerModel;
import com.mfzn.deepuses.model.xiangmu.SettingInfoModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.model.xmhf.VisitRrcordModel;
import com.mfzn.deepuses.model.xx.MsgMainModel;
import com.mfzn.deepuses.model.xx.MsgTdxxModel;
import com.mfzn.deepuses.model.xx.TeamApplyModel;
import com.wevey.selector.dialog.bean.DetailsModel;
import com.wevey.selector.dialog.bean.SelectModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public interface ApiService {

//    @FormUrlEncoded   //登录
//    @POST("/api/user/appLogin")
//    Flowable<HttpResult<UserModel>> appLogin(@Field("userPhone") String u_phone, @Field("userPwd") String pwd);

//    @FormUrlEncoded  //注册
//    @POST("/api/users/appRegister")
//    Flowable<HttpResult> appRegister(@Field("u_phone") String u_phone, @Field("smscode") String smscode,
//                                     @Field("u_pwd") String u_pwd, @Field("u_re_pwd") String u_re_pwd, @Field("u_name") String u_name);

    @FormUrlEncoded
    @POST("/api/users/setNickname")
        //修改昵称
    Flowable<HttpResult> appModifyName(@Query("token") String token, @Query("uid") String uid,
                                       @Field("u_name") String u_name);

//    @FormUrlEncoded  //找回密码
//    @POST("/api/users/forgetPwd")
//    Flowable<HttpResult> forgetPwd(@Field("u_phone") String u_phone, @Field("smscode") String smscode,
//                                   @Field("u_pwd") String u_pwd, @Field("u_re_pwd") String u_re_pwd);

    @Multipart  //上传图片
    @POST("/api/index/upImages")
    Call<UploadContractModel> uploadPhotoIcon(@Query("token") String token, @Query("uid") String uid, @Part List<MultipartBody.Part> partList);

    @GET("api/Company/checkModulesOutOfDate")
        //查看购买模块是否过期
    Flowable<HttpResult<LookQuanxianModel>> lookQuanxian(@Query("token") String token, @Query("uid") String uid,
                                                         @Query("companyID") String companyID, @Query("proID") String proID);

    @GET("/two.php/api/index/checkModulesOutOfDate")
        //查看购买模块是否过期
    Flowable<HttpResult<LookQuanxian2Model>> lookQuanxian2(@Query("token") String token, @Query("uid") String uid,
                                                           @Query("companyID") String companyID);

    //--------------------------------------------------------首页--------------------------------------------------------
    //"roleID": 1:创建者  2：超级管理员  3：普普通通管理员 4：普通员工

    @GET("/two.php/api/Company/showMenu")
        //判断组织架构的级别
    Flowable<HttpResult<JudgeLevelModel>> judgeLevel(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID);

    @GET("/two.php/api/Company/getData")
        //获取数据看板上的数字
    Flowable<HttpResult<KanbDataModel>> kanbData(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID);

    @GET("/two.php/api/Index/marketUrl")
        //获取营销链接地址
    Flowable<HttpResult> getMarketUrl(@Query("token") String token,
                                      @Query("uid") String uid,
                                      @Query("type") String type);

    //--------------------------------------------------------公司--------------------------------------------------------
    @GET("api/Company/getCompanyList")
    Flowable<HttpResult<List<SelectCompanyModel>>> companyList(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //解散公司/团队
    @POST("/two.php/api/Company/leaveCompany")
    Flowable<HttpResult> delCompany(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID);

    @GET("/two.php/api/Company/getBusinessScope")
        //经营范围
    Flowable<HttpResult<List<SelectLableModel>>> companyLable(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //创建公司
    @POST("/two.php/api/Company/createCompany")
    Flowable<HttpResult> establishCompany(@Query("token") String token, @Query("uid") String uid,
                                          @Field("companyName") String companyName, @Field("areaID") String areaID,
                                          @Field("businessScope") String businessScope,
                                          @Query("longitude") String longitude, @Query("latitude") String latitude,
                                          @Query("companyAddress") String companyAddress);

    @FormUrlEncoded  //扫码申请加入公司
    @POST("/two.php/api/Company/joinCompanyWithCode")
    Flowable<HttpResult> applyJoin(@Query("token") String token, @Query("uid") String uid,
                                   @Field("companyID") String companyID, @Field("staffName") String staffName,
                                   @Field("reason") String reason);

    @GET("api/Project/joinPro")
        //扫码项目二维码加入项目
    Flowable<HttpResult> joinXiangmu(@Query("token") String token, @Query("uid") String uid,
                                     @Query("proID") String proID, @Query("label") String label, @Query("remark") String remark);

    @GET("/api/index/getAreaID")
    Flowable<HttpResult<CityModel>> getAreaID(@Query("province_name") String province_name,
                                              @Query("city_name") String city_name,
                                              @Query("area_name") String area_name);

    @Multipart  //企业LOGO上传
    @POST("/two.php/api/Company/uploadLogo")
    Call<UserUploadModel> uploadLogoIcon(@Query("token") String token, @Query("uid") String uid, @Part List<MultipartBody.Part> partList);

    //--------------------------------------------------------组织架构--------------------------------------------------------
//    @GET("/two.php/api/Company/getDepartments")
//    //架构列表
//    Flowable<HttpResult<ZuzhiJiagouModel>> jiagouList(@Query("token") String token, @Query("uid") String uid,
//                                                      @Query("companyID") String companyID);

    @GET("/two.php/api/Company/searchStaff")
        //搜索员工
    Flowable<HttpResult<List<SearchKeywordModel>>> jiagouList(@Query("token") String token, @Query("uid") String uid,
                                                              @Query("companyID") String companyID, @Query("keywords") String keywords);

    @FormUrlEncoded  //编辑员工信息
    @POST("/two.php/api/Company/editStaff")
    Flowable<HttpResult> editStaff(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                   @Field("staffUID") String staffUID, @Field("positionName") String positionName,
                                   @Field("oldDepartmentID") String oldDepartmentID, @Field("newDepartmentID") String newDepartmentID,
                                   @Field("joinTime") String joinTime, @Field("staffName") String staffName);

    @FormUrlEncoded  //删除员工
    @POST("/two.php/api/Company/delStaff")
    Flowable<HttpResult> deleteStaff(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("staffUID") String staffUID, @Field("departmentID") String departmentID);

    @FormUrlEncoded  //删除部门
    @POST("/two.php/api/Company/delDepartment")
    Flowable<HttpResult> deleteBm(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("departmentID") String departmentID);

    @FormUrlEncoded  //修改当前部门名称
    @POST("/two.php/api/Company/changeDepartName")
    Flowable<HttpResult> modifyBmName(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                      @Field("departmentID") String departmentID, @Field("departName") String departName);

//    @FormUrlEncoded  //添加子部门
//    @POST("/two.php/api/Company/addDepartment")
//    Flowable<HttpResult> addSonbm(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
//                                  @Field("departmentID") String departmentID, @Field("departmentName") String departmentName,
//                                  @Field("departmentManagerID") String departmentManagerID, @Field("departmentLeaderID") String departmentLeaderID);

    @FormUrlEncoded  //移动员工到对应部门
    @POST("/two.php/api/Company/moveUsers")
    Flowable<HttpResult> moveStaff(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                   @Field("userIDs") String userIDs, @Field("oldDepartmentID") String oldDepartmentID,
                                   @Field("newDepartmentID") String newDepartmentID);

    //--------------------------------------------------------企业设置--------------------------------------------------------


    @GET("/two.php/api/Company/getCompanyScale")
        //公司规模
    Flowable<HttpResult<List<CompanyScaleModel>>> companyScale(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //企业信息设置
    @POST("/two.php/api/Company/setCompanyInfo")
    Flowable<HttpResult> companyInfo(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("companyName") String companyName, @Field("shortName") String shortName,
                                     @Field("companyAddress") String companyAddress,
                                     @Query("latitude") String latitude, @Query("longitude") String longitude,
                                     @Field("companyWebsite") String companyWebsite,
                                     @Field("businessScope") String businessScope, @Field("scaleID") String scaleID);

    @FormUrlEncoded  //备注
    @POST("/two.php/api/Company/setRemarkName")
    Flowable<HttpResult> remarks(@Query("token") String token, @Query("uid") String uid,
                                 @Field("remarkName") String remarkName, @Field("companyID") String companyID,
                                 @Field("staffUID") String staffUID);


    @FormUrlEncoded  //显示管理员列表
    @POST("/two.php/api/Company/managerList")
    Flowable<HttpResult<List<ManageSettingModel>>> manageSetting(@Query("token") String token, @Query("uid") String uid,
                                                                 @Field("companyID") String companyID);

    @FormUrlEncoded  //删除管理员
    @POST("/two.php/api/Company/delManager")
    Flowable<HttpResult> deleteManage(@Query("token") String token, @Query("uid") String uid,
                                      @Field("companyID") String companyID, @Query("managerID") String managerID);

    @FormUrlEncoded  //修改管理员
    @POST("/two.php/api/Company/updateManager")
    Flowable<HttpResult> authoritySetting(@Query("token") String token, @Query("uid") String uid,
                                          @Field("companyID") String companyID, @Field("userID") String userID,
                                          @Field("departIDs") String departIDs, @Field("authCreate") String authCreate,
                                          @Field("authData") String authData, @Field("authManage") String authManage,
                                          @Field("rechargeAuth") String rechargeAuth, @Field("crmAuth") String crmAuth);

    @FormUrlEncoded  //添加管理员
    @POST("/two.php/api/Company/addManager")
    Flowable<HttpResult> addAuthority(@Query("token") String token, @Query("uid") String uid,
                                      @Field("companyID") String companyID, @Field("managerID") String managerID,
                                      @Field("managerRoleID") String managerRoleID,
                                      @Field("departIDs") String departIDs, @Field("authCreate") String authCreate,
                                      @Field("authData") String authData, @Field("authManage") String authManage,
                                      @Field("rechargeAuth") String rechargeAuth, @Field("crmAuth") String crmAuth);

    //--------------------------------------------------------项目--------------------------------------------------------
    @GET("api/Project/proList")
    //项目列表
    Flowable<HttpResult<XiangmuModel>> xiangmuList(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                                   @Query("per") String per, @Query("page") Integer page, @Query("keyword") String keyword);

    @GET("api/Project/proDetail")
        //项目详情
    Flowable<HttpResult<ProjectDetailsModel>> xmDetails(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @GET("api/Project/joinProList")
        //加入项目申请列表接口
    Flowable<HttpResult<ProjectNewsModel>> projectNews(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                                       @Query("proID") String proID,
                                                       @Query("per") String per, @Query("page") Integer page);

    @GET("/two.php/api/Company/appliesList")
        //员工申请列表
    Flowable<HttpResult<TeamApplyModel>> teamApply(@Query("token") String token, @Query("uid") String uid,
                                                   @Query("per") String per, @Query("page") Integer page);

    @GET("api/Project/joinProCheck")
        //加入项目审核接口
    Flowable<HttpResult> joinProject(@Query("token") String token, @Query("uid") String uid, @Query("applyID") String applyID,
                                     @Query("status") String status, @Query("checkRemark") String checkRemark);

    @FormUrlEncoded  //同意/拒绝员工加入公司申请
    @POST("/two.php/api/Company/doApply")
    Flowable<HttpResult> joinTeam(@Query("token") String token, @Query("uid") String uid,
                                  @Field("companyID") String companyID, @Field("applyID") String applyID,
                                  @Field("type") String type, @Field("departmentID") String departmentID);

    @GET("api/Project/createPro")
        //创建项目
    Flowable<HttpResult<FoundProjectModel>> foundProject(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                                         @Query("proName") String proName, @Query("latitude") String latitude, @Query("longitude") String longitude,
                                                         @Query("detailAddress") String detailAddress, @Query("customName") String customName,
                                                         @Query("customTel") String customTel, @Query("sales_people") String sales_people,
                                                         @Query("contractMoney") String contractMoney, @Query("customLevel") String customLevel,
                                                         @Query("areaName") String areaName,
                                                         @Query("qualityTime") String qualityTime, @Query("qualityBegin") String qualityBegin,
                                                         @Query("qualityEnd") String qualityEnd, @Query("qualityRing") String qualityRing,
                                                         @Query("APP_VERSION") String APP_VERSION, @Query("companyCustomerID") String companyCustomerID);

    @GET("api/Project/proEdit")
        //项目信息编辑
    Flowable<HttpResult> editProject(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                     @Query("proID") String proID,
                                     @Query("pro_name") String pro_name, @Query("latitude") String latitude, @Query("longitude") String longitude,
                                     @Query("detailAddress") String detailAddress,
                                     @Query("sales_people") String sales_people,
                                     @Query("contractMoney") String contractMoney, @Query("customLevel") String customLevel,
                                     @Query("areaName") String areaName,
                                     @Query("qualityTime") String qualityTime, @Query("qualityBegin") String qualityBegin,
                                     @Query("qualityEnd") String qualityEnd, @Query("qualityRing") String qualityRing,
                                     @Query("APP_VERSION") String APP_VERSION, @Query("customerIDs") String customerIDs);

    @GET("api/Project/getCustomLevel")
        //客户级别列表
    Flowable<HttpResult<List<ProjectLevelModel>>> projectLevel(@Query("token") String token, @Query("uid") String uid);

    @GET("api/Project/createQrCode")
        //生成项目二维码
    Flowable<HttpResult<ProjectCodeModel>> projectCode(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @GET("api/Project/getData")
        //获取项目工作台上的数字
    Flowable<HttpResult<ProjectStagingModel>> projectStaging(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

//    @GET("api/after_sale/afterSaleOrderList")
//        //售后工单列表  0全部  1故障保修  2维护升级
//    Flowable<HttpResult<WorkorderListModel>> workorderList(@Query("token") String token, @Query("uid") String uid,
//                                                           @Query("proID") String proID, @Query("shType") String shType,
//                                                           @Query("per") String per, @Query("page") Integer page,
//                                                           @Query("keyword") String keyword);

    @GET("api/Project/delPro")
        //删除项目
    Flowable<HttpResult> deleteProject(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                       @Query("proID") String proID);

    @GET("api/after_sale/lookOrder")
        //查看工单信息
    Flowable<HttpResult<GongdanShuxingModel>> gongdanShuxing(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    @GET("api/after_sale/lookProcess")
        //查看处理过程
    Flowable<HttpResult<List<ChuliGuochengModel>>> chuliGuocheng(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    @FormUrlEncoded  //创建售后工单
    @POST("api/after_sale/orderCreateSave")
    Flowable<HttpResult> addWorkorder(@Query("token") String token, @Query("uid") String uid,
                                      @Field("proId") String proId, @Field("shType") String shType,
                                      @Field("contactName") String contactName, @Field("contactPhone") String contactPhone,
                                      @Field("wishTime") String wishTime, @Field("content") String content,
                                      @Field("fileId") String fileId);

    @FormUrlEncoded  //受理工单 isAccept 1受理2不受理
    @POST("api/after_sale/orderAcceptSave")
    Flowable<HttpResult> workorderAccept(@Query("token") String token, @Query("uid") String uid,
                                         @Field("orderNo") String orderNo, @Field("isAccept") String isAccept,
                                         @Field("result") String result, @Field("recommendContact") String recommendContact);

    @FormUrlEncoded  //编辑售后工单
    @POST("api/after_sale/orderEditSave")
    Flowable<HttpResult> editWorkorder(@Query("token") String token, @Query("uid") String uid,
                                       @Field("orderNo") String orderNo, @Field("shtype") String shtype,
                                       @Field("contactName") String contactName, @Field("contactPhone") String contactPhone,
                                       @Field("wishTime") String wishTime, @Field("content") String content,
                                       @Field("fileId") String fileId);

    @FormUrlEncoded  //取消工单
    @POST("api/after_sale/cancelOrderSave")
    Flowable<HttpResult> cancalAccept(@Query("token") String token, @Query("uid") String uid,
                                      @Field("orderNo") String orderNo, @Field("cancelNote") String cancelNote);

    @FormUrlEncoded  //删除售后工单
    @POST("api/after_sale/delShOrder")
    Flowable<HttpResult> deleteWorkorder(@Query("token") String token, @Query("uid") String uid,
                                         @Field("orderNo") String orderNo, @Field("delNote") String delNote);

    @FormUrlEncoded  //工单派工
    @POST("api/after_sale/orderPaiGong")
    Flowable<HttpResult> workorderDispatch(@Query("token") String token, @Query("uid") String uid,
                                           @Field("proID") String proId, @Field("orderNo") String orderNo, @Field("enginerID") String enginerID,
                                           @Field("name") String name, @Field("phone") String phone,
                                           @Field("note") String note);

    @FormUrlEncoded  //工单重新改派
    @POST("api/after_sale/orderReAssign")
    Flowable<HttpResult> newsDispatch(@Query("token") String token, @Query("uid") String uid,
                                      @Field("orderNo") String orderNo, @Field("enginerID") String enginerID,
                                      @Field("name") String name, @Field("phone") String phone,
                                      @Field("note") String note, @Field("shJobID") String shJobID);

    @FormUrlEncoded  //公司售后客服人员列表
    @POST("api/after_sale/shKfList")
    Flowable<HttpResult<List<CustomListModel>>> customList(@Query("token") String token, @Query("uid") String uid,
                                                           @Field("companyID") String companyID);

    @GET("/two.php/api/Company/kfDel")
        //公司删除售后人员
    Flowable<HttpResult> deleteCustom(@Query("token") String token, @Query("uid") String uid, @Query("kfID") String kfID);

    @GET("/two.php/api/Company/kfTypeList")
        //售后客服类型列表
    Flowable<HttpResult<List<CustomTypeModel>>> customType(@Query("token") String token, @Query("uid") String uid);

    @GET("/two.php/api/Company/kfSet")
        //售后客服人员设置
    Flowable<HttpResult> addCustom(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                   @Query("kfTypeID") String kfTypeID, @Query("name") String name, @Query("phone") String phone);

    @GET("/two.php/api/Company/kfEdit")
        //公司修改售后客服人员
    Flowable<HttpResult> editCustom(@Query("token") String token, @Query("uid") String uid, @Query("kfID") String kfID,
                                    @Query("kfTypeID") String kfTypeID, @Query("name") String name, @Query("phone") String phone);

    @GET("api/after_sale/visitList")
        //查看回访
    Flowable<HttpResult<VisitRrcordModel>> visitRecord(@Query("token") String token, @Query("uid") String uid, @Query("proId") String proId,
                                                       @Query("per") String per, @Query("page") Integer page);

    @FormUrlEncoded  //添加回访
    @POST("api/after_sale/addVisit")
    Flowable<HttpResult> addReturnVisit(@Query("token") String token, @Query("uid") String uid,
                                        @Field("proId") String proId, @Field("title") String title, @Field("nowDate") String nowDate,
                                        @Field("content") String content, @Field("nextDate") String nextDate);

    @GET("api/after_sale/lookShSet")
        //查看售后设置
    Flowable<HttpResult<SettingInfoModel>> settingInfo(@Query("token") String token, @Query("uid") String uid, @Query("proId") String proId);

    @FormUrlEncoded  //售后设置
    @POST("api/after_sale/shSet")
    Flowable<HttpResult> shouhouSetting(@Query("token") String token, @Query("uid") String uid, @Field("proId") String proId,
                                        @Field("qualityTime") String qualityTime, @Field("qualityBegin") String qualityBegin,
                                        @Field("qualityEnd") String qualityEnd, @Field("nextVisitTime") String nextVisitTime,
                                        @Field("visitSpace") String visitSpace, @Field("qualityRing") String qualityRing,
                                        @Field("ybRing") String ybRing, @Field("visitRing") String visitRing,
                                        @Field("ybEnd") String ybEnd, @Field("ybTime") String ybTime);

    @FormUrlEncoded  //维护项目添加
    @POST("api/after_sale/wbSetAdd")
    Flowable<HttpResult> addWhProject(@Query("token") String token, @Query("uid") String uid, @Field("proId") String proId,
                                      @Field("title") String title, @Field("nextDate") String nextDate,
                                      @Field("spaceDate") String spaceDate);

    @FormUrlEncoded  //维护项目编辑
    @POST("api/after_sale/wbSetEdit")
    Flowable<HttpResult> deleteWb(@Query("token") String token, @Query("uid") String uid, @Field("wbId") String wbId,
                                  @Field("title") String title, @Field("nextDate") String nextDate,
                                  @Field("spaceDate") String spaceDate, @Field("idDel") String idDel);

    @GET("api/after_sale/lookAppraise")
        //查看评价
    Flowable<HttpResult<CheckAppraiseModel>> checkAppraise(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    //------------------------------------------------------个人中心-------------------------------------------------
    @GET("/api/users/getuser")
    //获取用户数据
    Flowable<HttpResult<UserInfoModel>> appUserInfo(@Query("token") String token, @Query("uid") String uid);


    @POST("/two.php/api/Discover/newsList")
    Flowable<HttpResult<News>> newsList(@Query("token") String token,
                                        @Query("uid") String uid, @Query("per") String per,
                                        @Query("page") Integer page, @Query("label") String label);

    @POST("/two.php/api/Discover/search")
        //搜索
    Flowable<HttpResult<News>> searchZixun(@Query("token") String token,
                                           @Query("uid") String uid, @Query("per") String per,
                                           @Query("page") Integer page, @Query("key") String key, @Query("type") String type);

    @POST("/two.php/api/Discover/videoList")
    Flowable<HttpResult<Videos>> videoList(@Query("token") String token,
                                           @Query("uid") String uid,
                                           @Query("per") String per,
                                           @Query("page") Integer page);

    @FormUrlEncoded
    @POST("/api/users/roast")
//用户吐槽
    Flowable<HttpResult> appFeedback(@Query("token") String token,
                                     @Query("uid") String uid,
                                     @Field("content") String content);

    @GET("/api/users/createRecImg")
//为不同用户生成推广二维码
    Flowable<HttpResult> myPromotion(@Query("token") String token, @Query("uid") String uid, @Query("phone") String phone);

    @Multipart  //上传照片
    @POST("/api/users/uploadHead")
    Call<UserUploadModel> uploadMemberIcon(@Query("token") String token, @Query("uid") String uid, @Part List<MultipartBody.Part> partList);

    //设置新手机
    Flowable<HttpResult> appModifyPhone(@Query("token") String token,
                                        @Query("uid") String uid,
                                        @Field("nowPwd") String nowPwd,
                                        @Field("u_phone") String u_phone,
                                        @Field("smscode") String smscode);

    @POST("/two.php/api/User/getMsg")
    Flowable<HttpResult<MsgMainModel>> getMsg(@Query("token") String token,
                                              @Query("uid") String uid);

    //项目消息/系统消息/团队消息列表页  1 项目消息 2团队消息 3系统消息
    @POST("/two.php/api/User/getMsgList")
    Flowable<HttpResult<MsgTdxxModel>> getMsgList(@Query("token") String token,
                                                  @Query("uid") String uid,
                                                  @Query("type") String type,
                                                  @Query("per") String per,
                                                  @Query("page") Integer page);

    @POST("/two.php/api/User/setRead")
    Flowable<HttpResult> setRead(@Query("token") String token,
                                 @Query("uid") String uid,
                                 @Query("msgID") String msgID);

    @POST("/two.php/api/User/delMsg")
    Flowable<HttpResult> delMsg(@Query("token") String token,
                                @Query("uid") String uid,
                                @Query("msgID") String msgID);

    @GET("api/after_sale/lookReceipt")
        //查看回单信息
    Flowable<HttpResult<ClzGongdanDetailModel>> lookReceipt(@Query("token") String token,
                                                            @Query("uid") String uid,
                                                            @Query("receiptId") String receiptId);

    @POST("/two.php/api/Discover/addComment")
    Flowable<HttpResult> addComment(@Query("token") String token,
                                    @Query("uid") String uid,
                                    @Query("rowNum") String rowNum,
                                    @Query("comment") String comment);

    @POST("/two.php/api/Discover/commentsList")
    Flowable<HttpResult<CommentBean>> commentsList(@Query("token") String token,
                                                   @Query("uid") String uid,
                                                   @Query("rowNum") String rowNum);

    @POST("/two.php/api/Discover/addhits")
    Flowable<HttpResult> addhits(@Query("token") String token,
                                 @Query("uid") String uid,
                                 @Query("rowNum") String rowNum);

    //------------------------------------------------------支付-------------------------------------------------
    @GET("/two.php/api/Finance/companyInfo")
    //公司信息
    Flowable<HttpResult<CompanyInfoModel>> getCompany(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID);

    @GET("/two.php/api/Finance/rechargeCombo")
        //充值套餐
    Flowable<HttpResult<List<RechargeComboModel>>> rechargeCombo(@Query("token") String token, @Query("uid") String uid,
                                                                 @Query("companyID") String companyID);

    @GET("/two.php/api/Finance/levelRights")
        //会员权益
    Flowable<HttpResult<List<LevelRightsModel>>> getLevelRights(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //购买版块扣砖
    @POST("/two.php/api/Finance/buyModule")
    Flowable<HttpResult> openBk(@Query("token") String token, @Query("uid") String uid, @Field("proID") String proId,
                                @Field("moduleType") String moduleType, @Field("zhuanNum") String zhuanNum);

    @GET("/two.php/api/Finance/financialLogUser")
        //个人财务记录 type 0全部，1收入，2支出
    Flowable<HttpResult<BrickRecordModel>> brickRecord(@Query("token") String token, @Query("uid") String uid, @Query("type") String type,
                                                       @Query("per") String per, @Query("page") Integer page);

    @GET("/two.php/api/Finance/financialLogCompany")
        //公司财务记录 type 0全部，1收入，2支出
    Flowable<HttpResult<TransactionRecordModel>> transactionRecord(@Query("token") String token, @Query("uid") String uid,
                                                                   @Query("companyID") String companyID,
                                                                   @Query("type") String type, @Query("per") String per, @Query("page") Integer page);

    @FormUrlEncoded
    @POST("/two.php/api/Finance/createOrder")
    Flowable<HttpResult<AppPaymentModel>> appAliPayment(@Query("token") String token, @Query("uid") String uid,
                                                        @Field("comboID") String comboID, @Field("payType") String payType,
                                                        @Field("companyID") String companyID);

    @FormUrlEncoded
    @POST("/two.php/api/Finance/createOrder")
    Flowable<HttpResult<AppWXPaymentModel>> appWXPayment(@Query("token") String token, @Query("uid") String uid,
                                                         @Field("comboID") String comboID, @Field("payType") String payType,
                                                         @Field("companyID") String companyID);

    //------------------------------------------------------支付-------------------------------------------------
    @FormUrlEncoded  //添加客户
    @POST("/two.php/api/Customer/addCustomer")
    Flowable<HttpResult> buildCustomer(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                       @Field("customerName") String customerName, @Field("customerPhone") String customerPhone,
                                       @Field("followStatusID") String followStatusID, @Field("customerLevelID") String customerLevelID,
                                       @Field("customerSourceID") String customerSourceID, @Field("remark") String remark);

    @GET("/two.php/api/Customer/getAllSelections")
        //获取跟进状态、客户等级、客户来源、沟通方式接口
    Flowable<HttpResult<SelectModel>> getSelect(@Query("token") String token, @Query("uid") String uid,
                                                @Query("companyID") String companyID);

    @GET("/two.php/api/Customer/getCompanyCustomers")
        //获取公司客户列表
    Flowable<HttpResult<WholeCustomerModel>> wholeCustomer(@Query("token") String token, @Query("uid") String uid,
                                                           @Query("companyID") String companyID,
                                                           @Query("per") String per, @Query("page") Integer page,
                                                           @Query("keywords") String keywords, @Query("customerLevelID") String customerLevelID,
                                                           @Query("followStatusID") String followStatusID, @Query("hasFollowRecords") String hasFollowRecords);

    @FormUrlEncoded  //删除公司客户
    @POST("/two.php/api/Customer/delCompanyCustomer")
    Flowable<HttpResult> delCustomer(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("customerID") String customerID);

    @GET("/two.php/api/Customer/getCompanyCustomerInfo")
        //获取公司/我的客户详情接口（客户基本信息，项目信息）
    Flowable<HttpResult<DetailsModel>> customerDetails(@Query("token") String token, @Query("uid") String uid,
                                                       @Query("companyID") String companyID, @Query("companyCustomerID") String companyCustomerID);

    @FormUrlEncoded  //编辑客户信息接口
    @POST("/two.php/api/Customer/editCompanyCustomerInfo")
    Flowable<HttpResult> editInfo(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("companyCustomerID") String companyCustomerID,
                                  @Field("customerName") String customerName, @Field("customerPhone") String customerPhone,
                                  @Field("followStatusID") String followStatusID, @Field("customerLevelID") String customerLevelID,
                                  @Field("customerSourceID") String customerSourceID, @Field("remark") String remark,
                                  @Field("salesPersonUserID") String salesPersonUserID);

    @FormUrlEncoded  //添加关联项目
    @POST("/two.php/api/Customer/addRelatedPro")
    Flowable<HttpResult> guanliPro(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                   @Field("proID") String proID,
                                   @Field("customerUserID") String customerUserID);

    @GET("/two.php/api/Customer/getFollowRecords")
        //跟进记录列表接口
    Flowable<HttpResult<List<FollowProModel>>> followPro(@Query("token") String token, @Query("uid") String uid,
                                                         @Query("companyID") String companyID, @Query("customerID") String customerID);

    @FormUrlEncoded  //新增跟进记录（跟进状态、沟通方式、日期、内容、图片）
    @POST("/two.php/api/Customer/addFollowRecord")
    Flowable<HttpResult> addFollow(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                   @Field("communicationTypeID") String communicationTypeID,
                                   @Field("followStatusID") String followStatusID, @Field("customerID") String customerID,
                                   @Field("content") String content, @Field("imageUrls") String imageUrls,
                                   @Field("followTime") String followTime);

    @GET("/two.php/api/Customer/taskList")
        //我的任务列表
    Flowable<HttpResult<MyTaskModel>> myTask(@Query("token") String token, @Query("uid") String uid,
                                             @Query("companyID") String companyID, @Query("per") String per, @Query("page") Integer page);

    @FormUrlEncoded  //新建任务
    @POST("/two.php/api/Customer/addTask")
    Flowable<HttpResult> addTask(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                 @Field("customerID") String customerID,
                                 @Field("taskTime") String taskTime, @Field("noticeTime") String noticeTime,
                                 @Field("remark") String remark);

    @FormUrlEncoded  //删除任务
    @POST("/two.php/api/Customer/delTask")
    Flowable<HttpResult> delTask(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                 @Field("taskID") String taskID);

    @FormUrlEncoded  //编辑任务
    @POST("/two.php/api/Customer/editTask")
    Flowable<HttpResult> editTask(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("taskID") String taskID,
                                  @Field("taskTime") String taskTime, @Field("noticeTime") String noticeTime,
                                  @Field("remark") String remark);

    @GET("/two.php/api/Customer/getMyCustomers")
        //获取我的客户列表
    Flowable<HttpResult<WholeCustomerModel>> myCustomer(@Query("token") String token, @Query("uid") String uid,
                                                        @Query("companyID") String companyID,
                                                        @Query("per") String per, @Query("page") Integer page,
                                                        @Query("keywords") String keywords, @Query("customerLevelID") String customerLevelID,
                                                        @Query("followStatusID") String followStatusID,
                                                        @Query("hasFollowRecords") String hasFollowRecords);

    @FormUrlEncoded  //放弃跟进客户接口
    @POST("/two.php/api/Customer/quitFollow")
    Flowable<HttpResult> fangCustomer(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                      @Field("companyCustomerID") String companyCustomerID);

    @GET("/two.php/api/Customer/myShareRecords")
        //我分享客户的记录
    Flowable<HttpResult<MyShareModel>> myShare(@Query("token") String token, @Query("uid") String uid,
                                               @Query("companyID") String companyID,
                                               @Query("per") String per, @Query("page") Integer page);

    @FormUrlEncoded  //删除我的分享记录
    @POST("/two.php/api/Customer/delMyShareRecord")
    Flowable<HttpResult> delMyShare(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                    @Field("shareRecordID") String shareRecordID);

    @FormUrlEncoded  //新建分享客户接口
    @POST("/two.php/api/Customer/shareCustomers")
    Flowable<HttpResult> addShare(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("customers") String customers, @Field("toCompany") String toCompany);

    @GET("/two.php/api/Customer/myReceivedShares")
        //别人分享给我的客户接口
    Flowable<HttpResult<MyShareModel>> myShnew(@Query("token") String token, @Query("uid") String uid,
                                               @Query("companyID") String companyID,
                                               @Query("shareRecordID") String shareRecordID);

    @FormUrlEncoded  //选择接受哪些分享的客户接口
    @POST("/two.php/api/Customer/chooseCustomers")
    Flowable<HttpResult> setShareSel(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("shareRecordID") String shareRecordID, @Field("customers") String customers);

    @GET("/two.php/api/Customer/searchCompanies")
        //搜索公司接口
    Flowable<HttpResult<List<SearchComModel>>> searchCom(@Query("token") String token, @Query("uid") String uid,
                                                         @Query("keywords") String keywords);

    @POST("/api/user/appRegister")
    Flowable<HttpResult> appRegister(@Body RegisterRequest request);

    @POST("api/user/appLogin")
    Flowable<HttpResult<UserResponse>> appLogin(@Body LoginRequest request);

    @POST("/api/user/forgetPwd")
    Flowable<HttpResult> forgetPwd(@Body ForgetRequest forgetRequest);

    @GET("/api/user/getSmsCode")
//注册0/忘记密码1/换手机2
    Flowable<HttpResult<String>> getSmsCode(@Query("phone") String phone, @Query("type") String type);

    @POST("/api/user/changePwd")
    Flowable<HttpResult> changePwd(@Query("token") String token, @Query("uid") String uid, @Body ChangePwdRequest request);


    @GET("api/User/myEngineerList")
    Flowable<HttpResult<List<EnginerListModel>>> myEngineerList(@Query("token") String token, @Query("uid") String uid);

    @GET("api/user/searchEngineer")
    Flowable<HttpResult<SelectEnginerModel>> searchEngineer(@Query("token") String token, @Query("uid") String uid,
                                                            @Query("phone") String phone);

    @GET("api/user/addEngineer")
    Flowable<HttpResult> addEngineer(@Query("token") String token, @Query("uid") String uid,
                                     @Query("enginerID") String enginerID, @Query("remark") String remark);


    @GET("/api/User/getBusinessCard")
    Flowable<HttpResult<BusinessCardResponse>> getBusinessCard(@Query("userID") String userId);

    @FormUrlEncoded
    @POST("/api/User/editBusinessCard")
    Flowable<HttpResult> editBusinessCard(@Query("token") String token, @Query("uid") String uid, @Body EditBusinessCardRequest request);


    /*project*/

    @GET("api/Project/proList")
    Flowable<HttpResult<XiangmuModel>> getProjectList(@QueryMap Map<String, Object> map);

    @GET("api/Project/proMemberList")
    Flowable<HttpResult<StagingListModel>> getProMemberList(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @POST("api/Project/delProMember")
    Flowable<HttpResult> deleteProMember(@Query("token") String token, @Query("uid") String uid, @Body ProMemberRequest request);

    @GET("api/Project/addProMember")
    Flowable<HttpResult> addProMember(@Query("token") String token, @Query("uid") String uid, @Body ProMemberRequest request);


    /*Company*/
    @GET("api/Company/getCompanyInfo")
    Flowable<HttpResult<TeamManageModel>> getCompanyInfo(@QueryMap Map<String, Object> map);

    @GET("api/Company/generateCompanyQRCode")
//生成公司邀请二维码
    Flowable<HttpResult<ShareCodeModel>> shareCode(@QueryMap Map<String, Object> map);

    @GET("api/Company/getDepartments")
//架构列表
    Flowable<HttpResult<ZuzhiJiagouModel>> getDepartments(@Query("token") String token, @Query("uid") String uid,
                                                          @Query("companyID") String companyID);

    @POST("api/Company/addDepartment")
    Flowable<HttpResult> addDepartment(@Query("token") String token, @Query("uid") String uid, AddDepartmentRequest request);


    //售后
    @GET("api/after_sale/afterSaleOrderList")
    Flowable<HttpResult<WorkorderListModel>> afterSaleOrderList(@QueryMap Map<String, Object> map);
}
