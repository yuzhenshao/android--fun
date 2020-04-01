package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;
import com.wevey.selector.dialog.adapter.BottomListviewAdapter;
import com.wevey.selector.dialog.adapter.ChoiseBuzhouAdapter;
import com.wevey.selector.dialog.bean.BottomListviewModel;
import com.wevey.selector.dialog.bean.ObtainProcessModel;

import java.util.List;

/**
 * Created by Weavey on 2016/9/3.
 */
public class BotomListviewDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private ListView boListview;
    private TextView tv_bo_cancel;
    private TextView tv_finish;

    public BotomListviewDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.bottom_listview_dialog, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

        boListview = mDialogView.findViewById(R.id.boListview);
        tv_bo_cancel = (TextView) mDialogView.findViewById(R.id.tv_bo_cancel);
        tv_finish = (TextView) mDialogView.findViewById(R.id.tv_finish);

        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        List<BottomListviewModel> listModel = builder.getListModel();

        BottomListviewAdapter adapter = new BottomListviewAdapter(builder.getContext(),listModel);
        boListview.setAdapter(adapter);

        boListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickTopButton(BotomListviewDialog.this,
                            boListview,position);
                }
            }
        });

        tv_bo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickBottomButton(BotomListviewDialog.this,
                            tv_bo_cancel);
                }
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show() {

        mDialog.show();
    }

    public void dismiss() {

        mDialog.dismiss();
    }

    public Dialog getDialog() {

        return mDialog;
    }

    public static class Builder {

        private DialogInterface.OnTopAndBottomClickListener<BotomListviewDialog> onclickListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private List<BottomListviewModel> res;

        public Builder(Context context) {

            mContext = context;
            onclickListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;

        }

        public List<BottomListviewModel> getListModel() {
            return res;
        }

        public Builder setListModel(List<BottomListviewModel> res) {
            this.res = res;
            return this;
        }

        public Context getContext() {
            return mContext;
        }

        public DialogInterface.OnTopAndBottomClickListener<BotomListviewDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnTopAndBottomClickListener<BotomListviewDialog> onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public BotomListviewDialog build() {

            return new BotomListviewDialog(this);
        }
    }


}
