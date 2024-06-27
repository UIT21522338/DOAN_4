package com.example.doan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.doan.Model.InvoiceDetailModel;
import com.example.doan.R;

import java.util.ArrayList;

public class InvoiceDetailListAdapter extends RecyclerView.Adapter<InvoiceDetailListAdapter.InvoiceDetailHolder> {
    ArrayList<InvoiceDetailModel> list = new ArrayList<>();
    Context context;

    public InvoiceDetailListAdapter(ArrayList<InvoiceDetailModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public InvoiceDetailListAdapter.InvoiceDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listView = inflater.inflate(R.layout.viewholder_item_invoice_detail, parent, false);

        return new InvoiceDetailListAdapter.InvoiceDetailHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceDetailListAdapter.InvoiceDetailHolder holder, int position) {
        holder.txt_TieuDe_InvoiceDT.setText(list.get(position).getTitle());
        holder.txt_Price_InvoiceDT.setText(list.get(position).getPrice() + " VND");
        holder.txt_Size_InvoiceDT.setText(list.get(position).getSize() + "");
        holder.txt_SL_InvoiceDT.setText("x" + list.get(position).getSize());

        int drawableResoureId = holder.itemView.getResources().getIdentifier(list.get(position).getImgUrl(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResoureId)
                .transform(new GranularRoundedCorners(30, 30, 0 ,0))
                .into(holder.img_Shoes_InvoiceDT);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InvoiceDetailHolder extends RecyclerView.ViewHolder {
        ImageView img_Shoes_InvoiceDT;
        TextView txt_TieuDe_InvoiceDT, txt_SL_InvoiceDT, txt_Size_InvoiceDT, txt_Price_InvoiceDT;
        public InvoiceDetailHolder(@NonNull View itemView) {
            super(itemView);

            img_Shoes_InvoiceDT = itemView.findViewById(R.id.img_Shoes_InvoiceDT);
            txt_TieuDe_InvoiceDT = itemView.findViewById(R.id.txt_TieuDe_InvoiceDT);
            txt_SL_InvoiceDT = itemView.findViewById(R.id.txt_Size_Cart);
            txt_Size_InvoiceDT = itemView.findViewById(R.id.txt_Gia_Cart);
            txt_Price_InvoiceDT = itemView.findViewById(R.id.txt_Price_InvoiceDT);
        }
    }
}
