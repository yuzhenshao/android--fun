package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.khgl.MyTaskModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.ObtainTime;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class MyTaskAdapter extends RecyclerAdapter<MyTaskModel.DataBean, MyTaskAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnPhoneItemClickListener mOnPhoneItemClickListener = null;
    private OnDelItemClickListener mOnDelItemClickListener = null;

    private Context context;

    public MyTaskAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_task, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        MyTaskModel.DataBean model = data.get(position);

        holder.tvTaItemNote.setText(model.getRemark());
        holder.tvTaItemName.setText(model.getCustomerName());
        holder.tvTaItemPhone.setText(model.getCustomerPhone());

        String time = DateUtils.stampToDateTime4(String.valueOf(model.getTaskTime()));
        String substring = time.substring(0, 6);
        String substring2 = time.substring(7, time.length());

        String time1 = ObtainTime.monthDayTime();
        if(time1.equals(substring)) {
            holder.tvTaItemDate.setText("今天");
            holder.tvTaItemTime.setTextColor(context.getResources().getColor(R.color.color_d0021b));
        }else {
            holder.tvTaItemDate.setText(substring);
            holder.tvTaItemTime.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
        }
        holder.tvTaItemTime.setText(substring2);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDelItemClickListener.onItemClick(v,position);
            }
        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ta_item_note)
        TextView tvTaItemNote;
        @BindView(R.id.tv_ta_item_name)
        TextView tvTaItemName;
        @BindView(R.id.tv_ta_item_phone)
        TextView tvTaItemPhone;
        @BindView(R.id.tv_ta_item_date)
        TextView tvTaItemDate;
        @BindView(R.id.tv_ta_item_time)
        TextView tvTaItemTime;
        @BindView(R.id.tv_del)
        TextView tv_del;

        public MsgBusinessHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnPhoneItemClickListener {
        void onItemClick(View view, String phone);
    }

    public void setOnPhoneItemClickListener(OnPhoneItemClickListener listener) {
        this.mOnPhoneItemClickListener = listener;
    }

    public interface OnDelItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnDelItemClickListener(OnDelItemClickListener listener) {
        this.mOnDelItemClickListener = listener;
    }
}
