package com.mfzn.deepuses.purchasesellsave.capital.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.slidemenu.MenuHelper;
import com.libcommon.slidemenu.MenuItemClickListener;
import com.libcommon.titlebar.TitleBar;
import com.libcommon.titlebar.TitlebarPressedListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.IncomeExpenseTypeResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.IncomeExpenseTypeAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class IncomeExpenseTypeFragment extends BasicListFragment<IncomeExpenseTypeResponse> {

    private int type;

    public static IncomeExpenseTypeFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(ParameterConstant.CAPITAL_TYPE, type);
        IncomeExpenseTypeFragment fragment = new IncomeExpenseTypeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleBar();
    }

    @Override
    protected void getResourceList() {
        showDialog();
        type = getArguments().getInt(ParameterConstant.CAPITAL_TYPE);
        ApiServiceManager.getIncomeExpenseTypeList(type)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<IncomeExpenseTypeResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<IncomeExpenseTypeResponse>> reuslt) {
                        refreshSource(reuslt.getRes());
                    }
                });
    }

    protected void initTitleBar() {
        TitleBar mTitleBar = getActivity().findViewById(R.id.titlebar);
        if (mTitleBar != null) {
            mTitleBar.setElementPressedListener(new TitlebarPressedListener() {

                @Override
                public void leftPressed() {
                    getActivity().finish();
                }

                @Override
                public void rightPressed() {
                    DialogUtils.showEditDialog(getActivity(), "新增收入类别", "请输入类别名称", new
                            OnViewClickListener() {
                                @Override
                                public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                                    EditText editText = viewHolder.getView(com.libcommon.R.id.message);
                                    addIncomeExpenseType(editText.getText().toString());
                                }
                            });
                }
            });
        }

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        IncomeExpenseTypeAdapter mAdapter = new IncomeExpenseTypeAdapter(mSourceList);
        MenuHelper.attach(recyclerView, new MenuHelper.MenuEnableDecider() {
            @Override
            public boolean enable(int position) {
                return true;
            }
        });
        mAdapter.setOnMenuItemClickListener(new MenuItemClickListener() {
            @Override
            public void onClick(int index, View view) {
                if (index >= 0 && index < mSourceList.size()) {
                    switch (view.getId()) {
                        case R.id.delete:
                            showDeleteDialog(index);
                            break;
                        case R.id.edit:
                            showEditDialog(index);
                            break;
                    }
                }
            }
        }, R.id.edit, R.id.delete);

        return mAdapter;
    }

    private void showEditDialog(int index) {
        DialogUtils.showEditDialog(getActivity(), "修改收支类别", "请输入类别名称", new
                OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                        EditText editText = viewHolder.getView(R.id.message);
                        IncomeExpenseTypeResponse otherCostResponse = mSourceList.get(index);
                        otherCostResponse.setTypeName(editText.getText().toString());
                        editIncomeExpenseType(otherCostResponse);
                    }
                });
    }


    private void showDeleteDialog(int index) {
        DialogUtils.showConfirmDialog(getActivity(), "确定删除该类别", new OnViewClickListener() {
            @Override
            public void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view) {
                deleteIncomeExpenseType(index);
            }
        });
    }

    public void deleteIncomeExpenseType(int index) {
        IncomeExpenseTypeResponse incomeExpenseResponse = mSourceList.get(index);
        if (incomeExpenseResponse != null) {
            ApiServiceManager.delIncomeExpenseType(incomeExpenseResponse.getTypeID())
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(getActivity(), error.getMessage());
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(getActivity(), "删除成功");
                            mSourceList.remove(index);
                            adapter.notifyItemRemoved(index);
                        }
                    });
        }
    }

    public void editIncomeExpenseType(IncomeExpenseTypeResponse otherCostResponse) {
        if (otherCostResponse != null) {
            ApiServiceManager.editIncomeExpenseType(otherCostResponse)
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(getActivity(), "修改失败");
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(getActivity(), "修改成功");
                            getResourceList();
                        }
                    });
        }
    }

    private void addIncomeExpenseType(String name) {
        ApiServiceManager.addIncomeExpenseType(type, name)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(getActivity(), "新增失败");
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(getActivity(), "新增成功");
                        getResourceList();
                    }
                });
    }
}
