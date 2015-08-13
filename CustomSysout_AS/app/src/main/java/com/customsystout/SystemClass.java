package com.customsystout;

import java.sql.SQLOutput;

/**
 * Created by harshalbenake on 25/05/15.
 */
public final class SystemClass{

    public static final PrintStreamClass outVariable;

    static{
        outVariable=new PrintStreamClass();
    }

    private SystemClass(){
        //Prevents this class from being instantiated.
    }

}
