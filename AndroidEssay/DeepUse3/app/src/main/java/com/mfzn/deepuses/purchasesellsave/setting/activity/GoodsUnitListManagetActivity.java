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
import com.libcommon.slidemenu.MenuHelper;
import com.libcommon.slidemenu.MenuItemClickListener;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.CommodityRequest;
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
public class GoodsUnitListManagetActivity extends BasicListActivity<GoodsUnitResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("商品单位", R.mipmap.icon_titlebar_add);
    }

    @Override
    protected void getResourceList() {
        ApiServiceManager.getGoodsUnitList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<GoodsUnitResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());

                    }

                    @Override
                    public void onNext(HttpResult<List<GoodsUnitResponse>> reuslt) {
                        if (reuslt != null && !ListUtil.isEmpty(reuslt.getRes())) {
                            refreshSource(reuslt.getRes());
                        } else {
                            showErrorView(reuslt.getErrorMsg());
                        }
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        GoodsUnitAdapter mAdapter = new GoodsUnitAdapter(mSourceList, true);
        MenuHelper.attach(recyclerView, new MenuHelper.MenuEnableDecider() {
            @Override
            public boolean enable(int position) {
                return true;
            }
        });

        mAdapter.setOnMenuItemClickListener(new MenuItemClickListener() {
            @Override
            public void onClick(int index, View view) {
                showDeleteDialog(index);
            }
        }, R.id.cancel);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showEditDialog(position);
            }
        });
        return mAdapter;
    }

    @Override
    protected void rightPressedAction() {
        DialogUtils.showEditDialog(this, "新增单位", "请输入单位名称", new
                OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                        EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                        addGoodsUnit(editText.getText().toString());
                    }
                });
    }

    private void showEditDialog(int index) {
        DialogUtils.showEditDialog(this, "修改单位", mSourceList.get(index).getUnitName(),"请输入单位名称", new
                OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                        EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                        editGoodsUnit(index, editText.getText().toString());
                    }
                });
    }


    private void showDeleteDialog(int index) {
        DialogUtils.showConfirmDialog(this, "确定删除该单位？", new OnViewClickListener() {
            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                deleteCategoryUnit(index);
            }
        });
    }

    public void addGoodsUnit(String unitName) {
        ApiServiceManager.addGoodsUnit(unitName)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(GoodsUnitListManagetActivity.this, "修改失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(GoodsUnitListManagetActivity.this, "修改成功");
                        getResourceList();
                    }
                });
    }

    public void editGoodsUnit(int index, String unitName) {
        GoodsUnitResponse goodsUnitResponse = mSourceList.get(index);
        if (goodsUnitResponse != null) {
            ApiServiceManager.editGoodsUnit(goodsUnitResponse.getGoodsUnitID(), unitName)
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(GoodsUnitListManagetActivity.this, "修改失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(GoodsUnitListManagetActivity.this, "修改成功");
                            goodsUnitResponse.setUnitName(unitName);
                            adapter.notifyItemChanged(index);
                        }
                    });
        }
    }

    public void deleteCategoryUnit(int index) {
        GoodsUnitResponse goodsUnitResponse = mSourceList.get(index);
        if (goodsUnitResponse != null) {
            ApiServiceManager.deleteGoodsUnit(goodsUnitResponse.getGoodsUnitID())
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(GoodsUnitListManagetActivity.this, "删除失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(GoodsUnitListManagetActivity.this, "删除成功");
                            mSourceList.remove(index);
                            adapter.notifyItemRemoved(index);
                        }
                    });
        }
    }
}