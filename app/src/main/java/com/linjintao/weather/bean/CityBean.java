package com.linjintao.weather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 这是Gson解析结果的对象
 */
public class CityBean {
    @SerializedName("district")
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CityBean() {
    }
}
