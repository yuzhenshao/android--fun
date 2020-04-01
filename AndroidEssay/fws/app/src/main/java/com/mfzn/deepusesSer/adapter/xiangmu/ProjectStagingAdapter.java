package com.mfzn.deepusesSer.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.xiangmu.StagingListModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;


/**
 * Created by sun on 2018/6/12.
 */

public class ProjectStagingAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<StagingListModel.OthersBean> others;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    public ProjectStagingAdapter(Context mContext, List<StagingListModel.OthersBean> others) {
        this.context = mContext;
        this.others = others;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_project_staging, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        StagingListModel.OthersBean othersBean = others.get(position);

        Glide.with(context).load(ApiHelper.BASE_URL + othersBean.getU_head()).into(bbnViewHolder.ivStagItemIcon);
        bbnViewHolder.tvStagItemName.setText(othersBean.getU_name());
        bbnViewHolder.tvStagItemType.setText(othersBean.getLabelName());

//        HomePageBean.DataBean.TwoBean bean = twoBeen.get(position);
//
//        bbnViewHolder.setData(bean);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String store_id = sp.getString(Constants.LOGIN_STORE_ID, "");
//                if(!TextUtils.isEmpty(store_id)){
//                    String type = twoBeen.get(position).type;
//                    if(type.equals("hbfq")){
//                        Intent intent = new Intent(context, FlowerStagesActivity.class);
//                        context.startActivity(intent);
//                    }else if(type.equals("wlxy")){
//                        Intent intent = new Intent(context, FutureCumpusActivity.class);
//                        context.startActivity(intent);
//                    }
//                }else{
//                    PublicDialog.getPublicDialog().noPermissions((Activity) context);
//                }
//
//
////                else{
////                    String title = twoBeen.get(position).title;
////                    String url = twoBeen.get(position).url;
////                    Intent intent = new Intent(context, WebviewActivity.payclass);
////                    intent.putExtra(Constants.ME_WEBVIEW_TITLE,title);
////                    intent.putExtra(Constants.ME_WEBVIEW_URL,url);
////                    context.startActivity(intent);
////                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return others.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_stag_item_icon)
        RoundImageView ivStagItemIcon;
        @BindView(R.id.tv_stag_item_name)
        TextView tvStagItemName;
        @BindView(R.id.tv_stag_item_type)
        TextView tvStagItemType;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }

//        public void setData(HomePageBean.DataBean.TwoBean bean) {
//            //已得到数据了
//            //设置适配器
//            //初始化应用列表
//            Glide.with(context).load(bean.icon).into(iv_more_icon);
//            tv_more_name.setText(bean.title);
//        }
    }
}
