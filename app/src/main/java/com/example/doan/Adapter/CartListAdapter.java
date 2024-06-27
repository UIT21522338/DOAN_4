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
import com.example.doan.Helper.ChangeNumberItemsListener;
import com.example.doan.Helper.ManagementCart;
import com.example.doan.Model.CartItemModel;
import com.example.doan.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartHolder> {
    ArrayList<CartItemModel> listItemSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<CartItemModel> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSelected = listItemSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new CartHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        holder.txt_TieuDe_Cart.setText(listItemSelected.get(position).getShoes().getTitle());
        holder.txt_Gia_Cart.setText(listItemSelected.get(position).getShoes().getPrice().intValue() + " VNĐ");
        holder.txt_Size_Cart.setText(listItemSelected.get(position).getSize());
        holder.txt_Sl_Cart.setText(String.valueOf((listItemSelected.get(position).getNumberInCart())));

        int drawableResoureId = holder.itemView.getResources().getIdentifier(listItemSelected.get(position).getShoes().getPicUrl1(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResoureId)
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.img_Shoes_Cart);

        holder.txt_Plus_Cart.setOnClickListener( v -> {
            managementCart.plusNumberItem(listItemSelected, position, () -> {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            });

        });

        holder.txt_Minus_Cart.setOnClickListener( v -> {
            managementCart.minusNumberItem(listItemSelected, position, () -> {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            });

        });
    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder{
        TextView txt_TieuDe_Cart, txt_Gia_Cart, txt_Size_Cart, txt_Minus_Cart, txt_Sl_Cart, txt_Plus_Cart;
        ImageView img_Shoes_Cart;
        public CartHolder(@NonNull View itemView) {
            super(itemView);

            txt_TieuDe_Cart = itemView.findViewById(R.id.txt_TieuDe_InvoiceDT);
            txt_Gia_Cart = itemView.findViewById(R.id.txt_Gia_Cart);
            txt_Minus_Cart = itemView.findViewById(R.id.txt_Minus_Cart);
            txt_Sl_Cart = itemView.findViewById(R.id.txt_Sl_Cart);
            txt_Plus_Cart = itemView.findViewById(R.id.txt_Plus_Cart);
            txt_Size_Cart = itemView.findViewById(R.id.txt_Size_Cart);
            img_Shoes_Cart = itemView.findViewById(R.id.img_Shoes_InvoiceDT);
        }
    }
}
