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
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.bean.response.shop.ShopUserListResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class ShopStaffAdapter extends BaseQuickAdapter<ShopUserListResponse.ShopUserResponse, BaseViewHolder> {

    protected Context context;

    public ShopStaffAdapter(Context context, @Nullable List<ShopUserListResponse.ShopUserResponse> data) {
        super(R.layout.shop_staff_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopUserListResponse.ShopUserResponse item) {
        helper.setText(R.id.staff_name, item.getStaffName());
        if (!TextUtils.isEmpty(item.getUserAvatar())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getUserAvatar()).into((ImageView) helper.getView(R.id.user_avatar));
        } else {
            helper.setBackgroundColor(R.id.user_avatar, Color.parseColor("#F5F7FA"));
        }
    }
}
