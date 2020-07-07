package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.tree.MultiTreeAdapter;
import com.libcommon.tree.TreeNode;
import com.libcommon.utils.Utiles;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;

import java.util.List;

import static com.libcommon.tree.TreeNode.PARENT;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsCategoryManagerAdapter extends MultiTreeAdapter<GoodsCategoryResponse> {

    public GoodsCategoryManagerAdapter(@Nullable List<TreeNode<GoodsCategoryResponse>> data) {
        super(data);
    }

    @Override
    protected int getLeafLayoutId() {
        return R.layout.categpty_manager_item_view;
    }

    @Override
    protected int getParentLayoutId() {
        return R.layout.categpty_manager_item_view;
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeNode<GoodsCategoryResponse> item) {
        View view = helper.itemView.findViewById(R.id.icon);
        ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        mp.leftMargin = item.getLevel() * Utiles.dip2px(mContext, 10);
        final GoodsCategoryResponse categoryResponse = item.getData();
        if (categoryResponse != null) {
            helper.setText(R.id.title, categoryResponse.getCatName());
            helper.setVisible(R.id.icon, item.getItemType() == PARENT);
        }
        helper.addOnClickListener(R.id.icon_more);
    }
}
