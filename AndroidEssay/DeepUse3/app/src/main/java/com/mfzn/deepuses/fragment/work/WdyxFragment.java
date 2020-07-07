package com.mfzn.deepuses.fragment.work;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.work.WdyxPresent;
import com.wevey.selector.dialog.NoTitleAndDialog;

import butterknife.OnClick;

public class WdyxFragment extends BaseMvpFragment<WdyxPresent> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wdyx;
    }

    @Override
    public WdyxPresent newP() {
        return new WdyxPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
                                Uri uri = Uri.parse(ApiHelper.B_LOGIN_URL);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .build()
                        .show();
                break;
        }
    }

}
