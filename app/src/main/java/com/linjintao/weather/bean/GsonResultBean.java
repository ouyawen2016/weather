package com.linjintao.weather.bean;

/**
 * 这是用Gson解析得到的总结果
 */
public class GsonResultBean {
    public WeatherResultBean getResult() {
        return result;
    }

    public void setResult(WeatherResultBean result) {
        this.result = result;
    }

    private WeatherResultBean result;
}
