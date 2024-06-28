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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignUpActivity extends AppCompatActivity {
    private TextView txt_DangNhap, txt_Dangki_SignUp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ApiMobile apiMobile;
    private EditText edt_HoTen_SignUp, edt_SoDT_SignUp, edt_Pass_SignUp, edt_DiaChi_SignUp;
    ImageView btn_back_signup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txt_DangNhap = findViewById(R.id.txt_DangNhap);
        txt_Dangki_SignUp = findViewById(R.id.txt_Dangki_SignUp);

        edt_HoTen_SignUp = findViewById(R.id.edt_HoTen_SignUp);
        edt_SoDT_SignUp = findViewById(R.id.edt_SoDT_SignUp);
        edt_Pass_SignUp = findViewById(R.id.edt_Pass_SignUp);
        edt_DiaChi_SignUp = findViewById(R.id.edt_DiaChi_SignUp);
        btn_back_signup = findViewById(R.id.btn_back_signup);

        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);


        txt_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        txt_Dangki_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HoTen = edt_HoTen_SignUp.getText().toString().trim();
                String SoDT = edt_SoDT_SignUp.getText().toString().trim();
                String Pass = edt_Pass_SignUp.getText().toString().trim();
                String DiaChi = edt_DiaChi_SignUp.getText().toString().trim();

                if(KiemTraKiTuDacBiet(HoTen) || KiemTraKiTuDacBiet(DiaChi)){
                    Toast.makeText(SignUpActivity.this, "Có chứa kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                }else if (HoTen.length() == 0 || SoDT.length() == 0 || Pass.length() == 0 || DiaChi.length() == 0){
                    Toast.makeText(SignUpActivity.this, "Không thể để trống dòng", Toast.LENGTH_SHORT).show();
                }else if(Pass.length() < 7){
                    Toast.makeText(SignUpActivity.this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
                }else if(isValidPhoneNumber(SoDT)){
                    Toast.makeText(SignUpActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else{
                    compositeDisposable.add(apiMobile.dangki(HoTen, SoDT, Pass, DiaChi)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if(userModel.isSuccess()){
                                            Toast.makeText(SignUpActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                        }else{
                                            Toast.makeText(SignUpActivity.this, "Số điện thoại đã tồn tại", Toast.LENGTH_SHORT).show();
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(SignUpActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("abc", throwable.getMessage());
                                    }

                            )
                    );
                }


            }
        });



        btn_back_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean KiemTraKiTuDacBiet(String name) {
        // Biểu thức chính quy kiểm tra ký tự đặc biệt
        String regex = "[^a-zA-Z0-9 ]";
        Pattern pattern = Pattern.compile(regex); // Bien dich bieu thuc chinh quy thanh 1 mau
        Matcher matcher = pattern.matcher(name); // Ao dung mau len chuoi dau vao

        return matcher.find();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Biểu thức chính quy kiểm tra số điện thoại
        String regex = "^0[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
}
