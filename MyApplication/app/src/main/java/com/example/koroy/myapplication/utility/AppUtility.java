package com.example.koroy.myapplication.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.koroy.myapplication.MainActivity;
import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.constants.AppConstants;
import com.example.koroy.myapplication.fragment.HomeFragment;
import com.example.koroy.myapplication.fragment.OffersFragment;
import com.example.koroy.myapplication.fragment.SalonsFragment;

/**
 * Created by koroy on 12/6/2015.
 */
public class AppUtility {

    private Context _context;
    public static final String IMAGE_POSITION="image_position";
    public static final String IMAGE_LIST="image_list";
    public static final String SALON_DATA="salon_data";
    public static final String FAVOURITE_SALON="fav_salon";
    public static final String SALON_NAME="salon_name";

    // constructor
    public AppUtility(Context context) {
        this._context = context;
    }


    public void callSalon(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + "9836011558"));//change the number
        _context.startActivity(callIntent);
    }

    public void shareSalon(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Zaved Habibs";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kondapur, Hyderabad, 700865");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        _context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    /*
     * getting screen width
     */
    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }


}
