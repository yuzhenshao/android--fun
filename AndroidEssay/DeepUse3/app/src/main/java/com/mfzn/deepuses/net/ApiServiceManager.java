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
import com.mfzn.deepuses.bean.request.EditStaffRequest;
import com.mfzn.deepuses.bean.request.ProMemberRequest;
import com.mfzn.deepuses.bean.request.ForgetRequest;
import com.mfzn.deepuses.bean.request.LoginRequest;
import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.bean.request.ReSendAsOrderRequest;
import com.mfzn.deepuses.bean.request.RegisterRequest;
import com.mfzn.deepuses.bean.request.SendAsOrderRequest;
import com.mfzn.deepuses.bean.request.SupplierRequest;
import com.mfzn.deepuses.bean.request.sale.OrderOfferRequest;
import com.mfzn.deepuses.bean.request.sale.OrderOtherInRequest;
import com.mfzn.deepuses.bean.request.sale.OrderSalesBackRequest;
import com.mfzn.deepuses.bean.request.sale.OrderSalesRequest;
import com.mfzn.deepuses.bean.request.sale.OrderTakeGoodsBackRequest;
import com.mfzn.deepuses.bean.request.sale.OrderTakeGoodsRequest;
import com.mfzn.deepuses.bean.request.setting.AddSetCustomerRequest;
import com.mfzn.deepuses.bean.request.store.OrderAllotAddRequest;
import com.mfzn.deepuses.bean.request.store.OrderStockCheckRequest;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.bean.response.sale.OrderSalesListResponse;
import com.mfzn.deepuses.bean.response.sale.PersonalStoreListResponse;
import com.mfzn.deepuses.bean.response.settings.CustomerDetailResponse;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;
import com.mfzn.deepuses.bean.response.settings.MoneyAccountListResponse;
import com.mfzn.deepuses.bean.response.settings.RateResponse;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsDetailResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsListResponse;
import com.mfzn.deepuses.bean.response.settings.IncomeExpenseTypeResponse;
import com.mfzn.deepuses.bean.response.settings.OtherCostResponse;
import com.mfzn.deepuses.bean.response.settings.SupplierCustomerInfoResponse;
import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;
import com.mfzn.deepuses.bean.response.shop.ShopDataResponse;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.bean.response.store.GoodsStockResponse;
import com.mfzn.deepuses.bean.response.store.OrderAllotListResponse;
import com.mfzn.deepuses.bean.response.store.OrderOtherInOutListResponse;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckListResponse;
import com.mfzn.deepuses.bean.response.store.StockLogListResponse;
import com.mfzn.deepuses.bean.response.store.StockWarningResponse;
import com.mfzn.deepuses.bean.response.store.StoreAllCheckListResponse;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.model.jiagou.ShareCodeModel;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
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
import retrofit2.http.Field;
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

    public static Flowable<HttpResult> appModifyName(String userName) {
        return ApiHelper.getApiService().setNickname(UserHelper.getToken(), UserHelper.getUid(), userName);
    }

    public static Flowable<HttpResult> appModifyPhone(String userPhone, String smscode) {
        return ApiHelper.getApiService().appModifyPhone(UserHelper.getToken(), UserHelper.getUid(), userPhone, smscode);
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

    public static Flowable<HttpResult> editStaff(EditStaffRequest request) {
        return ApiHelper.getApiService().editStaff(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult<List<ManageSettingModel>>> managerList() {
        return ApiHelper.getApiService().managerList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId());
    }

    public static Flowable<HttpResult<List<SelectCompanyModel>>> getUserCompanyList(String userPhone) {
        return ApiHelper.getApiService().userCompanyList(userPhone);
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

    public static Flowable<HttpResult<ProjectNewsModel>> appliesList(String companyID, String proID, int type, int per, int page) {
        return ApiHelper.getApiService().appliesList(UserHelper.getToken(), UserHelper.getUid(), companyID,
                proID, type, per, page);
    }

    public static Flowable<HttpResult> editProject(EditProjectRequest request) {
        return ApiHelper.getApiService().editProject(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(), request);
    }

    //TODO
    public static Flowable<HttpResult> uploadLogo(String logoUrl) {
        return ApiHelper.getApiService().uploadLogo(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(), logoUrl);
    }

    public static Flowable<HttpResult<WholeCustomerModel>> getMyCustomer() {
        return ApiHelper.getApiService().myCustomer(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId(), "100", 0,
                null, null, null, null);
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

    public static Flowable<HttpResult<SettingInfoModel>> lookAsSet(String proId) {
        return ApiHelper.getApiService().lookAsSet(UserHelper.getToken(), UserHelper.getUid(), proId);
    }

    public static Flowable<HttpResult> asSet(AsSetRequest request) {
        return ApiHelper.getApiService().asSet(UserHelper.getToken(), UserHelper.getUid(), request);
    }

    public static Flowable<HttpResult> addVisit(String proId, String title, String nowDate,
                                                String content, String nextDate) {
        return ApiHelper.getApiService().addVisit(UserHelper.getToken(), UserHelper.getUid(), proId, title, nowDate, content, nextDate);
    }

    public static Flowable<HttpResult<CheckAppraiseModel>> lookAsOrderComment(String orderNo) {
        return ApiHelper.getApiService().lookAsOrderComment(UserHelper.getToken(), UserHelper.getUid(), orderNo);
    }

    public static Flowable<HttpResult<ClzGongdanDetailModel>> lookReceipt(String receiptId) {
        return ApiHelper.getApiService().lookReceipt(UserHelper.getToken(), UserHelper.getUid(), receiptId);
    }

    public static Flowable<HttpResult> delAsOrder(String orderNo, String delNote) {
        return ApiHelper.getApiService().delAsOrder(UserHelper.getToken(), UserHelper.getUid(), orderNo, delNote);
    }

    public static Flowable<HttpResult<News>> newsList(String per, int page, String kw) {
        return ApiHelper.getApiService().newsList(UserHelper.getToken(), UserHelper.getUid(), per, page, kw);
    }

    public static Flowable<HttpResult> buyModule(String proId, String moduleType, String brickNum) {
        return ApiHelper.getApiService().buyModule(UserHelper.getToken(), UserHelper.getUid(), proId, moduleType, brickNum);
    }

    public static Flowable<HttpResult<List<GoodsCategoryResponse>>> getGoodsCategoryList() {
        return ApiHelper.getApiService().getGoodsCategoryList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult<GoodsListResponse>> goodsList(int isPersonalStoreGoods) {
        return ApiHelper.getApiService().goodsList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), null, null,isPersonalStoreGoods);
    }

    public static Flowable<HttpResult<GoodsListResponse>> searchGoodsList(String kw) {
        return ApiHelper.getApiService().goodsList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), kw, null,0);
    }

    public static Flowable<HttpResult<GoodsDetailResponse>> getGoodsInfo(String goodsID) {
        return ApiHelper.getApiService().getGoodsInfo(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), goodsID);
    }

    public static Flowable<HttpResult> addGoods(CommodityRequest request) {
        return ApiHelper.getApiService().addGoods(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> editGoods(CommodityRequest request) {
        return ApiHelper.getApiService().editGoods(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }


    public static Flowable<HttpResult<List<GoodsUnitResponse>>> getGoodsUnitList() {
        return ApiHelper.getApiService().getGoodsUnitList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult> addGoodsUnit(String unitName) {
        return ApiHelper.getApiService().addGoodsUnit(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), unitName);
    }

    public static Flowable<HttpResult> editGoodsUnit(String goodsUnitID, String unitName) {
        return ApiHelper.getApiService().editGoodsUnit(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), goodsUnitID, unitName);
    }

    public static Flowable<HttpResult> deleteGoodsUnit(String goodsUnitID) {
        return ApiHelper.getApiService().deleteGoodsUnit(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), goodsUnitID);
    }

    public static Flowable<HttpResult> addGoodsCategory(String catName, String pID) {
        return ApiHelper.getApiService().addGoodsCategory(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), catName, pID);
    }

    public static Flowable<HttpResult> editGoodsCategory(String catName, String pID, String catID) {
        return ApiHelper.getApiService().editGoodsCategory(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), catName, pID, catID);
    }

    public static Flowable<HttpResult> delGoodsCategory(String catID) {
        return ApiHelper.getApiService().delGoodsCategory(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), catID);
    }

    public static Flowable<HttpResult<List<OtherCostResponse>>> getOtherCostList() {
        return ApiHelper.getApiService().getOtherCostList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult> editOtherCost(String name, String type) {
        return ApiHelper.getApiService().editOtherCost(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), name, type);
    }

    public static Flowable<HttpResult> deleteOtherCost(String type) {
        return ApiHelper.getApiService().deleteOtherCost(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), type);
    }

    public static Flowable<HttpResult> addOtherCost(String name) {
        return ApiHelper.getApiService().addOtherCost(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), name);
    }


    public static Flowable<HttpResult<List<IncomeExpenseTypeResponse>>> getIncomeExpenseTypeListt() {
        return ApiHelper.getApiService().getIncomeExpenseTypeListt(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult> editIncomeExpenseType(IncomeExpenseTypeResponse response) {
        return ApiHelper.getApiService().editIncomeExpenseType(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(),
                response.getTypeID(), response.getType(), response.getTypeName());
    }

    public static Flowable<HttpResult> delIncomeExpenseType(String typeId) {
        return ApiHelper.getApiService().delIncomeExpenseType(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), typeId);
    }

    public static Flowable<HttpResult> addIncomeExpenseType(int type, String name) {
        return ApiHelper.getApiService().addIncomeExpenseType(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), type, name);
    }

    public static Flowable<HttpResult<List<StoreResponse>>> getStoreList() {
        return ApiHelper.getApiService().getStoreList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult> editStore(StoreResponse request) {
        return ApiHelper.getApiService().editStore(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> delStore(String storeID) {
        return ApiHelper.getApiService().delStore(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), storeID);
    }

    public static Flowable<HttpResult> addStore(StoreResponse request) {
        return ApiHelper.getApiService().addStore(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(),
                request.getStoreName(), request.getChargePersonUserID(), request.getContactPhone(), request.getStoreAddress());
    }

    public static Flowable<HttpResult<SupplierListResponse>> getSupplierList() {
        return searchSupplierList(null);
    }

    public static Flowable<HttpResult<CustomerDetailResponse>> getCustomerInfo(String companyCustomerID) {
        return ApiHelper.getApiService().customerInfo(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), companyCustomerID);
    }

    public static Flowable<HttpResult<SupplierListResponse>> searchSupplierList(String kw) {
        return ApiHelper.getApiService().supplierList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), kw, Integer.MAX_VALUE + "", 0);
    }


    public static Flowable<HttpResult> editSupplier(SupplierRequest request) {
        return ApiHelper.getApiService().editSupplier(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> delSupplier(String supplierID) {
        return ApiHelper.getApiService().delSupplier(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), supplierID);
    }

    public static Flowable<HttpResult> addSupplier(SupplierRequest request) {
        return ApiHelper.getApiService().addSupplier(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request.getSupplierName(),
                request.getChargePerson(), request.getChargePersonPhone(), request.getContactAddress());
    }

    public static Flowable<HttpResult<List<RateResponse>>> getTaxRateList() {
        return ApiHelper.getApiService().taxRateList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult<MoneyAccountListResponse>> getMoneyAccountList(String mapShopID) {
        return ApiHelper.getApiService().moneyAccountList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), mapShopID);
    }

    public static Flowable<HttpResult<CustomerListResponse>> getCustomerList(String keywords) {
        return ApiHelper.getApiService().getSetCustomerList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), keywords, Integer.MAX_VALUE + "", 0);
    }

    public static Flowable<HttpResult> addSetCustomer(AddSetCustomerRequest request) {
        return ApiHelper.getApiService().addSetCustomer(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> editSetCustomer(AddSetCustomerRequest request) {
        return ApiHelper.getApiService().editSetCustomer(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> delSetCustomer(String companyCustomerID) {
        return ApiHelper.getApiService().delSetCustomer(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), companyCustomerID);
    }


    public static Flowable<HttpResult<StockWarningResponse>> getStockWarningList(String kw, String storeID) {
        return ApiHelper.getApiService().stockWarning(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), kw, storeID);
    }

    public static Flowable<HttpResult<GoodsStockResponse>> getGoodsStock(String kw) {
        return ApiHelper.getApiService().goodsStock(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), kw);
    }

    public static Flowable<HttpResult<OrderStockCheckListResponse>> getOrderStockCheckList(String kw, String storeID, int status) {
        return ApiHelper.getApiService().orderStockCheckList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), kw, storeID, status);
    }

    public static Flowable<HttpResult> editOrderStockCheck(OrderStockCheckRequest request) {
        return ApiHelper.getApiService().orderStockCheckEdit(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> delOrderStockCheck(String orderIDs) {
        return ApiHelper.getApiService().orderStockCheckDelBatch(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), orderIDs);
    }

    public static Flowable<HttpResult> addOrderStockCheck(OrderStockCheckRequest request) {
        return ApiHelper.getApiService().orderStockCheckAdd(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult<OrderOtherInOutListResponse>> getOrderOtherInOutList() {
        return getOrderOtherInOutList(null, null, null, 9);
    }

    public static Flowable<HttpResult<OrderOtherInOutListResponse>> searchOrderOtherInOutList(String keywords, int orderType) {
        return getOrderOtherInOutList(keywords, null, null, orderType);
    }

    public static Flowable<HttpResult<OrderOtherInOutListResponse>> getOrderOtherInOutList(String keywords, String startTime,
                                                                                           String endTime, int orderType) {
        return ApiHelper.getApiService().orderOtherInOutList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(),
                keywords, startTime, endTime, orderType);
    }

    public static Flowable<HttpResult<OrderAllotListResponse>> getOrderAllotList() {
        //String keywords, String startTime,int isCheck,String endTime, int orderType
        return ApiHelper.getApiService().orderAllotList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(),
                "", "", "", "", "", "", "", "");
    }

    public static Flowable<HttpResult<StoreAllCheckListResponse>> getSoreAllCheckList() {
        return ApiHelper.getApiService().storeAllCheckList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), 0);
    }


    //门店
    public static Flowable<HttpResult<ShopDataResponse>> getShopData(String mapShopID) {
        return ApiHelper.getApiService().getShopData(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), mapShopID);
    }

    public static Flowable<HttpResult<List<ShopListResponse>>> getShopList() {
        return ApiHelper.getApiService().shopList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult> addShop(String shopName, String chargePersonUserID, String contactPhone, String shopAddress, String remark) {
        return ApiHelper.getApiService().addShop(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(),
                shopName, chargePersonUserID, contactPhone, shopAddress, remark);
    }

    //销售

    public static Flowable<HttpResult<OrderOfferListResponse>> getOrderOfferList() {
        return ApiHelper.getApiService().orderOfferList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }

    public static Flowable<HttpResult> addOrderOffer(OrderOfferRequest request) {
        return ApiHelper.getApiService().addOrderOffer(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderSales(OrderSalesRequest request) {
        return ApiHelper.getApiService().addOrderSales(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderRetail(OrderSalesRequest request) {
        return ApiHelper.getApiService().addOrderRetail(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderSalesBack(OrderSalesBackRequest request) {
        return ApiHelper.getApiService().addOrderSalesBack(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderRetailBack(OrderSalesBackRequest request) {
        return ApiHelper.getApiService().addOrderRetailBack(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderTakeGoods(OrderTakeGoodsRequest request) {
        return ApiHelper.getApiService().addOrderTakeGoods(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderTakeGoodsBack(OrderTakeGoodsBackRequest request) {
        return ApiHelper.getApiService().addOrderTakeGoodsBack(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }


    public static Flowable<HttpResult<OrderSalesListResponse>> getOrderSalesList() {
        return ApiHelper.getApiService().orderSalesList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(),
                -1, -1, -1, -1);
    }

    public static Flowable<HttpResult<OrderSalesListResponse>> getOrderRetailList() {
        return ApiHelper.getApiService().orderRetailList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), -1);
    }

    public static Flowable<HttpResult<OrderSalesListResponse>> getOrderTakeGoodsList() {
        return ApiHelper.getApiService().orderTakeGoodsList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), -1, -1, -1);
    }

    public static Flowable<HttpResult<PersonalStoreListResponse>> getPersonalStoreList() {
        return ApiHelper.getApiService().personalStoreList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId());
    }


    public static Flowable<HttpResult> addOrderOtherIn(OrderOtherInRequest request) {
        return ApiHelper.getApiService().addOrderOtherIn(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderOtherOut(OrderSalesRequest request) {
        return ApiHelper.getApiService().addOrderOtherOut(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult> addOrderAllot(OrderAllotAddRequest request) {
        return ApiHelper.getApiService().addOrderAllot(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), request);
    }

    public static Flowable<HttpResult<StockLogListResponse>> getStockLogList() {
        return ApiHelper.getApiService().stockLogList(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getShopId(), "", "", "", "", "", "", 0, "");
    }

    public static Flowable<HttpResult> storeAllCheckSponsor(String shopID, String storeID, String beginDate, String endDate) {
        return ApiHelper.getApiService().storeAllCheckSponsor(UserHelper.getToken(), UserHelper.getUid(), shopID, storeID, beginDate, endDate);
    }

}
