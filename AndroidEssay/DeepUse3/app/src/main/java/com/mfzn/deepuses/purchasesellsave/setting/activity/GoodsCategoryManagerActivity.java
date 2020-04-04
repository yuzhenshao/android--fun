package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.tree.TreeNode;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCategoryAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author syz @date 2020-04-01
 */
public class GoodsCategoryManagerActivity extends BasicActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private GoodsCategoryAdapter mAdapter;
    private List<TreeNode<GoodsCategoryResponse>> mSourceList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("管理商品分类", R.mipmap.icon_titlebar_add);
        initData();
        initView();
    }

    private void initData() {
        showDialog();
        ApiServiceManager.getGoodsCategoryList("")
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<GoodsCategoryResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                    }

                    @Override
                    public void onNext(HttpResult<List<GoodsCategoryResponse>> reuslt) {
                        hideDialog();
                        if (reuslt != null && !ListUtil.isEmpty(reuslt.getRes())) {
                            initGoodsCategoryList(reuslt.getRes());
                        }
                    }
                });
    }

    private void initGoodsCategoryList(List<GoodsCategoryResponse> storeResponseList) {
        List<TreeNode<GoodsCategoryResponse>> result = new ArrayList<>();
        for (GoodsCategoryResponse res : storeResponseList) {
            TreeNode<GoodsCategoryResponse> node = new TreeNode<>(res);
            node.setLevel(0);
            result.add(node);
            resolveChildren(node, 1);
        }
        mSourceList.addAll(result);
        mAdapter.notifyDataSetChanged();
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


    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GoodsCategoryAdapter(mSourceList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void rightPressed() {
        DialogUtils.showEditDialog(this, "新增一级分类", "请输入分类名称", new OnViewClickListener() {

            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                addGoodsCategory(editText.getText().toString());
            }
        });
    }

    private void addGoodsCategory(String catName) {
        ApiServiceManager.addGoodsCategory("", catName, "")
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
                        initData();
                    }
                });
    }
}