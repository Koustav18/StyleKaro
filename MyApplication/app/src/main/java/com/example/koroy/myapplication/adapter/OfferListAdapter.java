package com.example.koroy.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.model.OfferModel;

import java.util.ArrayList;

/**
 * Created by koroy on 11/27/2015.
 */
public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.MyViewHolder> {
        ArrayList<OfferModel> data;
        private LayoutInflater inflater;
        private Context context;

    public OfferListAdapter(Context context, ArrayList<OfferModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.offer_row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
        }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        OfferModel current = data.get(position);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "Chantelli_Antiqua.ttf");
        holder.offerTitle.setText(current.getOfferName());
        //holder.offerDescription.setText(current.getOfferDescription());
        holder.offerTitle.setTypeface(font);
        holder.offerDescription.setTypeface(font);
        holder.goToOffers.setTypeface(font);

        holder.offerWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    holder.offerWishList.setImageResource(R.drawable.selected_heart);
            }
        });
        }

    @Override
    public int getItemCount() {
        return data.size();
        }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView offerTitle;
        TextView offerDescription;
        ImageView offerLogo;
        ImageView offerWishList;
        Button goToOffers;

        public MyViewHolder(View itemView) {
            super(itemView);
            offerTitle = (TextView) itemView.findViewById(R.id.offer_title);
            offerDescription = (TextView) itemView.findViewById(R.id.offer_detail_text);
            offerLogo = (ImageView) itemView.findViewById(R.id.salon_logo);
            offerWishList = (ImageView) itemView.findViewById(R.id.wishlist);
            goToOffers = (Button) itemView.findViewById(R.id.goto_offers);

        }
    }
}