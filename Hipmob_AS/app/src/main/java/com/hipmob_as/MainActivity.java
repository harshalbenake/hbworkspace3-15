package com.hipmob_as;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hipmob.android.HipmobCore;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an intent
                Intent i = new Intent(MainActivity.this, com.hipmob.android.HipmobCore.class);

                // REQUIRED: set the appid to the key you're provided
                i.putExtra(HipmobCore.KEY_APPID, "7b38a535167943dc8ec228af5d4fb25d");

                // REQUIRED: pass the host user identifier.
                i.putExtra(HipmobCore.KEY_USERID, getDeviceId(MainActivity.this));

                // launch the chat window
                startActivity(i);
            }
        });
    }


    /**
     * This method return used to get device id from TelephonyManager / SECURE . <Br>
     * <Br>
     * <B> A 64-bit number (as a hex string) that is randomly generated on the devices first boot and should remain constant for the lifetime of the device. (The value may change if a factory reset is performed on the device.) </B> <br>
     * Help : http://blog.vogella.com/2011/04/11/android-unique-identifier/ <br>
     * <Br>
     *
     * @return <B>String deviceID</B>
     */
    public String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();// default actual device ID
        if (deviceId == null || deviceId.equalsIgnoreCase("")) {
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return deviceId;
    }

}
