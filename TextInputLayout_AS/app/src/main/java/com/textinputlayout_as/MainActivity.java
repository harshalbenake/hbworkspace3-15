package com.textinputlayout_as;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    private EditText mEditText;
    private TextInputLayout mTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInputLayout = (TextInputLayout)findViewById(R.id.textInputLayout);
        Button button = (Button)findViewById(R.id.button);
        mEditText = (EditText)findViewById(R.id.edittext);
        mTextInputLayout.setHint("Enter Your Text"); //setting hint.

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = mEditText.getText().toString();

                if(!value.equals("HB"))
                {
                    mTextInputLayout.setError("You are not HB.");
                }
                else
                {
                    mTextInputLayout.setError(null);
                }
            }
        });

    }



}
