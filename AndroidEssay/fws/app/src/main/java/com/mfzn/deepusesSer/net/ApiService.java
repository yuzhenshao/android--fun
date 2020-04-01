package com.mfzn.deepusesSer.net;


import com.mfzn.deepusesSer.bean.UserResponse.BusinessCardResponse;
import com.mfzn.deepusesSer.bean.request.AcceptSendOrderRequest;
import com.mfzn.deepusesSer.bean.request.EditBusinessCardRequest;
import com.mfzn.deepusesSer.model.UploadContractModel;
import com.mfzn.deepusesSer.model.company.SelectCompanyModel;
import com.mfzn.deepusesSer.model.company.SelectLableModel;
import com.mfzn.deepusesSer.model.faxian.CommentBean;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.model.faxian.Videos;
import com.mfzn.deepusesSer.model.home.JudgeLevelModel;
import com.mfzn.deepusesSer.model.jiagou.SearchKeywordModel;
import com.mfzn.deepusesSer.model.jiagou.ShareCodeModel;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.model.msg.Message;
import com.mfzn.deepusesSer.model.myTeam.CompanyScaleModel;
import com.mfzn.deepusesSer.model.myTeam.TeamManageModel;
import com.mfzn.deepusesSer.model.person.UserInfoModel;
import com.mfzn.deepusesSer.model.person.UserUploadModel;
import com.mfzn.deepusesSer.model.shouhou.ChuliGuochengModel;
import com.mfzn.deepusesSer.model.shouhou.ClzGongdanDetailModel;
import com.mfzn.deepusesSer.model.shouhou.GongdanShuxingModel;
import com.mfzn.deepusesSer.model.shouhou.PingjiaDetailModel;
import com.mfzn.deepusesSer.model.xiangmu.FoundProjectModel;
import com.mfzn.deepusesSer.model.xiangmu.ProjectLevelModel;
import com.mfzn.deepusesSer.model.xiangmu.ProjectStagingModel;
import com.mfzn.deepusesSer.model.xiangmu.StagingListModel;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.model.xiangmu.XiangmuModel;

