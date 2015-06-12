package com.materialdesign.pana.allan.materialdesign_101.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.materialdesign.pana.allan.materialdesign_101.util.MyApplication;

/**
 * Created by allan on 11/06/15.
 */
public class VolleySingleton {

    private static  VolleySingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    /**
     * private constructor to avoid other classes to construct/initialize this class
     * initialize here the requestQueue and imageLoader
     */
    private  VolleySingleton(){
        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> cache = new LruCache<>((int) ((Runtime.getRuntime().maxMemory()/1024)/8));

            @Override
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s,bitmap);
            }
        });
    }


    /**
     *
     * @return VolleySingleton instance
     */
    public synchronized  static VolleySingleton getInstance(){
        if(instance == null){
            instance = new VolleySingleton();
        }
        return instance;
    }


    /**
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }


    /**
     *
     * @param request
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> request){

        getRequestQueue().add(request);
    }
}
