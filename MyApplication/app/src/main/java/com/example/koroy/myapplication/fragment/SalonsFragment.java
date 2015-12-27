package com.example.koroy.myapplication.fragment;

/**
 * Created by koroy on 11/27/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.adapter.CustomPagerAdapter;
import com.example.koroy.myapplication.adapter.SalonListAdapter;
import com.example.koroy.myapplication.adapter.ServiceListAdapter;
import com.example.koroy.myapplication.model.SalonModel;
import com.example.koroy.myapplication.model.ServiceModel;

import java.util.ArrayList;


public class SalonsFragment extends Fragment {

    private RecyclerView salonList;
    private SalonListAdapter salonListAdapter;

    public SalonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_salons, container, false);

        salonList = (RecyclerView) rootView.findViewById(R.id.salonList);

        salonListAdapter = new SalonListAdapter(getActivity(), getData());
        salonList.setAdapter(salonListAdapter);
        salonList.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Inflate the layout for this fragment
        return rootView;
    }


    private ArrayList<SalonModel> getData(){
        ArrayList<SalonModel> serviceModelArrayList=new ArrayList<SalonModel>();
        SalonModel serviceModel=new SalonModel();
        serviceModel.setSalonName("Zaved Habibs");
        serviceModel.setSalonLogo(R.drawable.habibs);
        serviceModel.setSalonAddress("Park Street,Kolkata");
        serviceModel.setRating(4.7);
        serviceModelArrayList.add(serviceModel);

        serviceModel=new SalonModel();
        serviceModel.setSalonName("Head Turners");
        serviceModel.setSalonLogo(R.drawable.head_turner);
        serviceModel.setSalonAddress("Saltlake, Sector-V");
        serviceModel.setRating(4.3);
        serviceModelArrayList.add(serviceModel);

        serviceModel=new SalonModel();
        serviceModel.setSalonName("M Salon");
        serviceModel.setSalonLogo(R.drawable.m_salon);
        serviceModel.setSalonAddress("Chingrighata, Sector-IV");
        serviceModel.setRating(4.8);
        serviceModelArrayList.add(serviceModel);

        serviceModel=new SalonModel();
        serviceModel.setSalonName("GIUSEPPE’S");
        serviceModel.setSalonLogo(R.drawable.guiseppe);
        serviceModel.setSalonAddress("Saltlake, Sector-I");
        serviceModel.setRating(4.6);
        serviceModelArrayList.add(serviceModel);

        return serviceModelArrayList;

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