package com.example.doan.Model;

import java.util.ArrayList;

public class InvoiceModelResult {
    boolean success;
    String message;
    ArrayList<InvoiceModel> result;

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

    public ArrayList<InvoiceModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<InvoiceModel> result) {
        this.result = result;
    }
}
