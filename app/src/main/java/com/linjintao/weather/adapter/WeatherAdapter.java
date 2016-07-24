package com.linjintao.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager的适配器
 */
public class WeatherAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentManager mManager;

    public WeatherAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragmentList = fragments;
        this.mManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     * 用于删除常用城市
     * @param fragments 删除后的FragmentList
     */
    public void setFragments(List<Fragment> fragments) {
        if(this.mFragmentList != null) {

            FragmentTransaction transaction = mManager.beginTransaction();

            for(Fragment fragment : this.mFragmentList) {
                transaction.remove(fragment);
            }

            transaction.commit();
            transaction = null;
            mManager.executePendingTransactions();
        }
        this.mFragmentList = fragments;
        notifyDataSetChanged();
    }
}
