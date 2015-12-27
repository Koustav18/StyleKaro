package com.example.koroy.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koroy.myapplication.MainActivity;
import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.fragment.HomeFragment;
import com.example.koroy.myapplication.fragment.OffersFragment;
import com.example.koroy.myapplication.fragment.SalonsFragment;
import com.example.koroy.myapplication.model.ServiceModel;
import com.example.koroy.myapplication.model.SubServiceModel;

import java.util.ArrayList;

/**
 * Created by koroy on 12/27/2015.
 */
public class SubServiceAdapter extends RecyclerView.Adapter<SubServiceAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<SubServiceModel> subserviceData;
    private LayoutInflater inflater;
    private Typeface font;
    private Dialog dialog;

    public SubServiceAdapter(Context context,ArrayList<SubServiceModel> data,Dialog dialog){
        this.context=context;
        this.subserviceData=data;
        inflater=LayoutInflater.from(context);
        this.dialog=dialog;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.subservice_row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SubServiceModel current = subserviceData.get(position);
        font = Typeface.createFromAsset(context.getAssets(), "Chantelli_Antiqua.ttf");
        holder.subServiceTitle.setText(current.getSubserviceName());
        holder.subServiceTitle.setTypeface(font);
        holder.subserviceDescription.setTypeface(font);
        holder.subservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
                displayView(1);
            }
        });

    }

    public void displayView(int position) {
        Fragment fragment = null;
        MainActivity mainActivity=(MainActivity)context;
        //String title = mainActivity.getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
               // title = mainActivity.getString(R.string.title_home);
                break;
            case 1:
                fragment = new SalonsFragment();
              //  title = mainActivity.getString(R.string.title_salons);
                break;
            case 2:
                fragment = new OffersFragment();
              //  title = mainActivity.getString(R.string.title_offers);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            //mainActivity.getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public int getItemCount() {
        return subserviceData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subServiceTitle,subserviceDescription;
        RelativeLayout subservice;
        public MyViewHolder(View itemView) {
            super(itemView);
            subServiceTitle = (TextView) itemView.findViewById(R.id.subservice_name);
            subservice=(RelativeLayout)itemView.findViewById(R.id.subservice);
            subserviceDescription=(TextView)itemView.findViewById(R.id.subservice_description);
        }
    }

}
