package com.mfzn.deepuses.model.company;

import android.text.TextUtils;

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
        curCompany=null;
        if (!TextUtils.isEmpty(UserHelper.getCompanyId())) {
            if (mCompanyModels != null && mCompanyModels.size() > 0) {
                for (SelectCompanyModel model : mCompanyModels) {
                    if (UserHelper.getCompanyId().equals(model.getCompanyID())) {
                        curCompany = model;
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

}
