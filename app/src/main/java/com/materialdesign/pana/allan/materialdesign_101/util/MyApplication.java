package com.materialdesign.pana.allan.materialdesign_101.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by allan on 11/06/15.
 */
public class MyApplication extends Application{

    public static final String RT_API_KEY = "54wzfswsa4qmjg8hjwa64d4c&q";
    private static MyApplication myApplicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        //oncreate method getting called before everything inside the app
        //initialize the myApplicationInstance
        myApplicationInstance = this;
    }


    /**
     * a public static getter to access this MyApplication class
     * @return myApplicationInstance
     */
    public static MyApplication getMyApplicationInstance(){
        return myApplicationInstance;
    }


    /**
     *
     * @return context
     */
    public static Context getAppContext(){
        Context context = myApplicationInstance.getApplicationContext();
        return context;
    }

}
