package com.fragmentcallback_as;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by harshalbenake on 21/05/15.
 */
public class FragmentTwo extends Fragment {
    TextView mtextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two,container,false);
        mtextView=(TextView)view.findViewById(R.id.textview);

        return view;
    }

    public void updateText(final String text){
        System.out.println("updateText "+text);
                mtextView.setText("Value "+text);
    }
}
