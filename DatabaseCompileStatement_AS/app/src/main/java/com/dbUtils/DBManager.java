package com.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


/**
 * This is a manager class used to manage database related information.
 * Created by harshalb
 */
public class DBManager {
    Context mContext;

    public DBManager(Context context) {
        this.mContext = context;
    }

    public void insertRawQuery(Context context) {
        ApplicationClass applicationClass = (ApplicationClass) context.getApplicationContext();
        SQLiteDatabase sqLiteDatabase = applicationClass.getWritableDatabase();

        for (int i = 0; i < 100; i++) {
            ContentValues values = new ContentValues();
            values.put("name", "rawname" + i);
            values.put("surname", "rawsurname" + i);
            values.put("result", i * i);
            sqLiteDatabase.insert(DatabasePojo.TABLENAME, null, values);
        }
    }

    public void insertCompileStatement(Context context) {
        ApplicationClass applicationClass = (ApplicationClass) context.getApplicationContext();
        SQLiteDatabase sqLiteDatabase = applicationClass.getWritableDatabase();
        String sql = "INSERT INTO " + DatabasePojo.TABLENAME + " VALUES (?,?,?);";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        sqLiteDatabase.beginTransaction();
        for (int i = 0; i < 100; i++) {
            statement.clearBindings();
            statement.bindString(1, "name" + i);
            statement.bindString(2, "surname" + i);
            statement.bindLong(3, i * i);
            statement.execute();
        }
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }

}
