package com.metadata_as;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMetadataValue();
    }

    private void getMetadataValue(){
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String metadataValue = bundle.getString("hbdata");
            System.out.println("API KEY : " + metadataValue);
        } catch (Exception e){
            System.out.println("Exception "+e.getMessage());
        }
    }
}
