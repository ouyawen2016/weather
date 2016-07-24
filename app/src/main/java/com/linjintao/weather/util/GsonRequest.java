package com.linjintao.weather.util;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by on 2016/7/21.
 */
public class GsonRequest<T> extends Request<T> {
    private final Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;


    public GsonRequest(int method, Class<T> classs, String url, Response.Listener<T> ohtherListener,
                       Response.ErrorListener listener) {
        super(method,url, listener);
        this.mClass = classs;
        mGson = new Gson();
        this.mListener = ohtherListener;
    }

    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listene,
                       Response.ErrorListener errorListener) {
        this(Method.GET, clazz, url, listene, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse
                    .headers));
            return Response.success(mGson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }
}
