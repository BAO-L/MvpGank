package com.example.bao.mvpgank.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by BAO on 2017/12/7.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {


    List<Fragment> fragments ;
    List<String> titles;

    public TabPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
