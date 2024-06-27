package com.example.doan.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.PopularListAdapter;
import com.example.doan.Model.PopularModel;
import com.example.doan.R;
import com.example.doan.Retrofit.ApiMobile;
import com.example.doan.Retrofit.RetrofitClient;
import com.example.doan.Utils.Utils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeMainActivity extends AppCompatActivity {
    private PopularListAdapter adapterPopular;
    private RecyclerView recyclerViewPopular;
    private ImageView img_icon_cart;
    private TextView txt_SeeAll;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMobile apiMobile;
    TextView txt_name_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);

        txt_SeeAll = findViewById(R.id.txt_SeeAll);
        txt_name_home = findViewById(R.id.txt_name_home);

        txt_name_home.setText(Utils.user.getHoten());

        initRecyclerview();
        bottom_navigation();

        txt_SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, ListProdcutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getListSP(){

    }

    private void bottom_navigation() {
        LinearLayout cartBtn = findViewById(R.id.linear_icon_cart);
        LinearLayout listProdductBtn = findViewById(R.id.linear_icon_listProduct);
        LinearLayout InvoiceBtn = findViewById(R.id.linear_icon_invoice);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeMainActivity.this, CartActivity.class));
            }
        });

        listProdductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, ListProdcutActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("getFromSeeAll", false);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        InvoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, InvoiceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerview() {
        compositeDisposable.add(apiMobile.GetListSP()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listSPModel -> {
                            if(listSPModel.isSuccess()){
                                adapterPopular = new PopularListAdapter(listSPModel.getResult(), this);
                                recyclerViewPopular = findViewById(R.id.rw_main1);
                                recyclerViewPopular.setAdapter(adapterPopular);
                                recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("abc", throwable.getMessage());
                        }

                )
        );
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            return true;
        }
        return false;

    }
}