package com.mfzn.deepuses.purchasesellsave.store.model;

public class StoreStatusFilter {
    public final static int PENDING_REVIEW = 0;
    public final static int PROFIT_LOSS_PROCESSING = 1;
    public final static int REVIEW_REJECTED = 2;
    public final static int PROFIT_LOSS_COMPLETED = 3;
    public final static int NO_PROFIT_LOSS = 4;
    private int status;//状态：0.待审核；1.盈亏处理中；2.审核被拒；3盈亏处理完成；4.无盈亏
    private String statusText;

    public StoreStatusFilter(int status) {
        this.status = status;
        this.statusText = getStatusTextByStatus();
    }

    private String getStatusTextByStatus() {
        switch (status) {
            case PENDING_REVIEW:
                return "待审核";
            case PROFIT_LOSS_PROCESSING:
                return "盈亏处理中";
            case REVIEW_REJECTED:
                return "审核被拒";
            case PROFIT_LOSS_COMPLETED:
                return "盈亏处理完成";
            case NO_PROFIT_LOSS:
                return "无盈亏";
        }
        return "全部";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
