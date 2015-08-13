package com.tablayout_as.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tablayout_as.R;

/**
 * Created by harshalbenake on 21/05/15.
 */
public class FragmentThree extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        textView.setText("FragmentThree");
        return view;
    }
}
