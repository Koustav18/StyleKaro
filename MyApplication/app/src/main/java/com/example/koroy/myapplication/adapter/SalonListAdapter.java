package com.example.koroy.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.SalonMapActivity;
import com.example.koroy.myapplication.SalonPageActivity;
import com.example.koroy.myapplication.model.SalonModel;
import com.example.koroy.myapplication.utility.AppUtility;

import java.util.ArrayList;

/**
 * Created by koroy on 11/27/2015.
 */
public class SalonListAdapter extends RecyclerView.Adapter<SalonListAdapter.MyViewHolder> {
        ArrayList<SalonModel> data;
        private LayoutInflater inflater;
        private Context context;
        private AppUtility appUtility;

    public SalonListAdapter(Context context, ArrayList<SalonModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        appUtility=new AppUtility(context);
        }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.salon_row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
        }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SalonModel salonModel = data.get(position);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "Chantelli_Antiqua.ttf");
        holder.salonTitle.setText(salonModel.getSalonName());
        holder.salonTitle.setTypeface(font);
        holder.salonAddress.setTypeface(font);
        holder.salonAddress.setText(salonModel.getSalonAddress());
        holder.salonRating.setTypeface(font);
        holder.salonRating.setText(salonModel.getRating()+"");
        holder.salonLogo.setImageResource(salonModel.getSalonLogo());
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUtility.callSalon();
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUtility.shareSalon();
            }
        });
        holder.offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Salon has no offer now",Toast.LENGTH_SHORT).show();
            }
        });

        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SalonMapActivity.class);
                i.putExtra(AppUtility.SALON_NAME, salonModel.getSalonName());
                context.startActivity(i);
            }
        });

        holder.salonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SalonPageActivity.class);
                i.putExtra(AppUtility.SALON_DATA,salonModel);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
        }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView salonTitle;
        TextView salonAddress;
        TextView salonRating;
        RelativeLayout salonLayout;
        ImageView phone,location,offers,share,salonLogo;


        public MyViewHolder(View itemView) {
            super(itemView);
            salonTitle = (TextView) itemView.findViewById(R.id.offer_title);
            salonAddress=(TextView)itemView.findViewById(R.id.offer_detail_text);
            salonLayout=(RelativeLayout)itemView.findViewById(R.id.salon_upper_layout);
            phone=(ImageView)itemView.findViewById(R.id.contact_salon);
            location=(ImageView)itemView.findViewById(R.id.location);
            offers=(ImageView)itemView.findViewById(R.id.offers);
            share=(ImageView)itemView.findViewById(R.id.share);
            salonLogo=(ImageView)itemView.findViewById(R.id.salon_logo);
            salonRating=(TextView)itemView.findViewById(R.id.salon_rating_view);
        }
    }
}