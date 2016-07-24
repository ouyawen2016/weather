package com.linjintao.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.linjintao.weather.service.NotificationService;

/**
 * Created by on 2016/7/21.
 * <p/>
 * /**
 * 这是接受服务的广播
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, NotificationService.class);
        context.startService(i);
    }

}


