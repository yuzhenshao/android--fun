package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.tree.TreeNode;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCategoryAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCategoryManagerAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GoodsCategoryManagerActivity extends BasicListActivity<TreeNode<GoodsCategoryResponse>> {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("管理商品分类", R.mipmap.icon_titlebar_add);
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getGoodsCategoryList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<GoodsCategoryResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<GoodsCategoryResponse>> reuslt) {
                        hideDialog();
                        if (reuslt != null && !ListUtil.isEmpty(reuslt.getRes())) {
                            initGoodsCategoryList(reuslt.getRes());
                        } else {
                            showErrorView("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        GoodsCategoryManagerAdapter mAdapter = new GoodsCategoryManagerAdapter(mSourceList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                if (view.getId() == R.id.icon_more) {
                    showCategoryActionDialog(i);
                }
            }
        });
        return mAdapter;
    }

    private void initGoodsCategoryList(List<GoodsCategoryResponse> storeResponseList) {
        List<TreeNode<GoodsCategoryResponse>> result = new ArrayList<>();
        for (GoodsCategoryResponse res : storeResponseList) {
            TreeNode<GoodsCategoryResponse> node = new TreeNode<>(res);
            node.setLevel(0);
            result.add(node);
            resolveChildren(node, 1);
        }
        refreshSource(result);
    }

    private static void resolveChildren(TreeNode<GoodsCategoryResponse> node, int level) {
        GoodsCategoryResponse data = node.getData();
        List<GoodsCategoryResponse> subCategorys = data.getSons();
        if (!ListUtil.isEmpty(subCategorys)) {
            for (GoodsCategoryResponse res : subCategorys) {
                TreeNode<GoodsCategoryResponse> n = new TreeNode<>(res);
                n.setLevel(level);
                n.setParent(node);
                node.getChildren().add(n);
                resolveChildren(n, level + 1);
            }
        }
    }

    private void showCategoryActionDialog(int index) {
        TreeNode<GoodsCategoryResponse> node = mSourceList.get(index);
        if (node != null && node.getData() != null) {
            new CustomDialog.Builder().setLayoutRes(R.layout.catelog_action_dialog)
                    .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                    .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                    .setGravity(Gravity.BOTTOM)
                    .addOnClickListener(R.id.add_category, R.id.rename_category, R.id.delete_category, R.id.cancel_category)
                    .setOnViewClickListener(new OnViewClickListener() {
                        @Override
                        public void onViewClick(BaseDialogFragment customDialog, BindViewHolder bindViewHolder, View view) {
                            if (customDialog != null) {
                                customDialog.dismiss();
                            }
                            switch (view.getId()) {
                                case R.id.add_category:
                                    showAddDialog("新增子分类", node.getId());
                                    break;
                                case R.id.rename_category:
                                    showRenameDialog(node.getPId(), node.getId());
                                    break;
                                case R.id.delete_category:
                                    showDeleteDialog(index);
                                    break;
                            }
                        }
                    }).create().show(getSupportFragmentManager(), getClass().getName());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view;
    }

    @Override
    protected void rightPressedAction() {
        showAddDialog("新增一级分类", "");
    }

    private void showAddDialog(String title, String pID) {
        DialogUtils.showEditDialog(this, title, "请输入分类名称", new OnViewClickListener() {

            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                addGoodsCategory(editText.getText().toString(), pID);
            }
        });
    }

    private void showRenameDialog(String pID, String catID) {
        DialogUtils.showEditDialog(this, "重命名", "请输入分类名称", new OnViewClickListener() {

            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                editGoodsCategory(editText.getText().toString(), pID, catID);
            }
        });
    }

    private void showDeleteDialog(int index) {
        DialogUtils.showConfirmDialog(this, "确定删除此分类？", new OnViewClickListener() {
            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                deleteGoodsCategory(index);
            }
        });
    }

    private void addGoodsCategory(String catName, String pID) {
        ApiServiceManager.addGoodsCategory(catName, pID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(GoodsCategoryManagerActivity.this, "添加失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(GoodsCategoryManagerActivity.this, "添加成功");
                        getResourceList();
                    }
                });
    }

    private void editGoodsCategory(String catName, String pID, String catID) {
        ApiServiceManager.editGoodsCategory(catName, pID, catID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(GoodsCategoryManagerActivity.this, "添加失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(GoodsCategoryManagerActivity.this, "添加成功");
                        getResourceList();
                    }
                });
    }

    public void deleteGoodsCategory(int index) {
        GoodsCategoryResponse categoryResponse = mSourceList.get(index).getData();
        if (categoryResponse != null) {
            ApiServiceManager.delGoodsCategory(categoryResponse.getCatID())
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(GoodsCategoryManagerActivity.this, "删除失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(GoodsCategoryManagerActivity.this, "删除成功");
                            getResourceList();
                        }
                    });
        }
    }
}