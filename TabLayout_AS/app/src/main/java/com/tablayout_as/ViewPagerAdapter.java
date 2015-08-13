package com.tablayout_as;

/**
 * Created by harshalbenake on 05/06/15.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater layoutInflater;
    public ViewPagerAdapter(Context context){
        this.mContext=context;
        layoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.page_item, container, false);
        container.addView(view);
        TextView title = (TextView) view.findViewById(R.id.tv_page_item);
        title.setText("Position: " + position);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}