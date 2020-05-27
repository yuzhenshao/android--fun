package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.tree.MultiTreeAdapter;
import com.libcommon.tree.TreeNode;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsCategoryActivity extends BasicActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private GoodsCategoryAdapter mAdapter;
    private List<TreeNode<GoodsCategoryResponse>> mSourceList = new ArrayList<>();
    private String curCatId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        curCatId=getIntent().getStringExtra(ParameterConstant.CATEGORY_ID);
        mTitleBar.updateTitleBar("选择商品分类");
        ApiServiceManager.getGoodsCategoryList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<GoodsCategoryResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    public void onNext(HttpResult<List<GoodsCategoryResponse>> reuslt) {
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
        mAdapter.setCurCatId(curCatId);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnTreeClickedListener(new MultiTreeAdapter.OnTreeClickedListener() {

            @Override
            public void onNodeClicked(View view, TreeNode node) {
                if (node.isLeaf()) {
                    Intent intent = new Intent();
                    intent.putExtra("Id", ((GoodsCategoryResponse) node.getData()).getCatID());
                    intent.putExtra("Name", ((GoodsCategoryResponse) node.getData()).getCatName());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_list;
    }
}
