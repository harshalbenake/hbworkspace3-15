package com.fragmentcallback_as;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_fragment_add=(Button)findViewById(R.id.btn_fragment_add);
        Button btn_listner=(Button)findViewById(R.id.btn_listner);

        btn_fragment_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                FragmentThree fragmentThree=new FragmentThree();
                fragmentTransaction.replace(R.id.fragment_container,fragmentThree);
                fragmentTransaction.commit();
            }
        });

        FragmentOne fragmentOne=(FragmentOne)getSupportFragmentManager().findFragmentById(R.id.fragment_one);
        final FragmentTwo fragmentTwo=(FragmentTwo)getSupportFragmentManager().findFragmentById(R.id.fragment_two);;
        fragmentOne.setListner(new FragmentOne.Listener() {
            @Override
            public void onHitListner(String text) {
                fragmentTwo.updateText(text);
            }
        });

        btn_listner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_listner.setOnClickListener");
                FragmentOne fragmentOne=(FragmentOne)getSupportFragmentManager().findFragmentById(R.id.fragment_one);
                final FragmentTwo fragmentTwo=(FragmentTwo)getSupportFragmentManager().findFragmentById(R.id.fragment_two);;
                fragmentOne.setListner(new FragmentOne.Listener() {
                    @Override
                    public void onHitListner(String text) {
                        fragmentTwo.updateText(text);
                    }
                });
            }
        });
    }

}
