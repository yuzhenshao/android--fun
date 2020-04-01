package com.mfzn.deepuses.net;

import com.libcommon.utils.DomainUtil;
import com.mfzn.deepuses.bean.request.AcceptAsOrderRequest;
import com.mfzn.deepuses.bean.request.AddCustomRequest;
import com.mfzn.deepuses.bean.request.AddDepartmentRequest;
import com.mfzn.deepuses.bean.request.AfterSaleOrderListRequest;
import com.mfzn.deepuses.bean.request.AsSetRequest;
import com.mfzn.deepuses.bean.request.ChangePwdRequest;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.request.CompanyInfoRequest;
import com.mfzn.deepuses.bean.request.CreateAfterSaleOrderRequest;
import com.mfzn.deepuses.bean.request.EditAsServicePeopleRequest;
import com.mfzn.deepuses.bean.request.EditProjectRequest;
import com.mfzn.deepuses.bean.request.ProMemberRequest;
import com.mfzn.deepuses.bean.request.ForgetRequest;
import com.mfzn.deepuses.bean.request.LoginRequest;
import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.bean.request.ReSendAsOrderRequest;
import com.mfzn.deepuses.bean.request.RegisterRequest;
import com.mfzn.deepuses.bean.request.SendAsOrderRequest;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import com.mfzn.deepuses.bean.response.StoreResponse;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.model.jiagou.ShareCodeModel;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.model.xiangmu.CheckAppraiseModel;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.model.xiangmu.ClzGongdanDetailModel;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.CustomTypeModel;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.model.xiangmu.SelectEnginerModel;
import com.mfzn.deepuses.model.xiangmu.SettingInfoModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.utils.UserHelper;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author yz @date 2020-02-27
 */
public class ApiServiceManager {

    public static Flowable<HttpResult> appRegister(RegisterRequest request) {
        return ApiHelper.getApiService().appRegister(request);
    }

    public static Flowable<HttpResult<UserResponse>> appLogin(LoginRequest request) {
        return ApiHelper.getApiService().appLogin(request);
    }

    public static Flowable<HttpResult> forgetPwd(ForgetRequest request) {
        return ApiHelper.getApiService().forgetPwd(request);
    }

    public static Flowable<HttpResult<String>> getSmsCode(String phone, String type) {
        return ApiHelper.getApiService().getSmsCode(phone, type);
    }

