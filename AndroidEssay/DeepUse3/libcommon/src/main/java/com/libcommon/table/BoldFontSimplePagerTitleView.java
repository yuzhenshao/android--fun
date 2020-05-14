package com.libcommon.table;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * @author yz @date 2020-05-04
 */

public class BoldFontSimplePagerTitleView extends SimplePagerTitleView {

    private float selectSize;
    private float unSelectSize = 15;

    public BoldFontSimplePagerTitleView(Context context) {
        super(context);
        selectSize = getTextSize();
    }

    public void onSelected(int index, int totalCount) {
        this.setTextColor(this.mSelectedColor);
        setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, selectSize);
    }

    public void onDeselected(int index, int totalCount) {
        this.setTextColor(this.mNormalColor);
        setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, unSelectSize);
    }

    public void setDPTextSize(float selectSize, float unSelectSize) {
        this.selectSize = selectSize;
        this.unSelectSize = unSelectSize;
    }
}
