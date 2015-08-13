package com.swipemenulistview_as;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import swipemenulistview.SwipeMenu;
import swipemenulistview.SwipeMenuCreator;
import swipemenulistview.SwipeMenuItem;
import swipemenulistview.SwipeMenuListView;

/**
 * This class is used for showing swipemenulistview.
 */
public class MainActivity extends Activity {

    private String[] arrData = {
            "Harshal Benake 0",
            "Harshal Benake 1",
            "Harshal Benake 2",
            "Harshal Benake 3",
            "Harshal Benake 4",
            "Harshal Benake 5",
            "Harshal Benake 6",
            "Harshal Benake 7",
            "Harshal Benake 8",
            "Harshal Benake 9",
            "Harshal Benake 10"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeMenuListView swipeMenuListView = (SwipeMenuListView) findViewById(R.id.listView);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "item1"
                SwipeMenuItem item1 = new SwipeMenuItem(getApplicationContext());
                // set item background
                item1.setBackground(new ColorDrawable(Color.parseColor("#FF9933")));
                // set item width
                item1.setWidth(100);

                // set item title
                item1.setTitle("Item 1");
                // set item title fontsize
                item1.setTitleSize(18);
                // set item title font color
                item1.setTitleColor(Color.WHITE);
                // set a icon
              //  item1.setIcon(R.drawable.ic_launcher);
                // add to menu
                menu.addMenuItem(item1);

                // create "item2"
                SwipeMenuItem item2 = new SwipeMenuItem(getApplicationContext());
                // set item background
                item2.setBackground(new ColorDrawable(Color.WHITE));
                // set item width
                item2.setWidth(100);
                // set a icon
                item2.setIcon(R.drawable.ic_launcher);
                // add to menu
                menu.addMenuItem(item2);

                // create "item3"
                SwipeMenuItem item3 = new SwipeMenuItem(getApplicationContext());
                // set item background
                item3.setBackground(new ColorDrawable(Color.parseColor("#138808")));
                // set item width
                item3.setWidth(100);
                // set a icon
                //item3.setIcon(R.drawable.ic_launcher);
                // set item title
                item3.setTitle("Item 3");
                // set item title fontsize
                item3.setTitleSize(18);
                // set item title font color
                item3.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(item3);
            }
        };

        // set creator
        swipeMenuListView.setMenuCreator(creator);

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(MainActivity.this, "Item 1 pressed", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Item 2 pressed", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Item 3 pressed", Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        // Right
       // swipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        // Left
        swipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);


        swipeMenuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
                System.out.println("setOnSwipeListener onSwipeStart");

            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
                System.out.println("setOnSwipeListener onSwipeEnd");

            }
        });

        ArrayList arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(arrData));
        CustomAdapter customAdapter=new CustomAdapter(arrayList);
        swipeMenuListView.setAdapter(customAdapter);
    }

    /**
     * Custom Adapter for listview.
     */
    class CustomAdapter extends BaseAdapter {
        private ArrayList<String> mData = new ArrayList<String>();
        private LayoutInflater mInflater;

        public CustomAdapter(ArrayList arrayList) {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mData=arrayList;
        }


        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            int type = getItemViewType(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.row_item, null);
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(mData.get(position));
            return convertView;
        }
    }

    public class ViewHolder {
        public TextView textView;
    }
}
