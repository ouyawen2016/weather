package com.linjintao.weather.util;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 这是网络请求队列的单例
 */
public class RequestQueueSingleton {
    private static RequestQueueSingleton mQueueSingleton;
    private RequestQueue mQueue;

    public RequestQueueSingleton() {
        getRequestQueue();
    }

    public synchronized static RequestQueueSingleton getInstance() {
        if(mQueueSingleton == null) {
            mQueueSingleton = new RequestQueueSingleton();
        }
        return mQueueSingleton;
    }

    public RequestQueue getRequestQueue() {
        if(mQueue == null) {
            mQueue = Volley.newRequestQueue(MyApplication.getContext());
        }
        return mQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
