package com.linjintao.weather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 这是Gson解析城市列表得到的总结果
 */
public class CityList {
    public List<CityBean> getCityList() {
        return mCityList;
    }

    public void setCityList(List<CityBean> CityList) {
        this.mCityList = CityList;
    }

    @SerializedName("result")
    public List<CityBean> mCityList;
}
