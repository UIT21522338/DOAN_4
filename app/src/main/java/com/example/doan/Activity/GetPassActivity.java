package com.example.doan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;
import com.example.doan.Retrofit.ApiMobile;
import com.example.doan.Retrofit.RetrofitClient;
import com.example.doan.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetPassActivity extends AppCompatActivity {
    private TextView txt_doimk;
    private EditText edt_SoDT_GetPass, edt_NewPass_GetPass, edt_ConfirmPass_GetPass;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMobile apiMobile;
    ImageView btn_back_getPass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpass);

        txt_doimk = findViewById(R.id.txt_doimk);
        edt_SoDT_GetPass = findViewById(R.id.edt_SoDT_GetPass);
        edt_NewPass_GetPass = findViewById(R.id.edt_NewPass_GetPass);
        edt_ConfirmPass_GetPass = findViewById(R.id.edt_ConfirmPass_GetPass);
        btn_back_getPass = findViewById(R.id.btn_back_getPass);

        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);


        txt_doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SoDT = edt_SoDT_GetPass.getText().toString().trim();
                String NewPass = edt_NewPass_GetPass.getText().toString().trim();
                String ConfirmPass = edt_ConfirmPass_GetPass.getText().toString().trim();

                if(NewPass.equals(ConfirmPass)){
                    compositeDisposable.add(apiMobile.GetPass(SoDT, NewPass,ConfirmPass)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if(userModel.isSuccess()){
                                            Toast.makeText(GetPassActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(GetPassActivity.this, LoginActivity.class));

                                        }else{
                                            Toast.makeText(GetPassActivity.this, "Số điện thoại không tồn tại", Toast.LENGTH_SHORT).show();

                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(GetPassActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }else{
                    Toast.makeText(GetPassActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_back_getPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetPassActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
