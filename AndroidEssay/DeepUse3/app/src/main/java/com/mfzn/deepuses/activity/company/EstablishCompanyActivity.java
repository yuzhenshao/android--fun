package com.mfzn.deepuses.activity.company;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.jiagou.ShareCodeActivity;
import com.mfzn.deepuses.activityxm.MapLocationActivity;
import com.mfzn.deepuses.adapter.company.SelectLableAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.JsonBean;
import com.mfzn.deepuses.model.company.SelectLableModel;
import com.mfzn.deepuses.present.company.EstablishCompanyPresent;
import com.mfzn.deepuses.present.company.SelectLablePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.GetJsonDataUtil;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.wevey.selector.dialog.TishiDialog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class EstablishCompanyActivity extends BaseMvpActivity<EstablishCompanyPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_est_bold)
    TextView tvEstBold;
    @BindView(R.id.et_est_company)
    EditText etEstCompany;
    @BindView(R.id.et_est_address)
    EditText etEstAddress;
    @BindView(R.id.et_est_details)
    EditText etEstDetails;
    @BindView(R.id.et_est_range)
    EditText etEstRange;
    @BindView(R.id.tv_est_err)
    TextView tvEstErr;
    @BindView(R.id.but_establish)
    Button butEstablish;

    private Thread thread;
    private OptionsPickerView pickerView2;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private String areaID;
    private String businessScope;

    private String longitude;//经度
    private String latitude;//纬度

    private String companyid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_establish_company;
    }

    @Override
    public EstablishCompanyPresent newP() {
        return new EstablishCompanyPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_establish_company));
        tvEstBold.getPaint().setFakeBoldText(true);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 子线程中解析省市区数据
                initJsonData();
            }
        });
        thread.start();

        etEstCompany.addTextChangedListener(mOnInputChangeListener);
        etEstAddress.addTextChangedListener(mOnInputChangeListener);
        etEstDetails.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.et_est_range, R.id.but_establish, R.id.et_est_address, R.id.et_est_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_est_address:
                showPickerView();
                pickerView2.show(view);
                break;
            case R.id.et_est_range:
                startActivityForResult(new Intent(this, SelectLableActivity.class), Constants.SELECT_LAVLE);
                break;
            case R.id.but_establish:
                String trim = etEstCompany.getText().toString().trim();
                String details = etEstDetails.getText().toString().trim();
                getP().establishCompany(trim,areaID,businessScope,longitude,latitude,details);
                break;
            case R.id.et_est_details:
                getRxPermissions()
                        .request(Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    startActivityForResult(new Intent(context, MapLocationActivity.class), Constants.MAP_LOCATION);
                                } else {
                                    getvDelegate().toastShort("亲，请先开启定位权限");
                                }
                            }
                        });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.SELECT_LAVLE == requestCode){
            if(data != null) {
                String name = data.getStringExtra(Constants.SELECT_LAVLE_NAME);
                businessScope = data.getStringExtra(Constants.SELECT_LAVLE_ID);
                etEstRange.setText(name);
            }
        } else if (Constants.MAP_LOCATION == requestCode) {
            if (data != null) {
//                String weizhi = data.getStringExtra(Constants.MAP_LOCATION_WEIZHI);
                longitude = data.getStringExtra(Constants.MAP_LOCATION_JINGDU);
                latitude = data.getStringExtra(Constants.MAP_LOCATION_WEIDU);
                String address = data.getStringExtra(Constants.MAP_LOCATION_ADDRESS);
                etEstDetails.setText(address);
//                etFouDetail.setText(address);
            }
        }
    }

    public void establishCompanySuccess(String sss) {
        companyid = sss;
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.ESTABLSISH);
        RxBus.getInstance().post(eventMsg);


        new TishiDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setContentText("尊敬的" + etEstCompany.getText().toString().trim() + "，云知枢免费赠送您15天售后功能试用，赶紧去体验吧！")
                .setbuttext("知道了")
                .setCanceledOnTouchOutside(false)
                .setSingleListener(new com.wevey.selector.dialog.DialogInterface.OnSingleClickListener<TishiDialog>() {
                    @Override
                    public void clickSingleButton(TishiDialog dialog, View view) {
                        Intent intent = new Intent(EstablishCompanyActivity.this, ShareCodeActivity.class);
                        intent.putExtra(Constants.COMPANY_CODE,1);
                        intent.putExtra(Constants.COMPANY_CODE_TEXT,sss);
                        startActivity(intent);
                        dialog.dismiss();
                        finish();
                    }
                })
                .build()
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(!TextUtils.isEmpty(companyid)) {
                Intent intent = new Intent(EstablishCompanyActivity.this, ShareCodeActivity.class);
                intent.putExtra(Constants.COMPANY_CODE,1);
                intent.putExtra(Constants.COMPANY_CODE_TEXT,companyid);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //获取省市区ID
    public void setAddress(String id) {
        this.areaID = id;
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(etEstCompany.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etEstAddress.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etEstDetails.getText().toString().trim())) {
                butEstablish.setEnabled(true);
                butEstablish.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butEstablish.setEnabled(false);
                butEstablish.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    private void showPickerView() {
        pickerView2 = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String pickerViewText = options1Items.get(options1).getPickerViewText();
                String s = options2Items.get(options1).get(option2);
                String s1 = options3Items.get(options1).get(option2).get(options3);
                etEstAddress.setText(pickerViewText + " " + s + " " + s1);
                getP().commitAddress(pickerViewText, s, s1);
            }
        }).setSubCalSize(15).setSubmitColor(R.color.color_203B64)//确定按钮文字颜色
                .setCancelColor(R.color.color_303133)//取消按钮文字颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCyclic(false, false, false)//设置是否循环
                .build();
        pickerView2.setPicker(options1Items, options2Items, options3Items);
        Dialog mDialog = pickerView2.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            params.width = getWindowManager().getDefaultDisplay().getWidth();//设置这个才可以全屏展示
            pickerView2.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    private void initJsonData() {//解析数据
        //注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
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
    public ArrayList<JsonBean> parseData(String result) {
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
