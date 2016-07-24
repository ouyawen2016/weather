package com.linjintao.weather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.linjintao.weather.R;
import com.linjintao.weather.adapter.FutureAdapter;
import com.linjintao.weather.db.WeatherSharedPre;

import java.util.ArrayList;
import java.util.Map;

/**
 * 未来天气信息
 */
public class CityFutureDay extends Activity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.future_city);

        initListView(); //初始化ListView
    }

    /**
     * 初始化ListView
     */
    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.city_lv_future);
        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) WeatherSharedPre.
                showFutherFromPre();
        FutureAdapter futhreDayAdapter = new FutureAdapter(this, list);
        listView.setAdapter(futhreDayAdapter);
    }
}
