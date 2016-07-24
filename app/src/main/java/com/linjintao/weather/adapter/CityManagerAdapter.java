package com.linjintao.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linjintao.weather.R;

import java.util.List;
import java.util.Map;

/**
 * 添加常用城市的适配器
 */
public class CityManagerAdapter extends BaseAdapter {
    private LayoutInflater mLayIn;
    private List<Map<String, Object>> mFavoriteCityList;

    public CityManagerAdapter(List<Map<String, Object>> list, Context context) {
        mLayIn = LayoutInflater.from(context);
        this.mFavoriteCityList = list;
    }

    @Override
    public int getCount() {
        return mFavoriteCityList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if(convertView == null) {
            convertView = mLayIn.inflate(R.layout.manager_list, null);
            vh = new ViewHolder();
            vh.textView = (TextView) convertView.findViewById(R.id.item_tv_city);
            vh.imageView = (ImageView) convertView.findViewById(R.id.item_iv_city_picture);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textView.setText(mFavoriteCityList.get(position).get("name").toString());
        vh.imageView.setImageResource(R.drawable.delete);
        return convertView;
    }

    /**
     * 缓存View
     */
    private class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }
}
