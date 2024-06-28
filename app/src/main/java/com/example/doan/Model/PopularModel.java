package com.example.doan.Model;

import java.io.Serializable;

public class PopularModel implements Serializable {
    private int productcolorid;
    private String tensp;
//    private Float rate;
    private Double giasp;
    private String img1;
    private String img2;
    private String thongtinsp;
    private Float rate;

//    private String description;
//    private int numberinCart;

//    public int getNumberinCart() {
//        return numberinCart;
//    }
//
//    public void setNumberinCart(int numberinCart) {
//        this.numberinCart = numberinCart;
//    }


    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Double getGiasp() {
        return giasp;
    }

    public void setGiasp(Double giasp) {
        this.giasp = giasp;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public PopularModel(int productcolorid, String tensp, Double giasp, String img1, String img2, String thongtinSP, Float rate) {
        this.productcolorid = productcolorid;
        this.tensp = tensp;
        this.giasp = giasp;
        this.img1 = img1;
        this.img2 = img2;
        this.thongtinsp = thongtinSP;
        this.rate = rate;
    }



    public String getPicUrl1() {
        return img1;
    }

    public void setPicUrl1(String picUrl1) {
        this.img1 = picUrl1;
    }

    public String getPicUrl2() {
        return img2;
    }

    public void setPicUrl2(String picUrl2) {
        this.img2 = picUrl2;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getTitle() {
        return tensp;
    }

    public void setTitle(String title) {
        tensp = title;
    }

//    public Float getRate() {
//        return rate;
//    }
//
//    public void setRate(Float rate) {
//        this.rate = rate;
//    }

    public Double getPrice() {
        return giasp;
    }

    public void setPrice(Double price) {
        this.giasp = price;
    }

    public int getProductcolorid() {
        return productcolorid;
    }

    public void setProductcolorid(int productcolorid) {
        this.productcolorid = productcolorid;
    }

    public String getThongtinSP() {
        return thongtinsp;
    }

    public void setThongtinSP(String thongtinSP) {
        this.thongtinsp = thongtinSP;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
