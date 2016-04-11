package com.hehejxw.sunshine.Application;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by XiaowenJiang on 4/8/16.
 */
public class Weather extends Application {
    private static final String TAG =Weather.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Weather mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //initialize global request queue
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);
            }
        });
        //initialize JodaTime package
        JodaTimeAndroid.init(this);
    }

    public static synchronized Weather getmInstance() {
        if(mInstance==null) mInstance = new Weather();
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public <T> void add(Request<T> REQ)
    {
        REQ.setTag(TAG);
        getRequestQueue().add(REQ);
    }

    public void cancel(){
        mRequestQueue.cancelAll(TAG);
    }
}
