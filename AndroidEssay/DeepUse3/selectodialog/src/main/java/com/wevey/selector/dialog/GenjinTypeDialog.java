package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;
import com.wevey.selector.dialog.adapter.GenjinListviewAdapter;
import com.wevey.selector.dialog.bean.SelectModel;

import java.util.List;

/**
 * Created by Weavey on 2016/9/3.
 */
public class GenjinTypeDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private ListView boListview;
    private ImageView iv_gen_delete;
    private TextView tv_finish;

    public GenjinTypeDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.genjin_type_dialog, null);

        boListview = mDialogView.findViewById(R.id.genListview);
        iv_gen_delete = (ImageView) mDialogView.findViewById(R.id.iv_gen_delete);
        tv_finish = (TextView) mDialogView.findViewById(R.id.tv_finish);

        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        lp.y = 500;

        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        List<SelectModel.FollowStatusBean> listModel = builder.getListModel();

        GenjinListviewAdapter adapter = new GenjinListviewAdapter(builder.getContext(),listModel);
        boListview.setAdapter(adapter);

        boListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().onItemClick(GenjinTypeDialog.this,
                            boListview,position);
                }
            }
        });

        iv_gen_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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

        private List<SelectModel.FollowStatusBean> res;

        private DialogInterface.OnItemClickListener<GenjinTypeDialog> onclickListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;

            onclickListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;

        }

        public Context getContext() {

            return mContext;
        }

        public List<SelectModel.FollowStatusBean> getListModel() {
            return res;
        }

        public Builder setListModel(List<SelectModel.FollowStatusBean> res) {
            this.res = res;
            return this;
        }

        public DialogInterface.OnItemClickListener<GenjinTypeDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnItemClickListener<GenjinTypeDialog> onclickListener) {
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

        public GenjinTypeDialog build() {

            return new GenjinTypeDialog(this);
        }
    }


}
