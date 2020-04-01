package com.libcommon.tree;

import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.R;
import com.libcommon.utils.ListUtil;
import com.libcommon.utils.Utiles;

import java.util.ArrayList;
import java.util.List;

import static com.libcommon.tree.TreeNode.LEAF;
import static com.libcommon.tree.TreeNode.PARENT;

/**
 * @author yz @date 2020-03-30
 */
public abstract class MultiTreeAdapter<T extends NodeId> extends BaseMultiItemQuickAdapter<TreeNode<T>, BaseViewHolder> {

    public interface OnTreeClickedListener<T extends NodeId> {
        void onNodeClicked(View view, TreeNode<T> node);
    }

    private OnTreeClickedListener<T> onTreeClickedListener;

    protected MultiTreeAdapter(@Nullable final List<TreeNode<T>> dataToBind) {
        super(dataToBind);
        addItemType(LEAF, getLeafLayoutId());
        addItemType(PARENT, getParentLayoutId());

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TreeNode<T> node = dataToBind.get(position);
                List<TreeNode<T>> children = getNodeChildren(node);

                if (node.isExpand()) {
                    node.setExpand(false);
                    if (!ListUtil.isEmpty(children)) {
                        List<TreeNode<T>> removeNodes = MultiTreeAdapter.this.getRemoveNode(dataToBind, children, position);
                        if (!ListUtil.isEmpty(removeNodes)) {
                            dataToBind.removeAll(removeNodes);
                            MultiTreeAdapter.this.notifyItemRangeRemoved(position + 1, removeNodes.size());
                        }
                    }
                } else {
                    node.setExpand(true);
                    if (!ListUtil.isEmpty(children)) {
                        dataToBind.addAll(position + 1, children);
                        MultiTreeAdapter.this.notifyItemRangeInserted(position + 1, children.size());
                    }
                }
                if (onTreeClickedListener != null) {
                    onTreeClickedListener.onNodeClicked(view, node);
                }

            }
        });
    }


    public static <T extends NodeId> List<TreeNode<T>> getNodeChildren(TreeNode<T> node) {
        List<TreeNode<T>> result = new ArrayList<>();
        getRNodeChildren(result, node);
        return result;
    }

    private static <T extends NodeId> void getRNodeChildren(List<TreeNode<T>> result, TreeNode<T> node) {
        List<TreeNode<T>> children = node.getChildren();
        for (TreeNode<T> n : children) {
            result.add(n);
            if (n.isExpand() && !n.isLeaf()) {
                getRNodeChildren(result, n);
            }
        }
    }

    protected abstract int getLeafLayoutId();

    protected abstract int getParentLayoutId();

    private List<TreeNode<T>> getRemoveNode(List<TreeNode<T>> dataToBind,
                                            List<TreeNode<T>> children, int startPosition) {
        List<TreeNode<T>> nodeList = new ArrayList<>();
        ArrayMap<String, TreeNode<T>> map = new ArrayMap<>();
        for (TreeNode<T> childNode : children) {
            map.put(childNode.getId(), childNode);
        }
        if (map.size() != 0 && dataToBind.size() > startPosition + children.size()) {
            for (TreeNode<T> node : dataToBind.subList(startPosition, startPosition + children.size() + 1)) {
                if (map.containsKey(node.getId())) {
                    nodeList.add(node);
                }
            }
        }
        return nodeList;
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeNode<T> item) {
        View view = helper.itemView.findViewById(R.id.container);
        ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        mp.leftMargin = item.getLevel() * Utiles.dip2px(mContext, 10);
    }

    public void setOnTreeClickedListener(OnTreeClickedListener onTreeClickedListener) {
        this.onTreeClickedListener = onTreeClickedListener;
    }
}