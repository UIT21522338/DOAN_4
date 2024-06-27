package com.example.doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.doan.Activity.DetailActivity;
import com.example.doan.Activity.InvoiceDetailActivity;
import com.example.doan.Activity.LoginActivity;
import com.example.doan.Activity.MainActivity;
import com.example.doan.Model.InvoiceModel;
import com.example.doan.Model.PopularModel;
import com.example.doan.R;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceHolder>{
    @NonNull
    ArrayList<InvoiceModel> list = new ArrayList<>();
    Context context;

    public InvoiceAdapter(ArrayList<InvoiceModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public InvoiceAdapter.InvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listView = inflater.inflate(R.layout.viewholder_list_invoice, parent, false);

        return new InvoiceAdapter.InvoiceHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.InvoiceHolder holder, int position) {
        holder.txt_title_invocie.setText(list.get(position).getTitle());
        holder.txt_status_invoice.setText(list.get(position).getStatus());
        holder.txt_size_invoice.setText(list.get(position).getSize().intValue() + "");
        holder.txt_sl_invoice.setText( "x" +list.get(position).getNumberInCart().intValue());
        holder.txt_itemcost_invoice.setText(list.get(position).getPrice() + " VND");
        holder.txt_SoLuongSP_invoice.setText(list.get(position).getTongSP().intValue() + " sản phẩm");
        holder.txt_thanhtien_invoice.setText(list.get(position).getTongTien() + " VND");

        int drawableResoureId = holder.itemView.getResources().getIdentifier(list.get(position).getPicUrl(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResoureId)
                .transform(new GranularRoundedCorners(30, 30, 0 ,0))
                .into(holder.img_shoes_invoice);

        if (list.get(position).getTongSP() > 1){
            holder.linear_xemthem.setVisibility(View.VISIBLE);
        }else{
            holder.linear_xemthem.setVisibility(View.GONE);
        }

        holder.contraint_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InvoiceDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_invoice", list.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InvoiceHolder extends RecyclerView.ViewHolder {
        ImageView img_shoes_invoice;
        TextView txt_title_invocie, txt_status_invoice, txt_size_invoice, txt_sl_invoice, txt_itemcost_invoice, txt_SoLuongSP_invoice, txt_thanhtien_invoice;
        LinearLayout linear_xemthem;
        ConstraintLayout contraint_invoice;
        public InvoiceHolder(@NonNull View itemView) {
            super(itemView);

            img_shoes_invoice = itemView.findViewById(R.id.img_shoes_invoice);
            txt_title_invocie = itemView.findViewById(R.id.txt_title_invocie);
            txt_status_invoice = itemView.findViewById(R.id.txt_status_invoice);
            txt_size_invoice = itemView.findViewById(R.id.txt_size_invoice);
            txt_sl_invoice = itemView.findViewById(R.id.txt_sl_invoice);
            txt_itemcost_invoice = itemView.findViewById(R.id.txt_itemcost_invoice);
            txt_SoLuongSP_invoice = itemView.findViewById(R.id.txt_SoLuongSP_invoice);
            txt_thanhtien_invoice = itemView.findViewById(R.id.txt_thanhtien_invoice);
            linear_xemthem = itemView.findViewById(R.id.linear_xemthem);
            contraint_invoice = itemView.findViewById(R.id.contraint_invoice);
        }
    }
}
