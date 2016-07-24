package com.linjintao.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linjintao.weather.R;

import java.util.List;



/**
 * 城市列表的适配器
 */
public class CityAdapter extends BaseAdapter {
    private LayoutInflater mLiAdapter;
    private List<String> mData;

    /**
     * 缓存View
     */
    private class ViewHolder {
        private TextView tv;
    }

    public CityAdapter(Context context, List<String> list) {
        mLiAdapter = LayoutInflater.from(context); //根据上下文加载布局
        this.mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
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
            vh = new ViewHolder();
            convertView = mLiAdapter.inflate(R.layout.city_list, null);
            vh.tv = (TextView) convertView.findViewById(R.id.list_tv_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(mData.get(position));
        return convertView;
    }
}
