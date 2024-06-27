package com.example.doan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.CartListAdapter;
import com.example.doan.Adapter.InvoiceAdapter;
import com.example.doan.Helper.ChangeNumberItemsListener;
import com.example.doan.Helper.ManagementCart;
import com.example.doan.Model.PopularModel;
import com.example.doan.R;
import com.example.doan.Retrofit.ApiMobile;
import com.example.doan.Retrofit.RetrofitClient;
import com.example.doan.Utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView_Cart;
    private RecyclerView.Adapter adapter;
    private TextView txt_Total_MyCart, txt_Delivery_MyCart, txt_TotalCost_MyCart;
    private ScrollView scrollView;
    private ManagementCart managementCart;
    private ArrayList<PopularModel> list = new ArrayList<>();
    private EditText edt_name_cart, edt_soDT_cart, edt_diachi_cart;
    private TextView btn_cart;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMobile apiMobile;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cart);
        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateInit();

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double delivery = 30000.0;
                double total = Math.round(managementCart.getTotalForCreate(Utils.cartItemModels) + delivery);
                if(total == delivery){
                    Toast.makeText(CartActivity.this, "Không thể thanh toán vì chưa có sản phẩm", Toast.LENGTH_SHORT).show();
                }else{
                    String SoDT = Utils.user.getSodt();
                    double tienhang = total - delivery;
                    String hoTen = edt_name_cart.getText().toString();
                    String diaChi = edt_diachi_cart.getText().toString();

                    Log.d("test", new Gson().toJson(Utils.cartItemModels));
                    compositeDisposable.add(apiMobile.getResultPayment(SoDT,total,tienhang,delivery ,new Gson().toJson(Utils.cartItemModels), hoTen, diaChi)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    Result -> {
                                        Toast.makeText(CartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                        Utils.cartItemModels = new ArrayList<>();

                                        Utils.user.setDiachi(diaChi);
                                        Utils.user.setHoten(hoTen);

                                        Intent intent = new Intent(CartActivity.this, HomeMainActivity.class);
                                        startActivity(intent);
                                    },
                                    throwable -> {
                                        Toast.makeText(CartActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }
            }
        });
    }

    private void calculateInit() {
        double delivery = 30000.0;
        double total = Math.round(managementCart.getTotalForCreate(Utils.cartItemModels) + delivery);
        double itemTotal = Math.round(managementCart.getTotalForCreate(Utils.cartItemModels) * 100)/100;
        txt_Total_MyCart.setText((int) itemTotal + " VND");
        txt_Delivery_MyCart.setText((int)delivery + " VND");
        txt_TotalCost_MyCart.setText((int)total + " VND");
    }


    private void initList() {
        if (Utils.cartItemModels.size() != 0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView_Cart.setLayoutManager(linearLayoutManager);
            adapter = new CartListAdapter(Utils.cartItemModels, this, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    calculateCart();
                }
            });
            adapter.notifyDataSetChanged();
            recyclerView_Cart.setAdapter(adapter);
        }


    }

    private void calculateCart() {
        double delivery = 30000.0;
        double total = Math.round(managementCart.getTotal() + delivery);
        double itemTotal = Math.round(managementCart.getTotal() * 100)/100;
        txt_Total_MyCart.setText((int) itemTotal + " VND");
        txt_Delivery_MyCart.setText((int)delivery + " VND");
        txt_TotalCost_MyCart.setText((int)total + " VND");
    }

    private void initView() {
        recyclerView_Cart = findViewById(R.id.recyclerView_Cart);
        txt_Total_MyCart = findViewById(R.id.txt_Total_MyCart);
        txt_Delivery_MyCart = findViewById(R.id.txt_Delivery_MyCart);
        txt_TotalCost_MyCart = findViewById(R.id.txt_TotalCost_MyCart);
        scrollView = findViewById(R.id.scrollView);
        edt_name_cart = findViewById(R.id.edt_name_cart);
        edt_soDT_cart = findViewById(R.id.edt_soDT_cart);
        edt_diachi_cart = findViewById(R.id.edt_diachi_cart);
        btn_cart = findViewById(R.id.btn_cart);

        edt_name_cart.setText(Utils.user.getHoten());
        edt_soDT_cart.setText(Utils.user.getSodt());
        edt_diachi_cart.setText(Utils.user.getDiachi());

    }
}
