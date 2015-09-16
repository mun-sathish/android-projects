package sathishapp.json.network;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import sathishapp.json.MyApplication;

/**
 * Created by Sathish Mun on 05-07-2015.
 */
public class VolleySingleton {

     private static VolleySingleton sInstance = null;
     private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;

    private VolleySingleton()
    {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());

        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            private LruCache<String,Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/1024/8));

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance()
    {
        if(sInstance == null)
        {
             sInstance = new VolleySingleton();
        }
        return sInstance;

    }

    public RequestQueue getmRequestQueue()
    {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader()
    {
        return imageLoader;
    }
}
