package com.example.koroy.myapplication.fragment;

/**
 * Created by koroy on 11/27/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.adapter.OfferListAdapter;
import com.example.koroy.myapplication.adapter.SalonListAdapter;
import com.example.koroy.myapplication.model.OfferModel;
import com.example.koroy.myapplication.model.SalonModel;

import java.util.ArrayList;


public class OffersFragment extends Fragment {

    private RecyclerView offerList;
    private OfferListAdapter offerListAdapter;

    public OffersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);

        offerList = (RecyclerView) rootView.findViewById(R.id.offerList);

        offerListAdapter = new OfferListAdapter(getActivity(), getData());
        offerList.setAdapter(offerListAdapter);
        offerList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Inflate the layout for this fragment
        return rootView;
    }

    private ArrayList<OfferModel> getData(){
        ArrayList<OfferModel> offerModelArrayList=new ArrayList<OfferModel>();
        OfferModel offerModel=new OfferModel();
        offerModel.setOfferName("Offer1");
        offerModelArrayList.add(offerModel);

        offerModel=new OfferModel();
        offerModel.setOfferName("Offer2");
        offerModelArrayList.add(offerModel);

        offerModel=new OfferModel();
        offerModel.setOfferName("Offer3");
        offerModelArrayList.add(offerModel);

        offerModel=new OfferModel();
        offerModel.setOfferName("Offer4");
        offerModelArrayList.add(offerModel);

        return offerModelArrayList;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}