package com.tablayout_as;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tablayout_as.Adapter.ViewFragmentPagerAdapter;


public class FragmentPagerAdapterActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_activity_main);
        tabSetup();
    }

    public void tabSetup(){
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        ViewFragmentPagerAdapter viewFragmentPagerAdapter = new ViewFragmentPagerAdapter(getSupportFragmentManager());
        tabLayout.setTabsFromPagerAdapter(viewFragmentPagerAdapter);
        viewPager.setAdapter(viewFragmentPagerAdapter);
        viewPager.setCurrentItem(1);
      //  tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("tab getText: " + tab.getText());
                System.out.println("tab getPosition: " + tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
