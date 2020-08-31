package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.slidemenu.MenuQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.MoneyAccountListResponse;

import java.util.List;

public class MoneyAccountAdapter extends MenuQuickAdapter<MoneyAccountListResponse.AccountResponse, BaseViewHolder> {
    public MoneyAccountAdapter(@Nullable List<MoneyAccountListResponse.AccountResponse> data) {
        super(R.layout.money_account_item_view,R.layout.account_delete_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyAccountListResponse.AccountResponse item) {
        helper.setText(R.id.account_name, item.getAccountName())
                .setText(R.id.account_money, "余额：" + item.getNowMoney() + "元")
        .addOnClickListener(R.id.account_container)
        .addOnClickListener(R.id.financial_log)
        .addOnClickListener(R.id.money_transfer);
        switch (item.getAccountType()) {
            case MoneyAccountListResponse.ACCOUNT_CASH:
                helper.setText(R.id.account_type, "现金")
                        .setImageResource(R.id.money_account_bg, R.mipmap.bg_cash);
                break;
            case MoneyAccountListResponse.ACCOUNT_ACCOUNT:
                helper.setText(R.id.account_type, "银行账户")
                        .setImageResource(R.id.money_account_bg, R.mipmap.bg_account);
                break;
            case MoneyAccountListResponse.ACCOUNT_WEIXIN:
                helper.setText(R.id.account_type, "微信")
                        .setImageResource(R.id.money_account_bg, R.mipmap.bg_wx);
                break;
            case MoneyAccountListResponse.ACCOUNT_ALI:
                helper.setText(R.id.account_type, "支付宝")
                        .setImageResource(R.id.money_account_bg, R.mipmap.bg_ali_pay);
                break;
        }
    }
}
