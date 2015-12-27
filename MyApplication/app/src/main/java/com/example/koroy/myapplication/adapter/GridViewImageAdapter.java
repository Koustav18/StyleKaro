package com.example.koroy.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.koroy.myapplication.FullScreenActivity;
import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.utility.AppUtility;
import com.example.koroy.myapplication.utility.SalonGridImageLoader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by koroy on 12/6/2015.
 */
public class GridViewImageAdapter extends BaseAdapter {
    private Activity _activity;
    private ArrayList<String> urlList = new ArrayList<String>();
    private LayoutInflater inflater;
    private OutputStream output;
    SalonGridImageLoader salonGridImageLoader;
    private String salonName;


    public GridViewImageAdapter(Activity activity, ArrayList<String> urls,String salonName
                                ) {
        this._activity = activity;
        this.urlList = urls;
        inflater=activity.getLayoutInflater();
        salonGridImageLoader=new SalonGridImageLoader(activity);
        this.salonName=salonName;

    }

    @Override
    public int getCount() {
        return this.urlList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.urlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Simple functin to set a Drawable to the image View
     * @param drawable
     */
    private void setImage(Drawable drawable,ImageView mImageView)
    {
        mImageView.setBackgroundDrawable(drawable);
    }



    public class ViewHolder {
        ImageView thumbImage;

    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.image_grid_row, null);
                holder = new ViewHolder();
                holder.thumbImage = (ImageView)convertView.findViewById(R.id.image);
                convertView.setTag(holder); // set the View holder
            }
            else
                holder = (ViewHolder) convertView.getTag();
            salonGridImageLoader.display(urlList.get(position),holder.thumbImage);
            // image view click listener
            holder.thumbImage.setOnClickListener(new OnImageClickListener(position));

            return convertView;
        }

        class OnImageClickListener implements View.OnClickListener {

            int _postion;

            // constructor
            public OnImageClickListener(int position) {
                this._postion = position;
            }

            @Override
            public void onClick(View v) {
                Intent i = new Intent(_activity, FullScreenActivity.class);
                i.putExtra(AppUtility.IMAGE_POSITION,_postion);
                i.putExtra(AppUtility.IMAGE_LIST,urlList);
                i.putExtra(AppUtility.SALON_NAME,salonName);
                _activity.startActivity(i);
            }

        }
    }



