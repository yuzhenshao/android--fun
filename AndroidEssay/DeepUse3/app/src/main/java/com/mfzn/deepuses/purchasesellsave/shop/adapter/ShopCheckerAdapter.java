package com.mfzn.deepuses.purchasesellsave.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.shop.ShopUserListResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class ShopCheckerAdapter extends BaseQuickAdapter<ShopUserListResponse.ShopUserResponse, BaseViewHolder> {

    protected Context context;
    private ShopUserListResponse.ShopUserResponse curShopUserResponse;

    public ShopCheckerAdapter(Context context, @Nullable List<ShopUserListResponse.ShopUserResponse> data) {
        super(R.layout.shop_checker_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopUserListResponse.ShopUserResponse item) {
        helper.setText(R.id.staff_name, item.getStaffName())
                .setImageResource(R.id.selecte_btn, isSelected(item) ? R.mipmap.icon_selected : R.mipmap.icon_unselected);
        if (!TextUtils.isEmpty(item.getUserAvatar())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getUserAvatar()).into((ImageView) helper.getView(R.id.user_avatar));
        } else {
            helper.setBackgroundColor(R.id.user_avatar, Color.parseColor("#F5F7FA"));
        }
    }

    private boolean isSelected(ShopUserListResponse.ShopUserResponse item) {
        return curShopUserResponse != null && !TextUtils.isEmpty(curShopUserResponse.getUserID())
                && curShopUserResponse.getUserID().equals(item.getUserID());
    }

    public void setCurShopUserResponse(ShopUserListResponse.ShopUserResponse curShopUserResponse) {
        this.curShopUserResponse = curShopUserResponse;
    }

}
