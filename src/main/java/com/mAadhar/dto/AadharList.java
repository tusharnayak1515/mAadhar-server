package com.mAadhar.dto;

import java.util.ArrayList;
import java.util.List;

public class AadharList {
    private boolean success;
    private String error;
    private List<AadharUser> aadharList = new ArrayList<AadharUser>();

    public AadharList() {
        
    }

    public AadharList(boolean success, String error, List<AadharUser> aadharList) {
        this.success = success;
        this.error = error;
        this.aadharList = aadharList;
    }

    public List<AadharUser> getAadharList() {
        return aadharList;
    }

    public void setAadharList(List<AadharUser> aadharList) {
        this.aadharList = aadharList;
    }

    public void addAadhar(AadharUser aadharUser) {
        this.aadharList.add(aadharUser);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
