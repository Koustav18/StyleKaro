package com.example.koroy.myapplication.backendtask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by koroy on 12/13/2015.
 */
public class GenericImageDownloader extends AsyncTask<String,Integer,Bitmap> {

    private String imageUrl;

    public GenericImageDownloader(String url){
        this.imageUrl=url;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url;
        BufferedOutputStream out;
        InputStream in;
        BufferedInputStream buf;
        //BufferedInputStream buf;
        try {
            url = new URL(imageUrl);
            in = url.openStream();

            /*
             * THIS IS NOT NEEDED
             *
             * YOU TRY TO CREATE AN ACTUAL IMAGE HERE, BY WRITING
             * TO A NEW FILE
             * YOU ONLY NEED TO READ THE INPUTSTREAM
             * AND CONVERT THAT TO A BITMAP
            out = new BufferedOutputStream(new FileOutputStream("testImage.jpg"));
            int i;

             while ((i = in.read()) != -1) {
                 out.write(i);
             }
             out.close();
             in.close();
             */

            // Read the inputstream
            buf = new BufferedInputStream(in);

            // Convert the BufferedInputStream to a Bitmap
            Bitmap bMap = BitmapFactory.decodeStream(buf);

            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }

            return bMap;

        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }

        return null;
    }
}
