package com.linjintao.weather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 这是Gson解析得到三个对象之一
 */
public class SkyBean {
    public String getWindyDirection() {
        return windyDirection;
    }

    public void setWindyDirection(String windy) {
        this.windyDirection = windy;
    }
    @SerializedName("wind_direction")
    private String windyDirection;

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }
    @SerializedName("wind_strength")
    private String windPower;

    public String getHumid() {
        return humid;
    }

    public void setHumid(String humid) {
        this.humid = humid;
    }
    @SerializedName("humidity")
    private String humid;

}
