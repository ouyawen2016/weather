package com.linjintao.weather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 这是Gson解析得到结果的三个对象
 */
public class WeatherResultBean {
    public WeatherInformationBean getWeather() {
        return weather;
    }

    public void setWeather(WeatherInformationBean weather) {
        this.weather = weather;
    }

    public List<FutureWeatherBean> getFutureWeather() {
        return futureWeather;
    }

    public void setFutureWeather(List<FutureWeatherBean> futureWeather) {
        this.futureWeather = futureWeather;
    }

    public SkyBean getSkyInformation() {
        return skyInformation;
    }

    public void setSkyInformation(SkyBean skyInformation) {
        this.skyInformation = skyInformation;
    }

    @SerializedName("today")
    private WeatherInformationBean weather;
    @SerializedName("future")
    private List<FutureWeatherBean> futureWeather;
    @SerializedName("sk")
    private SkyBean skyInformation;
}
