package com.example.koroy.myapplication.fragment;

/**
 * Created by koroy on 11/27/2015.
 */
import android.app.Activity;
import android.content.Context;
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
import com.example.koroy.myapplication.adapter.ServiceListAdapter;
import com.example.koroy.myapplication.model.ServiceModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private CustomPagerAdapter customPagerAdapter;
    ViewPager viewPager;
    private RecyclerView serviceList;
    private ServiceListAdapter serviceListAdapter;


    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private ArrayList<ServiceModel> getData(){
        ArrayList<ServiceModel> serviceModelArrayList=new ArrayList<ServiceModel>();
        ServiceModel serviceModel=new ServiceModel();
        serviceModel.setServiceName("Hair Service");
        //serviceModel.setServiceDescription("");
        serviceModelArrayList.add(serviceModel);

        serviceModel=new ServiceModel();
        serviceModel.setServiceName("Skin Service");
        serviceModelArrayList.add(serviceModel);

        serviceModel=new ServiceModel();
        serviceModel.setServiceName("Hands & Feet");
        serviceModelArrayList.add(serviceModel);

        serviceModel=new ServiceModel();
        serviceModel.setServiceName("Body Service");
        serviceModelArrayList.add(serviceModel);

        serviceModel=new ServiceModel();
        serviceModel.setServiceName("Make up");
        serviceModelArrayList.add(serviceModel);

        return serviceModelArrayList;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        customPagerAdapter = new CustomPagerAdapter(getActivity());

        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        viewPager.setAdapter(customPagerAdapter);

        serviceList = (RecyclerView) rootView.findViewById(R.id.serviceList);

        serviceListAdapter = new ServiceListAdapter(getActivity(), getData());
        serviceList.setAdapter(serviceListAdapter);
        serviceList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Inflate the layout for this fragment
        return rootView;
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