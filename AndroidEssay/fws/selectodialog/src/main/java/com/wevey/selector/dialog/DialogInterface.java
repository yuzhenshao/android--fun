package com.wevey.selector.dialog;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Weavey on 2016/12/4.
 */

public interface DialogInterface {

    interface OnLeftAndRightClickListener<T> {

        void clickLeftButton(T dialog, View view);

        void clickRightButton(T dialog, View view);
    }

    interface OnTopAndBottomClickListener<T> {

        void clickTopButton(T dialog, View view, int position);

        void clickBottomButton(T dialog, View view);
    }

    interface OnLeftAndRightClick2Listener<T> {

        void clickLeftButton(T dialog, View view);

        void clickRightButton(T dialog, View view, EditText text);
        void clickRightButton2(T dialog, View view, EditText text1, EditText text2);
    }

    interface OnRefusalLeftAndRightClickListener<T> {

        void clickLeftButton(T dialog, View view, EditText et);

        void clickRightButton(T dialog, View view);

        void addTextChanged(T dialog, EditText et, TextView tv);
    }

    interface OnDingweiJiaodiClickListener<T> {

        void clickLeftButton(T dialog, View view);

        void clickRightButton(T dialog, View view);

        void clickYouwuButton(T dialog, View view, ImageView iv, ImageView iv2);

        void clickChongtuButton(T dialog, View view, ImageView iv, ImageView iv2);
    }

    interface OnProjectClickListener<T> {

        void clickFuwuButton(T dialog, View view, ImageView iv, ImageView iv2);

        void clickGerenButton(T dialog, View view, ImageView iv, ImageView iv2);

        void clickCompleteButton(T dialog, View view);

        void clickZhuanButton(T dialog, View view, ImageView iv, TextView tv);
    }

    interface OnSingleClickListener<T> {

        void clickSingleButton(T dialog, View view);
    }

    interface OnVipClickListener<T> {

        void clickSingleButton(T dialog, View view, String money, int level_id, String name);
    }

    interface OnChoiseClickListener<T> {

        void clickSingleButton(T dialog, View view, String id, String name);
    }

    interface OnSingleClickListenerS<T> {

        void clickSingleButton(T dialog, View view,String store_id,String merchant_id);
    }

    interface OnYanJuanClickListener<T> {

        void clickSingleButton(T dialog, View view, String et);
    }

    interface OnXuanZhongClickListener<T> {

        void clickSingleButton(T dialog, View view, String id, String name,List<String> alist);
    }

    interface OnItemClickListener<T> {

        void onItemClick(T dialog, View button, int position);
    }

    interface OnShangchuanZhaopianClickListener<T> {

        void clickPaizhaoButton(T dialog, View view);

        void clickXiangceButton(T dialog, View view);

        void clickQuxiaoButton(T dialog, View view);
    }

    interface OnApplyEquTypeClickListener<T> {

        void clickPrintButton(T dialog, View view);

        void clickScanButton(T dialog, View view);

        void clickBobaoButton(T dialog, View view);
    }

    interface OnChoiceClickListener<T> {

        void clickAdd(T dialog, View view);

        void clickDelete(T dialog, View view);

        void clickDingdan(T dialog, View view);

        void clickQuxiao1(T dialog, View view);

        void clickQuxiao2(T dialog, View view);
    }

    interface OnShareClickListener<T> {

        void clickWeixinButton(T dialog, View view);

        void clickPengyouButton(T dialog, View view);

        void clickQQButton(T dialog, View view);

        void clickWeiboButton(T dialog, View view);

        void clickKongjianButton(T dialog, View view);

        void clickDuanxinButton(T dialog, View view);
    }
    interface OnZhushouClickListener<T> {

        void clickHulueButton(T dialog, View view);

        void clickYincangButton(T dialog, View view);
    }
}
