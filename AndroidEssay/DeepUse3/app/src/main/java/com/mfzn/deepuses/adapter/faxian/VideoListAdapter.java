package com.mfzn.deepuses.adapter.faxian;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.listener.VideoStateListenerAdapter;
import com.mfzn.deepuses.model.faxian.Videos;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.MyJZVideoPlayerStandard;
import com.mfzn.deepuses.utils.UIUtils;


import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.jzvd.JzvdStd;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author llmf
 * @description: 视频列表的Adapter
 */

public class VideoListAdapter extends RecyclerAdapter<Videos.DataBean, VideoListAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public VideoListAdapter(Context context) {
        super(context);
    }
    private ImageWatcherHelper iwHelper;

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        Videos.DataBean model = data.get(position);
        holder.llTitle.setVisibility(VISIBLE);
        holder.tvTitle.setText(model.getTitle());
        holder.tvAuthor.setText(model.getSource());
        holder.tvLike.setText(String.valueOf(model.getLikeNums()));
        holder.tvComment.setText(String.valueOf(model.getCommentsNums()));
        holder.tvShare.setText(String.valueOf(model.getTransNums()));

        holder.videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE);
        holder.videoPlayer.tinyBackImageView.setVisibility(GONE);

        holder.videoPlayer.titleTextView.setText("");//清除标题,防止复用的时候出现
        holder.videoPlayer.setVideoStateListener(new VideoStateListenerAdapter() {

            boolean isVideoParsing = false; //视频是否在解析的标识
            String videoUrl = ApiHelper.BASE_URL + model.getContent();

            @Override
            public void onStartClick() {

                if (!TextUtils.isEmpty(videoUrl)) {
                    //如果已经解析过
                    holder.videoPlayer.setUp(videoUrl, model.getTitle(), JzvdStd.SCREEN_WINDOW_LIST);
                    holder.videoPlayer.startVideo();
                    return;
                }

                //解析视频
                parseVideo();
            }

            private void parseVideo() {

                if (isVideoParsing) {
                    return;
                } else {
                    isVideoParsing = true;
                }

                //隐藏开始按钮 显示加载中
                holder.videoPlayer.setAllControlsVisiblity(GONE, GONE, GONE, VISIBLE, VISIBLE, GONE, GONE);
                holder.llTitle.setVisibility(GONE);

                UIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        //更改视频是否在解析的标识
                        isVideoParsing = false;

                        //准备播放
                        holder.videoPlayer.setUp(videoUrl, model.getTitle(), JzvdStd.SCREEN_WINDOW_LIST);

                        //开始播放
                        holder.videoPlayer.startVideo();
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(v, v.getTag().toString(), position);
                }
            }
        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_title)
        LinearLayout llTitle;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_like)
        TextView tvLike;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.video_player)
        MyJZVideoPlayerStandard videoPlayer;

        public MsgBusinessHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);

        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
