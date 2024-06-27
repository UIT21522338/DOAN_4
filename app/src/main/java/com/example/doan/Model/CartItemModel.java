package com.example.doan.Model;

public class CartItemModel {
    PopularModel shoes;
    int numberInCart;
    String size;

    public CartItemModel(PopularModel shoes, int numberInCart, String size) {
        this.shoes = shoes;
        this.numberInCart = numberInCart;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public PopularModel getShoes() {
        return shoes;
    }

    public void setShoes(PopularModel shoes) {
        this.shoes = shoes;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
