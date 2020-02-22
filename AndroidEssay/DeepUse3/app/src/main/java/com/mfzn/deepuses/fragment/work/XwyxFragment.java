package com.mfzn.deepuses.fragment.work;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.present.work.XwyxPresent;
import com.mfzn.deepuses.utils.ToastUtil;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.ShenqingDialog;

import butterknife.OnClick;

public class XwyxFragment extends BaseMvpFragment<XwyxPresent> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_xwyx;
    }

    @Override
    public XwyxPresent newP() {
        return new XwyxPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void getMarketUrlSuccess(HttpResult result){
        ToastUtil.showToast(getActivity(),result.getMsg());
        if (result != null && result.getRes() != null && !"".equals(result.getRes().toString())){
            String url = result.getRes().toString();
            ClipboardManager cmb = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(url );

        }
    }

    @OnClick({R.id.btn_commit})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                ShenqingDialog normalAlertDialog = new ShenqingDialog.Builder(getActivity())
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setLeftButtonText("取消")
                        .setRightButtonText("复制链接")
                        .setRightButtonTextColor(R.color.color_203B64)
                        .setTitleTextColor(R.color.color_00000000)
                        .setContentTextColor(R.color.color_00000000)
                        .setContentText("复制链接在PC端后台进行操作\n需更多服务请联系服务商")
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<ShenqingDialog>() {
                            @Override
                            public void clickLeftButton(ShenqingDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(ShenqingDialog dialog, View view) {
                                getP().getMarketUrl();
                                dialog.dismiss();
                            }
                        })
                        .build();
                normalAlertDialog.show();
                break;
        }
    }

}
