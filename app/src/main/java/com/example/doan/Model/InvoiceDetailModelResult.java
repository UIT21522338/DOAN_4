package com.example.doan.Model;

import java.util.ArrayList;

public class InvoiceDetailModelResult {
    boolean success;
    String message;
    ArrayList<InvoiceDetailModel> result;

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

    public ArrayList<InvoiceDetailModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<InvoiceDetailModel> result) {
        this.result = result;
    }
}
