package com.linjintao.weather.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.linjintao.weather.R;
import com.linjintao.weather.activity.CityActivity;
import com.linjintao.weather.activity.CityChoose;
import com.linjintao.weather.activity.CityFutureDay;
import com.linjintao.weather.activity.CityManager;
import com.linjintao.weather.bean.FutureWeatherBean;
import com.linjintao.weather.bean.GsonResultBean;
import com.linjintao.weather.bean.SkyBean;
import com.linjintao.weather.bean.WeatherInformationBean;
import com.linjintao.weather.bean.WeatherResultBean;
import com.linjintao.weather.db.WeatherSharedPre;
import com.linjintao.weather.util.GsonRequest;
import com.linjintao.weather.util.HttpUtil;
import com.linjintao.weather.util.MyApplication;
import com.linjintao.weather.util.ProgressHelper;
import com.linjintao.weather.util.RequestQueueSingleton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 这是天气Fragment
 */
public class WeatherFragment extends Fragment implements View.OnClickListener {
    public static String mCityName;
    private Button mBChange, mBRefreshs, mBFuture, mBAddCity;
    private TextView mTvDate, mTvWeather, mTvtemperature, mTvHumid, mTvWindyPower,
            mTvWindyDirection, mTvDressing, mTvUv, mTvComfort, mTvWash,
            mTvTravel, mTvExercise, mTvTitleName;
    private ArrayList<String> mDataList = null; //城市列表

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setButtonListener(); //注册监听

                if (new File("data/data/com.linjintao.weather/shared_prefs/" +
                "weatherInfo.xml").exists()) {
            WeatherInformationBean wif = WeatherSharedPre.showWeatherFromPre();
            SkyBean sb = WeatherSharedPre.showSkyFromPre();
            showWeather(wif, sb);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_fragment, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        mBChange = (Button) view.findViewById(R.id.city_b_change);
        mBRefreshs = (Button) view.findViewById(R.id.city_b_refreshs);
        mBFuture = (Button) view.findViewById(R.id.city_b_future);
        mBAddCity = (Button) view.findViewById(R.id.city_b_add);

        mTvTitleName = (TextView) view.findViewById(R.id.city_tv_title);
        mTvTitleName.setText("请选择城市...");
        mTvDate = (TextView) view.findViewById(R.id.city_tv_date);
        mTvWeather = (TextView) view.findViewById(R.id.city_tv_weather);
        mTvtemperature = (TextView) view.findViewById(R.id.city_tv_temperature);
        //各种其他
        mTvWindyDirection = (TextView) view.findViewById(R.id.city_tv_wind1_text);
        mTvWindyPower = (TextView) view.findViewById(R.id.city_tv_wind2_text);
        mTvHumid = (TextView) view.findViewById(R.id.city_tv_humid_text);
        mTvDressing = (TextView) view.findViewById(R.id.city_tv_clother_text);
        mTvUv = (TextView) view.findViewById(R.id.city_tv_uv_text);
        mTvComfort = (TextView) view.findViewById(R.id.city_tv_comfort_text);
        mTvWash = (TextView) view.findViewById(R.id.city_tv_wash_text);
        mTvTravel = (TextView) view.findViewById(R.id.city_tv_travel_text);
        mTvExercise = (TextView) view.findViewById(R.id.city_tv_excise_text);
    }

