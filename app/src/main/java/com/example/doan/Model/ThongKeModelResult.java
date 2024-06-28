package com.example.doan.Model;

import java.util.ArrayList;

public class ThongKeModelResult {
    boolean success;
    String message;
    ArrayList<ThongKe> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ThongKe> getResult() {
        return result;
    }

    public void setResult(ArrayList<ThongKe> result) {
        this.result = result;
    }
}
