package com.mfzn.deepuses.purchasesellsave.setting.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yz @date 2020-05-04
 */
public class BannerIndicator extends View {

    private int mCellCount;
    private int currentPosition;
    private Paint mPaint;
    /**
     * 指示器小圆点半径
     */
    private int mCellRadius = 3;
    /**
     * 指示器小圆点间距
     */
    private int mCellMargin = 4;
    /**
     * 指示器小圆点激活状态的颜色
     */
    private int mIndicatorColor = Color.parseColor("#000000");

    public BannerIndicator(Context context) {
       this(context,null);
    }

    public BannerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public void setCellCount(int cellCount) {
        mCellCount = cellCount;
        invalidate();
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 重新测量当前界面的宽度
        int width = getPaddingLeft() + getPaddingRight() + mCellRadius * 2 * mCellCount + mCellMargin * (mCellCount - 1);
        int height = getPaddingTop() + getPaddingBottom() + mCellRadius * 2;
        width = resolveSize(width, widthMeasureSpec);
        height = resolveSize(height, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mCellCount; i++) {
            if (i == currentPosition) {
                mPaint.setColor(mIndicatorColor);
            } else {
                mPaint.setColor(Color.WHITE);
            }
            int left = getPaddingLeft() + i * mCellRadius * 2 + mCellMargin * i;

            canvas.drawCircle(left + mCellRadius, getHeight() / 2, mCellRadius, mPaint);
        }
    }
}
