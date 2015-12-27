package com.example.koroy.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.koroy.myapplication.adapter.FullScreenImageAdapter;
import com.example.koroy.myapplication.adapter.GridViewImageAdapter;
import com.example.koroy.myapplication.utility.AppUtility;
import com.example.koroy.myapplication.utility.SalonGridImageLoader;

import java.util.ArrayList;

public class FullScreenActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager viewPager;
    private FullScreenImageAdapter gridViewImageAdapter;
    private ArrayList<String> urlList;
    private int position=0;
    private String salonName;
    private TextView salonNameText;
    private Typeface font;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");

        mToolbar = (Toolbar) findViewById(R.id.toolbar_full_image);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        salonName=getIntent().getStringExtra(AppUtility.SALON_NAME);

        salonNameText=(TextView)mToolbar.findViewById(R.id.salon_name);
        salonNameText.setTypeface(font);
        salonNameText.setText(salonName);

        urlList=(ArrayList<String>)getIntent().getSerializableExtra(AppUtility.IMAGE_LIST);
        position=getIntent().getIntExtra(AppUtility.IMAGE_POSITION,0);
        salonName=getIntent().getStringExtra(AppUtility.SALON_NAME);
        gridViewImageAdapter=new FullScreenImageAdapter(this,urlList,0);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(gridViewImageAdapter);
        viewPager.setCurrentItem(position);



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);


    }
}
