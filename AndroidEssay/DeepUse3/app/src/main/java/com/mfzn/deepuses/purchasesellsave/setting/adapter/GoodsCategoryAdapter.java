package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.tree.MultiTreeAdapter;
import com.libcommon.tree.TreeNode;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;

import java.util.List;

import static com.libcommon.tree.TreeNode.PARENT;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsCategoryAdapter extends MultiTreeAdapter<GoodsCategoryResponse> {

    private String curCatId;

    public GoodsCategoryAdapter(@Nullable List<TreeNode<GoodsCategoryResponse>> data) {
        super(data);
    }

    @Override
    protected int getLeafLayoutId() {
        return R.layout.item_select_view;
    }

    @Override
    protected int getParentLayoutId() {
        return R.layout.item_category_view;
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeNode<GoodsCategoryResponse> item) {
        super.convert(helper, item);
        final GoodsCategoryResponse categoryResponse = item.getData();
        if (categoryResponse != null) {
            helper.setText(R.id.title, categoryResponse.getCatName());
            if (item.getItemType() == PARENT) {
                helper.setImageResource(R.id.category_btn, item.isExpand() ? R.mipmap.icon_up : R.mipmap.icon_down);
            } else {
                helper.setImageResource(R.id.selecte_btn, isSelected(categoryResponse) ? R.mipmap.icon_selected : R.mipmap.icon_unselected);
            }

        }
    }

    private boolean isSelected(GoodsCategoryResponse categoryResponse) {
        return !TextUtils.isEmpty(curCatId) && curCatId.equals(categoryResponse.getCatID());
    }


    public void setCurCatId(String curCatId) {
        this.curCatId = curCatId;
    }
}
