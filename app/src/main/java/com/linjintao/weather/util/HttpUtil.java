package com.linjintao.weather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

///**
// * 这是发送访问网络请求的工具
// */
public class HttpUtil {
//    /**
//     * 使用HttpClient来获取要查询的城市天气信息
//     *
//     * @param cityName 城市名字
//     * @return 从服务器得到的字符串
//     */
//    public static String sendHttpRequest(String cityName) {
//        String response = null;
//        HttpClient client = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("http://v.juhe.cn/weather/index?format=2&cityname="
//                + cityName + "&key=3256ea25c27e10502962da950edf0436");
//        try {
//            HttpResponse httpResponse = client.execute(httpGet);
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                HttpEntity entity = httpResponse.getEntity();
//                response = EntityUtils.toString(entity, "utf-8");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //关闭流
//            client.getConnectionManager().shutdown();
//        }
//        return response;
//    }
//
//    /**
//     * 通过HttpClient来获取全国城市列表的字符串
//     *
//     * @return json数据字符串
//     */
//    public static String sendCityList() {
//        String response = null;
//        HttpClient client = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("http://v.juhe.cn/weather/citys?key=3256ea25c27e10502962da950edf0436");
//        try {
//            HttpResponse httpResponse = client.execute(httpGet);
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                HttpEntity entity = httpResponse.getEntity();
//                response = EntityUtils.toString(entity, "utf-8");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //关闭流
//            client.getConnectionManager().shutdown();
//        }
//        return response;
//    }
    /**
     * 使用HttpURLConnection来获取要查询的城市天气信息
     *
     * @param cityName 城市名字
     * @return 从服务器得到的字符串
     */
    public static String sendRequestByConnection(String cityName) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String response = null;
        try {
            String str = "http://v.juhe.cn/weather/index?format=2&cityname=" +
                    cityName + "&key=3256ea25c27e10502962da950edf0436";
            String send = URLEncoder.encode(str, "utf-8");
            URL url = new URL(send);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(7000);
            connection.setReadTimeout(7000);
            InputStream in = connection.getInputStream();
            //对获取到的输入流进行读写
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            response = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    /**
     * 用HttpURLConnection来获取全国城市列表的Json字符串
     *
     * @return 全国城市的json字符串
     */
    public static String cityListByHttpClient() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String response = null;
        try {
            String str = "http://v.juhe.cn/weather/citys?key=3256ea25c27e10502962da950edf0436";
            String send = URLEncoder.encode(str, "utf-8");
            URL url = new URL(send);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(7000);
            connection.setConnectTimeout(7000);
            InputStream in = connection.getInputStream();
            //对得到的流进行读取
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            response = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    /**
     * 判断是不是有网络
     *
     * @return 有网络放回true， 无网络就返回false
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager conn = (ConnectivityManager) MyApplication.getContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d("CR7", "info之前");
        NetworkInfo info = conn.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }
}

