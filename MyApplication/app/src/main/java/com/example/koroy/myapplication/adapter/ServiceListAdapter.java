package com.example.koroy.myapplication.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koroy.myapplication.R;
import com.example.koroy.myapplication.model.ServiceModel;
import com.example.koroy.myapplication.model.SubServiceModel;

import java.util.ArrayList;

/**
 * Created by koroy on 11/27/2015.
 */
public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyViewHolder> {
    private ArrayList<ServiceModel> data;
    private LayoutInflater inflater;
    private Context context;
    private Typeface font;

    public ServiceListAdapter(Context context, ArrayList<ServiceModel> data) {
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
        View view = inflater.inflate(R.layout.service_row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
        }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ServiceModel current = data.get(position);
        font = Typeface.createFromAsset(context.getAssets(), "Chantelli_Antiqua.ttf");
        holder.serviceTitle.setText(current.getServiceName());
        holder.serviceTitle.setTypeface(font);
        holder.serviceDescription.setTypeface(font);

        holder.serviceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.gender_pick_dialog,null);
                RadioButton male=(RadioButton)layout.findViewById(R.id.male);
                RadioButton female=(RadioButton)layout.findViewById(R.id.female);
                male.setTypeface(font);
                female.setTypeface(font);
                dialog.setContentView(layout);

                dialog.getWindow().getAttributes().windowAnimations =
                        R.style.dialog_animation;
                dialog.show();

                female.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        createSubServiceDialog(current.getServiceName());
                    }
                });

                male.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        createSubServiceDialog(current.getServiceName());
                    }
                });


              //  dialog.setTitle("Select your gender");

            }
        });

        }

    private ArrayList<SubServiceModel> createSubServiceList(String serviceName){
        ArrayList<SubServiceModel> subserviceList=new ArrayList<SubServiceModel>();
        SubServiceModel subServiceModel;
        if(serviceName.equalsIgnoreCase("Hair Service")){
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Hair Cut");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Hair Color");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Hair Styling");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Hair Treatment");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Hair Straightning");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Hair Wash");
            subserviceList.add(subServiceModel);
        }else if(serviceName.equalsIgnoreCase("Skin Service")){
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Facial");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Waxing");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Threading");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Blech");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Clean up");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Mask");
            subserviceList.add(subServiceModel);
        }else if(serviceName.equalsIgnoreCase("Hands & Feet")){
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Manicure");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Pedicure");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Nail Polish");
            subserviceList.add(subServiceModel);
        }else if(serviceName.equalsIgnoreCase("Body Service")){
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Body Massage");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Body Wrap");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Body Exfoliation");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Body Therapy");
            subserviceList.add(subServiceModel);
        }else if(serviceName.equalsIgnoreCase("Make up")){
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Bridal/Groom");
            subserviceList.add(subServiceModel);
            subServiceModel=new SubServiceModel();
            subServiceModel.setSubserviceName("Daily Makeup");
            subserviceList.add(subServiceModel);
        }
        return subserviceList;
    }

    private void createSubServiceDialog(String service){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.subservice_pick_dialog,null);

        TextView headerSubservice=(TextView)layout.findViewById(R.id.header_subservice);
        headerSubservice.setTypeface(font);
        RecyclerView subserviceList=(RecyclerView)layout.findViewById(R.id.subservice_list);
        SubServiceAdapter subServiceAdapter=new SubServiceAdapter(context,createSubServiceList(service),dialog);
        subserviceList.setAdapter(subServiceAdapter);
        subserviceList.setLayoutManager(new LinearLayoutManager(context));

        dialog.setContentView(layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, 1000);

        dialog.getWindow().getAttributes().windowAnimations =
                R.style.dialog_animation;
        dialog.show();

    }

    @Override
    public int getItemCount() {
        return data.size();
        }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serviceTitle;
        TextView serviceDescription;
        CardView serviceCard;
        public MyViewHolder(View itemView) {
            super(itemView);
            serviceTitle = (TextView) itemView.findViewById(R.id.offer_title);
            serviceDescription=(TextView)itemView.findViewById(R.id.offer_detail_text);
            serviceCard=(CardView)itemView.findViewById(R.id.card_view);
        }
    }
}