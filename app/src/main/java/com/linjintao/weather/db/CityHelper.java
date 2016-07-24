package com.linjintao.weather.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.linjintao.weather.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是将城市列表写进sharedPre中
 */
public class CityHelper {
    /**
     * 将城市列表的集合写进sharedPre中
     *
     * @param arrayList 城市列表
     */
    public static void writeCityList(List<String> arrayList) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("cityList",
                Context.MODE_PRIVATE).edit();

        editor.putInt("list_size", arrayList.size()); //集合的长度

        //写进sharedPre
        for (int i = 0; i < arrayList.size(); i++) {
            editor.remove("list_" + i);
            editor.putString("list_" + i, arrayList.get(i));
        }

        editor.apply();

    }


    /**
     * 读取sharedPre中的城市列表
     *
     * @return 城市列表
     */
    public static List<String> showCityList() {
        SharedPreferences mSharedPreference1 = MyApplication.getContext().getSharedPreferences
                ("cityList", Context.MODE_PRIVATE);

        ArrayList<String> cityList = new ArrayList<String>(); //城市列表的长度
        int size = mSharedPreference1.getInt("list_size", 0);

        for (int i = 0; i < size; i++) {
            cityList.add(mSharedPreference1.getString("list_" + i, null));
        }

        return cityList;

    }
}
