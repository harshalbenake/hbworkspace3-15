package com.dbUtils;

/**
 * This is database pojo class.
 * Created by harshalb
 */
public class DatabasePojo {

    public static String TABLENAME = "hbdemoTable";
    public static String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLENAME + " ("
//            + "_id INTEGER PRIMARY KEY, " //Don't remove this column.
            + "name VARCHAR, "
            + "surname VARCHAR, "
            + "result VARCHAR "
            + ")";
    public int name;
    public int surname;
    public int result;
}
