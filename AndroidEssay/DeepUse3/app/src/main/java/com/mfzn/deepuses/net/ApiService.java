package com.mfzn.deepuses.net;


import com.mfzn.deepuses.bean.request.AcceptAsOrderRequest;
import com.mfzn.deepuses.bean.request.AddCustomRequest;
import com.mfzn.deepuses.bean.request.AddCustomerRequest;
import com.mfzn.deepuses.bean.request.AddDepartmentRequest;
import com.mfzn.deepuses.bean.request.AddFollowRecordRequest;
import com.mfzn.deepuses.bean.request.AsSetRequest;
import com.mfzn.deepuses.bean.request.ChangePwdRequest;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.request.CreateAfterSaleOrderRequest;
import com.mfzn.deepuses.bean.request.EditAsServicePeopleRequest;
import com.mfzn.deepuses.bean.request.EditProjectRequest;
import com.mfzn.deepuses.bean.request.EditStaffRequest;
import com.mfzn.deepuses.bean.request.ProMemberRequest;
import com.mfzn.deepuses.bean.request.EditBusinessCardRequest;
import com.mfzn.deepuses.bean.request.ForgetRequest;
import com.mfzn.deepuses.bean.request.LoginRequest;
import com.mfzn.deepuses.bean.request.ReSendAsOrderRequest;
import com.mfzn.deepuses.bean.request.RegisterRequest;
import com.mfzn.deepuses.bean.request.SendAsOrderRequest;
import com.mfzn.deepuses.bean.request.SupplierRequest;
import com.mfzn.deepuses.bean.request.capital.AddBorrowRequest;
import com.mfzn.deepuses.bean.request.capital.AddIncomeExpenseRequest;
import com.mfzn.deepuses.bean.request.capital.MoneyTransferRequest;
import com.mfzn.deepuses.bean.request.purchase.AddBorrowHandleLogRequest;
import com.mfzn.deepuses.bean.request.purchase.OrderPurchaseAddRequest;
import com.mfzn.deepuses.bean.request.sale.OrderOfferRequest;
import com.mfzn.deepuses.bean.request.sale.OrderOtherInRequest;
import com.mfzn.deepuses.bean.request.sale.OrderSalesBackRequest;
import com.mfzn.deepuses.bean.request.sale.OrderSalesRequest;
import com.mfzn.deepuses.bean.request.sale.OrderTakeGoodsBackRequest;
import com.mfzn.deepuses.bean.request.sale.OrderTakeGoodsRequest;
import com.mfzn.deepuses.bean.request.setting.AddMoneyAccountRequest;
import com.mfzn.deepuses.bean.request.setting.AddSetCustomerRequest;
import com.mfzn.deepuses.bean.request.setting.SetUserAuthRequest;
import com.mfzn.deepuses.bean.request.store.OrderAllotAddRequest;
import com.mfzn.deepuses.bean.request.store.OrderStockCheckRequest;
import com.mfzn.deepuses.bean.response.BusinessCardResponse;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import com.mfzn.deepuses.bean.response.WaitingCheckResponse;
import com.mfzn.deepuses.bean.response.capital.BorrowInfoResponse;
import com.mfzn.deepuses.bean.response.capital.BorrowListResponse;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.bean.response.capital.MoneyAccountFinancialLogListResponse;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeListResponse;
import com.mfzn.deepuses.bean.response.purchase.OrderPurchaseDetailResponse;
import com.mfzn.deepuses.bean.response.purchase.OrderPurchaseListResponse;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.bean.response.sale.OrderSalesListResponse;
import com.mfzn.deepuses.bean.response.sale.PersonalStoreListResponse;
import com.mfzn.deepuses.bean.response.settings.CustomerDetailResponse;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;
import com.mfzn.deepuses.bean.response.settings.MoneyAccountListResponse;
import com.mfzn.deepuses.bean.response.settings.MyStoreResponse;
import com.mfzn.deepuses.bean.response.settings.RateResponse;
import com.mfzn.deepuses.bean.response.settings.ShopCheckerResponse;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsDetailResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsListResponse;
import com.mfzn.deepuses.bean.response.settings.IncomeExpenseTypeResponse;
import com.mfzn.deepuses.bean.response.settings.OtherCostResponse;
import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;
import com.mfzn.deepuses.bean.response.shop.ShopDataResponse;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.bean.response.shop.ShopUserListResponse;
import com.mfzn.deepuses.bean.response.shop.UserAuthResponse;
import com.mfzn.deepuses.bean.response.store.GoodsStockResponse;
import com.mfzn.deepuses.bean.response.store.OrderAllotListResponse;
import com.mfzn.deepuses.bean.response.store.OrderOtherInOutListResponse;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckListResponse;
import com.mfzn.deepuses.bean.response.store.OtherWaitingInOutDetailResponse;
import com.mfzn.deepuses.bean.response.store.StockLogListResponse;
import com.mfzn.deepuses.bean.response.store.StockWarningResponse;
import com.mfzn.deepuses.bean.response.store.StoreAllCheckListResponse;
import com.mfzn.deepuses.bean.response.store.WaitingInDetailResponse;
import com.mfzn.deepuses.bean.response.store.WaitingOutDetailResponse;
import com.mfzn.deepuses.bean.response.store.WaitingInOutListResponse;
import com.mfzn.deepuses.bean.response.user.WaitingCheckListResponse;
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


    @GET("api/Index/getQiniuToken")
    Flowable<HttpResult> getQiniuToken();

    @Multipart  //上传图片
    @POST("api/index/upImages")
    Call<UploadContractModel> uploadPhotoIcon(@Query("token") String token, @Query("uid") String uid, @Part List<MultipartBody.Part> partList);

    //查看购买模块是否过期
    @GET("api/Company/checkModulesOutOfDate")
    Flowable<HttpResult<LookQuanxianModel>> lookQuanxian(@Query("token") String token, @Query("uid") String uid,
                                                         @Query("companyID") String companyID, @Query("proID") String proID);

    //查看购买模块是否过期
    @GET("api/Company/checkModulesOutOfDate")
    Flowable<HttpResult<LookQuanxian2Model>> lookQuanxian2(@Query("token") String token, @Query("uid") String uid,
                                                           @Query("companyID") String companyID);

    //--------------------------------------------------------首页--------------------------------------------------------
    //"roleID": 1:创建者  2：超级管理员  3：普普通通管理员 4：普通员工

    @GET("api/Company/showMenu")
        //判断组织架构的级别
    Flowable<HttpResult<JudgeLevelModel>> judgeLevel(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID);

    @GET("api/Company/getData")
        //获取数据看板上的数字
    Flowable<HttpResult<KanbDataModel>> kanbData(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID);

    //获取营销链接地址
    @GET("api/Index/marketUrl")
    Flowable<HttpResult> getMarketUrl(@Query("token") String token, @Query("uid") String uid, @Query("type") String type);

    //--------------------------------------------------------公司--------------------------------------------------------
    @GET("api/Company/getCompanyList")
    Flowable<HttpResult<List<SelectCompanyModel>>> companyList(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //解散公司/团队
    @POST("api/Company/leaveCompany")
    Flowable<HttpResult> delCompany(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID);

    @GET("api/Company/getBusinessScope")
//经营范围
    Flowable<HttpResult<List<SelectLableModel>>> companyLable(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //创建公司
    @POST("api/Company/createCompany")
    Flowable<HttpResult> establishCompany(@Query("token") String token, @Query("uid") String uid,
                                          @Field("companyName") String companyName, @Field("areaID") String areaID,
                                          @Field("businessScope") String businessScope,
                                          @Query("longitude") String longitude, @Query("latitude") String latitude,
                                          @Query("companyAddress") String companyAddress);

    @GET("api/Project/joinPro")
//扫码项目二维码加入项目
    Flowable<HttpResult> joinXiangmu(@Query("token") String token, @Query("uid") String uid,
                                     @Query("proID") String proID, @Query("label") String label, @Query("remark") String remark);

    @GET("api/index/getAreaID")
    Flowable<HttpResult<CityModel>> getAreaID(@Query("province_name") String province_name,
                                              @Query("city_name") String city_name,
                                              @Query("area_name") String area_name);

    @POST("api/Company/uploadLogo")
    Call<UserUploadModel> uploadLogoIcon(@Query("token") String token, @Query("uid") String uid, @Part List<MultipartBody.Part> partList);

    //--------------------------------------------------------组织架构--------------------------------------------------------
    @GET("api/Company/searchStaff")
    //搜索员工
    Flowable<HttpResult<List<SearchKeywordModel>>> jiagouList(@Query("token") String token, @Query("uid") String uid,
                                                              @Query("companyID") String companyID, @Query("keywords") String keywords);

    //编辑员工信息
    @POST("api/Company/editStaff")
    Flowable<HttpResult> editStaff(@Query("token") String token, @Query("uid") String uid, @Body EditStaffRequest request);

    @FormUrlEncoded  //删除员工
    @POST("api/Company/delStaff")
    Flowable<HttpResult> deleteStaff(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("staffUserID") String staffUID);

    @FormUrlEncoded  //删除部门
    @POST("api/Company/delDepartment")
    Flowable<HttpResult> deleteBm(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("departmentID") String departmentID);

    @FormUrlEncoded  //移动员工到对应部门
    @POST("api/Company/moveUsers")
    Flowable<HttpResult> moveStaff(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                   @Field("userIDs") String userIDs, @Field("oldDepartmentID") String oldDepartmentID,
                                   @Field("newDepartmentID") String newDepartmentID);

    //--------------------------------------------------------企业设置--------------------------------------------------------


    @GET("api/Company/getCompanyScale")
        //公司规模
    Flowable<HttpResult<List<CompanyScaleModel>>> companyScale(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //企业信息设置
    @POST("api/Company/setCompanyInfo")
    Flowable<HttpResult> companyInfo(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("companyName") String companyName, @Field("shortName") String shortName,
                                     @Field("companyAddress") String companyAddress,
                                     @Query("latitude") String latitude, @Query("longitude") String longitude,
                                     @Field("companyWebsite") String companyWebsite,
                                     @Field("businessScope") String businessScope, @Field("scaleID") String scaleID);

    @FormUrlEncoded  //备注
    @POST("api/Company/setRemarkName")
    Flowable<HttpResult> remarks(@Query("token") String token, @Query("uid") String uid,
                                 @Field("remarkName") String remarkName, @Field("companyID") String companyID,
                                 @Field("staffUserID") String staffUID);

    @FormUrlEncoded  //删除管理员
    @POST("api/Company/delManager")
    Flowable<HttpResult> deleteManage(@Query("token") String token, @Query("uid") String uid,
                                      @Field("companyID") String companyID, @Query("managerID") String managerID);


    //--------------------------------------------------------项目--------------------------------------------------------
    @GET("api/Project/proDetail")
    //项目详情
    Flowable<HttpResult<ProjectDetailsModel>> xmDetails(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);


    @FormUrlEncoded  //同意/拒绝员工加入公司申请
    @POST("api/Company/doApply")
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

    @GET("api/Project/createQrCode")
        //生成项目二维码
    Flowable<HttpResult<ProjectCodeModel>> projectCode(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @GET("api/Project/getData")
        //获取项目工作台上的数字
    Flowable<HttpResult<ProjectStagingModel>> projectStaging(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @GET("api/Project/delPro")
        //删除项目
    Flowable<HttpResult> deleteProject(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                       @Query("proID") String proID);

    @FormUrlEncoded  //编辑售后工单
    @POST("api/after_sale/editAfterSaleOrder")
    Flowable<HttpResult> editWorkorder(@Query("token") String token, @Query("uid") String uid,
                                       @Field("orderNo") String orderNo, @Field("asType") String shtype,
                                       @Field("contactName") String contactName, @Field("contactPhone") String contactPhone,
                                       @Field("wishTime") String wishTime, @Field("content") String content,
                                       @Field("fileUrls") String fileId);

    @FormUrlEncoded  //取消工单
    @POST("api/after_sale/cancelAsOrder")
    Flowable<HttpResult> cancalAccept(@Query("token") String token, @Query("uid") String uid,
                                      @Field("orderNo") String orderNo, @Field("cancelNote") String cancelNote);

    @GET("api/after_sale/visitList")
        //查看回访
    Flowable<HttpResult<VisitRrcordModel>> visitRecord(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proId,
                                                       @Query("per") String per, @Query("page") Integer page);

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

    //------------------------------------------------------个人中心-------------------------------------------------
    @GET("api/user/getuser")
    //获取用户数据
    Flowable<HttpResult<UserResponse>> appUserInfo(@Query("token") String token, @Query("uid") String uid);

    @GET("api/Content/videoList")
    Flowable<HttpResult<Videos>> videoList(@Query("token") String token,
                                           @Query("uid") String uid,
                                           @Query("per") String per,
                                           @Query("page") Integer page, @Query("kw") String kw);

    //用户吐槽
    @FormUrlEncoded
    @POST("api/user/roast")
    Flowable<HttpResult> appFeedback(@Query("token") String token, @Query("uid") String uid, @Field("content") String content);

    @GET("api/user/myQrCode")
//为不同用户生成推广二维码
    Flowable<HttpResult> myPromotion(@Query("token") String token, @Query("uid") String uid, @Query("phone") String phone);

    @Multipart  //上传照片
    @POST("api/users/uploadHead")
    Call<UserUploadModel> uploadMemberIcon(@Query("token") String token, @Query("uid") String uid, @Part List<MultipartBody.Part> partList);

    @POST("api/User/getMsg")
    Flowable<HttpResult<MsgMainModel>> getMsg(@Query("token") String token,
                                              @Query("uid") String uid);

    //项目消息/系统消息/团队消息列表页  1 项目消息 2团队消息 3系统消息
    @POST("api/User/getMsgList")
    Flowable<HttpResult<MsgTdxxModel>> getMsgList(@Query("token") String token,
                                                  @Query("uid") String uid,
                                                  @Query("type") String type,
                                                  @Query("per") String per,
                                                  @Query("page") Integer page);

    @POST("api/User/setRead")
    Flowable<HttpResult> setRead(@Query("token") String token,
                                 @Query("uid") String uid,
                                 @Query("msgID") String msgID);

    @POST("api/User/delMsg")
    Flowable<HttpResult> delMsg(@Query("token") String token,
                                @Query("uid") String uid,
                                @Query("msgID") String msgID);

    @POST("api/Discover/addComment")
    Flowable<HttpResult> addComment(@Query("token") String token,
                                    @Query("uid") String uid,
                                    @Query("rowNum") String rowNum,
                                    @Query("comment") String comment);

    @GET("api/Discover/commentsList")
    Flowable<HttpResult<CommentBean>> commentsList(@Query("token") String token,
                                                   @Query("uid") String uid,
                                                   @Query("rowNum") String rowNum);

    @POST("api/Discover/addhits")
    Flowable<HttpResult> addhits(@Query("token") String token,
                                 @Query("uid") String uid,
                                 @Query("rowNum") String rowNum);

    //------------------------------------------------------支付-------------------------------------------------
    @GET("api/Company/getCompanyInfo")
    //公司信息
    Flowable<HttpResult<CompanyInfoModel>> getCompany(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID);

    @GET("api/Finance/rechargeCombo")
        //充值套餐
    Flowable<HttpResult<List<RechargeComboModel>>> rechargeCombo(@Query("token") String token, @Query("uid") String uid,
                                                                 @Query("companyID") String companyID);

    @GET("api/Finance/levelRights")
        //会员权益
    Flowable<HttpResult<List<LevelRightsModel>>> getLevelRights(@Query("token") String token, @Query("uid") String uid);

    @FormUrlEncoded  //购买版块扣砖
    @POST("api/Finance/buyModule")
    Flowable<HttpResult> openBk(@Query("token") String token, @Query("uid") String uid, @Field("proID") String proId,
                                @Field("moduleType") String moduleType, @Field("brickNum") String brickNum);

    @GET("api/Finance/financialLogUser")
        //个人财务记录 type 0全部，1收入，2支出
    Flowable<HttpResult<BrickRecordModel>> brickRecord(@Query("token") String token, @Query("uid") String uid, @Query("type") String type,
                                                       @Query("per") String per, @Query("page") Integer page);

    @GET("api/Finance/financialLogCompany")
        //公司财务记录 type 0全部，1收入，2支出
    Flowable<HttpResult<TransactionRecordModel>> transactionRecord(@Query("token") String token, @Query("uid") String uid,
                                                                   @Query("companyID") String companyID,
                                                                   @Query("type") String type, @Query("per") String per, @Query("page") Integer page);

    @FormUrlEncoded
    @POST("api/Finance/createOrder")
    Flowable<HttpResult<AppPaymentModel>> appAliPayment(@Query("token") String token, @Query("uid") String uid,
                                                        @Field("comboID") String comboID, @Field("payType") String payType,
                                                        @Field("companyID") String companyID);

    @FormUrlEncoded
    @POST("api/Finance/createOrder")
    Flowable<HttpResult<AppWXPaymentModel>> appWXPayment(@Query("token") String token, @Query("uid") String uid,
                                                         @Field("comboID") String comboID, @Field("payType") String payType,
                                                         @Field("companyID") String companyID);

    //------------------------------------------------------支付-------------------------------------------------

    @POST("api/Customer/addCustomer")
//添加客户
    Flowable<HttpResult> buildCustomer(@Query("token") String token, @Query("uid") String uid, @Body AddCustomerRequest request);

    @GET("api/Customer/getAllSelections")
        //获取跟进状态、客户等级、客户来源、沟通方式接口
    Flowable<HttpResult<SelectModel>> getSelect(@Query("token") String token, @Query("uid") String uid,
                                                @Query("companyID") String companyID);

    @GET("api/Customer/getCompanyCustomers")
        //获取公司客户列表
    Flowable<HttpResult<WholeCustomerModel>> wholeCustomer(@Query("token") String token, @Query("uid") String uid,
                                                           @Query("companyID") String companyID,
                                                           @Query("per") String per, @Query("page") Integer page,
                                                           @Query("keywords") String keywords, @Query("customerLevelID") String customerLevelID,
                                                           @Query("followStatusID") String followStatusID, @Query("hasFollowRecords") String hasFollowRecords);

    @FormUrlEncoded  //删除公司客户
    @POST("api/Customer/delCompanyCustomer")
    Flowable<HttpResult> delCustomer(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("customerID") String customerID);

    @GET("api/Customer/getCompanyCustomerInfo")
        //获取公司/我的客户详情接口（客户基本信息，项目信息）
    Flowable<HttpResult<DetailsModel>> customerDetails(@Query("token") String token, @Query("uid") String uid,
                                                       @Query("companyID") String companyID, @Query("companyCustomerID") String companyCustomerID);

    @FormUrlEncoded  //编辑客户信息接口
    @POST("api/Customer/editCompanyCustomerInfo")
    Flowable<HttpResult> editInfo(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("companyCustomerID") String companyCustomerID,
                                  @Field("customerName") String customerName, @Field("customerPhone") String customerPhone,
                                  @Field("followStatusID") String followStatusID, @Field("customerLevelID") String customerLevelID,
                                  @Field("customerSourceID") String customerSourceID, @Field("remark") String remark,
                                  @Field("salesPersonUserID") String salesPersonUserID);

    @FormUrlEncoded  //添加关联项目
    @POST("api/Customer/addRelatedPro")
    Flowable<HttpResult> guanliPro(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                   @Field("proID") String proID,
                                   @Field("customerID") String customerUserID);

    @GET("api/Customer/getFollowRecords")
//跟进记录列表接口
    Flowable<HttpResult<List<FollowProModel>>> followPro(@Query("token") String token, @Query("uid") String uid,
                                                         @Query("companyID") String companyID, @Query("customerID") String customerID);


    @POST("api/Customer/addFollowRecord")
        //新增跟进记录（跟进状态、沟通方式、日期、内容、图片）
    Flowable<HttpResult> addFollow(@Query("token") String token, @Query("uid") String uid,
                                   @Body AddFollowRecordRequest request);

    @GET("api/Customer/taskList")
//我的任务列表
    Flowable<HttpResult<MyTaskModel>> myTask(@Query("token") String token, @Query("uid") String uid,
                                             @Query("companyID") String companyID, @Query("per") String per, @Query("page") Integer page);

    @FormUrlEncoded  //新建任务
    @POST("api/Customer/addTask")
    Flowable<HttpResult> addTask(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                 @Field("customerID") String customerID,
                                 @Field("taskTime") String taskTime, @Field("noticeTime") String noticeTime,
                                 @Field("remark") String remark);

    @FormUrlEncoded  //删除任务
    @POST("api/Customer/delTask")
    Flowable<HttpResult> delTask(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                 @Field("taskID") String taskID);

    @FormUrlEncoded  //编辑任务
    @POST("api/Customer/editTask")
    Flowable<HttpResult> editTask(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("taskID") String taskID,
                                  @Field("taskTime") String taskTime, @Field("noticeTime") String noticeTime,
                                  @Field("remark") String remark);

    @GET("api/Customer/getMyCustomers")
//获取我的客户列表
    Flowable<HttpResult<WholeCustomerModel>> myCustomer(@Query("token") String token, @Query("uid") String uid,
                                                        @Query("companyID") String companyID,
                                                        @Query("per") String per, @Query("page") Integer page,
                                                        @Query("keywords") String keywords, @Query("customerLevelID") String customerLevelID,
                                                        @Query("followStatusID") String followStatusID,
                                                        @Query("hasFollowRecords") String hasFollowRecords);

    @FormUrlEncoded  //放弃跟进客户接口
    @POST("api/Customer/quitFollow")
    Flowable<HttpResult> fangCustomer(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                      @Field("companyCustomerID") String companyCustomerID);

    @GET("api/Customer/myShareRecords")
//我分享客户的记录
    Flowable<HttpResult<MyShareModel>> myShare(@Query("token") String token, @Query("uid") String uid,
                                               @Query("companyID") String companyID,
                                               @Query("per") String per, @Query("page") Integer page);

    @FormUrlEncoded  //删除我的分享记录
    @POST("api/Customer/delMyShareRecord")
    Flowable<HttpResult> delMyShare(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                    @Field("shareRecordID") String shareRecordID);

    @FormUrlEncoded  //新建分享客户接口
    @POST("api/Customer/shareCustomers")
    Flowable<HttpResult> addShare(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                  @Field("customers") String customers, @Field("toCompany") String toCompany);

    @GET("api/Customer/myReceivedShares")
//别人分享给我的客户接口
    Flowable<HttpResult<MyShareModel>> myShnew(@Query("token") String token, @Query("uid") String uid,
                                               @Query("companyID") String companyID,
                                               @Query("shareRecordID") String shareRecordID);

    @FormUrlEncoded  //选择接受哪些分享的客户接口
    @POST("api/Customer/chooseCustomers")
    Flowable<HttpResult> setShareSel(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                     @Field("shareRecordID") String shareRecordID, @Field("customers") String customers);

    @GET("api/Customer/searchCompanies")
//搜索公司接口
    Flowable<HttpResult<List<SearchComModel>>> searchCom(@Query("token") String token, @Query("uid") String uid,
                                                         @Query("keywords") String keywords);


    //user
    @POST("api/user/appRegister")
    Flowable<HttpResult> appRegister(@Body RegisterRequest request);

    @POST("api/user/appLogin")
    Flowable<HttpResult<UserResponse>> appLogin(@Body LoginRequest request);

    @POST("api/user/forgetPwd")
    Flowable<HttpResult> forgetPwd(@Body ForgetRequest forgetRequest);

    @GET("api/user/getSmsCode")
    Flowable<HttpResult<String>> getSmsCode(@Query("phone") String phone, @Query("type") String type);

    @POST("api/user/changePwd")
    Flowable<HttpResult> changePwd(@Query("token") String token, @Query("uid") String uid, @Body ChangePwdRequest request);

    @GET("api/User/myEngineerList")
    Flowable<HttpResult<List<EnginerListModel>>> myEngineerList(@Query("token") String token, @Query("uid") String uid);

    @GET("api/User/searchEngineer")
    Flowable<HttpResult<List<SelectEnginerModel>>> searchEngineer(@Query("token") String token, @Query("uid") String uid,
                                                                  @Query("phone") String phone);

    @GET("api/user/addEngineer")
    Flowable<HttpResult> addEngineer(@Query("token") String token, @Query("uid") String uid,
                                     @Query("engineerUserID") String enginerID, @Query("remark") String remark);

    @FormUrlEncoded
    @POST("api/user/delEngineer")
    Flowable<HttpResult> delEngineer(@Query("token") String token, @Query("uid") String uid, @Field("engineerUserID") String enginerID);

    @FormUrlEncoded
    @POST("api/user/uploadAvatar")
    Flowable<HttpResult> uploadAvatar(@Query("token") String token, @Query("uid") String uid, @Field("userAvatar") String userAvatar);

    @FormUrlEncoded
    @POST("api/user/setNickname")
    Flowable<HttpResult> setNickname(@Query("token") String token, @Query("uid") String uid,
                                     @Field("nickName") String userName);

    @FormUrlEncoded
    @POST("api/user/changePhone")
    Flowable<HttpResult> appModifyPhone(@Query("token") String token,
                                        @Query("uid") String uid,
                                        @Field("userPhone") String userPhone,
                                        @Field("smscode") String smscode);

    @GET("api/User/getBusinessCard")
    Flowable<HttpResult<BusinessCardResponse>> getBusinessCard(@Query("userID") String userId);

    @POST("api/User/editBusinessCard")
    Flowable<HttpResult> editBusinessCard(@Query("token") String token, @Query("uid") String uid, @Body EditBusinessCardRequest request);


    @GET("pss/User/getCompanyList")
    Flowable<HttpResult<List<SelectCompanyModel>>> userCompanyList(@Query("userPhone") String userPhone);

    /*project*/
    @GET("api/Project/proList")
    Flowable<HttpResult<XiangmuModel>> getProjectList(@QueryMap Map<String, Object> map);

    @GET("api/Project/proMemberList")
    Flowable<HttpResult<StagingListModel>> getProMemberList(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proID);

    @POST("api/Project/delProMember")
    Flowable<HttpResult> deleteProMember(@Query("token") String token, @Query("uid") String uid, @Body ProMemberRequest request);

    @POST("api/Project/addProMember")
    Flowable<HttpResult> addProMember(@Query("token") String token, @Query("uid") String uid, @Body ProMemberRequest request);

    //加入项目审核接口
    @GET("api/Project/doApplyCheck")
    Flowable<HttpResult> doApplyCheck(@Query("token") String token, @Query("uid") String uid, @Query("applyID") String applyID,
                                      @Query("status") String status, @Query("checkRemark") String checkRemark);

    @GET("api/Company/appliesList")
        //员工申请列表
    Flowable<HttpResult<TeamApplyModel>> teamApply(@Query("token") String token, @Query("uid") String uid,
                                                   @Query("per") String per, @Query("page") Integer page);


    @GET("api/Project/appliesList")
//加入项目申请列表接口
    Flowable<HttpResult<ProjectNewsModel>> appliesList(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                                       @Query("proID") String proID, @Query("type") int tyep, @Query("per") int per, @Query("page") int page);

    //项目信息编辑
    @POST("api/Project/editPro")
    Flowable<HttpResult> editProject(@Query("token") String token, @Query("uid") String uid, @Query("companyID") String companyID,
                                     @Body EditProjectRequest request);

    /*Company*/
    @GET("api/Company/getCompanyInfo")
    Flowable<HttpResult<TeamManageModel>> getCompanyInfo(@QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("api/Company/generateCompanyQRCode")
//生成公司邀请二维码
    Flowable<HttpResult<ShareCodeModel>> shareCode(@Query("token") String token, @Query("uid") String uid,
                                                   @Field("companyID") String companyID);

    @GET("api/Company/getDepartments")
//架构列表
    Flowable<HttpResult<ZuzhiJiagouModel>> getDepartments(@Query("token") String token, @Query("uid") String uid,
                                                          @Query("companyID") String companyID);

    @POST("api/Company/addDepartment")
    Flowable<HttpResult> addDepartment(@Query("token") String token, @Query("uid") String uid, @Body AddDepartmentRequest request);

    @GET("api/Company/managerList")
    Flowable<HttpResult<List<ManageSettingModel>>> managerList(@Query("token") String token, @Query("uid") String uid,
                                                               @Query("companyID") String companyID);

    @FormUrlEncoded
    @POST("api/Company/updateManager")
    Flowable<HttpResult> updateManager(@Query("token") String token, @Query("uid") String uid,
                                       @Field("companyID") String companyID, @Field("managerID") String userID,
                                       @Field("proCreateAuth") int authCreate,
                                       @Field("adminCreateAuth") int authData,
                                       @Field("rechargeAuth") int rechargeAuth, @Field("crmAuth") int crmAuth);

    @FormUrlEncoded  //添加管理员
    @POST("api/Company/addManager")
    Flowable<HttpResult> addManager(@Query("token") String token, @Query("uid") String uid,
                                    @Field("companyID") String companyID, @Field("managerID") String managerID,
                                    @Field("managerRoleID") String managerRoleID,
                                    @Field("departIDs") String departIDs, @Field("proCreateAuth") String authCreate,
                                    @Field("authData") String authData, @Field("authManage") String authManage,
                                    @Field("rechargeAuth") String rechargeAuth, @Field("crmAuth") String crmAuth);


    @FormUrlEncoded  //扫码申请加入公司
    @POST("api/Company/joinWithQrCode")
    Flowable<HttpResult> joinWithQrCode(@Query("token") String token, @Query("uid") String uid,
                                        @Field("companyID") String companyID, @Field("staffName") String staffName,
                                        @Field("remark") String reason);

    @FormUrlEncoded  //修改当前部门名称
    @POST("api/Company/editDepartment")
    Flowable<HttpResult> modifyBmName(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID,
                                      @Field("departmentID") String departmentID, @Field("departmentName") String departName);


    @FormUrlEncoded  //修改当前部门名称
    @POST("api/Company/uploadLogo")
    Flowable<HttpResult> uploadLogo(@Query("token") String token, @Query("uid") String uid, @Field("companyID") String companyID, @Field("logoUrl") String logoUrl);


    //售后
    @GET("api/after_sale/afterSaleOrderList")
    Flowable<HttpResult<WorkorderListModel>> afterSaleOrderList(@QueryMap Map<String, Object> map);


    @POST("api/after_sale/createAfterSaleOrder")
    Flowable<HttpResult> createAfterSaleOrder(@Query("token") String token, @Query("uid") String uid,
                                              @Body CreateAfterSaleOrderRequest request);

    @GET("api/after_sale/lookAsOrder")
//查看工单信息
    Flowable<HttpResult<GongdanShuxingModel>> lookAsOrder(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    @GET("api/after_sale/lookAsOrderProcess")
//查看处理过程
    Flowable<HttpResult<List<ChuliGuochengModel>>> lookAsOrderProcess(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    @GET("api/after_sale/asServicePeopleList")
    Flowable<HttpResult<List<CustomListModel>>> asServicePeopleList(@Query("token") String token, @Query("uid") String uid,
                                                                    @Query("companyID") String companyID);

    @POST("api/after_sale/addAsServicePeople")
//售后客服人员设置
    Flowable<HttpResult> addCustom(@Query("token") String token, @Query("uid") String uid, @Body AddCustomRequest request);

    @FormUrlEncoded
    @POST("api/after_sale/delAsServicePeople")
//公司删除售后人员
    Flowable<HttpResult> delAsServicePeople(@Query("token") String token, @Query("uid") String uid, @Field("kfID") String kfID);

    @POST("api/after_sale/editAsServicePeople")
//公司修改售后客服人员
    Flowable<HttpResult> editAsServicePeople(@Query("token") String token, @Query("uid") String uid, @Body EditAsServicePeopleRequest request);

    @GET("api/after_sale/getAsServicePeopleTypeList")
//售后客服类型列表
    Flowable<HttpResult<List<CustomTypeModel>>> getAsServicePeopleTypeList(@Query("token") String token, @Query("uid") String uid);

    @POST("api/after_sale/acceptAsOrder")
//受理工单 isAccept 1受理2不受理
    Flowable<HttpResult> acceptAsOrder(@Query("token") String token, @Query("uid") String uid,
                                       @Body AcceptAsOrderRequest request);

    @POST("api/after_sale/sendAsOrder")
    Flowable<HttpResult> sendAsOrder(@Query("token") String token, @Query("uid") String uid, @Body SendAsOrderRequest request);

    @POST("api/after_sale/reSendAsOrder")
    Flowable<HttpResult> reSendAsOrder(@Query("token") String token, @Query("uid") String uid, @Body ReSendAsOrderRequest request);

    @GET("api/after_sale/lookAsSet")
//查看售后设置
    Flowable<HttpResult<SettingInfoModel>> lookAsSet(@Query("token") String token, @Query("uid") String uid, @Query("proID") String proId);

    @POST("api/after_sale/asSet")
    Flowable<HttpResult> asSet(@Query("token") String token, @Query("uid") String uid, @Body AsSetRequest request);

    @FormUrlEncoded
    @POST("api/after_sale/addVisit")
    Flowable<HttpResult> addVisit(@Query("token") String token, @Query("uid") String uid,
                                  @Field("proID") String proId, @Field("title") String title, @Field("nowDate") String nowDate,
                                  @Field("content") String content, @Field("nextDate") String nextDate);

    @GET("api/after_sale/lookAsOrderComment")
//查看评价
    Flowable<HttpResult<CheckAppraiseModel>> lookAsOrderComment(@Query("token") String token, @Query("uid") String uid, @Query("orderNo") String orderNo);

    @GET("api/after_sale/lookReceipt")
//查看回单信息
    Flowable<HttpResult<ClzGongdanDetailModel>> lookReceipt(@Query("token") String token, @Query("uid") String uid,
                                                            @Query("receiptID") String receiptId);

    @FormUrlEncoded
    @POST("api/after_sale/delAsOrder")
    Flowable<HttpResult> delAsOrder(@Query("token") String token, @Query("uid") String uid,
                                    @Field("orderNo") String orderNo, @Field("delNote") String delNote);

    //content
    @GET("api/Content/newsList")
    Flowable<HttpResult<News>> newsList(@Query("token") String token,
                                        @Query("uid") String uid, @Query("per") String per,
                                        @Query("page") Integer page, @Query("kw") String kw);


    /*  Finance*/
    @POST("api/Finance/buyModule")
    Flowable<HttpResult> buyModule(@Query("token") String token, @Query("uid") String uid, @Field("proID") String proId,
                                   @Field("moduleType") String moduleType, @Field("brickNum") String brickNum);

    //进销存
    @GET("pss/Setting/goodsCategoryList")
    Flowable<HttpResult<List<GoodsCategoryResponse>>> getGoodsCategoryList(@Query("token") String token,
                                                                           @Query("uid") String uid, @Query("shopID") String shopID);

    @GET("pss/Setting/goodsList")
    Flowable<HttpResult<GoodsListResponse>> goodsList(@Query("token") String token, @Query("uid") String uid,
                                                      @Query("shopID") String shopID, @Query("kw") String kw,
                                                      @Query("goodsCatID") String goodsCatID, @Query("isPersonalStoreGoods") int isPersonalStoreGoods
            ,@Query("per") Integer per, @Query("page") Integer page);

    @GET("pss/Setting/goodsInfo")
    Flowable<HttpResult<GoodsDetailResponse>> getGoodsInfo(@Query("token") String token, @Query("uid") String uid,
                                                           @Query("shopID") String shopID, @Query("goodsID") String goodsID);


    @POST("pss/Setting/addGoods")
    Flowable<HttpResult> addGoods(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                  @Body CommodityRequest request);

    @POST("pss/Setting/editGoods")
    Flowable<HttpResult> editGoods(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                   @Body CommodityRequest request);

    @FormUrlEncoded
    @POST("pss/Setting/delGoods")
    Flowable<HttpResult> delGoods(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                   @Field("goodsID") String goodsID);

    @FormUrlEncoded
    @POST("pss/Setting/addGoodsCategory")
    Flowable<HttpResult> addGoodsCategory(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                          @Field("catName") String catName, @Field("pID") String pID);

    @FormUrlEncoded
    @POST("pss/Setting/editGoodsCategory")
    Flowable<HttpResult> editGoodsCategory(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                           @Field("catName") String catName, @Field("pID") String pID, @Field("catID") String catID);

    @FormUrlEncoded
    @POST("pss/Setting/delGoodsCategory")
    Flowable<HttpResult> delGoodsCategory(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                          @Field("catID") String catID);

    @GET("pss/Setting/goodsUnitList")
    Flowable<HttpResult<List<GoodsUnitResponse>>> getGoodsUnitList(@Query("token") String token,
                                                                   @Query("uid") String uid, @Query("shopID") String shopID);

    @FormUrlEncoded
    @POST("pss/Setting/delGoodsUnit")
    Flowable<HttpResult> deleteGoodsUnit(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                         @Field("goodsUnitID") String goodsUnitID);

    @FormUrlEncoded
    @POST("pss/Setting/addGoodsUnit")
    Flowable<HttpResult> addGoodsUnit(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                      @Field("unitName") String unitName);


    @FormUrlEncoded
    @POST("pss/Setting/editGoodsUnit")
    Flowable<HttpResult> editGoodsUnit(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                       @Field("goodsUnitID") String goodsUnitID, @Field("unitName") String unitName);

    @GET("pss/Setting/otherCostTypeList")
    Flowable<HttpResult<List<OtherCostResponse>>> getOtherCostList(@Query("token") String token,
                                                                   @Query("uid") String uid, @Query("shopID") String shopID);

    @FormUrlEncoded
    @POST("pss/Setting/editOtherCostType")
    Flowable<HttpResult> editOtherCost(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                       @Field("otherCostTypeName") String otherCostTypeName, @Field("otherCostTypeID") String otherCostTypeID);

    @FormUrlEncoded
    @POST("pss/Setting/delOtherCostType")
    Flowable<HttpResult> deleteOtherCost(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                         @Field("otherCostTypeID") String otherCostTypeID);

    @FormUrlEncoded
    @POST("pss/Setting/addOtherCostType")
    Flowable<HttpResult> addOtherCost(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                      @Field("otherCostTypeName") String otherCostTypeName);


    @GET("pss/Setting/incomeExpenseTypeList")
    Flowable<HttpResult<List<IncomeExpenseTypeResponse>>> getIncomeExpenseTypeListt(@Query("token") String token,
                                                                                    @Query("uid") String uid, @Query("shopID") String shopID, @Query("type") int type);

    @FormUrlEncoded
    @POST("pss/Setting/editIncomeExpenseType")
    Flowable<HttpResult> editIncomeExpenseType(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                               @Field("typeID") String typeID, @Field("type") int type, @Field("typeName") String typeName);

    @FormUrlEncoded
    @POST("pss/Setting/delIncomeExpenseType")
    Flowable<HttpResult> delIncomeExpenseType(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                              @Field("typeID") String typeID);

    @FormUrlEncoded
    @POST("pss/Setting/addIncomeExpenseType")
    Flowable<HttpResult> addIncomeExpenseType(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                              @Field("type") int type, @Field("typeName") String typeName);

    @GET("pss/Setting/storeListWithMy")
    Flowable<HttpResult<List<MyStoreResponse>>> storeListWithMy(@Query("token") String token,
                                                                @Query("uid") String uid, @Query("shopID") String shopID, @Query("storeType") int storeType);

    @GET("pss/Setting/storeList")
    Flowable<HttpResult<List<StoreResponse>>> getStoreList(@Query("token") String token,
                                                           @Query("uid") String uid, @Query("shopID") String shopID);

    @POST("pss/Setting/editStore")
    Flowable<HttpResult> editStore(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body StoreResponse request);

    @FormUrlEncoded
    @POST("pss/Setting/delStore")
    Flowable<HttpResult> delStore(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("storeID") String storeID);

    @FormUrlEncoded
    @POST("pss/Setting/addStore")
    Flowable<HttpResult> addStore(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                  @Field("storeName") String storeName, @Field("chargePersonUserID") String chargePersonUserID,
                                  @Field("contactPhone") String contactPhone, @Field("storeAddress") String storeAddress);

    @GET("pss/Setting/customerInfo")
    Flowable<HttpResult<CustomerDetailResponse>> customerInfo(@Query("token") String token, @Query("uid") String uid,
                                                              @Query("shopID") String shopID, @Query("companyCustomerID") String companyCustomerID);


    @GET("pss/Setting/supplierList")
    Flowable<HttpResult<SupplierListResponse>> supplierList(@Query("token") String token, @Query("uid") String uid,
                                                            @Query("shopID") String shopID, @Query("kw") String kw,
                                                            @Query("per") String per, @Query("page") Integer page);

    @POST("pss/Setting/editSupplier")
    Flowable<HttpResult> editSupplier(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                      @Body SupplierRequest request);

    @FormUrlEncoded
    @POST("pss/Setting/delSupplier")
    Flowable<HttpResult> delSupplier(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("supplierID") String supplierID);

    @FormUrlEncoded
    @POST("pss/Setting/addSupplier")
    Flowable<HttpResult> addSupplier(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                     @Field("supplierName") String supplierName, @Field("chargePerson") String chargePerson,
                                     @Field("chargePersonPhone") String chargePersonPhone, @Field("contactAddress") String contactAddress);

    @GET("pss/Setting/taxRateList")
    Flowable<HttpResult<List<RateResponse>>> taxRateList(@Query("token") String token, @Query("uid") String uid,
                                                         @Query("shopID") String shopID);

    @GET("pss/Setting/moneyAccountList")
    Flowable<HttpResult<MoneyAccountListResponse>> moneyAccountList(@Query("token") String token, @Query("uid") String uid,
                                                                    @Query("shopID") String shopID, @Query("mapShopID") String mapShopID);


    @GET("pss/Setting/customerList")
    Flowable<HttpResult<CustomerListResponse>> getSetCustomerList(@Query("token") String token, @Query("uid") String uid,
                                                                  @Query("shopID") String shopID, @Query("keywords") String keywords,
                                                                  @Query("per") String per, @Query("page") Integer page);

    @POST("pss/Setting/addCustomer")
    Flowable<HttpResult> addSetCustomer(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                        @Body AddSetCustomerRequest request);

    @POST("pss/Setting/editCustomer")
    Flowable<HttpResult> editSetCustomer(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                         @Body AddSetCustomerRequest request);

    @FormUrlEncoded
    @POST("pss/Setting/delCustomer")
    Flowable<HttpResult> delSetCustomer(@Query("token") String token, @Query("uid") String uid,
                                        @Query("shopID") String shopID, @Field("companyCustomerID") String companyCustomerID);

    @POST("pss/Setting/addMoneyAccount")
    Flowable<HttpResult> addMoneyAccount(@Query("token") String token, @Query("uid") String uid,
                                         @Query("shopID") String shopID, @Body AddMoneyAccountRequest request);

    @FormUrlEncoded
    @POST("pss/Setting/delMoneyAccount")
    Flowable<HttpResult> delMoneyAccount(@Query("token") String token, @Query("uid") String uid,
                                         @Query("shopID") String shopID, @Field("accountID") String accountID);


    //门店
    @GET("pss/Statistics/getShopData")
    Flowable<HttpResult<ShopDataResponse>> getShopData(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("mapShopID") String mapShopID);

    @GET("pss/Shop/shopList")
    Flowable<HttpResult<List<ShopListResponse>>> shopList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    @FormUrlEncoded
    @POST("pss/Shop/addShop")
    Flowable<HttpResult> addShop(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                 @Field("shopName") String shopName, @Field("chargePersonUserID") String chargePersonUserID,
                                 @Field("contactPhone") String contactPhone, @Field("shopAddress") String shopAddress,
                                 @Field("remark") String remark);

    @GET("pss/Setting/shopUserList")
    Flowable<HttpResult<ShopUserListResponse>> getShopUserList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    @GET("pss/Setting/getUserAuth")
    Flowable<HttpResult<UserAuthResponse>> getUserAuth(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("userID") String userID);

    @FormUrlEncoded
    @POST("pss/Setting/shopUserAdd")
    Flowable<HttpResult> shopUserAdd(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("userID") String userID);

    @POST("pss/Setting/setUserAuth")
    Flowable<HttpResult> setUserAuth(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body SetUserAuthRequest request);

    @GET("pss/Setting/getShopCheckerList")
    Flowable<HttpResult<List<ShopCheckerResponse>>> getShopCheckerList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    @FormUrlEncoded
    @POST("pss/Setting/setShopChecker")
    Flowable<HttpResult> setShopChecker(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("data_id") String data_id,@Field("checkPersonUserID") String checkPersonUserID);


    //销售
    @GET("pss/Sale/orderOfferList")
    Flowable<HttpResult<OrderOfferListResponse>> orderOfferList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    @GET("pss/Sale/orderOfferInfo")
    Flowable<HttpResult<OrderOfferListResponse.OrderOfferResponse>> orderOfferInfo(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,@Query("orderID") String orderID);

    @FormUrlEncoded
    @POST("pss/Sale/orderOfferDel")
    Flowable<HttpResult> orderOfferDel(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("orderID") String orderID);

    @POST("pss/Sale/orderOfferAdd")
    Flowable<HttpResult> addOrderOffer(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderOfferRequest request);

    @POST("pss/Sale/orderSalesAdd")
    Flowable<HttpResult> addOrderSales(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderSalesRequest request);

    @POST("pss/Sale/orderRetailAdd")
    Flowable<HttpResult> addOrderRetail(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderSalesRequest request);

    @POST("pss/Sale/orderSalesBackAdd")
    Flowable<HttpResult> addOrderSalesBack(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderSalesBackRequest request);

    @POST("pss/Sale/orderRetailBackAdd")
    Flowable<HttpResult> addOrderRetailBack(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderSalesBackRequest request);

    @POST("pss/Sale/orderTakeGoodsAdd")
    Flowable<HttpResult> addOrderTakeGoods(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderTakeGoodsRequest request);

    @POST("pss/Sale/orderTakeGoodsBackAdd")
    Flowable<HttpResult> addOrderTakeGoodsBack(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderTakeGoodsBackRequest request);


    @GET("pss/Sale/orderSalesList")
    Flowable<HttpResult<OrderSalesListResponse>> orderSalesList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                                                @Query("isCheck") int isCheck, @Query("isGathering") int isGathering, @Query("isOutStore") int isOutStore, @Query("isCanceled") int isCanceled);

    @GET("pss/Sale/orderRetailList")
    Flowable<HttpResult<OrderSalesListResponse>> orderRetailList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("storeType") int storeType);

    @GET("pss/Sale/orderTakeGoodsList")
    Flowable<HttpResult<OrderSalesListResponse>> orderTakeGoodsList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                                                    @Query("isCheck") int isCheck, @Query("isOutStore") int isOutStore, @Query("isCanceled") int isCanceled);

    @GET("pss/Sale/personalStoreList")
    Flowable<HttpResult<PersonalStoreListResponse>> personalStoreList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    //仓库
    @POST("pss/Store/orderOtherInAdd")
    Flowable<HttpResult> addOrderOtherIn(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderOtherInRequest request);

    @POST("pss/Store/orderOtherOutAdd")
    Flowable<HttpResult> addOrderOtherOut(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderSalesRequest request);

    @POST("pss/Store/orderAllotAdd")
    Flowable<HttpResult> addOrderAllot(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderAllotAddRequest request);

    @GET("pss/Store/stockLogList")
    Flowable<HttpResult<StockLogListResponse>> stockLogList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                                            @Query("keywords") String keywords, @Query("startTime") String startTime,
                                                            @Query("endTime") String endTime, @Query("storeID") String storeID, @Query("inOrOut") String inOrOut,
                                                            @Query("inOrOutType") String inOrOutType, @Query("per") int per, @Query("storeType") String storeType);

    @GET("pss/Store/stockWarning")
    Flowable<HttpResult<StockWarningResponse>> stockWarning(@Query("token") String token, @Query("uid") String uid,
                                                            @Query("shopID") String shopID, @Query("kw") String kw, @Query("storeID") String storeID);

    @FormUrlEncoded
    @POST("pss/Store/notifyBuy")
    Flowable<HttpResult> notifyBuy(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                             @Field("data_id") String data_id);


    @GET("pss/Store/goodsStock")
    Flowable<HttpResult<GoodsStockResponse>> goodsStock(@Query("token") String token, @Query("uid") String uid,
                                                        @Query("shopID") String shopID, @Query("kw") String kw);

    @GET("pss/Store/orderStockCheckList")
    Flowable<HttpResult<OrderStockCheckListResponse>> orderStockCheckList(@Query("token") String token, @Query("uid") String uid,
                                                                          @Query("shopID") String shopID, @Query("keywords") String keywords, @Query("storeID") String storeID, @Query("status") int status);

    @POST("pss/Store/orderStockCheckEdit")
    Flowable<HttpResult> orderStockCheckEdit(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                             @Body OrderStockCheckRequest request);

    @FormUrlEncoded
    @POST("pss/Store/orderStockCheckDelBatch")
    Flowable<HttpResult> orderStockCheckDelBatch(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("orderIDs") String orderIDs);

    @POST("pss/Store/orderStockCheckAdd")
    Flowable<HttpResult> orderStockCheckAdd(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                            @Body OrderStockCheckRequest request);

    @GET("pss/Store/orderOtherInOutList")
    Flowable<HttpResult<OrderOtherInOutListResponse>> orderOtherInOutList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                                                          @Query("keywords") String keywords, @Query("startTime") String startTime,
                                                                          @Query("endTime") String endTime, @Query("orderType") String orderType);


    @GET("pss/Store/orderAllotList")
    Flowable<HttpResult<OrderAllotListResponse>> orderAllotList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                                                @Query("isCheck") String isCheck, @Query("keywords") String keywords, @Query("startTime") String startTime,
                                                                @Query("endTime") String endTime, @Query("fromStoreID") String fromStoreID, @Query("toStoreID") String toStoreID,
                                                                @Query("status") String status, @Query("exportExcel") String exportExcel);

    //其他出入库单--详情
    @GET("pss/Store/orderOtherInOutInfo")
    Flowable<HttpResult<OtherWaitingInOutDetailResponse>> orderOtherInOutInfo(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("orderID") String orderID);

    //其他出库单--确认出库按钮
    @FormUrlEncoded
    @POST("pss/Store/orderOtherOutDoOutAction")
    Flowable<HttpResult> orderOtherOutDoOutAction(@Query("token") String token, @Query("uid") String uid,
                                           @Query("shopID") String shopID, @Field("orderID") String storeID);


    //全库盘点--列表
    @GET("pss/Store/storeAllCheckList")
    Flowable<HttpResult<StoreAllCheckListResponse>> storeAllCheckList(@Query("token") String token, @Query("uid") String uid,
                                                                      @Query("shopID") String shopID, @Query("exportExcel") int exportExcel);

    @FormUrlEncoded
    @POST("pss/Store/storeAllCheckSponsor")
    Flowable<HttpResult> storeAllCheckSponsor(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("storeID") String storeID,
                                              @Field("beginDate") String beginDate, @Field("endDate") String endDate);

    @FormUrlEncoded
    @POST("pss/Store/storeAllCheckDone")
    Flowable<HttpResult> storeAllCheckDone(@Query("token") String token, @Query("uid") String uid,
                                           @Query("shopID") String shopID, @Field("orderID") String storeID);

    //待入库列表
    @GET("pss/Store/waitingInList")
    Flowable<HttpResult<WaitingInOutListResponse>> waitingInList(@Query("token") String token, @Query("uid") String uid,
                                                                     @Query("shopID") String shopID, @Query("orderType") int orderType);

    //待入库列表--入库商品明细
    @GET("pss/Store/waitingInDetail")
    Flowable<HttpResult<List<WaitingInDetailResponse>>> waitingInDetail(@Query("token") String token, @Query("uid") String uid,
                                                                        @Query("shopID") String shopID, @Query("dataID") String dataID);
    //待入库列表--部分入库
    @FormUrlEncoded
    @POST("pss/Store/doPartIn")
    Flowable<HttpResult> doPartIn(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                 @Field("detailIDs") String detailIDs, @Field("outNums") String outNums, @Field("dataID") String dataID);

    //待入库列表--全部入库
    @FormUrlEncoded
    @POST("pss/Store/doAllIn")
    Flowable<HttpResult> doAllIn(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("dataID") String dataID);


    //待出库列表
    @GET("pss/Store/waitingOutList")
    Flowable<HttpResult<WaitingInOutListResponse>> waitingOutList(@Query("token") String token, @Query("uid") String uid,
                                                                      @Query("shopID") String shopID, @Query("orderType") int orderType);
    //待出库列表--出库商品明细
    @GET("pss/Store/waitingOutDetail")
    Flowable<HttpResult<WaitingOutDetailResponse>> waitingOutDetail(@Query("token") String token, @Query("uid") String uid,
                                                                    @Query("shopID") String shopID, @Query("dataID") String dataID);

    //待出库列表--全部出库
    @FormUrlEncoded
    @POST("pss/Store/doAllOut")
    Flowable<HttpResult> doAllOut(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("dataID") String dataID);


    //待出库列表--部分出库
    @FormUrlEncoded
    @POST("pss/Store/doPartOut")
    Flowable<HttpResult> doPartOut(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                 @Field("detailIDs") String detailIDs, @Field("outNums") String outNums, @Field("dataID") String dataID);



    @GET("pss/Statistics/waitingCheck")
    Flowable<HttpResult<WaitingCheckResponse>> waitingCheck(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    //user
    @GET("pss/User/waitingCheckOrderPurchase")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderPurchase(@Query("token") String token, @Query("uid") String uid,
                                                                             @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //报价单--待审核
    @GET("pss/User/waitingCheckOrderOffer")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderOffer(@Query("token") String token, @Query("uid") String uid,
                                                                          @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //调拨单--待审核
    @GET("pss/User/waitingCheckOrderAllot")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderAllot(@Query("token") String token, @Query("uid") String uid,
                                                                          @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //其他出库单--待审核
    @GET("pss/User/waitingCheckOrderOtherOut")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderOtherOut(@Query("token") String token, @Query("uid") String uid,
                                                                             @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //零售退货单--待审核
    @GET("pss/User/waitingCheckOrderRetailBack")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderRetailBack(@Query("token") String token, @Query("uid") String uid,
                                                                               @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //销售退货单--待审核
    @GET("pss/User/waitingCheckOrderSalesBack")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderSalesBack(@Query("token") String token, @Query("uid") String uid,
                                                                              @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);


    //个人领用单单--待审核
    @GET("pss/User/waitingCheckOrderTakeGoods")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderTakeGoods(@Query("token") String token, @Query("uid") String uid,
                                                                              @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //销售单--待审核
    @GET("pss/User/waitingCheckOrderSales")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderSales(@Query("token") String token, @Query("uid") String uid,
                                                                          @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //采购退货单--待审核
    @GET("pss/User/waitingCheckOrderPurchaseBack")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderPurchaseBack(@Query("token") String token, @Query("uid") String uid,
                                                                                 @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //全库盘点单盈亏处理--待审核
    @GET("pss/User/waitingCheckOrderStoreCheckAll")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderStoreCheckAll(@Query("token") String token, @Query("uid") String uid,
                                                                                  @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //盘点单盈亏处理--待审核
    @GET("pss/User/waitingCheckOrderStoreCheck")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckOrderStoreCheck(@Query("token") String token, @Query("uid") String uid,
                                                                               @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //收支管理--待审核
    @GET("pss/User/waitingCheckIncomeExpense")
    Flowable<HttpResult<WaitingCheckListResponse>> waitingCheckIncomeExpense(@Query("token") String token, @Query("uid") String uid,
                                                                             @Query("shopID") String shopID, @Query("hasChecked") String hasChecked);

    //单据审核
    @FormUrlEncoded
    @POST("pss/User/doOrderCheck")
    Flowable<HttpResult> doOrderCheck(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                      @Field("checkApplyID") String checkApplyID, @Field("checkStatus") int checkStatus);


    //采购
    //应付管理--列表
    @GET("pss/Capital/shouldPayList")
    Flowable<HttpResult<PayerPayeeListResponse>> shouldPayList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    //应付管理--详情
    @GET("pss/Capital/shouldPayInfo")
    Flowable<HttpResult<PayerPayeeDetailResponse>> shouldPayInfo(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID
    ,@Query("customerOrSupplier") String customerOrSupplier, @Query("customerOrSupplierID") String customerOrSupplierID);

    //应付管理--付款
    @FormUrlEncoded
    @POST("pss/Capital/shouldPayDoPay")
    Flowable<HttpResult> shouldPayDoPay(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                      @Field("dataID") String dataID, @Field("moneyAccountID") String moneyAccountID,
                                        @Field("showName") String showName, @Field("money") String money);


    //应收管理--列表
    @GET("pss/Capital/shouldGatheringList")
    Flowable<HttpResult<PayerPayeeListResponse>> shouldGatheringList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    //应收管理--详情
    @GET("pss/Capital/shouldGatheringInfo")
    Flowable<HttpResult<PayerPayeeDetailResponse>> shouldGatheringInfo(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID
            , @Query("customerOrSupplier") String customerOrSupplier, @Query("customerOrSupplierID") String customerOrSupplierID);

    //应收管理--付款
    @FormUrlEncoded
    @POST("pss/Capital/shouldGatheringDoGathering")
    Flowable<HttpResult> shouldGatheringDoGathering(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID,
                                        @Field("dataID") String dataID, @Field("moneyAccountID") String moneyAccountID,
                                        @Field("showName") String showName, @Field("money") String money);

    //借入借出--列表
    @GET("pss/Capital/borrowList")
    Flowable<HttpResult<BorrowListResponse>> borrowList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("type") int type);

    //收支管理--列表
    @GET("pss/Capital/incomeExpenseList")
    Flowable<HttpResult<IncomeExpenseListResponse>> incomeExpenseList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("type") int type);

    //账户管理--账户流水
    @GET("pss/Capital/moneyAccountFinancialLog")
    Flowable<HttpResult<MoneyAccountFinancialLogListResponse>> moneyAccountFinancialLog(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("moneyAccountID") String moneyAccountID);

    //借入借出--新增
    @POST("pss/Capital/addBorrow")
    Flowable<HttpResult> addBorrow(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body AddBorrowRequest request);

    //借入借出--详情
    @GET("pss/Capital/borrowInfo")
    Flowable<HttpResult<BorrowInfoResponse>> borrowInfo(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("orderID") String orderID);

    //借入借出--新增结算历史
    @POST("pss/Capital/addBorrowHandleLog")
    Flowable<HttpResult> addBorrowHandleLog(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body AddBorrowHandleLogRequest request);


    //收支管理--新增
    @POST("pss/Capital/addIncomeExpense")
    Flowable<HttpResult> addIncomeExpense(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body AddIncomeExpenseRequest request);

    //收支管理--新增
    @POST("pss/Capital/moneyTransfer")
    Flowable<HttpResult> moneyTransfer(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body MoneyTransferRequest request);


    //采购

    //采购单--列表
    @GET("pss/Purchase/orderPurchaseList")
    Flowable<HttpResult<OrderPurchaseListResponse>> orderPurchaseList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);

    @GET("pss/Purchase/orderPurchaseBackList")
    Flowable<HttpResult<OrderPurchaseListResponse>> orderPurchaseBackList(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID);


    @GET("pss/Purchase/orderPurchaseInfo")
    Flowable<HttpResult<OrderPurchaseDetailResponse>> orderPurchaseInfo(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Query("orderID") String orderID);


    //采购单--新增
    @POST("pss/Purchase/orderPurchaseAdd")
    Flowable<HttpResult> orderPurchaseAdd(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderPurchaseAddRequest request);

    //采购退货单--新增
    @POST("pss/Purchase/orderPurchaseBackAdd")
    Flowable<HttpResult> orderPurchaseBackAdd(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Body OrderPurchaseAddRequest request);

    @FormUrlEncoded
    @POST("pss/Purchase/orderPurchaseCancel")
    Flowable<HttpResult> orderPurchaseCancel(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("orderID") String orderID);

    @FormUrlEncoded
    @POST("pss/Purchase/orderPurchaseDelBatch")
    Flowable<HttpResult> orderPurchaseDelBatch(@Query("token") String token, @Query("uid") String uid, @Query("shopID") String shopID, @Field("orderIDs") String orderIDs);


}