    public static Flowable<HttpResult> changePwd(ChangePwdRequest request) {
        return ApiHelper.getApiService().changePwd(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    //工程师通讯录
    public static Flowable<HttpResult<List<EnginerListModel>>> getEngineerList() {
        return ApiHelper.getApiService().myEngineerList(UserHelper.getToken(), UserHelper.getUid());
    }

    public static Flowable<HttpResult<List<SelectEnginerModel>>> searchEngineer(String phone) {
        return ApiHelper.getApiService().searchEngineer(UserHelper.getToken(), UserHelper.getUid(), phone);
    }

    public static Flowable<HttpResult> addEngineer(String enginerID, String remark) {
        return ApiHelper.getApiService().addEngineer(UserHelper.getToken(), UserHelper.getUid(), enginerID, remark);
    }

    public static Flowable<HttpResult> delEngineer(String enginerID) {
        return ApiHelper.getApiService().delEngineer(UserHelper.getToken(), UserHelper.getUid(), enginerID);
    }

    public static Flowable<HttpResult> uploadAvatar(String userAvatar) {
        return ApiHelper.getApiService().uploadAvatar(UserHelper.getToken(), UserHelper.getUid(), userAvatar);
    }

    public static Flowable<HttpResult> appModifyName( String userName){
        return ApiHelper.getApiService().setNickname(UserHelper.getToken(), UserHelper.getUid(), userName);
    }

    public static Flowable<HttpResult> appModifyPhone(String userPhone, String smscode){
        return ApiHelper.getApiService().appModifyPhone(UserHelper.getToken(), UserHelper.getUid(), userPhone,smscode);
    }

    //Company
    public static Flowable<HttpResult<TeamManageModel>> getCompanyInfo(CompanyInfoRequest request) {
        return ApiHelper.getApiService().getCompanyInfo(DomainUtil.object2Map(request));
    }

    public static Flowable<HttpResult<ShareCodeModel>> generateCompanyQRCode(String companyID) {
        return ApiHelper.getApiService().shareCode(UserHelper.getToken(), UserHelper.getUid(), companyID);
    }

    public static Flowable<HttpResult<ZuzhiJiagouModel>> getDepartments() {
        return ApiHelper.getApiService().getDepartments(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId());
    }

    public static Flowable<HttpResult> addDepartment(AddDepartmentRequest request) {
        return ApiHelper.getApiService().addDepartment(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult<List<ManageSettingModel>>> managerList(){
        return ApiHelper.getApiService().managerList(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId());
    }

    //Project
    public static Flowable<HttpResult<XiangmuModel>> getProjectList(ProjectListRequest request) {
        return ApiHelper.getApiService().getProjectList(DomainUtil.object2Map(request));
    }

    public static Flowable<HttpResult<StagingListModel>> getProMemberList(String proID) {
        return ApiHelper.getApiService().getProMemberList(UserHelper.getToken(), UserHelper.getUid(), proID);
    }

    public static Flowable<HttpResult> deleteProMember(ProMemberRequest request) {
        return ApiHelper.getApiService().deleteProMember(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult> addProMember(ProMemberRequest request) {
        return ApiHelper.getApiService().addProMember(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult> doApplyCheck(String applyID, String status, String checkRemark) {
        return ApiHelper.getApiService().doApplyCheck(UserHelper.getToken(), UserHelper.getUid(), applyID,
                status, checkRemark);
    }

    public static Flowable<HttpResult<ProjectNewsModel>> appliesList(String companyID, String proID, int type,int per, int page) {
        return ApiHelper.getApiService().appliesList(UserHelper.getToken(), UserHelper.getUid(), companyID,
                proID,type, per, page);
    }

    public static Flowable<HttpResult> editProject(EditProjectRequest request){
        return ApiHelper.getApiService().editProject(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),request);
    }

    //TODO
    public static Flowable<HttpResult> uploadLogo(String logoUrl){
        return ApiHelper.getApiService().uploadLogo(UserHelper.getToken(), UserHelper.getUid(),UserHelper.getCompanyId(),logoUrl);
    }


    //售后
    public static Flowable<HttpResult<WorkorderListModel>> afterSaleOrderList(AfterSaleOrderListRequest request) {
        return ApiHelper.getApiService().afterSaleOrderList(DomainUtil.object2Map(request));
    }

    public static Flowable<HttpResult> createAfterSaleOrder(CreateAfterSaleOrderRequest request) {
        return ApiHelper.getApiService().createAfterSaleOrder(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult<GongdanShuxingModel>> lookAsOrder(String orderNo) {
        return ApiHelper.getApiService().lookAsOrder(UserHelper.getToken(), UserHelper.getUid(), orderNo);
    }

    public static Flowable<HttpResult<List<ChuliGuochengModel>>> lookAsOrderProcess(String orderNo) {
        return ApiHelper.getApiService().lookAsOrderProcess(UserHelper.getToken(), UserHelper.getUid(), orderNo);
    }

    public static Flowable<HttpResult<List<CustomListModel>>> asServicePeopleList() {
        return ApiHelper.getApiService().asServicePeopleList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId());
    }

    public static Flowable<HttpResult> addCustom(AddCustomRequest request) {
        return ApiHelper.getApiService().addCustom(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult> delAsServicePeople(String kfID) {
        return ApiHelper.getApiService().delAsServicePeople(UserHelper.getToken(), UserHelper.getUid(), kfID);
    }

    public static Flowable<HttpResult> editAsServicePeople(EditAsServicePeopleRequest request) {
        return ApiHelper.getApiService().editAsServicePeople(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult<List<CustomTypeModel>>> getAsServicePeopleTypeList() {
        return ApiHelper.getApiService().getAsServicePeopleTypeList(UserHelper.getToken(), UserHelper.getUid());
    }

    public static Flowable<HttpResult> acceptAsOrder(AcceptAsOrderRequest request) {
        return ApiHelper.getApiService().acceptAsOrder(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult> sendAsOrder(SendAsOrderRequest request) {
        return ApiHelper.getApiService().sendAsOrder(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult> reSendAsOrder(ReSendAsOrderRequest request) {
        return ApiHelper.getApiService().reSendAsOrder(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult<SettingInfoModel>> lookAsSet(String proId){
        return ApiHelper.getApiService().lookAsSet(UserHelper.getToken(), UserHelper.getUid(),proId);
    }

    public static Flowable<HttpResult> asSet(AsSetRequest request){
        return ApiHelper.getApiService().asSet(UserHelper.getToken(), UserHelper.getUid(),request);
    }

    public static Flowable<HttpResult> addVisit(String proId, String title, String nowDate,
                                                String content, String nextDate){
        return ApiHelper.getApiService().addVisit(UserHelper.getToken(), UserHelper.getUid(),proId,title,nowDate,content,nextDate);
    }

    public static  Flowable<HttpResult<CheckAppraiseModel>> lookAsOrderComment(String orderNo){
        return ApiHelper.getApiService().lookAsOrderComment(UserHelper.getToken(), UserHelper.getUid(),orderNo);
    }

    public static  Flowable<HttpResult<ClzGongdanDetailModel>> lookReceipt(String receiptId){
        return ApiHelper.getApiService().lookReceipt(UserHelper.getToken(), UserHelper.getUid(),receiptId);
    }

    public static Flowable<HttpResult> delAsOrder(String orderNo, String delNote){
        return ApiHelper.getApiService().delAsOrder(UserHelper.getToken(), UserHelper.getUid(),orderNo,delNote);
    }

    public static Flowable<HttpResult<News>> newsList(String per,int page,String kw) {
        return ApiHelper.getApiService().newsList(UserHelper.getToken(), UserHelper.getUid(),per,page,kw);
    }

    public static Flowable<HttpResult> buyModule(String proId, String moduleType, String brickNum) {
        return ApiHelper.getApiService().buyModule(UserHelper.getToken(), UserHelper.getUid(), proId, moduleType, brickNum);
    }

    public static Flowable<HttpResult<List<GoodsCategoryResponse>>> getGoodsCategoryList(String shopID) {
        return ApiHelper.getApiService().getGoodsCategoryList(UserHelper.getToken(), UserHelper.getUid(), shopID);
    }


    public static Flowable<HttpResult> addGoods(String shopID,CommodityRequest request) {
        return ApiHelper.getApiService().addGoods(UserHelper.getToken(), UserHelper.getUid(), shopID,request);
    }



    public static Flowable<HttpResult<List<StoreResponse>>> getStoreList(String shopID){
        return ApiHelper.getApiService().getStoreList(UserHelper.getToken(), UserHelper.getUid(), shopID);
    }


    public static Flowable<HttpResult<List<GoodsUnitResponse>>> getGoodsUnitList(String shopID){
        return ApiHelper.getApiService().getGoodsUnitList(UserHelper.getToken(), UserHelper.getUid(), shopID);
    }
}
