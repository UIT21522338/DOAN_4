package com.example.doan.Model;

import java.util.ArrayList;

public class GetAllSPModelResult {
    boolean success;
    String message;
    ArrayList<PopularModel> resultNam;
    ArrayList<PopularModel> resultNu;

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

    public ArrayList<PopularModel> getResultNam() {
        return resultNam;
    }

    public void setResultNam(ArrayList<PopularModel> resultNam) {
        this.resultNam = resultNam;
    }

    public ArrayList<PopularModel> getResultNu() {
        return resultNu;
    }

    public void setResultNu(ArrayList<PopularModel> resultNu) {
        this.resultNu = resultNu;
    }
}
