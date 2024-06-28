package com.example.doan.Retrofit;

import io.reactivex.rxjava3.core.Observable;

import com.example.doan.Model.CartItemModel;
import com.example.doan.Model.CartItemModelResult;
import com.example.doan.Model.GetAllSPModelResult;
import com.example.doan.Model.InvoiceDetailModelResult;
import com.example.doan.Model.InvoiceModelResult;
import com.example.doan.Model.ListSPModel;
import com.example.doan.Model.ThongKeModelResult;
import com.example.doan.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiMobile {
    @GET("GetListSP.php")
    Observable<ListSPModel> GetListSP();

    @GET("AllSP.php")
    Observable<GetAllSPModelResult> getAllSP();

    @GET("ThongKeSLDonHang.php")
    Observable<ThongKeModelResult> thongKeSL();

    @GET("ThongKeDoanhThu.php")
    Observable<ThongKeModelResult> thongKeDoanhThu();

    @POST("test1.php")
    @FormUrlEncoded
    Observable<GetAllSPModelResult> SearchSP(
            @Field("danhMuc") String danhMuc,
            @Field("name") String name
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
      @Field("sodt") String sodt,
      @Field("password") String password
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangki(
            @Field("HoTen") String HoTen,
            @Field("SoDT") String SoDT,
            @Field("Pass") String Pass,
            @Field("DiaChi") String DiaChi
    );

    @POST("GetPass.php")
    @FormUrlEncoded
    Observable<UserModel> GetPass(
            @Field("SoDT") String SoDT,
            @Field("NewPass") String NewPass,
            @Field("ConfirmPass") String ConfirmPass
    );

    @POST("LichSuDonHang.php")
    @FormUrlEncoded
    Observable<InvoiceModelResult> GetInvoice(
            @Field("SoDT") String SoDT
    );

    @POST("ChiTietDonHang.php")
    @FormUrlEncoded
    Observable<InvoiceDetailModelResult> GetInvoiceDetail(
            @Field("SoDT") String SoDT,
            @Field("MaDH") String MaDH
    );

    @POST("ThanhToan.php")
    @FormUrlEncoded
    Observable<CartItemModelResult> getResultPayment(
            @Field("SoDT") String SoDT,
            @Field("TongTien") Double TongTien,
            @Field("TienHang") Double TienHang,
            @Field("PhiShip") Double PhiShip,
            @Field("cart") String cart,
            @Field("hoTen") String hoTen,
            @Field("DiaChi") String DiaChi
    );
}
