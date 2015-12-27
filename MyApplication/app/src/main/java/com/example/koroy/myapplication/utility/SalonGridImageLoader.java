package com.example.koroy.myapplication.utility;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.example.koroy.myapplication.backendtask.GenericImageDownloader;

/**
 * Created by koroy on 12/13/2015.
 */
public class SalonGridImageLoader implements ComponentCallbacks2 {
    private TCLruCache cache;

    public SalonGridImageLoader(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        int maxKb = am.getMemoryClass() * 1024;
        int limitKb = maxKb / 8; // 1/8th of total ram
        cache = new TCLruCache(limitKb);
    }

    public void display(String url, ImageView imageview) {
        //imageview.setImageResource(defaultresource);
        Bitmap image = cache.get(url);
        if (image != null) {
            imageview.setImageBitmap(image);
        }
        else {
            new DownloadImageFromUrl(url,imageview).execute();
        }
    }



    private class TCLruCache extends LruCache<String, Bitmap> {

        public TCLruCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            int kbOfBitmap = value.getByteCount() / 1024;
            return kbOfBitmap;
        }
    }

    private class DownloadImageFromUrl extends GenericImageDownloader {
        private ImageView resturantLogo;
        private String imageUrl;
        private DownloadImageFromUrl(String imageUrl,ImageView imageView){
            super(imageUrl);
            resturantLogo=imageView;
            this.imageUrl=imageUrl;
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            super.onPostExecute(bmp);
            if (bmp != null) {
                cache.put(imageUrl, bmp);
            }

            resturantLogo.setImageBitmap(bmp);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    @Override
    public void onLowMemory() {
    }

    @Override
    public void onTrimMemory(int level) {
        if (level >= TRIM_MEMORY_MODERATE) {
            cache.evictAll();
        }
        else if (level >= TRIM_MEMORY_BACKGROUND) {
            cache.trimToSize(cache.size() / 2);
        }
    }

}
