package com.linjintao.weather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.linjintao.weather.R;
import com.linjintao.weather.adapter.CityManagerAdapter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Creaton 2016/7/23.
 */
public class CityManager extends Activity implements View.OnClickListener {
    private ArrayList<Map<String, Object>> mList;
    private ListView mLvFavoriteCityList;
    private Button mBSearch;
    private EditText mEditText;
    private CityManagerAdapter mManagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.manager_city);

        initView(); //初始化控件

        mList = new ArrayList<Map<String, Object>>();

        mLvFavoriteCityList = (ListView) findViewById(R.id.manager_lv_city_list);
        mManagerAdapter = new CityManagerAdapter(mList, this);
        mLvFavoriteCityList.setAdapter(mManagerAdapter);

        mLvFavoriteCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manager_sv_search:
                String name = mEditText.getText().toString();
//                Map<String, Object> map = new HashMap<>();
//                map.put("cityName", name);
//                mList.add(map);
//                mManagerAdapter.notifyDataSetChanged();
                Intent intent = new Intent();
                intent.putExtra("cityName", name);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBSearch = (Button) findViewById(R.id.manager_sv_search);
        mBSearch.setOnClickListener(this);
        mEditText = (EditText) findViewById(R.id.manager_et_search_text);
    }
}
