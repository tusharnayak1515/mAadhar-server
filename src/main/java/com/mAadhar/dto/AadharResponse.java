package com.mAadhar.dto;

public class AadharResponse {
    private boolean success;
    private String error;
    private AadharUser aadhar;

    public AadharResponse() {
        
    }

    public AadharResponse(boolean success, String error, AadharUser aadhar) {
        this.success = success;
        this.error = error;
        this.aadhar = aadhar;
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

    public AadharUser getAadhar() {
        return aadhar;
    }

    public void setAadhar(AadharUser aadhar) {
        this.aadhar = aadhar;
    }

}
