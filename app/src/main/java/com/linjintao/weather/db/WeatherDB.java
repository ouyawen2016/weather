package com.linjintao.weather.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.linjintao.weather.bean.CityBean;
import com.linjintao.weather.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是将全国的城市列表存进数据库
 */
public class WeatherDB {
    /**
     * 数据库名称
     */
    public static final String DB_NAME = "linjintao_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static WeatherDB mWeather;
    private SQLiteDatabase mDB;

    /**
     * 私有化构造方法
     */
    private WeatherDB() {
        WeatherOpenHelper helper = new WeatherOpenHelper(MyApplication.getContext(),
                DB_NAME, null, VERSION);
        mDB = helper.getWritableDatabase();
    }

    /**
     * 获取数据库单例
     * @return 数据库单例
     */
    public synchronized static WeatherDB getInstance() {
        if(mWeather == null) {
            mWeather = new WeatherDB();
        }
        return mWeather;
    }

    /**
     * 从数据库中读取全部城市的列表
     */
    public List<CityBean> loadCities() {
        List<CityBean> cityBeanList = new ArrayList<>();
        Cursor cursor = mDB.query("City", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                CityBean cityBean = new CityBean();
                cityBean.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                cityBeanList.add(cityBean);
            } while(cursor.moveToNext());
        }
        return cityBeanList;
    }

    /**
     * 将所有城市保存到数据库
     */
    public void saveCities(List<CityBean> list) {
        mDB.beginTransaction(); //开启事务
        if(list != null) {
            for(int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                CityBean cityBean = list.get(i);
                values.put("city_name", cityBean.getCityName());
                mDB.insert("City", null, values);
            }
        }
        mDB.endTransaction(); //结束事务
    }
}
