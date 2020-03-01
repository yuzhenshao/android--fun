package com.mfzn.deepuses.net;

import com.libcommon.utils.DomainUtil;
import com.mfzn.deepuses.bean.request.AcceptAsOrderRequest;
import com.mfzn.deepuses.bean.request.AddCustomRequest;
import com.mfzn.deepuses.bean.request.AddDepartmentRequest;
import com.mfzn.deepuses.bean.request.AfterSaleOrderListRequest;
import com.mfzn.deepuses.bean.request.ChangePwdRequest;
import com.mfzn.deepuses.bean.request.CompanyInfoRequest;
import com.mfzn.deepuses.bean.request.CreateAfterSaleOrderRequest;
import com.mfzn.deepuses.bean.request.EditAsServicePeopleRequest;
import com.mfzn.deepuses.bean.request.NewsListRequest;
import com.mfzn.deepuses.bean.request.ProMemberRequest;
import com.mfzn.deepuses.bean.request.ForgetRequest;
import com.mfzn.deepuses.bean.request.LoginRequest;
import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.bean.request.RegisterRequest;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.model.jiagou.ShareCodeModel;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.CustomTypeModel;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.model.xiangmu.SelectEnginerModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;
import io.reactivex.Flowable;

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

    public static Flowable<HttpResult<SelectEnginerModel>> searchEngineer(String phone) {
        return ApiHelper.getApiService().searchEngineer(UserHelper.getToken(), UserHelper.getUid(), phone);
    }

    public static Flowable<HttpResult> addEngineer(String enginerID, String remark) {
        return ApiHelper.getApiService().addEngineer(UserHelper.getToken(), UserHelper.getUid(), enginerID, remark);
    }

    public static Flowable<HttpResult> delEngineer(String enginerID) {
        return ApiHelper.getApiService().delEngineer(UserHelper.getToken(), UserHelper.getUid(), enginerID);
    }

    //TODO
    public static Flowable<HttpResult> uploadAvatar(String userAvatar){
        return ApiHelper.getApiService().uploadAvatar(UserHelper.getToken(), UserHelper.getUid(),userAvatar);
    }



    //Company
    public static Flowable<HttpResult<TeamManageModel>> getCompanyInfo(CompanyInfoRequest request) {
        return ApiHelper.getApiService().getCompanyInfo(DomainUtil.object2Map(request));
    }

    public static Flowable<HttpResult<ShareCodeModel>> generateCompanyQRCode(CompanyInfoRequest request) {
        return ApiHelper.getApiService().shareCode(DomainUtil.object2Map(request));
    }

    public static Flowable<HttpResult<ZuzhiJiagouModel>> getDepartments() {
        return ApiHelper.getApiService().getDepartments(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId());
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

    public static Flowable<HttpResult> addDepartment(AddDepartmentRequest request) {
        return ApiHelper.getApiService().addDepartment(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    //售后
    public static Flowable<HttpResult<WorkorderListModel>> afterSaleOrderList(AfterSaleOrderListRequest request) {
        return ApiHelper.getApiService().afterSaleOrderList(DomainUtil.object2Map(request));
    }

    public static Flowable<HttpResult> createAfterSaleOrder(CreateAfterSaleOrderRequest request){
        return ApiHelper.getApiService().createAfterSaleOrder(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult<GongdanShuxingModel>> lookAsOrder(String orderNo){
        return ApiHelper.getApiService().lookAsOrder(UserHelper.getToken(), UserHelper.getUid(), orderNo);
    }

    public static Flowable<HttpResult<List<ChuliGuochengModel>>> lookAsOrderProcess(String orderNo){
        return ApiHelper.getApiService().lookAsOrderProcess(UserHelper.getToken(), UserHelper.getUid(), orderNo);
    }

    public static Flowable<HttpResult<List<CustomListModel>>> asServicePeopleList(){
        return ApiHelper.getApiService().asServicePeopleList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId());
    }

    public static  Flowable<HttpResult> addCustom(AddCustomRequest request){
        return ApiHelper.getApiService().addCustom(UserHelper.getToken(), UserHelper.getUid(),request);
    }

    public static Flowable<HttpResult> delAsServicePeople(String kfID){
        return ApiHelper.getApiService().delAsServicePeople(UserHelper.getToken(), UserHelper.getUid(),kfID);
    }

    public static Flowable<HttpResult> editAsServicePeople(EditAsServicePeopleRequest request){
        return ApiHelper.getApiService().editAsServicePeople(UserHelper.getToken(), UserHelper.getUid(),request);
    }

    public static Flowable<HttpResult<List<CustomTypeModel>>> getAsServicePeopleTypeList(){
        return ApiHelper.getApiService().getAsServicePeopleTypeList(UserHelper.getToken(), UserHelper.getUid());
    }

    public static  Flowable<HttpResult> acceptAsOrder( AcceptAsOrderRequest request){
        return ApiHelper.getApiService().acceptAsOrder(UserHelper.getToken(), UserHelper.getUid(),request);
    }

    public static Flowable<HttpResult<News>> newsList(NewsListRequest request){
        return ApiHelper.getApiService().newsList(DomainUtil.object2Map(request));
    }

}
