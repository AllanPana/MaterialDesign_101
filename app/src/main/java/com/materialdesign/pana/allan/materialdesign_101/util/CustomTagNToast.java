package com.materialdesign.pana.allan.materialdesign_101.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by allan on 10/06/15.
 */
public class CustomTagNToast {


    public static void setToast(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

    public static void setLog(String msg){
        Log.d("allanLog",msg);
    }
}
