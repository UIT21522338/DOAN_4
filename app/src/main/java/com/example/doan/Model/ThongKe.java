package com.example.doan.Model;

public class ThongKe {
    private String ngayorder;
    private int tong;

    private int tongtien;
    private String thang, nam;

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getNgayOrder() {
        return ngayorder;
    }

    public void setNgayOrder(String ngayOrder) {
        this.ngayorder = ngayOrder;
    }

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }
}