import java.util.List;

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

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public interface ApiService {
    //--------------------------------------------------------用户--------------------------------------------------------
    @GET("user/getSmsCode")
        //发送短信验证码  注册0/忘记密码1/换手机2
    Flowable<HttpResult<String>> getSmsCode(@Query("phone") String phone, @Query("type") String type);

    @FormUrlEncoded   //登录
    @POST("user/appLogin")
    Flowable<HttpResult<UserModel>> appLogin(@Field("userPhone") String userPhone, @Field("userPwd") String userPwd);

    @FormUrlEncoded  //注册
    @POST("user/appRegister")
    Flowable<HttpResult> appRegister(@Field("userPhone") String userPhone, @Field("smsCode") String smscode, @Field("userPwd") String userPwd,
                                     @Field("userRePwd") String userRePwd, @Field("userType") String userType, @Field("userName") String userName);

    @FormUrlEncoded
    @POST("user/setNickname")
        //修改昵称
    Flowable<HttpResult> appModifyName(@Query("token") String token, @Query("uid") String uid,
                                       @Field("nickName") String nickName);

    @FormUrlEncoded  //找回密码
    @POST("user/forgetPwd")
    Flowable<HttpResult> forgetPwd(@Field("userPhone") String userPhone, @Field("smsCode") String smsCode,
                                   @Field("userPwd") String userPwd, @Field("userRePwd") String userRePwd);

    @FormUrlEncoded
    @POST("user/changePwd")
        //修改密码
    Flowable<HttpResult> appModifyPwd(@Query("token") String token, @Query("uid") String uid,
                                      @Field("oldPwd") String oldPwd, @Field("userPwd") String userPwd, @Field("userRePwd") String userRePwd);

    @FormUrlEncoded
    @POST("user/uploadAvatar")
    Flowable<HttpResult> uploadAvatar(@Query("token") String token, @Query("uid") String uid, @Field("userAvatar") String userAvatar);

    @FormUrlEncoded
    @POST("user/roast")
        //用户吐槽
    Flowable<HttpResult> appFeedback(@Query("token") String token,
                                     @Query("uid") String uid,
                                     @Field("content") String content);

    @GET("User/getBusinessCard")
    Flowable<HttpResult<BusinessCardResponse>> getBusinessCard(@Query("userID") int userId);

    @POST("User/editBusinessCard")
    Flowable<HttpResult> editBusinessCard(@Query("token") String token, @Query("uid") String uid, @Body EditBusinessCardRequest request);


    //工单
    @GET("after_sale/getEngineerAsOrderList")
    //售后工单列表  0全部  1故障保修  2维护升级
    Flowable<HttpResult<WorkorderListModel>> workorderList(@Query("token") String token, @Query("uid") String uid,
                                                           @Query("asType") String asType, @Query("status") String status,
                                                           @Query("per") String per, @Query("page") Integer page);


    @GET("after_sale/lookAsOrder")
        //查看工单信息
    Flowable<HttpResult<GongdanShuxingModel>> gongdanShuxing(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    @GET("after_sale/lookAsOrderProcess")
        //查看处理过程
    Flowable<HttpResult<List<ChuliGuochengModel>>> chuliGuocheng(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    @POST("after_sale/doAcceptSendOrder")//接单或拒绝
    Flowable<HttpResult> jiedan(@Query("token") String token,
                                @Query("uid") String uid,
                                @Body AcceptSendOrderRequest request);

    @FormUrlEncoded
    @POST("after_sale/createReceipt")
        //工程师创建回单
    Flowable<HttpResult> createReceipt(@Query("token") String token,
                                       @Query("uid") String uid,
                                       @Field("orderNo") String orderNo,
                                       @Field("faultText") String faultText,
                                       @Field("faultFileUrls") String faultFileUrls,
                                       @Field("content") String content,
                                       @Field("contentFileUrls") String contentFileUrls,
                                       @Field("status") String status,
                                       @Field("sign") String sign,
                                       @Field("money") String money,
                                       @Field("moneyNote") String moneyNote);

    @GET("after_sale/lookReceipt")
        //查看回单信息
    Flowable<HttpResult<ClzGongdanDetailModel>> lookReceipt(@Query("token") String token,
                                                            @Query("uid") String uid,
                                                            @Query("receiptID") String receiptID);

    @GET("after_sale/lookAsOrderComment")
        //查看评价信息
    Flowable<HttpResult<PingjiaDetailModel>> lookAppraise(@Query("token") String token,
                                                          @Query("uid") String uid,
                                                          @Query("orderNo") String orderNo);

    //发现
    @GET("Content/newsList")
    Flowable<HttpResult<News>> newsList(@Query("token") String token,
                                        @Query("uid") String uid,
                                        @Query("per") String per,
                                        @Query("page") Integer page, @Query("kw") String kw);


    @GET("Content/videoList")
    Flowable<HttpResult<Videos>> videoList(@Query("token") String token,
                                           @Query("uid") String uid,
                                           @Query("per") String per,
                                           @Query("page") Integer page, @Query("kw") String kw);

    //消息
    @GET("User/getSerMsgList")//获取消息详情列表
    Flowable<HttpResult<Message>> getSerMsgList(@Query("token") String token,
                                                @Query("uid") String uid,
                                                @Query("per") String per,
                                                @Query("page") Integer page);

    @FormUrlEncoded
    @POST("User/setRead")
    Flowable<HttpResult> setRead(@Query("token") String token,
                                 @Query("uid") String uid,
                                 @Field("msgID") String msgID);

    @GET("Index/getQiniuToken")
    Flowable<HttpResult> getQiniuToken();


    //--------------------------------------------------------首页--------------------------------------------------------
    //"roleID": 1:创建者  2：超级管理员  3：普普通通管理员 4：普通员工

    @GET("Company/showMenu")
        //判断组织架构的级别
    Flowable<HttpResult<JudgeLevelModel>> judgeLevel(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID);

    //--------------------------------------------------------公司--------------------------------------------------------
    @GET("Company/companyList")
    //公司列表
    Flowable<HttpResult<List<SelectCompanyModel>>> companyList(@Query("token") String token, @Query("uid") String uid);

    @GET("Company/getBusinessScope")
        //经营范围
    Flowable<HttpResult<List<SelectLableModel>>> companyLable(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //创建公司
    @POST("Company/createCompany")
    Flowable<HttpResult> establishCompany(@Query("token") String token, @Query("uid") String uid,
                                          @Field("companyName") String companyName, @Field("areaID") String areaID,
                                          @Field("businessScope") String businessScope);

    @FormUrlEncoded  //扫码申请加入公司
    @POST("/two.php/api/Company/joinCompanyWithCode")
    Flowable<HttpResult> applyJoin(@Query("token") String token, @Query("uid") String uid,
                                   @Field("companyID") String companyID, @Field("positionName") String positionName,
                                   @Field("reason") String reason);

    //--------------------------------------------------------组织架构--------------------------------------------------------
    @GET("Company/getDepartments")
    //架构列表
    Flowable<HttpResult<ZuzhiJiagouModel>> jiagouList(@Query("token") String token, @Query("uid") String uid,
                                                      @Query("companyID") String companyID);

    @GET("Company/searchStaff")
        //搜索员工
    Flowable<HttpResult<List<SearchKeywordModel>>> jiagouList(@Query("token") String token, @Query("uid") String uid,
                                                              @Query("companyID") String companyID, @Query("keywords") String keywords);

    @FormUrlEncoded  //编辑员工信息
    @POST("Company/editStaff")
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

    @FormUrlEncoded  //添加子部门
    @POST("/two.php/api/Company/addDepartment")
    Flowable<HttpResult> addSonbm(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("departmentID") String departmentID, @Field("departmentName") String departmentName,
                                  @Field("departmentManagerID") String departmentManagerID, @Field("departmentLeaderID") String departmentLeaderID);

    @FormUrlEncoded  //移动员工到对应部门
    @POST("/two.php/api/Company/moveUsers")
    Flowable<HttpResult> moveStaff(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                   @Field("userIDs") String userIDs, @Field("oldDepartmentID") String oldDepartmentID,
                                   @Field("newDepartmentID") String newDepartmentID);

    //--------------------------------------------------------企业设置--------------------------------------------------------
    @GET("/two.php/api/Company/getCompanyInfo")
    //团队管理
    Flowable<HttpResult<TeamManageModel>> teamManage(@Query("token") String token, @Query("uid") String uid,
                                                     @Query("companyID") String companyID);

    @GET("/two.php/api/Company/getCompanyScale")
        //公司规模
    Flowable<HttpResult<List<CompanyScaleModel>>> companyScale(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //企业信息设置
    @POST("/two.php/api/Company/setCompanyInfo")
    Flowable<HttpResult> companyInfo(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("companyName") String companyName, @Field("shortName") String shortName,
                                     @Field("companyAddress") String companyAddress, @Field("companyWebsite") String companyWebsite,
                                     @Field("businessScope") String businessScope, @Field("scaleID") String scaleID);

    @FormUrlEncoded  //备注
    @POST("/two.php/api/Company/setRemarkName")
    Flowable<HttpResult> remarks(@Query("token") String token, @Query("uid") String uid,
                                 @Field("remarkName") String remarkName, @Field("companyID") String companyID,
                                 @Field("staffUID") String staffUID);

    @GET("/two.php/api/Company/createCode")
        //生成公司邀请二维码
    Flowable<HttpResult<ShareCodeModel>> shareCode(@Query("token") String token, @Query("uid") String uid,
                                                   @Query("companyID") String companyID);

    //--------------------------------------------------------项目--------------------------------------------------------
    @GET("/two.php/api/order_main/proList")
    //项目列表
    Flowable<HttpResult<XiangmuModel>> xiangmuList(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                                   @Query("per") String per, @Query("page") Integer page);

    @GET("/two.php/api/order_main/createPro")
        //创建项目
    Flowable<HttpResult<FoundProjectModel>> foundProject(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                                         @Query("pro_name") String pro_name, @Query("latitude") String latitude, @Query("longitude") String longitude,
                                                         @Query("detailAddress") String detailAddress, @Query("customName") String customName,
                                                         @Query("customTel") String customTel, @Query("sales_people") String sales_people,
                                                         @Query("contractMoney") String contractMoney, @Query("customLevel") String customLevel,
                                                         @Query("start_time") String start_time, @Query("end_time") String end_time, @Query("areaName") String areaName);

    @GET("/two.php/api/order_main/getCustomLevel")
        //客户级别列表
    Flowable<HttpResult<List<ProjectLevelModel>>> projectLevel(@Query("token") String token, @Query("uid") String uid);

    @GET("/two.php/api/order_main/createQrCode")
        //生成项目二维码
    Flowable<HttpResult> projectCode(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @GET("/two.php/api/order_main/getData")
        //获取项目工作台上的数字
    Flowable<HttpResult<ProjectStagingModel>> projectStaging(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @GET("/two.php/api/order_main/proMemberList")
        //项目成员列表
    Flowable<HttpResult<StagingListModel>> stagingList(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);


    @Multipart  //上传图片
    @POST("/api/index/upImages")
    Call<UploadContractModel> uploadPhotoIcon(@Query("token") String token, @Query("uid") String uid, @Part List<MultipartBody.Part> partList);


    //--------------------------------------------------------个人中心--------------------------------------------------------
    @GET("/api/user/getuser")
    //获取用户数据
    Flowable<HttpResult<UserModel>> appUserInfo(@Query("token") String token,
                                                    @Query("uid") String uid);


    @FormUrlEncoded
    @POST("/api/user/setPhone")
        //设置新手机
    Flowable<HttpResult> appModifyPhone(@Query("token") String token,
                                        @Query("uid") String uid,
                                        @Field("nowPwd") String nowPwd,
                                        @Field("u_phone") String u_phone,
                                        @Field("smscode") String smscode);



    @POST("/two.php/api/Discover/search")
        //搜索
    Flowable<HttpResult<News>> searchZixun(@Query("token") String token,
                                           @Query("uid") String uid, @Query("per") String per,
                                           @Query("page") Integer page, @Query("key") String key, @Query("type") String type);

    @GET("/api/user/myQrCode")
//为不同用户生成推广二维码
    Flowable<HttpResult> myPromotion(@Query("token") String token, @Query("uid") String uid, @Query("phone") String phone);

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
}
