package com.linjintao.weather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 这是Gson解析得到三个对象之一
 */
public class WeatherInformationBean {
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    @SerializedName("city")
    private String cityName;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    private String temperature;


    public String getPublishData() {
        return publishData;
    }

    public void setPublishData(String publishData) {
        this.publishData = publishData;
    }
    @SerializedName("date_y")
    private String publishData;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    private String weather;

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }
    @SerializedName("dressing_index")
    private String dress;

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }
    @SerializedName("uv_index")
    private String uv;

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }
    @SerializedName("comfort_index")
    private String comfort;

    public String getWash() {
        return wash;
    }

    public void setWash(String wash) {
        this.wash = wash;
    }
    @SerializedName("wash_index")
    private String wash;

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }
    @SerializedName("travel_index")
    private String travel;

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
    @SerializedName("exercise_index")
    private String exercise;

    public WeatherInformationBean() {
    }

}
