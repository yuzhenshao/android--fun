package com.mfzn.deepuses.common;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.JsonBean;
import com.mfzn.deepuses.model.company.CityModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderSalesActivity;
import com.mfzn.deepuses.utils.GetJsonDataUtil;
import com.mfzn.deepuses.utils.ObtainTime;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PickerDialogView {

    public static void showGoodSPosition(Activity activity, List<String> data, OnOptionsSelectListener listener) {
        OptionsPickerView pickerView = new OptionsPickerBuilder(activity, listener).setSubCalSize(15).setSubmitColor(R.color.color_303133)//确定按钮文字颜色
                .setCancelColor(R.color.color_606266)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCyclic(false, false, false)//设置是否循环
                .build();
        pickerView.setPicker(data, null, null);
        Dialog mDialog = pickerView.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            params.width = activity.getWindowManager().getDefaultDisplay().getWidth();
            pickerView.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pickerView.show();
    }


    public static void showTimeSelect(Activity activity, OnTimeSelectListener listener) {
        TimePickerView pickerView = new TimePickerBuilder(activity, listener)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setSubCalSize(15)
                .setSubmitColor(R.color.color_4A90E2)//确定按钮文字颜色
                .setCancelColor(R.color.color_4A90E2)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();


        Dialog mDialog = pickerView.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            params.width = activity.getWindowManager().getDefaultDisplay().getWidth();
            pickerView.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pickerView.show();
    }


    public static void showAddress(Activity activity, AddOrderSalesActivity.onCityCallBack callBack) {
        if (ListUtil.isEmpty(options1Items)) {
            initJsonData(activity);
        }
        OptionsPickerView pickerView = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String pickerViewText = options1Items.get(options1).getPickerViewText();
                String s = options2Items.get(options1).get(option2);
                String s1 = options3Items.get(options1).get(option2).get(options3);
                commitAddress(pickerViewText, s, s1, callBack);
            }
        }).setSubCalSize(15).setSubmitColor(R.color.color_203B64)//确定按钮文字颜色
                .setCancelColor(R.color.color_303133)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCyclic(false, false, false)//设置是否循环
                .build();
        pickerView.setPicker(options1Items, options2Items, options3Items);
        Dialog mDialog = pickerView.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            params.width = activity.getWindowManager().getDefaultDisplay().getWidth();
            pickerView.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pickerView.show();
    }

    public static void commitAddress(String provinceName, String cityName, String areaName, AddOrderSalesActivity.onCityCallBack callBack) {
        ApiHelper.getApiService().getAreaID(provinceName, cityName, areaName)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult<CityModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        callBack.cityModelSelected(null);
                    }

                    @Override
                    public void onNext(HttpResult<CityModel> result) {
                        callBack.cityModelSelected(result.getRes());
                    }
                });
    }

    private static List<JsonBean> options1Items = new ArrayList<>();
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private static void initJsonData(Activity activity) {//解析数据
        //注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
        String JsonData = new GetJsonDataUtil().getJson(activity, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        options1Items = jsonBean;//添加省份数据
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            options2Items.add(cityList);//添加城市数据
            options3Items.add(province_AreaList);//添加地区数据  3603004715
        }
    }

    //Gson 解析
    public static ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
