package com.linjintao.weather.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.linjintao.weather.R;
import com.linjintao.weather.activity.CityActivity;
import com.linjintao.weather.activity.WelcomeActivity;
import com.linjintao.weather.bean.WeatherInformationBean;
import com.linjintao.weather.db.WeatherSharedPre;
import com.linjintao.weather.receiver.AlarmReceiver;
import com.linjintao.weather.util.HttpUtil;


/**
 * 这是后台定时更新的服务
 */
public class NotificationService extends Service {
    private static final int m_NOTIFICATION_ID = 1;
    private static final int m_NO_NETWORK_ID = 2;
    private String mCityName;
    private static boolean mFIRST_WARITE_OR_NOT = false; //用于处理第一次service写进文件时滞后的标志

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCityName = CityActivity.mCityName; //获取CityActivity的名字
        if (HttpUtil.isNetworkConnected()) {
            if (mFIRST_WARITE_OR_NOT) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        WriteInSharedToUpdate();
                        notifyUpdateWeather(); //前台服务
                        updateWeatherClock(); //定时器
                    }
                }).start();
            } else {
                notifyUpdateWeather(); //前台服务
                updateWeatherClock(); //定时器
                mFIRST_WARITE_OR_NOT = true;
            }
        } else {
            notifyNoNetwork();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWeatherClock() {
        //设置定时器
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int sixHour = 1000 * 60 * 60 * 6;
        long triggerTime = SystemClock.elapsedRealtime() + sixHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i,
                PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent);
    }

    private void notifyUpdateWeather() {
        WeatherInformationBean weatherInformationBean = WeatherSharedPre.showWeatherFromPre();
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("天气更新啦");
        builder.setSmallIcon(R.drawable.app);
        builder.setContentTitle(weatherInformationBean.getPublishData());
        builder.setContentInfo(weatherInformationBean.getTemperature());
        builder.setContentText(weatherInformationBean.getWeather());
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(false);
        //设置点击前台服务启动app
        Intent intent = new Intent(this, WelcomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        startForeground(m_NOTIFICATION_ID, notification);
    }

    /**
     * 没有网络时的后台通知
     */
    private void notifyNoNetwork() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("当前没有网络");
        builder.setContentTitle("请连接网络再试图更新天气");
        builder.setSmallIcon(R.drawable.app);
        builder.setWhen(System.currentTimeMillis());
        Notification notifi = builder.build();
        manager.notify(m_NO_NETWORK_ID, notifi);
    }

    /**
     * 访问网络来将数据写进SharedPre中，实现更新
     */
//    private void WriteInSharedToUpdate() {
////        String respon = HttpUtil.sendHttpRequest(mCityName);
//        WeatherInformationBean weaInfo = JsonAnalyze.jsonWeatherInformation(respon);
//        WeatherSharedPre.saveWeatherInfo(weaInfo);
//        List<FutureWeatherBean> fuWea = JsonAnalyze.jsonFutureWeather(respon);
//        WeatherSharedPre.saveFutherInfo(fuWea);
//    }
}

