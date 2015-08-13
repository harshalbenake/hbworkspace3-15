package com.tablayout_as;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_pager_adapter=(Button)findViewById(R.id.btn_pager_adapter);
        Button btn_fragment_adapter=(Button)findViewById(R.id.btn_fragment_adapter);

        btn_pager_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PagerAdapterActivity.class);
                startActivity(intent);
            }
        });

        btn_fragment_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FragmentPagerAdapterActivity.class);
                startActivity(intent);
            }
        });
    }
}
