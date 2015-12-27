package com.example.koroy.myapplication;

import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.koroy.myapplication.adapter.GridViewImageAdapter;
import com.example.koroy.myapplication.utility.AppUtility;

import java.util.ArrayList;

public class GridImageActivity extends AppCompatActivity {

    private GridViewImageAdapter gridViewImageAdapter;
    private GridView gridView;
    private Toolbar toolbar;
    private TextView gridHeader;
    private Typeface font;
    private String salonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image);
        font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");
        toolbar=(Toolbar)findViewById(R.id.toolbar_salon_image_grid);
        setSupportActionBar(toolbar);
        gridHeader=(TextView)toolbar.findViewById(R.id.salon_header_name);
        gridHeader.setTypeface(font);
        salonName=getIntent().getStringExtra(AppUtility.SALON_NAME);
        gridHeader.setText(salonName);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        gridView=(GridView)findViewById(R.id.gridView);

        gridViewImageAdapter=new GridViewImageAdapter(this,getImageList(),salonName);
        gridView.setAdapter(gridViewImageAdapter);
    }

    private ArrayList<String> getImageList(){
        ArrayList<String> urls=new ArrayList<>();
        for(int i=0;i<12;i++){
            urls.add("http://res.cloudinary.com/koustav18/image/upload/c_fit,h_192,q_60/v1449385488/salon_five_kpy46a.png");
        }

      /*  urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385478/salon_four_haujj1.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385406/salon_one_lbqef0.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385488/salon_five_kpy46a.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385478/salon_four_haujj1.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385406/salon_one_lbqef0.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385488/salon_five_kpy46a.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385478/salon_four_haujj1.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385406/salon_one_lbqef0.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385488/salon_five_kpy46a.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385478/salon_four_haujj1.jpg");
        urls.add("http://res.cloudinary.com/koustav18/image/upload/v1449385406/salon_one_lbqef0.jpg");*/

        return urls;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
