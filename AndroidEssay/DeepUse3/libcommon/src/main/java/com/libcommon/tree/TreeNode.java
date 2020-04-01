package com.libcommon.tree;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.libcommon.utils.ListUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yz @date 2020-03-30
 */
public class TreeNode<T extends NodeId> implements MultiItemEntity, Serializable {

    private static final long serialVersionUID = -305403593350065521L;

    public static final int LEAF = 0;

    public static final int PARENT = 1;


    private T data;

    private int level;

    private boolean isExpand;

    private TreeNode<T> parent;

    private List<TreeNode<T>> children = new ArrayList<>();

    public TreeNode(T data) {
        this(data, -1);
    }

    public TreeNode(T data, int maxViewType) {
        this.data = data;
    }

    public String getId() {
        return data == null ? "" : data.getNodeId();
    }

    public String getPId() {
        return parent == null ? "" : parent.getId();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
        if (!isExpand) {
            for (TreeNode node : children) {
                node.setExpand(false);
            }
        }
    }

    public int getLevel() {
        if (parent == null) {
            return 0;
        }
        return parent.getLevel() + 1;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return ListUtil.isEmpty(children);
    }

    @Override
    public int getItemType() {
        return isLeaf() ? LEAF : PARENT;
    }

    @Override
    public TreeNode<T> clone() {
        TreeNode<T> newLeaf = new TreeNode<>(getData());
        newLeaf.setLevel(getLevel());
        List<TreeNode<T>> subList = getChildren();
        if (!ListUtil.isEmpty(subList)) {
            List<TreeNode<T>> newSubList = new ArrayList<>();
            for (TreeNode<T> node : subList) {
                TreeNode<T> nodeClone = node.clone();
                nodeClone.setParent(newLeaf);
                newSubList.add(nodeClone);
            }
            newLeaf.setChildren(newSubList);
        }
        return newLeaf;
    }
}
