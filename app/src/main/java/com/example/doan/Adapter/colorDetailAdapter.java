package com.example.doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.doan.Activity.DetailActivity;
import com.example.doan.Model.PopularModel;
import com.example.doan.R;

import java.util.ArrayList;

public class colorDetailAdapter extends RecyclerView.Adapter<colorDetailAdapter.colorAdapter>{
    ArrayList<PopularModel> list;
    Context context;


    public colorDetailAdapter(ArrayList<PopularModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public colorDetailAdapter.colorAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_color_detail, parent, false);
        return new colorAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull colorDetailAdapter.colorAdapter holder, int position) {
        int drawableResoureId = holder.itemView.getResources().getIdentifier(list.get(position).getPicUrl1(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResoureId)
                .transform(new GranularRoundedCorners(30, 30, 0 ,0))
                .into(holder.imgSP);

        holder.linear_color_detail.setOnClickListener(new View.OnClickListener() {
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

    public class colorAdapter extends RecyclerView.ViewHolder {
        ImageView imgSP;
        LinearLayout linear_color_detail;
        public colorAdapter(@NonNull View itemView) {
            super(itemView);
            imgSP = itemView.findViewById(R.id.imgSP);
            linear_color_detail = itemView.findViewById(R.id.linear_color_detail);
        }
    }
}
