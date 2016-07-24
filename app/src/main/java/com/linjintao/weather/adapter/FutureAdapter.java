package com.linjintao.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linjintao.weather.R;

import java.util.List;
import java.util.Map;

/**
 * 未来七天天气适配器
 */
public class FutureAdapter extends BaseAdapter {
    private LayoutInflater mLyFutureLayout;
    private List<Map<String, Object>> mFutureList;

    public FutureAdapter(Context context, List<Map<String, Object>> list) {
        mLyFutureLayout = LayoutInflater.from(context);
        this.mFutureList = list;
    }

    @Override
    public int getCount() {
        return mFutureList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {

            convertView = mLyFutureLayout.inflate(R.layout.future_list, null);
            vh = new ViewHolder();
            initView(vh, convertView); //初始化控件
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        setCityText(vh, position); //加载数据
        return convertView;
    }

    private class ViewHolder {
        private TextView mTvWeek;
        private TextView mTvWeather;
        private TextView mTvTemperature;
    }

    private void initView(ViewHolder vh, View convertView) {
        vh.mTvWeek = (TextView) convertView.findViewById(R.id.future_tv_week);
        vh.mTvWeather = (TextView) convertView.findViewById(R.id.future_tv_weather);
        vh.mTvTemperature = (TextView) convertView.findViewById(R.id.future_tv_temperature);
    }

    private void setCityText(ViewHolder vh, int position) {
        vh.mTvWeek.setText(mFutureList.get(position).get("week").toString());
        vh.mTvWeather.setText(mFutureList.get(position).get("weather").toString());
        vh.mTvTemperature.setText(mFutureList.get(position).get("temperature").toString());
    }
}
