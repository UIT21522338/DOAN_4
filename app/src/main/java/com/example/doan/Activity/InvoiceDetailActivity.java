package com.example.doan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.InvoiceAdapter;
import com.example.doan.Adapter.InvoiceDetailListAdapter;
import com.example.doan.Model.InvoiceDetailModel;
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

public class InvoiceDetailActivity extends AppCompatActivity {
    private InvoiceDetailListAdapter invoiceDetailListAdapter;
    private RecyclerView recyclerview_invoiceDT;
    private TextView txt_ngaydathang_invoice_detail, txt_name_invoice_detail, txt_soDT_invoice_detail, txt_diachi_invoice_detail,
            txt_tongtienhang_invoice_detail, txt_thanhtien_invoice_detail, txt_madh_invoice_detail, txt_phiship_invoice_detail;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMobile apiMobile;
    InvoiceModel shoes;
    String MaDH;
    ImageView btn_back_invoicedetail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_inoivce_detail);

        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);

        initRecyclerview();
        initView();

        btn_back_invoicedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvoiceDetailActivity.this, InvoiceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        shoes = (InvoiceModel) bundle.get("object_invoice");

        MaDH = shoes.getMADH();

        txt_madh_invoice_detail.setText("DH" + MaDH);
        txt_ngaydathang_invoice_detail.setText(shoes.getNgayDatHang());
        txt_tongtienhang_invoice_detail.setText(shoes.getTiengHang() + "");
        txt_thanhtien_invoice_detail.setText(shoes.getTongTien() + "");
        txt_phiship_invoice_detail.setText(shoes.getPhiShip() + "");

        ArrayList<InvoiceDetailModel> list = new ArrayList<>();
        recyclerview_invoiceDT = findViewById(R.id.recyclerview_invoiceDT);
        String SoDT = Utils.user.getSodt();

        compositeDisposable.add(apiMobile.GetInvoiceDetail(SoDT, MaDH)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listInvoiceDetailModel -> {
                            if(listInvoiceDetailModel.isSuccess()){
                                invoiceDetailListAdapter = new InvoiceDetailListAdapter(listInvoiceDetailModel.getResult(), this);
                                recyclerview_invoiceDT.setAdapter(invoiceDetailListAdapter);
                                recyclerview_invoiceDT.setLayoutManager(new LinearLayoutManager(this));
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("abc", throwable.getMessage());
                        }

                )
        );

    }

    private void initRecyclerview() {

//        list.add(new InvoiceDetailModel("Nike Air", 38.0, 2.0, 200000.0, "shoes_main2"));
//        list.add(new InvoiceDetailModel("Nike Air", 38.0, 2.0, 200000.0, "shoes_main2"));
    }

    private void initView(){
        txt_ngaydathang_invoice_detail = findViewById(R.id.txt_ngaydathang_invoice_detail);
        txt_name_invoice_detail = findViewById(R.id.txt_name_invoice_detail);
        txt_soDT_invoice_detail = findViewById(R.id.txt_soDT_invoice_detail);
        txt_diachi_invoice_detail = findViewById(R.id.txt_diachi_invoice_detail);
        txt_tongtienhang_invoice_detail = findViewById(R.id.txt_tongtienhang_invoice_detail);
        txt_thanhtien_invoice_detail = findViewById(R.id.txt_thanhtien_invoice_detail);
        txt_madh_invoice_detail = findViewById(R.id.txt_madh_invoice_detail);
        txt_phiship_invoice_detail = findViewById(R.id.txt_phiship_invoice_detail);
        btn_back_invoicedetail = findViewById(R.id.btn_back_invoicedetail);


        txt_name_invoice_detail.setText(Utils.user.getHoten());
        txt_soDT_invoice_detail.setText(Utils.user.getSodt());
        txt_diachi_invoice_detail.setText(Utils.user.getDiachi());

    }
}
