package com.example.doan.Model;

import java.util.ArrayList;
import java.util.List;

public class ListSPModel {
    boolean success;
    String message;
    ArrayList<PopularModel> result;

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

    public ArrayList<PopularModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<PopularModel> result) {
        this.result = result;
    }
}
