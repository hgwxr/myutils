package skill.android.wl.myutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;

public class WheelViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
//        DatePicker picker = ((DatePicker) findViewById(R.id.picker));
        Button button = ((Button) findViewById(R.id.picker_view));
        button.setOnClickListener(this);
//        String data = Util.getTextFromAssets(this, "city.json");
//        CityBean cityBean = new Gson().fromJson(data, CityBean.class);
        final LoopView loopView = (LoopView) findViewById(R.id.loopView);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("item " + i);
        }
        //设置是否循环播放
//        loopView.setNotLoop();
        //滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (toast == null) {
                    toast = Toast.makeText(WheelViewActivity.this, "item " + index, Toast.LENGTH_SHORT);
                }
                toast.setText("item " + index);
                toast.show();
            }
        });
        //设置原始数据
        loopView.setItems(list);

        //设置初始位置
        loopView.setInitPosition(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.picker_view:
               /* if (mCityPicker==null){
                   mCityPicker= new CityPicker(WheelViewActivity.this,findViewById(R.id.rl_container))
                   .setOnCitySelectListener(new CityPicker.OnCitySelectListener() {
                       @Override
                       public void onCitySelect(String province, String city, String county) {
                           Toast.makeText(WheelViewActivity.this, province + city + county, Toast.LENGTH_SHORT).show();
                       }
                   });
                }
                mCityPicker.show();*/
               // showPickerView();
                break;
        }
    }
   /* private void showPickerView() {// 弹出选择器

      *//*  OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cityBean.getpList().get(options1).getPickerViewText()+
                        cityBean.getpList().get(options1).getcList().get(options2)+
                        cityBean.getpList().get(options1).getcList().get(options3).getaList().get(options2).getPickerViewText();

                Toast.makeText(WheelViewActivity.this,tx,Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        *//**//*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*//**//*
        pvOptions.setPicker(cityBean.getpList());//三级选择器
        pvOptions.show();*//*
    }*/

    /*private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *

         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        ||jsonBean.get(i).getCityList().get(c).getArea().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }


             * 添加城市数据

            options2Items.add(CityList);


             * 添加地区数据

            options3Items.add(Province_AreaList);
        }*//*


    }*/
}
