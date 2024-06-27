package com.example.doan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.doan.Adapter.PopularListAdapter;
import com.example.doan.R;
import com.example.doan.Retrofit.ApiMobile;
import com.example.doan.Retrofit.RetrofitClient;
import com.example.doan.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    private TextView txt_signup, txt_laymk, txt_dangNhap;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMobile apiMobile;
    EditText edt_sodt_login, edt_matkhau_login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_signup = findViewById(R.id.txt_signup);
        txt_laymk = findViewById(R.id.txt_laymk);
        txt_dangNhap = findViewById(R.id.txt_dangNhap);
        edt_sodt_login = findViewById(R.id.edt_sodt_login);
        edt_matkhau_login = findViewById(R.id.edt_matkhau_login);

        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        txt_laymk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, GetPassActivity.class));
            }
        });

        txt_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sodt = edt_sodt_login.getText().toString().trim();
                String password = edt_matkhau_login.getText().toString().trim();
                compositeDisposable.add(apiMobile.dangnhap(sodt, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSuccess()){
                                        Utils.user = userModel.getResult().get(0);
                                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HomeMainActivity.class));

                                    }else{
                                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

                                    }
                                },
                                throwable -> {
                                    Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e("abc", throwable.getMessage());
                                }

                        )
                );

            }
        });


    }
}
