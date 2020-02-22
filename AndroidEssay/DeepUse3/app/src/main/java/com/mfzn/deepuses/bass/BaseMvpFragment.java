package com.mfzn.deepuses.bass;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.utils.ToastUtil;
import com.shehuan.nicedialog.NiceDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.IView;
import cn.droidlover.xdroidmvp.mvp.VDelegate;
import cn.droidlover.xdroidmvp.mvp.VDelegateBase;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by wanglei on 2016/12/29.
 */

public abstract class BaseMvpFragment<P extends IPresent> extends RxFragment implements IView<P> {

    private VDelegate vDelegate;
    private P p;
    protected Activity context;
    private View rootView;
    protected LayoutInflater layoutInflater;

    private RxPermissions rxPermissions;

    private Unbinder unbinder;

    private NiceDialog mLoadDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getP();

        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        bindEvent();
        initData(savedInstanceState);
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
        }
        if (p != null) {
            if (!p.hasV()) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();

        p = null;
        vDelegate = null;
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(getActivity());
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void bindEvent() {

    }

    public void showDialog() {
        try {
            if (mLoadDialog == null) {
                mLoadDialog = NiceDialog.init().setLayoutId(R.layout.layout_loading);
            }

            mLoadDialog.setWidth(100)
                    .setHeight(100)
                    .setDimAmount(0)
                    .show(getChildFragmentManager());
        } catch (Exception ignored) {

        }
    }

    public void hideDialog() {
        try {
            if (mLoadDialog != null) {
                mLoadDialog.dismiss();
            }
        } catch (Exception ignored) {

        }
    }

    public void showMessage(String msg) {
        ToastUtil.showToast(context, msg);
    }

    public void showError(NetError error) {
        hideDialog();
        if (error != null) {
            switch (error.getType()) {
                case NetError.ParseError:
                    showMessage("数据解析异常");
                    break;

                case NetError.AuthError:
                    showMessage("身份验证异常,请重新登录");
//                    UserHelper.logout();
//                    UserHelper.setOut(true);
                    Router.newIntent(context).to(LoginActivity.class).launch();
                    break;

                case NetError.BusinessError:
                    showMessage(error.getMessage());
                    break;

                case NetError.NoConnectError:
                    showMessage("网络无连接");
                    break;

                case NetError.NoDataError:
                    showMessage("数据为空");
                    break;

                case NetError.OtherError:
                    showMessage("其他异常");
                    break;
                default:
                    break;
            }
//            contentLayout.showError();
        }
    }
}
