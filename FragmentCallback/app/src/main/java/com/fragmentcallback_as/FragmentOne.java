package com.fragmentcallback_as;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by harshalbenake on 21/05/15.
 */
public class FragmentOne extends Fragment{
    Listener mListener;

    public interface Listener{
        public void onHitListner(String text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one,container,false);
        final EditText editText=(EditText)view.findViewById(R.id.edittext);
        Button button=(Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null) {
                    System.out.println("editText.getText().toString() "+editText.getText().toString());
                    mListener.onHitListner(editText.getText().toString());
                }
            }
        });

        return view;
    }

    public void setListner(Listener listener){
        this.mListener=listener;
    }
}
