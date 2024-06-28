package com.example.doan.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.doan.Adapter.PopularListAdapter;
import com.example.doan.R;
import com.example.doan.Retrofit.ApiMobile;
import com.example.doan.Retrofit.RetrofitClient;
import com.example.doan.Utils.Utils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThongKe extends AppCompatActivity {
    Toolbar toolbar;
    BarChart barChart, barchartDT;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMobile apiMobile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);

        initview();
        ActionToolBar();
        apiMobile = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMobile.class);

        getDataChart();
        settingBarchartDT();

    }

    private void settingBarchartDT(){
        barchartDT.getDescription().setEnabled(false);
        barchartDT.setDrawValueAboveBar(false);
        XAxis xAxis = barchartDT.getXAxis();
        xAxis.setAxisMinimum(1);
        xAxis.setAxisMaximum(12);
        YAxis yAxisright = barchartDT.getAxisRight();
        yAxisright.setAxisMinimum(0);
        YAxis yAxisleft = barchartDT.getAxisLeft();
        yAxisleft.setAxisMinimum(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_thongke, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tkdoanhthu){
            getTKThang();
            return true;
        }else if(id == R.id.tksl){
            getDataChart();
            return true;
        }else if(id == R.id.logout){
            startActivity(new Intent(ThongKe.this, LoginActivity.class));
            finish();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    private void getTKThang() {
        barChart.setVisibility(View.VISIBLE);
        barchartDT.setVisibility(View.GONE);

        compositeDisposable.add(apiMobile.thongKeDoanhThu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        thongKeDTModel -> {
                            if(thongKeDTModel.isSuccess()){
                                List<BarEntry> listdataDT = new ArrayList<>();
                                List<String> xvalues = new ArrayList<>();

                                for(int i = 0; i < thongKeDTModel.getResult().size(); i++){
                                    String thang = thongKeDTModel.getResult().get(i).getThang();
                                    String nam = thongKeDTModel.getResult().get(i).getNam();
                                    String date = thang + "-" + nam;

                                    Float tongtien = (float) thongKeDTModel.getResult().get(i).getTongtien();
                                    listdataDT.add(new BarEntry(i, tongtien));
                                    xvalues.add(date);

                                    BarDataSet barDataSet = new BarDataSet(listdataDT, "Thống kê doanh thu theo tháng");
                                    BarData data = new BarData(barDataSet);
                                    barChart.setData(data);
                                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                                    barDataSet.setValueTextColor(Color.BLACK);
                                    barDataSet.setValueTextSize(16f);

                                    barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xvalues));

                                    barChart.invalidate();


                                }
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("abc", throwable.getMessage());
                        }

                )
        );
    }

    private void getDataChart() {
        barchartDT.setVisibility(View.VISIBLE);

        List<BarEntry> listdata = new ArrayList<>();
        List<String> xvalues = new ArrayList<>();

        compositeDisposable.add(apiMobile.thongKeSL()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        thongKeModel -> {
                            if(thongKeModel.isSuccess()){
                                Log.d("abc", "success");
                                for(int i = 0; i < thongKeModel.getResult().size(); i++){
                                    String ngayOrder = thongKeModel.getResult().get(i).getNgayOrder();
                                    Float tong = (float) thongKeModel.getResult().get(i).getTong();

                                    listdata.add(new BarEntry(i, tong));
                                    xvalues.add(ngayOrder);
                                }

                                Log.d("abc", new Gson().toJson(xvalues));

                                BarDataSet barDataSet = new BarDataSet(listdata, "Thống kê số lượng đơn hàng đã bán theo ngày");
                                BarData data = new BarData(barDataSet);
                                barChart.setData(data);
                                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                                barDataSet.setValueTextColor(Color.BLACK);
                                barDataSet.setValueTextSize(16f);
                                barChart.getDescription().setEnabled(false);
                                barChart.invalidate();

                                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xvalues));
                                barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                                barChart.getXAxis().setGranularity(1f);
                                barChart.getXAxis().setGranularityEnabled(true);
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("abc", throwable.getMessage());
                        }

                )
        );
    }

    private void initview(){
        toolbar = findViewById(R.id.toolbar);
        barChart = findViewById(R.id.barchart);
        barchartDT = findViewById(R.id.barchartDT);
    }

    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
