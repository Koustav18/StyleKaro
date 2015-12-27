package com.example.koroy.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koroy.myapplication.adapter.CustomPagerAdapter;
import com.example.koroy.myapplication.adapter.SalonPagerAdapter;
import com.example.koroy.myapplication.model.SalonModel;
import com.example.koroy.myapplication.utility.AppUtility;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SalonPageActivity extends AppCompatActivity {

    private SalonPagerAdapter salonPagerAdapter;
    ViewPager viewPager;
    Typeface font;
    TextView addressLabel,addressValue,phoneLabel,phoneValue,serviceListLabel,rateYourStylistLabel,serviceTimingLabel,sundayLabel,sundayValue,
            mondayLabel,mondayValue,tuesdayLabel,tuesdayValue,wednesdayLabel,wednesdayValue,thursdayLabel,thursdayValue,fridayLabel,fridayValue,saturdayLabel,saturdayValue,
            uniquenessLabel,unisex,cardAccepted,serviceTax,parking,ac,homeService,needAppointment,wifi,bridal,reviewLabel,reviewOneProfileName,
            reviewOneProfileRatingLabel,reviewOneProfileRatingValue,reviewOneValue,reviewTwoProfileName,salonRating,salonInfo,
            reviewTwoProfileRatingLabel,reviewTwoProfileRatingValue,reviewTwoValue,stylistInfo,salonVoteCount,salonRatingText,salonHeaderName;
    ImageView map,reviewOneProfilePic,reviewTwoProfilePic,unisexImg,cardAcceptanceImage,serviceTaxImage,parkingImage,
            acImage,homeServiceImg,appointmentImage,wifiImg,bridalImg,seeAll,submitReview;
    Button readAllReviews;
    LinearLayout servicePics;
    ImageView stylistOne,stylistTwo,stylistThree,stylistFour,stylistFive,stylistSix,stylistSeven,stylistEight,stylistNine,
            offerIcon,shareIcon,rateIcon,favouriteIcon,phoneIcon,salonSubmitReview;
    CardView ratingStructure;
    CardView ratingStructureSalon;
    private boolean ratingStructureVisibility = false;
    private boolean ratingSalonStructureVisibility = false;
    EditText reviewField,salonReviewField;
    private AppUtility appUtility;

    //Menu Updater
    private int hot_number = 0;
    private TextView ui_hot = null;
    private SalonModel salonModel;

    private Toolbar toolbar;
    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_page);
        font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");
        mPrefs = getPreferences(MODE_PRIVATE);
        appUtility=new AppUtility(this);

        salonModel=(SalonModel)getIntent().getSerializableExtra(AppUtility.SALON_DATA);

        toolbar=(Toolbar)findViewById(R.id.toolbar_salon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        salonPagerAdapter = new SalonPagerAdapter(this);

        viewPager = (ViewPager) findViewById(R.id.salon_photo_pager);
        viewPager.setAdapter(salonPagerAdapter);

        initializeViewItems();
        salonHeaderName.setText(salonModel.getSalonName());

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGridOfImages();
            }
        });

        servicePics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGridOfImages();
            }
        });

        stylistOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createDialog();
            }
        });
        stylistTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });

        stylistThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingStructure.setVisibility(View.GONE);
                ratingStructureVisibility=false;
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMapctivity();
            }
        });

        favouriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteIcon.setImageResource(R.drawable.selected_heart);
               /* SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = mPrefs.getString("MyObject", "");
                ArrayList<SalonModel> obj = gson.fromJson(json, ArrayList<SalonModel.class>);
                Gson gson = new Gson();
                String json = gson.toJson(salonModel); // myObject - instance of MyObject
                prefsEditor.putString(AppUtility.FAVOURITE_SALON, json);
                prefsEditor.commit();*/
            }
        });

        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUtility.shareSalon();
            }
        });

        offerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SalonPageActivity.this,"There are no offers.",Toast.LENGTH_SHORT).show();
            }
        });

        rateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSalonDialog();
            }
        });

        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUtility.callSalon();
            }
        });

        salonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingSalonStructureVisibility=false;
                ratingStructureSalon.setVisibility(View.GONE);
            }
        });


    }


    private void callMapctivity(){
       Intent i = new Intent(SalonPageActivity.this,SalonMapActivity.class);
        i.putExtra(AppUtility.SALON_NAME,salonModel.getSalonName());
        startActivity(i);
    }

    private void createSalonDialog(){
        if(ratingSalonStructureVisibility){
            ratingSalonStructureVisibility=false;
            ratingStructureSalon.setVisibility(View.GONE);
        }else{
            ratingSalonStructureVisibility=true;
            ratingStructureSalon.setVisibility(View.VISIBLE);
        }
    }

    private void createDialog(){
        stylistInfo.setText("Jack Morrisson");

        if(ratingStructureVisibility){
            ratingStructure.setVisibility(View.GONE);
            ratingStructureVisibility=false;
        }else{
            ratingStructure.setVisibility(View.VISIBLE);
            ratingStructureVisibility=true;
        }

    }

    private void callGridOfImages(){
        Intent i = new Intent(SalonPageActivity.this, GridImageActivity.class);
        i.putExtra(AppUtility.SALON_NAME,salonModel.getSalonName());
        startActivity(i);
    }

    private void initializeViewItems(){
        salonHeaderName= (TextView) toolbar.findViewById(R.id.salon_header_name);
        addressLabel=(TextView)findViewById(R.id.address_label);
        addressValue=(TextView)findViewById(R.id.address);
        phoneLabel=(TextView)findViewById(R.id.phone_label);
        phoneValue=(TextView)findViewById(R.id.phone);
        serviceListLabel=(TextView)findViewById(R.id.service_list);
        rateYourStylistLabel=(TextView)findViewById(R.id.stylist_tv);
        serviceTimingLabel=(TextView)findViewById(R.id.timing_tv);
        sundayLabel=(TextView)findViewById(R.id.sunday_level);
        sundayValue=(TextView)findViewById(R.id.sunday_time);
        mondayLabel=(TextView)findViewById(R.id.monday_level);
        mondayValue=(TextView)findViewById(R.id.monday_time);
        tuesdayLabel=(TextView)findViewById(R.id.tuesday_level);
        tuesdayValue=(TextView)findViewById(R.id.tuesday_time);
        wednesdayLabel=(TextView)findViewById(R.id.wednesday_level);
        wednesdayValue=(TextView)findViewById(R.id.wednesday_time);
        thursdayLabel=(TextView)findViewById(R.id.thursday_level);
        thursdayValue=(TextView)findViewById(R.id.thursday_time);
        fridayLabel=(TextView)findViewById(R.id.friday_level);
        fridayValue=(TextView)findViewById(R.id.friday_time);
        saturdayLabel=(TextView)findViewById(R.id.saturday_level);
        saturdayValue=(TextView)findViewById(R.id.saturday_time);
        uniquenessLabel=(TextView)findViewById(R.id.special_tag_tv);
        unisex=(TextView)findViewById(R.id.unisex);
        cardAccepted=(TextView)findViewById(R.id.card_acceptance);
        serviceTax=(TextView)findViewById(R.id.service_tax);
        parking=(TextView)findViewById(R.id.parking);
        ac=(TextView)findViewById(R.id.air_condition);
        homeService=(TextView)findViewById(R.id.home_service);
        needAppointment=(TextView)findViewById(R.id.appoinment_needed);
        wifi=(TextView)findViewById(R.id.wifi);
        bridal=(TextView)findViewById(R.id.bridal);
        reviewLabel=(TextView)findViewById(R.id.reviews_tv);
        reviewOneProfileName=(TextView)findViewById(R.id.user_one_name);
        reviewOneProfileRatingLabel=(TextView)findViewById(R.id.user_one_rating_tv);
        reviewOneProfileRatingValue=(TextView)findViewById(R.id.rating_tv);
        reviewOneValue=(TextView)findViewById(R.id.review_tv);
        reviewTwoProfileName=(TextView)findViewById(R.id.user_two_name);
        reviewTwoProfileRatingLabel=(TextView)findViewById(R.id.user_two_rating_tv);
        reviewTwoProfileRatingValue=(TextView)findViewById(R.id.rating_tv_two);
        reviewTwoValue=(TextView)findViewById(R.id.review_tv_two);
        stylistInfo=(TextView)findViewById(R.id.stylist_info);
        salonVoteCount=(TextView)findViewById(R.id.salon_vote_count);
        salonRating=(TextView)findViewById(R.id.salon_rating);
        salonInfo=(TextView)findViewById(R.id.salon_info);
        salonRatingText=(TextView)findViewById(R.id.salon_rating_text);

        reviewField=(EditText)findViewById(R.id.review_field);
        salonReviewField=(EditText)findViewById(R.id.salon_review_field);

        map=(ImageView)findViewById(R.id.map);
        reviewOneProfilePic=(ImageView)findViewById(R.id.user_pic_one);
        reviewTwoProfilePic=(ImageView)findViewById(R.id.user_pic_two);
        unisexImg=(ImageView)findViewById(R.id.unisex_img);
        cardAcceptanceImage=(ImageView)findViewById(R.id.card_acceptance_img);
        serviceTaxImage=(ImageView)findViewById(R.id.service_tax_img);
        parkingImage=(ImageView)findViewById(R.id.parking_img);
        acImage=(ImageView)findViewById(R.id.ac_image);
        homeServiceImg=(ImageView)findViewById(R.id.home_service_img);
        appointmentImage=(ImageView)findViewById(R.id.appointment_img);
        wifiImg=(ImageView)findViewById(R.id.wifi_img);
        bridalImg=(ImageView)findViewById(R.id.bridal_img);
        seeAll=(ImageView)findViewById(R.id.see_all);
        stylistOne=(ImageView)findViewById(R.id.stylist_one);
        stylistTwo=(ImageView)findViewById(R.id.stylist_two);
        stylistThree=(ImageView)findViewById(R.id.stylist_three);
        submitReview=(ImageView)findViewById(R.id.submit_review);
        offerIcon=(ImageView)findViewById(R.id.offers);
        shareIcon=(ImageView)findViewById(R.id.share);
        rateIcon=(ImageView)findViewById(R.id.rate);
        favouriteIcon=(ImageView)findViewById(R.id.favourites);
        phoneIcon=(ImageView)findViewById(R.id.phone_icon);
        salonSubmitReview=(ImageView)findViewById(R.id.salon_submit_review);

        readAllReviews=(Button)findViewById(R.id.review_all_btn);

        servicePics=(LinearLayout)findViewById(R.id.service_pics);
        ratingStructure=(CardView)findViewById(R.id.review_structure);
        ratingStructureSalon=(CardView)findViewById(R.id.review_structure_salon);

        initializeFonts();
    }

    private void initializeFonts(){
        salonHeaderName.setTypeface(font);
        addressLabel.setTypeface(font);
        addressValue.setTypeface(font);
        phoneLabel.setTypeface(font);
        phoneValue.setTypeface(font);
        serviceListLabel.setTypeface(font);
        rateYourStylistLabel.setTypeface(font);
        serviceTimingLabel.setTypeface(font);
        sundayLabel.setTypeface(font);
        sundayValue.setTypeface(font);
        mondayLabel.setTypeface(font);
        mondayValue.setTypeface(font);
        tuesdayLabel.setTypeface(font);
        tuesdayValue.setTypeface(font);
        wednesdayLabel.setTypeface(font);
        wednesdayValue.setTypeface(font);
        thursdayLabel.setTypeface(font);
        thursdayValue.setTypeface(font);
        fridayLabel.setTypeface(font);
        fridayValue.setTypeface(font);
        saturdayLabel.setTypeface(font);
        saturdayValue.setTypeface(font);
        uniquenessLabel.setTypeface(font);
        unisex.setTypeface(font);
        cardAccepted.setTypeface(font);
        serviceTax.setTypeface(font);
        parking.setTypeface(font);
        ac.setTypeface(font);
        homeService.setTypeface(font);
        needAppointment.setTypeface(font);
        wifi.setTypeface(font);
        bridal.setTypeface(font);
        reviewLabel.setTypeface(font);
        reviewOneProfileName.setTypeface(font);
        reviewOneProfileRatingLabel.setTypeface(font);
        reviewOneProfileRatingValue.setTypeface(font);
        reviewOneValue.setTypeface(font);
        reviewTwoProfileName.setTypeface(font);
        reviewTwoProfileRatingLabel.setTypeface(font);
        reviewTwoProfileRatingValue.setTypeface(font);
        reviewTwoValue.setTypeface(font);
        stylistInfo.setTypeface(font);
        readAllReviews.setTypeface(font);
        reviewField.setTypeface(font);
        salonVoteCount.setTypeface(font);
        salonRating.setTypeface(font);
        salonInfo.setTypeface(font);
        salonRatingText.setTypeface(font);
        salonReviewField.setTypeface(font);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_salon_page, menu);


        return super.onCreateOptionsMenu(menu);
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
