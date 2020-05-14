package com.mfzn.deepuses.purchasesellsave.setting;

import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;

public class SupplierManager {

    private static SupplierManager INSTANCE;

    private SupplierListResponse mSupplierListResponse;

    private SupplierListResponse.SupplierResponse curSupplierResponse;

    public static SupplierManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SupplierManager();
        }
        return INSTANCE;
    }

    public SupplierListResponse getSupplierListResponse() {
        return mSupplierListResponse;
    }

    public void setSupplierListResponse(SupplierListResponse supplierListResponse) {
        mSupplierListResponse = supplierListResponse;
    }

    public SupplierListResponse.SupplierResponse getCurSupplierResponse() {
        return curSupplierResponse;
    }

    public void setCurSupplierResponse(SupplierListResponse.SupplierResponse curSupplierResponse) {
        this.curSupplierResponse = curSupplierResponse;
    }
}
