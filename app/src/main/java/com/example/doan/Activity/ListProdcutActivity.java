package com.example.doan.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.doan.Adapter.PopularListAdapter;
import com.example.doan.Adapter.ViewPageListAdapter;
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

public class ListProdcutActivity extends AppCompatActivity {
    private GridView gridViewListProduct;
    ArrayList<PopularModel> list = new ArrayList<>();

    ArrayList<PopularModel> list_tmpNam = new ArrayList<>();
    ArrayList<PopularModel> list_tmpNu = new ArrayList<>();

    private LinearLayout linear_women_list, linear_men_list;
    private TextView txt_women_list, txt_men_list;
    private EditText edt_search;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ViewPageListAdapter listAdapter;
    ApiMobile apiMobile;

    String click = "Nam";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        gridViewListProduct = findViewById(R.id.gridViewListProduct);
        linear_women_list = findViewById(R.id.linear_women_list);
        linear_men_list = findViewById(R.id.linear_men_list);
        txt_women_list = findViewById(R.id.txt_women_list);
        txt_men_list = findViewById(R.id.txt_men_list);
        edt_search = findViewById(R.id.edt_search);


        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);


        txt_men_list.setTypeface(txt_men_list.getTypeface(), Typeface.BOLD_ITALIC);
        txt_women_list.setTypeface(null , Typeface.NORMAL);


        compositeDisposable.add(apiMobile.getAllSP()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        List -> {
                            if(List.isSuccess()){
                                listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNam());
                                gridViewListProduct.setAdapter(listAdapter);

                                list_tmpNam = List.getResultNam();
                                list_tmpNu = List.getResultNu();
                            }
                        },
                        throwable -> {
                            Toast.makeText(ListProdcutActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("abc", throwable.getMessage());
                        }

                )
        );

        linear_men_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                click = "Nam";
                txt_men_list.setTypeface(txt_men_list.getTypeface(), Typeface.BOLD_ITALIC);
                txt_women_list.setTypeface(null , Typeface.NORMAL);

                if(edt_search.getText().toString() == ""){
                    compositeDisposable.add(apiMobile.getAllSP()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    List -> {
                                        if(List.isSuccess()){
                                            listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNam());
                                            gridViewListProduct.setAdapter(listAdapter);

                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(ListProdcutActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }else{
                    compositeDisposable.add(apiMobile.SearchSP("Nam", edt_search.getText().toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    List -> {
                                        if(List.isSuccess()){
                                            listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNam());
                                            gridViewListProduct.setAdapter(listAdapter);
                                        }else{
                                            Log.d("abid", "error");
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(ListProdcutActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }



            }
        });

        linear_women_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                click = "Nữ";
                txt_women_list.setTypeface(txt_women_list.getTypeface(), Typeface.BOLD_ITALIC);
                txt_men_list.setTypeface(null, Typeface.NORMAL);

                if(edt_search.getText().toString() == ""){
                    compositeDisposable.add(apiMobile.getAllSP()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    List -> {
                                        if(List.isSuccess()){
                                            listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNu());
                                            gridViewListProduct.setAdapter(listAdapter);
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(ListProdcutActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }else{
                    compositeDisposable.add(apiMobile.SearchSP("Nữ", edt_search.getText().toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    List -> {
                                        if(List.isSuccess()){
                                            listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNu());
                                            gridViewListProduct.setAdapter(listAdapter);
                                        }else{
                                            Log.d("abid", "error");
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(ListProdcutActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }


            }
        });

        edt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_search.requestFocus();
            }
        });
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() == 0){
                    compositeDisposable.add(apiMobile.getAllSP()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    List -> {
                                        if(List.isSuccess()){
                                            if(click.equals("Nữ")){
                                                listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNu());
                                                Log.d("abid", List.getResultNu().get(0).getTitle());
                                            }else{
                                                listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNam());
                                                Log.d("abid", List.getResultNam().get(0).getTitle());
                                            }
                                            gridViewListProduct.setAdapter(listAdapter);
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(ListProdcutActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }else{
                    compositeDisposable.add(apiMobile.SearchSP(click, s.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    List -> {
                                        if(List.isSuccess()){
                                            if(click.equals("Nữ")){
                                                listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNu());
                                            }else{
                                                listAdapter = new ViewPageListAdapter(ListProdcutActivity.this, R.layout.viewholder_list_product, List.getResultNam());
                                            }
                                            gridViewListProduct.setAdapter(listAdapter);
                                        }else{
                                            Log.d("abid", "error");
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(ListProdcutActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }



            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        if ((Boolean) bundle.get("getFromSeeAll") != true){
            edt_search.requestFocus();
        }
    }
}
