package com.linjintao.weather.bean;

/**
 * 这是Gson解析得到三个对象之一
 */
public class FutureWeatherBean {
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    private String week;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    private String temperature;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    private String weather;

    public FutureWeatherBean() {
    }

}
