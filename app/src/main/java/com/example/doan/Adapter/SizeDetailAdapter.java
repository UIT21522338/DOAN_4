package com.example.doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;

import java.util.ArrayList;
import java.util.List;

public class SizeDetailAdapter extends RecyclerView.Adapter<SizeDetailAdapter.SizeHolder>{
    Context context;
    ArrayList<String> list;

    private int checkedPosition = 0;
    public SizeDetailAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SizeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_size_detail, parent, false);
        return new SizeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SizeHolder extends RecyclerView.ViewHolder {
        TextView txt_size;
        public SizeHolder(@NonNull View itemView) {
            super(itemView);
            txt_size = itemView.findViewById(R.id.txt_size);
        }

        void bind(final String item) {
            if (checkedPosition == -1) {
                txt_size.setText(item);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    txt_size.setText(item);
                    txt_size.setBackground(context.getResources().getDrawable(R.drawable.blue_button_background));
                } else {
                    txt_size.setText(item);
                    txt_size.setBackground(context.getResources().getDrawable(R.drawable.grey_bg));

                }
            }

            txt_size.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txt_size.setText(item);
                    txt_size.setBackground(context.getResources().getDrawable(R.drawable.blue_button_background));

                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();

                        Intent intent = new Intent("selected_size");
                        intent.putExtra("size_position",checkedPosition);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }
                }
            });
        }
    }

    public String getSelected() {
        if (checkedPosition != -1) {
            return list.get(checkedPosition);
        }
        return null;
    }
}
