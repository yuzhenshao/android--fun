package com.mfzn.deepusesSer.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sun on 2017/6/23.
 */

public class MyDuiZhangPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list;
    String[] titles;

    /**
     *
     *    带有 FragmentManager的构造
     * @param fm
     * @param list
     * @param
     */
    public MyDuiZhangPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
//        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    /**
     *    有 PagerTabStrip返回title
     */
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position];
//    }
}
