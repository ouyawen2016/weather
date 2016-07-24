package com.linjintao.weather.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.linjintao.weather.R;
import com.linjintao.weather.adapter.WeatherAdapter;
import com.linjintao.weather.bean.CityBean;
import com.linjintao.weather.bean.CityList;
import com.linjintao.weather.db.WeatherDB;
import com.linjintao.weather.fragment.WeatherFragment;
import com.linjintao.weather.util.GsonRequest;
import com.linjintao.weather.util.HttpUtil;
import com.linjintao.weather.util.ProgressHelper;
import com.linjintao.weather.util.RequestQueueSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气信息界面
 */
public class CityActivity extends FragmentActivity implements WeatherFragment.CallBack {
    public static String mCityName;
    private ArrayList<String> mDataList = null;
    private WeatherDB mDB;
    public static WeatherAdapter mWeatherAdapter;
    public static List<Fragment> mFragments;
    public static FragmentManager mManager;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activty_city);

        mDB = WeatherDB.getInstance(); //实例化数据库
        getCityList(); //获取城市列表

        mFragments = new ArrayList<>();
        WeatherFragment fragment = WeatherFragment.newInstance();
        mFragments.add(fragment); //添加一个FWeatherragment
        fragment.setOnCallBack(this);
        loadViewPager();
    }

    /**
     * 得到城市列表
     */
    private void getCityList() {
        ArrayList<CityBean> lb = (ArrayList<CityBean>) mDB.loadCities(); ///先从数据库中读取数据
        if (lb.size() == 0) {

            final ProgressHelper helper = new ProgressHelper(new ProgressDialog(this));
            helper.showProgressDialog(); //显示加载框

            //判断网络是不是连接了
            if (HttpUtil.isNetworkConnected()) {
                String url = "http://v.juhe.cn/weather/citys?key=3256ea25c27e10502962da950edf0436";
                GsonRequest<CityList> request = new GsonRequest<>(url, CityList.class,
                        new Response.Listener<CityList>() {
                            @Override
                            public void onResponse(CityList cityList) {

                                ArrayList<CityBean> list = (ArrayList<CityBean>) cityList.getCityList();
                                //将cityBeanList对象转成ArrayList<String>对象
                                mDataList = (ArrayList<String>) toStringCity(list);
                                mDB.saveCities(list); //将数据保存到数据库

                                helper.closeProgressDialog(); //关闭对话框
                                Toast.makeText(CityActivity.this, "加载成功！请选择城市！",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("CR7", volleyError.getMessage());
                    }
                });
                RequestQueueSingleton.getInstance().addToRequestQueue(request); //把网络请求消息添加进队列

            } else {
                Toast.makeText(this, "请打开网络", Toast.LENGTH_SHORT).show();
            }

        } else {
            mDataList = (ArrayList<String>) toStringCity(lb); //第二次启动后，直接从数据库拿城市列表
            Log.d("CR7", "我在访问数据库");
        }
    }

    /**
     * 将cityBean对象转为String类型的城市列表
     */
    public List<String> toStringCity(List<CityBean> cityBeanList) {
        if (cityBeanList != null) {
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < cityBeanList.size(); i++) {
                CityBean bean = cityBeanList.get(i);
                String s = bean.getCityName();
                stringList.add(s);
            }
            return stringList;
        }
        return null;
    }

    private void loadViewPager() {
        mManager = getSupportFragmentManager();
        mWeatherAdapter = new WeatherAdapter(mManager, mFragments);
        ViewPager mVppager = (ViewPager) findViewById(R.id.city_vp_viewpager);
        mVppager.setAdapter(mWeatherAdapter);
    }

    @Override
    public void onCallBack() {
        Intent intent = new Intent(this, CityChoose.class);
        intent.putStringArrayListExtra("cityList", mDataList);
        startActivityForResult(intent, 1);
    }
}
