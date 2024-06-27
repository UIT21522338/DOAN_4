package com.example.doan.Model;

public class User {
    String khachhangid, hoten, diachi, sodt, email, password;

    public User(String khachhangid, String hoten, String diachi, String sodt, String email, String password) {
        this.khachhangid = khachhangid;
        this.hoten = hoten;
        this.diachi = diachi;
        this.sodt = sodt;
        this.email = email;
        this.password = password;
    }

    public User(String hoten, String diachi, String sodt, String password) {
        this.hoten = hoten;
        this.diachi = diachi;
        this.sodt = sodt;
        this.password = password;
    }

    public String getKhachhangid() {
        return khachhangid;
    }

    public void setKhachhangid(String khachhangid) {
        this.khachhangid = khachhangid;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
