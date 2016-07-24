package com.linjintao.weather.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.linjintao.weather.bean.FutureWeatherBean;
import com.linjintao.weather.bean.SkyBean;
import com.linjintao.weather.bean.WeatherInformationBean;
import com.linjintao.weather.util.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *这是将完整的天气信息对象存进sharedPre中
 */
public class WeatherSharedPre {

    /**
     * 将天气信息读取出来
     *
     * @return 天气信息对象
     */
    public static WeatherInformationBean showWeatherFromPre() {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences
                ("weatherInfo", Context.MODE_PRIVATE);

        WeatherInformationBean weatherInformation = new WeatherInformationBean();
        weatherInformation.setCityName(preferences.getString("cityName", ""));
        weatherInformation.setPublishData(preferences.getString("date", ""));
        weatherInformation.setWeather(preferences.getString("weather", ""));
        weatherInformation.setTemperature(preferences.getString("temperature", ""));
        weatherInformation.setDress(preferences.getString("dress", ""));
        weatherInformation.setUv(preferences.getString("uv", ""));
        weatherInformation.setComfort(preferences.getString("comfort", ""));
        weatherInformation.setWash(preferences.getString("wash", ""));
        weatherInformation.setTravel(preferences.getString("travel", ""));
        weatherInformation.setExercise(preferences.getString("exercise", ""));
        return weatherInformation;
    }

    /**
     * 将天空信息读出来
     * @return 天空信息
     */
    public static SkyBean showSkyFromPre() {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences
                ("weatherInfo", Context.MODE_PRIVATE);
        SkyBean skyBean = new SkyBean();
        skyBean.setWindyDirection(preferences.getString("windyDirection", ""));
        skyBean.setWindPower(preferences.getString("windyPower", ""));
        skyBean.setHumid(preferences.getString("humid", ""));
        return skyBean;
    }

    /**
     * 将未来七天天气写进sharedPre中
     *
     * @param futureWeathers 未来七天天气的对象集合
     */
    public static void saveFutherInfo(List<FutureWeatherBean> futureWeathers) {
        SharedPreferences.Editor spe = MyApplication.getContext().getSharedPreferences("futureInfo",
                Context.MODE_PRIVATE).edit();
        FutureWeatherBean fw;
        spe.putInt("list_size", futureWeathers.size());
        for (int i = 0; i < futureWeathers.size(); i++) {
            fw = futureWeathers.get(i);
            spe.putString("week" + i, fw.getWeek());
            spe.putString("weather" + i, fw.getWeather());
            spe.putString("temperature" + i, fw.getTemperature());
        }
        spe.apply();
    }

    /**
     * 将未来七天天气信息读取出来
     *
     * @return 未来七天天气信息对象集合
     */
    public static List<Map<String, Object>> showFutherFromPre() {
        SharedPreferences sp = MyApplication.getContext().
                getSharedPreferences("futureInfo", Context.MODE_PRIVATE);
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < sp.getInt("list_size", 0); i++) {
            map = new HashMap<String, Object>();
            map.put("week", sp.getString("week" + i, ""));
            map.put("weather", sp.getString("weather" + i, ""));
            map.put("temperature", sp.getString("temperature" + i, ""));
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 将天气信息保存到sharedPre中
     * @param sky 天空情况对象
     * @param wfb 天气信息对象
     */
    public static void saveWeatherInfo(SkyBean sky, WeatherInformationBean wfb) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("weatherInfo",
                Context.MODE_PRIVATE).edit();

        String cityName = wfb.getCityName();
        String date = wfb.getPublishData();
        String weather = wfb.getWeather();
        String temperature = wfb.getTemperature();
        String dress = wfb.getDress();
        String uv = wfb.getUv();
        String comfort = wfb.getComfort();
        String wash = wfb.getWash();
        String travel = wfb.getTravel();
        String exercise = wfb.getExercise();

        String windyDirection = sky.getWindyDirection();
        String windyPower = sky.getWindPower();
        String humid = sky.getHumid();

        editor.putString("cityName", cityName);
        editor.putString("date", date);
        editor.putString("weather", weather);
        editor.putString("temperature", temperature);
        editor.putString("dress", dress);
        editor.putString("uv", uv);
        editor.putString("comfort", comfort);
        editor.putString("wash", wash);
        editor.putString("travel", travel);
        editor.putString("exercise", exercise);
        editor.putString("windyDirection", windyDirection);
        editor.putString("windyPower", windyPower);
        editor.putString("humid", humid);
        editor.apply();
    }

    public static void saveFavoriteCity(List<String> cities) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("city",
                Context.MODE_PRIVATE).edit();
        editor.putInt("size", cities.size());
        for(int i = 0; i < cities.size(); i++) {
            String name = cities.get(i);
            editor.putString("city" + i, name);
        }
        editor.apply();
    }

    public static List<String> showFavoriteCity() {
        SharedPreferences sp = MyApplication.getContext().
                getSharedPreferences("city", Context.MODE_PRIVATE);
        ArrayList<String> arrayList = new ArrayList<>();
        int size = sp.getInt("size", 0);
        for(int i = 0; i < size; i++) {
            String name = sp.getString("city" + i, "");
            arrayList.add(name);
        }
        return arrayList;
    }
}
