package com.linjintao.weather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.linjintao.weather.R;
import com.linjintao.weather.adapter.CityAdapter;
import com.linjintao.weather.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 选择城市的Activity
 */
public class CityChoose extends Activity implements View.OnClickListener {
    private EditText mEtSearch;
    private ArrayList<String> mData;
    private Button mBSure;
    private ListView mLvCityList;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.choose_city);

        mData = getIntent().getStringArrayListExtra("cityList");  // 从上一个活动中读取城市列表

        initChooseView(); //初始化控件
        CityAdapter cityAdapter = new CityAdapter(this, mData);
        mLvCityList.setAdapter(cityAdapter);

        //注册监听事件
        mLvCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (HttpUtil.isNetworkConnected()) {
                    String cityName = mData.get(i);//得到点击了的城市的名字
                    Intent intent = new Intent();
                    intent.putExtra("cityName", cityName);//将字符串传回CityActivity
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(CityChoose.this, "请打开网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        String searchCityName = mEtSearch.getText().toString(); //得到搜索框的字符串
        //判读输入的城市名字是不是存在于城市列表中，有的话就传回CityActivity中
        if (HttpUtil.isNetworkConnected()) {

            if (isCity(mData, searchCityName)) {

                Intent intent = new Intent();
                intent.putExtra("cityName", searchCityName);
                setResult(RESULT_OK, intent);
                finish();

            } else {
                Toast.makeText(CityChoose.this, "请输入正确的城市名!", Toast.LENGTH_SHORT).
                        show();
            }
        } else {
            Toast.makeText(CityChoose.this, "请先打开网络", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断查询是输入的是不是城市列表中存在的城市
     *
     * @param list 城市列表
     * @param cityName  输入的城市名字
     * @return 如果存在的话就返回true 否则返回false
     */
    public boolean isCity(List<String> list, String cityName) {
        for (int i = 0; i < list.size(); i++) {
            String city = list.get(i);
            if (city.equals(cityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化控件
     */
    private void initChooseView() {
        mEtSearch = (EditText) findViewById(R.id.choose_et_search);
        mBSure = (Button) findViewById(R.id.choose_b_sure);
        mLvCityList = (ListView) findViewById(R.id.choose_lv_citylist);
        mBSure.setOnClickListener(this);
    }
}


