package com.example.doan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.InvoiceAdapter;
import com.example.doan.Adapter.PopularListAdapter;
import com.example.doan.Model.InvoiceModel;
import com.example.doan.Model.PopularModel;
import com.example.doan.R;
import com.example.doan.Retrofit.ApiMobile;
import com.example.doan.Retrofit.RetrofitClient;
import com.example.doan.Utils.Utils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InvoiceActivity extends AppCompatActivity {
    private InvoiceAdapter invoiceAdapter;
    private RecyclerView recyclerview_invoice;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMobile apiMobile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        initRecyclerview();

    }

    private void initRecyclerview() {
        ArrayList<InvoiceModel> list = new ArrayList<>();
        recyclerview_invoice = findViewById(R.id.recyclerview_invoice);

        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);
        String SoDT = Utils.user.getSodt();
        compositeDisposable.add(apiMobile.GetInvoice(SoDT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listInvoiceModel -> {
                            if(listInvoiceModel.isSuccess()){
                                invoiceAdapter = new InvoiceAdapter(listInvoiceModel.getResult(), this);
                                recyclerview_invoice.setAdapter(invoiceAdapter);
                                recyclerview_invoice.setLayoutManager(new LinearLayoutManager(this));

                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("abc", throwable.getMessage());
                        }

                )
        );
//        list.add(new InvoiceModel("DH1","shoes_main2", "Nike Air", 200000.0, 5.0, "Dang giao hang", 38.0, 800000.0, 6.0 ));
//        list.add(new InvoiceModel("DH2","shoes_main1", "Nike Air", 200000.0, 2.0, "Cho xac nhan", 39.0, 500000.0, 1.0 ));
//
//
//        invoiceAdapter = new InvoiceAdapter(list, this);
//        recyclerview_invoice.setAdapter(invoiceAdapter);
//        recyclerview_invoice.setLayoutManager(new LinearLayoutManager(this));
    }
}
