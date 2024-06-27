package com.example.doan.Model;

import java.io.Serializable;

public class InvoiceDetailModel {
    String title;
    Double size;
    Double sl;
    Double Price;
    String imgUrl;

    public InvoiceDetailModel(String title, Double size, Double sl, Double price, String imgUrl) {
        this.title = title;
        this.size = size;
        this.sl = sl;
        Price = price;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
