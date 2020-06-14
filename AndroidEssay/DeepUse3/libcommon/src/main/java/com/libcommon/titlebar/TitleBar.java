package com.libcommon.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libcommon.R;

/**
 * @author yz @date 2020-03-23
 */
public class TitleBar extends RelativeLayout implements View.OnClickListener {

    private ElementPressedListener elementListener;

    private ImageView mLeftIv;
    private LinearLayout rightContainer;
    private ImageView mRightIv;
    private TextView mRightTv;
    private TextView mCenterTv;
    private View statusBar;

    public void setElementPressedListener(ElementPressedListener elementListener) {
        this.elementListener = elementListener;
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this);
        statusBar = findViewById(R.id.status_bar);
        mLeftIv = findViewById(R.id.back_container);
        mLeftIv.setOnClickListener(this);
        rightContainer = findViewById(R.id.right_element_container);
        mRightIv = findViewById(R.id.right_img);
        mRightIv.setOnClickListener(this);
        mRightTv = findViewById(R.id.right_text);
        mRightTv.setOnClickListener(this);
        mCenterTv = findViewById(R.id.center_element);
    }

    public void updateTitleBar(String title) {
        mCenterTv.setText(title);
        rightContainer.setVisibility(GONE);
    }

    public void updateTitleBar(String title, String rightContent) {
        mCenterTv.setText(title);
        mRightTv.setText(rightContent);
        mRightTv.setVisibility(VISIBLE);
        mRightIv.setVisibility(GONE);
    }

    public void updateTitleBar(String title, int rightResImageId) {
        mCenterTv.setText(title);
        mRightIv.setImageResource(rightResImageId);
        mRightIv.setVisibility(VISIBLE);
        mRightTv.setVisibility(GONE);
    }

    public void onClick(View v) {
        if (elementListener == null) {
            return;
        }
        if (v == mLeftIv) {
            elementListener.leftPressed();
        } else if (v == mRightIv || v == mRightTv) {
            elementListener.rightPressed();
        }
    }

    public void setStatusBarHide() {
        statusBar.setVisibility(GONE);
    }
}