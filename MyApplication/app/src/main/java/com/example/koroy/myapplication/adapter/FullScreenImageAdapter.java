package com.example.koroy.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.utility.SalonGridImageLoader;
import com.example.koroy.myapplication.utility.TouchImageView;

import java.util.ArrayList;

/**
 * Created by koroy on 12/14/2015.
 */
public class FullScreenImageAdapter extends PagerAdapter {

    private Activity mContext;
    private ArrayList<String> imageUrls;
    int pos;
    private LayoutInflater inflater;
    private SalonGridImageLoader salonGridImageLoader;

    public FullScreenImageAdapter(Activity context,ArrayList<String> imageUrls,int position){
        this.mContext=context;
        this.imageUrls=imageUrls;
        this.pos=position;
        salonGridImageLoader=new SalonGridImageLoader(context);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TouchImageView imgDisplay;

        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen, container,
                false);

        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);

        salonGridImageLoader.display(imageUrls.get(position),imgDisplay);

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
