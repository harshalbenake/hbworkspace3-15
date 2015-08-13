package com.tablayout_as.Adapter;

/**
 * Created by harshalbenake on 05/06/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.tablayout_as.Fragment.FragmentOne;
import com.tablayout_as.Fragment.FragmentThree;
import com.tablayout_as.Fragment.FragmentTwo;
import java.util.ArrayList;
import java.util.List;

public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public ViewFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}