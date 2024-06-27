package com.example.doan.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.doan.Activity.DetailActivity;
import com.example.doan.Model.PopularModel;
import com.example.doan.Model.imgDetailModel;
import com.example.doan.R;

import java.util.ArrayList;

public class ViewPageListAdapter extends ArrayAdapter<PopularModel> {
    Activity context;
    int idLayout;
    ArrayList<PopularModel> list;

    public ViewPageListAdapter(@NonNull Activity context1, int idLayout, ArrayList<PopularModel> list) {
        super(context1, idLayout, list);
        this.context = context1;
        this.idLayout = idLayout;
        this.list = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlacter = context.getLayoutInflater();
        convertView = myFlacter.inflate(idLayout, null);

        ConstraintLayout  constraintPopList = convertView.findViewById(R.id.constraintPopList);

        TextView txt_Tieude_viewholderList = convertView.findViewById(R.id.txt_Tieude_viewholderList);
        txt_Tieude_viewholderList.setText(list.get(position).getTitle());

        TextView txt_Gia_viewholderList = convertView.findViewById(R.id.txt_Gia_viewholderList);
        txt_Gia_viewholderList.setText(list.get(position).getPrice().intValue() + " VNĐ");

        TextView txt_rate_viewholderList = convertView.findViewById(R.id.txt_rate_viewholderList);
//        txt_rate_viewholderList.setText(list.get(position).getRate().toString());

        ImageView img_pop_viewholderList = convertView.findViewById(R.id.img_pop_viewholderList);

        int drawableResoureId = convertView.getResources().getIdentifier(list.get(position).getPicUrl1(),
                "drawable", convertView.getContext().getPackageName());
        Glide.with(convertView.getContext())
                .load(drawableResoureId)
                .transform(new GranularRoundedCorners(30, 30, 0 ,0))
                .into(img_pop_viewholderList);

        ViewTreeObserver vto = txt_Tieude_viewholderList.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = txt_Tieude_viewholderList.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if(txt_Tieude_viewholderList.getLineCount() > 0){
                    txt_Tieude_viewholderList.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    txt_Tieude_viewholderList.setMarqueeRepeatLimit(-1);
                    txt_Tieude_viewholderList.setMaxLines(1);
                    txt_Tieude_viewholderList.setHorizontallyScrolling(true);
                    txt_Tieude_viewholderList.setSelected(true);
                }

            }
        });

        convertView.findViewById(R.id.img_pop_viewholderList).setOnClickListener(new View.OnClickListener() {
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

        return convertView;
    }

}
