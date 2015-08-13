package com.databasecompilestatement_as;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.dbUtils.DBManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button insert_raw_query = (Button) findViewById(R.id.insert_raw_query);
        Button insert_compile_statement = (Button) findViewById(R.id.insert_compile_statement);
        Button save_to_sdcard = (Button) findViewById(R.id.save_to_sdcard);
        ListView listView=(ListView)findViewById(R.id.listView);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        final DBManager dbManager = new DBManager(MainActivity.this);
        insert_raw_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                dbManager.insertRawQuery(MainActivity.this);
                long diff = System.currentTimeMillis() - startTime;
                String result="insertRawQuery time diff: " + diff;
                arrayAdapter.add(String.valueOf(result));
            }
        });

        insert_compile_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                dbManager.insertCompileStatement(MainActivity.this);
                long diff = System.currentTimeMillis() - startTime;
                String result="insertCompileStatement time diff: " + diff;
                arrayAdapter.add(result);
            }
        });


        save_to_sdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatabaseToSdcard(MainActivity.this);
            }
        });
    }

    /**
     * save Database To Sdcard
     *
     * @param context
     */
    public static void saveDatabaseToSdcard(Context context) {
        try {
            InputStream myInput = new FileInputStream("/data/data/com.databasecompilestatement_as/databases/" + "hbdemodb.db");

            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + "hbdemodb.db");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                }
            }

            OutputStream myOutput = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/" + "hbdemodb.db");

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
