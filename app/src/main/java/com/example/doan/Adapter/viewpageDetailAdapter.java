package com.example.doan.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Model.PopularModel;
import com.example.doan.Model.imgDetailModel;
import com.example.doan.R;

import java.util.ArrayList;

public class viewpageDetailAdapter extends RecyclerView.Adapter<viewpageDetailAdapter.viewpageDetailHolder> {
    ArrayList<imgDetailModel> listImg;

    public viewpageDetailAdapter(ArrayList<imgDetailModel> listImg) {
        this.listImg = listImg;
    }

    @NonNull
    @Override
    public viewpageDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpage_img_detail, parent, false);
        return new viewpageDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewpageDetailHolder holder, int position) {
        holder.imgShoes.setImageResource(listImg.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return listImg.size();
    }

    public class viewpageDetailHolder extends RecyclerView.ViewHolder{
        ImageView imgShoes;
        public viewpageDetailHolder(@NonNull View itemView) {
            super(itemView);
            imgShoes = itemView.findViewById(R.id.imgShoes);
        }
    }
}