    /**
     * 注册监听
     */
     private void setButtonListener() {
         mBChange.setOnClickListener(this);
         mBRefreshs.setOnClickListener(this);
         mBFuture.setOnClickListener(this);
         mBAddCity.setOnClickListener(this);
     }
    /**
     * 展示天气
     *
     * @param wif 天气对象
     */
    private void showWeather(WeatherInformationBean wif, SkyBean skyBean) {
        mTvTitleName.setText(wif.getCityName());
        mTvDate.setText(wif.getPublishData());
        mTvWeather.setText(wif.getWeather());
        mTvtemperature.setText(wif.getTemperature());
        mTvExercise.setText(wif.getExercise());
        mTvDressing.setText(wif.getDress());
        mTvComfort.setText(wif.getComfort());
        mTvUv.setText(wif.getUv());
        mTvTravel.setText(wif.getTravel());
        mTvWash.setText(wif.getWash());

        mTvWindyDirection.setText(skyBean.getWindyDirection());
        mTvWindyPower.setText(skyBean.getWindPower());
        mTvHumid.setText(skyBean.getHumid());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_b_change:

                mDataList = CityActivity.mDataList; // 得到城市列表
                Intent intent = new Intent(getActivity(), CityChoose.class);
                intent.putStringArrayListExtra("cityList", mDataList);
                startActivityForResult(intent, 1);
                break;

            case R.id.city_b_add:

                if (new File("data/data/com.linjintao.weather/shared_prefs/city.xml").exists()) {
                        ArrayList<String> cities = (ArrayList<String>) WeatherSharedPre.
                                showFavoriteCity();
                    Intent intet = new Intent(getActivity(), CityManager.class);
                    intet.putStringArrayListExtra("cityList", cities);
                    startActivityForResult(intet, 2);
                } else {
                    Intent intet = new Intent(getActivity(), CityManager.class);
                    startActivityForResult(intet, 2);
                }
                break;
            case R.id.city_b_future:
                //判断是不是点击了查看未来七天天气的按钮
                if (mTvTitleName.getText().toString().equals("请选择城市...")) {
                    Toast.makeText(MyApplication.getContext(), "请先选择城市!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent mi = new Intent(MyApplication.getContext(), CityFutureDay.class);
                    startActivity(mi);
                }
                break;
            case R.id.city_b_refreshs:
                //判断是不是点击了刷新的按钮
                if (mTvTitleName.getText().toString().equals("请选择城市...")) {
                    Toast.makeText(MyApplication.getContext(), "请选择城市", Toast.LENGTH_SHORT).show();
                } else if (HttpUtil.isNetworkConnected()) {
                    ProgressHelper progressHelper = new ProgressHelper(new ProgressDialog(getActivity()));
                    progressHelper.showProgressDialog();
                    queryWeatherFromServer(mTvTitleName.getText().toString());
                    progressHelper.closeProgressDialog();
                } else {
                    Toast.makeText(MyApplication.getContext(), "请打开网络", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    mCityName = data.getStringExtra("cityName");
                    ArrayList<String> cities = new ArrayList<>();
                    cities.add(mCityName);
                    WeatherSharedPre.saveFavoriteCity(cities);
                    queryWeatherFromServer(mCityName);
                    break;
                }
            case 2:
                if (resultCode == getActivity().RESULT_OK) {
                    CityActivity.mFragments.add(WeatherFragment.newInstance());
                    CityActivity.mWeatherAdapter.notifyDataSetChanged();
                    String name = data.getStringExtra("cityName");
                    queryWeatherFromServer(name);
                }
            default:
                break;
        }
    }

    /**
     * 获取天气信息数据，并保存到本地中
     * @param cityName 要查询天气信息的城市
     */
    private void queryWeatherFromServer(String cityName) {
        String url = "http://v.juhe.cn/weather/index?format=2&cityname=" +
                cityName + "&key=3256ea25c27e10502962da950edf0436";
        GsonRequest<GsonResultBean> request = new GsonRequest<>(url,
                GsonResultBean.class, new Response.Listener<GsonResultBean>() {
            @Override
            public void onResponse(GsonResultBean gsonResultBean) {
                WeatherResultBean weaRestBean = gsonResultBean.getResult();
                SkyBean sky = weaRestBean.getSkyInformation();
                WeatherInformationBean wfb = weaRestBean.getWeather();
                List<FutureWeatherBean> fwb = weaRestBean.getFutureWeather();

                showWeather(wfb, sky); //更新天气

                //将天气和天空信息存进文件
                WeatherSharedPre.saveWeatherInfo(sky, wfb);
                WeatherSharedPre.saveFutherInfo(fwb);

                Toast.makeText(MyApplication.getContext(), "刷新成功！", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueueSingleton.getInstance().addToRequestQueue(request); //添加进网络请求队列
    }
}