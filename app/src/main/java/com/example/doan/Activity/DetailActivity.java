package com.example.doan.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.doan.Adapter.PopularListAdapter;
import com.example.doan.Adapter.SizeDetailAdapter;
import com.example.doan.Adapter.colorDetailAdapter;
import com.example.doan.Adapter.viewpageDetailAdapter;
import com.example.doan.Model.CartItemModel;
import com.example.doan.Model.PopularModel;
import com.example.doan.Model.imgDetailModel;
import com.example.doan.R;
import com.example.doan.Retrofit.ApiMobile;
import com.example.doan.Retrofit.RetrofitClient;
import com.example.doan.Utils.Utils;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    DotsIndicator dotsIndicator;
    TextView txt_TieuDe, txt_Gia, txt_MoTa, txt_Rate, btn_cart;
    ArrayList<imgDetailModel> listImg = new ArrayList<>();

    colorDetailAdapter colorAdapter;
    RecyclerView recyclerViewColor, recyclerViewSize;
    SizeDetailAdapter sizeAdapter;

    int size;
    ImageView btn_back_detail, btn_cart_detail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessage, new IntentFilter("selected_size"));
        viewPager = findViewById(R.id.viewPager);
        txt_TieuDe = findViewById(R.id.txt_TieuDe);
        txt_Gia = findViewById(R.id.txt_Gia);
        txt_MoTa = findViewById(R.id.txt_MoTa);
        txt_Rate = findViewById(R.id.txt_Rate);
        dotsIndicator = findViewById(R.id.dotsIndicator);
        recyclerViewColor = findViewById(R.id.recyclerViewColor);
        recyclerViewSize = findViewById(R.id.recyclerViewSize);
        btn_cart = findViewById(R.id.btn_cart);
        btn_back_detail = findViewById(R.id.btn_back_detail);
        btn_cart_detail = findViewById(R.id.btn_cart_detail);

        btn_cart_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, CartActivity.class));
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        // Lay shoes ng dung chon
        PopularModel shoes = (PopularModel) bundle.get("object_shoes");



        txt_TieuDe.setText(shoes.getTitle());
        txt_Gia.setText(shoes.getPrice().intValue() + " VNĐ");
        txt_MoTa.setText(shoes.getThongtinSP());
        txt_Rate.setText(shoes.getRate().toString());

        int imgid1 = getResources().getIdentifier(shoes.getPicUrl1() , "drawable" , getPackageName()) ;
        int imgid2 = getResources().getIdentifier(shoes.getPicUrl2() , "drawable" , getPackageName()) ;

        // Lay list img tu shoes ng dung chon
        listImg.add(new imgDetailModel(imgid1));
        listImg.add(new imgDetailModel(imgid2));

        // Hien thi view page
        viewpageDetailAdapter viewpageAdapter = new viewpageDetailAdapter(listImg);
        viewPager.setAdapter(viewpageAdapter);
        dotsIndicator.setViewPager2(viewPager);


        // Lay danh sach sp khac mau
        ArrayList<PopularModel> listShoes = new ArrayList<>();
        listShoes = (ArrayList<PopularModel>) bundle.get("object_list_shoes");

        ArrayList<PopularModel> listSameProduct = new ArrayList<>();

        for (int i = 0; i < listShoes.size(); i++){
            if (listShoes.get(i).getTitle().equals(shoes.getTitle())){
                listSameProduct.add(listShoes.get(i));
            }
        }

        // Hien thi mau SP
        colorAdapter = new colorDetailAdapter(listSameProduct, this);
        recyclerViewColor.setAdapter(colorAdapter);
        recyclerViewColor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Hien thi Size
        ArrayList<String> listSize = new ArrayList<>();
        listSize.add("38");
        listSize.add("39");
        listSize.add("40");
        listSize.add("41");
        listSize.add("42");
        listSize.add("43");
        sizeAdapter = new SizeDetailAdapter(this, listSize);
        recyclerViewSize.setAdapter(sizeAdapter);
        recyclerViewSize.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItemModel itemModel = new CartItemModel(shoes, 1, listSize.get(size));
                boolean flag = false;
                for (int i = 0; i < Utils.cartItemModels.size(); i++){
                    if (Utils.cartItemModels.get(i).getShoes().getProductcolorid() == shoes.getProductcolorid() && Utils.cartItemModels.get(i).getSize() == itemModel.getSize()){
                        int sl = Utils.cartItemModels.get(i).getNumberInCart() + 1;
                        Utils.cartItemModels.get(i).setNumberInCart(sl);
                        flag = true;
                        break;
                    }
                }

                if(flag == false){
                    Utils.cartItemModels.add(itemModel);
                }

                Toast.makeText(DetailActivity.this,"Thêm sản phẩm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
            }
        });

        btn_back_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, HomeMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public BroadcastReceiver mMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            size = intent.getIntExtra("size_position", 0);

        }
    };
}
