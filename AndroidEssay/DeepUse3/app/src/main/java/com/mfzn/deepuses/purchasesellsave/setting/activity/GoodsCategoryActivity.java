package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.titlebar.TitleBar;
import com.libcommon.tree.TreeNode;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import com.mfzn.deepuses.bean.response.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCategoryAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsUnitAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsCategoryActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.titlebar)
    TitleBar mTitleBar;

    private GoodsCategoryAdapter mAdapter;
    private List<TreeNode<GoodsCategoryResponse>> mSourceList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        mTitleBar.updateTitleBar("商品分类", "管理");
        ApiServiceManager.getGoodsCategoryList("")
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
}
