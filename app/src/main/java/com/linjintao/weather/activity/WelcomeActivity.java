package com.linjintao.weather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.linjintao.weather.R;

/**
 * 欢迎界面
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        //在主线程创建的Handler
        int SHOW_TIME = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, CityActivity.class);
                startActivity(i);
                finish();
            }
        }, SHOW_TIME);
    }

}

