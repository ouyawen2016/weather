//package com.linjintao.weather.util;
//
///**
// * Created by  on 2016/7/21.
// */
//
//
//import com.linjintao.weather.bean.FutureWeatherBean;
//import com.linjintao.weather.bean.WeatherInformationBean;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * json解析
// */
//public class JsonAnalyze {
//    /**
//     * 用json解析数据
//     *
//     * @param response 从网路上得到的字符串
//     * @return 返回得到的天气信息对象
//     */
//    public static WeatherInformationBean jsonWeatherInformation(String response) {
//        WeatherInformationBean weatherInformationBean = null;
//        try {
//            if (response != null) {
//                JSONObject jsonResult = new JSONObject(response).getJSONObject("result");
//                JSONObject jsonToday = jsonResult.getJSONObject("today");
//                String cityName = jsonToday.getString("city");
//                String publishDate = jsonToday.getString("date_y");
//                String weather = jsonToday.getString("weather");
//                String temperature = jsonToday.getString("temperature");
//
//                //得到各种指数
//                String dressing = jsonToday.getString("dressing_index");
//                String uv = jsonToday.getString("uv_index");
//                String comfort = jsonToday.getString("comfort_index");
//                String wash = jsonToday.getString("wash_index");
//                String travel = jsonToday.getString("travel_index");
//                String exercise = jsonToday.getString("exercise_index");
//
//                //得到风力风向湿度
//                JSONObject object = jsonResult.getJSONObject("sk");
//                String windyPower = object.getString("wind_strength");
//                String windyDirection = object.getString("wind_direction");
//                String humid = object.getString("humidity");
//
//                weatherInformationBean = new WeatherInformationBean();
//                weatherInformationBean.setCityName(cityName);
//                weatherInformationBean.setPublishData(publishDate);
//                weatherInformationBean.setWeather(weather);
//                weatherInformationBean.setTemperature(temperature);
////                weatherInformationBean.setWindyDirection(windyDirection);
////                weatherInformationBean.setWindPower(windyPower);
////                weatherInformationBean.setHumid(humid);
//                weatherInformationBean.setDress(dressing);
//                weatherInformationBean.setUv(uv);
//                weatherInformationBean.setComfort(comfort);
//                weatherInformationBean.setWash(wash);
//                weatherInformationBean.setTravel(travel);
//                weatherInformationBean.setExercise(exercise);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return weatherInformationBean;
//    }
//
//    /**
//     * 解析未来七天天气的数据
//     *
//     * @param response 得到的字符串
//     * @return 未来天气的集合
//     */
//    public static List<FutureWeatherBean> jsonFutureWeather(String response) {
//        ArrayList<FutureWeatherBean> futureWeathers = null;
//        try {
//            if (response != null) {
//                futureWeathers = new ArrayList<FutureWeatherBean>();
//                JSONObject jsonResult = new JSONObject(response).getJSONObject("result");
//                JSONArray jsonFuture = jsonResult.getJSONArray("future");
//                //遍历对象数组
//                for (int i = 0; i < jsonFuture.length(); i++) {
//                    FutureWeatherBean futureWeather = new FutureWeatherBean();
//                    JSONObject object = jsonFuture.getJSONObject(i);
//                    String weather = object.getString("weather");
//                    String temperature = object.getString("temperature");
//                    String week = object.getString("week");
//                    futureWeather.setWeek(week);
//                    futureWeather.setWeather(weather);
//                    futureWeather.setTemperature(temperature);
//                    futureWeathers.add(futureWeather);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return futureWeathers;
//    }
//
//    /**
//     * 解析全国城市列表的数据
//     *
//     * @param response 字符串
//     * @return 全国城市的集合
//     */
//    public static List<String> jsonCity(String response) {
//        ArrayList<String> cityBeans = new ArrayList<String>();
//        try {
//            if (response != null) {
//                JSONObject jsonObject = new JSONObject(response);
//                JSONArray jsonArray = jsonObject.getJSONArray("result");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                    String s = jsonObject1.getString("district");
//                    cityBeans.add(s);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cityBeans;
//    }
//}

