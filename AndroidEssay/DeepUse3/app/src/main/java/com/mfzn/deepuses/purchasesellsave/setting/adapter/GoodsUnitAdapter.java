package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.slidemenu.MenuQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import java.util.List;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsUnitAdapter extends MenuQuickAdapter<GoodsUnitResponse, BaseViewHolder> {
    public GoodsUnitAdapter(@Nullable List<GoodsUnitResponse> data) {
        super(R.layout.item_select_view,R.layout.delete_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsUnitResponse item) {
        helper.setText(R.id.title, item.getUnitName())
                .setImageResource(R.id.selecte_btn, item.isSelected()?R.mipmap.icon_selected:R.mipmap.icon_unselected);
    }
}