package com.example.productcreation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> {
     Context context;
     ArrayList product_id,product_name,description;
     Activity activity;
     Animation translate_anim;

    customAdapter(Activity activity,Context context,ArrayList<String> product_id,ArrayList<String> product_name,ArrayList<String> description){
        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
    }
    @NonNull
    @Override
    public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listrow,parent,false);
         return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, int position) {
        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.product_name_txt.setText(String.valueOf(product_name.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,updateActivity.class);
                intent.putExtra("product_id",String.valueOf(product_id.get(holder.getAdapterPosition())));
                intent.putExtra("product_name",String.valueOf(product_name.get(holder.getAdapterPosition())));
                intent.putExtra("description",String.valueOf(description.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_id_txt,product_name_txt,description_txt;
//        LinearLayout mainLayout;
        RelativeLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id);
            product_name_txt = itemView.findViewById(R.id.product_name);
            description_txt = itemView.findViewById(R.id.description);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
