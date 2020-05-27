package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.slidemenu.MenuHelper;
import com.libcommon.slidemenu.MenuItemClickListener;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.GoodsCategoryResponse;
import com.mfzn.deepuses.bean.response.GoodsUnitResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsUnitAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsUnitListActivity extends BasicActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private GoodsUnitAdapter mAdapter;
    private List<GoodsUnitResponse> mSourceList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initEvent();
    }

    private void initData() {
        mTitleBar.updateTitleBar("选择商品单位");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GoodsUnitAdapter(mSourceList, false);
        recyclerView.setAdapter(mAdapter);
        ApiServiceManager.getGoodsUnitList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<GoodsUnitResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    public void onNext(HttpResult<List<GoodsUnitResponse>> reuslt) {
                        if (reuslt != null && !ListUtil.isEmpty(reuslt.getRes())) {
                            mSourceList.addAll(reuslt.getRes());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsUnitResponse item = mSourceList.get(position);
                if (item != null) {
                    item.setSelected(!item.isSelected());
                    adapter.notifyItemChanged(position);
                    Intent intent = new Intent();
                    intent.putExtra("Id", item.getGoodsUnitID());
                    intent.putExtra("Name", item.getUnitName());
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
