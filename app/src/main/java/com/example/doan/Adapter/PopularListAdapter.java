package com.example.doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.doan.Activity.DetailActivity;
import com.example.doan.Model.PopularModel;
import com.example.doan.R;

import java.util.ArrayList;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.Viewholder> {
    ArrayList<PopularModel> list = new ArrayList<>();
    Context context;

    public PopularListAdapter(ArrayList<PopularModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listView = inflater.inflate(R.layout.viewholder_pop_list, parent, false);

        return new Viewholder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularListAdapter.Viewholder holder, int position) {
        holder.txt_Tieude_viewholder.setText(list.get(position).getTitle());
        holder.txt_Gia_viewholder.setText(list.get(position).getPrice().intValue() + " VND");
        holder.txt_rate_viewholder.setText(list.get(position).getRate().toString());

        int drawableResoureId = holder.itemView.getResources().getIdentifier(list.get(position).getPicUrl1(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResoureId)
                .transform(new GranularRoundedCorners(30, 30, 0 ,0))
                .into(holder.img_pop_viewholder);


        holder.constraintPopList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_shoes", list.get(position));
                bundle.putSerializable("object_list_shoes", list);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView txt_Tieude_viewholder, txt_Gia_viewholder, txt_rate_viewholder;
        ImageView img_pop_viewholder;
        ConstraintLayout constraintPopList;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txt_Tieude_viewholder = itemView.findViewById(R.id.txt_Tieude_viewholderList);
            txt_Gia_viewholder = itemView.findViewById(R.id.txt_Gia_viewholderList);
            txt_rate_viewholder = itemView.findViewById(R.id.txt_rate_viewholderList);
            img_pop_viewholder = itemView.findViewById(R.id.img_pop_viewholderList);
            constraintPopList = itemView.findViewById(R.id.constraintPopList);
        }
    }
}