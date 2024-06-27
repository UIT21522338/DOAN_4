package com.example.doan.Model;

import java.io.Serializable;

public class InvoiceModel implements Serializable {
    String MADH;
    private String picUrl;
    private String Title;
    private Double price;
    private Double numberInCart;
    private String status;
    private Double size;
    private Double TongTien;
    private Double tongSP;
    private String ngayDatHang;
    private Double tienHang;
    private Double phiShip;

    public InvoiceModel(String MADH, String picUrl, String title, Double price, Double numberInCart, String status, Double size, Double tongTien, Double tongSP, String ngayDatHang, Double tiengHang, Double phiShip) {
        this.MADH = MADH;
        this.picUrl = picUrl;
        Title = title;
        this.price = price;
        this.numberInCart = numberInCart;
        this.status = status;
        this.size = size;
        TongTien = tongTien;
        this.tongSP = tongSP;
        this.ngayDatHang = ngayDatHang;
        this.tienHang = tiengHang;
        this.phiShip = phiShip;
    }

    public Double getTiengHang() {
        return tienHang;
    }

    public void setTiengHang(Double tiengHang) {
        this.tienHang = tiengHang;
    }

    public Double getPhiShip() {
        return phiShip;
    }

    public void setPhiShip(Double phiShip) {
        this.phiShip = phiShip;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public String getMADH() {
        return MADH;
    }

    public void setMADH(String MADH) {
        this.MADH = MADH;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(Double numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getTongTien() {
        return TongTien;
    }

    public void setTongTien(Double tongTien) {
        TongTien = tongTien;
    }

    public Double getTongSP() {
        return tongSP;
    }

    public void setTongSP(Double tongSP) {
        this.tongSP = tongSP;
    }
}
