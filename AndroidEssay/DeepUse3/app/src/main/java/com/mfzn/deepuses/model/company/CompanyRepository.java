package com.mfzn.deepuses.model.company;

import android.text.TextUtils;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yz @date 2020-04-09
 */
public class CompanyRepository {

    private static CompanyRepository INSTANCE = null;
    private List<SelectCompanyModel> mCompanyModels = new ArrayList<>();
    private SelectCompanyModel curCompany;
    private SelectCompanyModel.ShopResponse curShopResponse;

    public static CompanyRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CompanyRepository();
        }
        return INSTANCE;
    }

    public List<SelectCompanyModel> getCompanyModels() {
        return mCompanyModels;
    }

    public void setCompanyModels(List<SelectCompanyModel> companyModels) {
        mCompanyModels = companyModels;
        curCompany = null;
        if (!TextUtils.isEmpty(UserHelper.getCompanyId())) {
            if (mCompanyModels != null && mCompanyModels.size() > 0) {
                for (SelectCompanyModel model : mCompanyModels) {
                    if (UserHelper.getCompanyId().equals(model.getCompanyID())) {
                        curCompany = model;
                        if (!ListUtil.isEmpty(curCompany.getShops())) {
                            if (!TextUtils.isEmpty(UserHelper.getShopId())) {
                                for (SelectCompanyModel.ShopResponse shop : curCompany.getShops()) {
                                    if (UserHelper.getShopId().equals(shop)) {
                                        curShopResponse = shop;
                                        UserHelper.setShopId(curShopResponse.getShopID());
                                        return;
                                    }
                                }
                            }
                            curShopResponse = curCompany.getMainShop();
                            UserHelper.setShopId(curShopResponse.getShopID());
                        }
                        break;
                    }
                }
            }
        }
    }

    public SelectCompanyModel getCurCompany() {
        return curCompany;
    }

    public void setCurCompany(SelectCompanyModel curCompany) {
        this.curCompany = curCompany;
    }

    public SelectCompanyModel.ShopResponse getCurShopResponse() {
        return curShopResponse;
    }

    public void setCurShopResponse(SelectCompanyModel.ShopResponse curShopResponse) {
        this.curShopResponse = curShopResponse;
    }
}
