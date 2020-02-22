package com.mfzn.deepuses.fragment.work;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.present.work.SmPresent;
import com.mfzn.deepuses.utils.ToastUtil;
import com.wevey.selector.dialog.NoTitleAndDialog;

import butterknife.OnClick;

public class B2bsmFragment extends BaseMvpFragment<SmPresent> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sm;
    }

    @Override
    public SmPresent newP() {
        return new SmPresent();
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
                new NoTitleAndDialog.Builder(context)
                        .setHeight(0.20f)  //屏幕高度*0.23
                        .setWidth(0.65f)  //屏幕宽度*0.65
                        .setContentText("复制链接在PC端后台进行操作\n需更多服务请联系服务商")
                        .setContentTextColor(R.color.black)
                        .setContentTextSize(15)
                        .setLeftButtonText("取消")
                        .setLeftButtonTextColor(R.color.black)
                        .setRightButtonText("复制链接")
                        .setRightButtonTextColor(R.color.color_203B64)
                        .setButtonTextSize(14)
                        .setCanceledOnTouchOutside(true)
                        .setOnclickListener(new com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener<NoTitleAndDialog>() {
                            @Override
                            public void clickLeftButton(NoTitleAndDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(NoTitleAndDialog dialog, View view) {
                                getP().getMarketUrl();
                                dialog.dismiss();
                            }
                        })
                        .build()
                        .show();
                break;
        }
    }
}
