package com.tablayout_as;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by harshalbenake on 05/06/15.
 */
public class CustomDialog {
    public Button mFirstButton;
    public Button mMiddleButton;
    public Button mLastButton;
    private TextView mTitleTextView;
    private LinearLayout mMiddleLayout;
    private LinearLayout mBottomLayout;
    Dialog dialog ;
    private Context context;
    LayoutInflater inflater;
    private String mTitle;

    public CustomDialog(Activity context, String title) {
        this.context=context;
        this.mTitle=title;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=initLayout();
        dialog.setContentView(view);
    }

    /**
     * Initialize Layout
     * @return
     */
    public View initLayout() {
        inflater = LayoutInflater.from(context);
        View rowView =inflater.inflate(R.layout.custom_dialog, null);
        mTitleTextView=(TextView)rowView.findViewById(R.id.dailog_title);
        mFirstButton=(Button)rowView.findViewById(R.id.first_btn);
        mMiddleButton=(Button)rowView.findViewById(R.id.middle_btn);
        mLastButton=(Button)rowView.findViewById(R.id.last_btn);
        mMiddleLayout=(LinearLayout)rowView.findViewById(R.id.middle_layout);
        mBottomLayout=(LinearLayout)rowView.findViewById(R.id.bottom_layout);

        //set values...
        mTitleTextView.setText(mTitle);
        return rowView;
    }

    /**
     * show dialog
     */
    public void show(){
        dialog.show();
    }

    /**
     * dismiss dialog
     */
    public void dismiss(){
        dialog.dismiss();
    }



    /**
     * add view
     * @param view
     */
    public void addView(View view){
        mMiddleLayout.addView(view);
    }

    /**
     * set content view by resourceId
     * @param resourceId
     */
    public View setContentView(int resourceId){
        View view =inflater.inflate(resourceId, null);
        addView(view);
        return view;
    }
    /**
     * set content view by view
     * @param view
     */
    public void setContentView(View view){
        addView(view);
    }


    /**
     * set FirstButton
     * @param name
     */
    public void setFirstButton(String name){
        mBottomLayout.setVisibility(View.VISIBLE);
        mFirstButton.setVisibility(View.VISIBLE);
        mFirstButton.setText(name);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        params.weight=99;

        mFirstButton.setLayoutParams(params);

    }

    /**
     * set MiddleButton
     * @param name
     */
    public void setMiddleButton(String name){
        mMiddleButton.setVisibility(View.VISIBLE);
        mMiddleButton.setText(name);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        params.weight=49.5f;
        mFirstButton.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        params1.weight=49.5f;
        params1.leftMargin=2;
        mMiddleButton.setLayoutParams(params1);
    }


    /**
     * set LastButton
     * @param name
     */
    public void setLastButton(String name){
        mLastButton.setVisibility(View.VISIBLE);
        mLastButton.setText(name);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params1.weight=33;
        mFirstButton.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params2.weight=33;
        params2.leftMargin=2;
        mMiddleButton.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params3.weight=33;
        params3.leftMargin=2;
        mLastButton.setLayoutParams(params3);
    }

    /**
     * This method is used set First Button onClickListener.
     * @param onClickListener
     */
    public void setFirstButtonOnClickListener(View.OnClickListener onClickListener) {
        mFirstButton.setOnClickListener(onClickListener);
    }

    /**
     * This method is used set Second Button onClickListener.
     * @param onClickListener
     */
    public void setMiddleButtonOnClickListener(View.OnClickListener onClickListener) {
        mMiddleButton.setOnClickListener(onClickListener);
    }

    /**
     * This method is used set Third Button onClickListener.
     * @param onClickListener
     */
    public void setThirdButtonOnClickListener(View.OnClickListener onClickListener) {
        mLastButton.setOnClickListener(onClickListener);
    }

    /**
     * This method is used to set whether the dialog is canceled by Back key or not.
     * @param flag
     */
    public void setCancelable(boolean flag) {
        dialog.setCancelable(flag);
    }
}
