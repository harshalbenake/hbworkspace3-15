package com.tablayout_as;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               customTabDialog();
            }
        });
    }

    public void customTabDialog(){
        final CustomDialog customDialog=new CustomDialog(MainActivity.this, "TabLayout Dialog");
        View view=getLayoutInflater().inflate(R.layout.tablayout_main, null);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getApplicationContext());
        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
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

        customDialog.setContentView(view);
        customDialog.setFirstButton("OK");
        customDialog.setFirstButtonOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.setCancelable(false);
        customDialog.show();
    }
}
