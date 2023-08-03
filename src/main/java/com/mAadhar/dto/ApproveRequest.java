package com.mAadhar.dto;

public class ApproveRequest {
    private long aadharId;

    public ApproveRequest() {
        
    }

    public ApproveRequest(long aadharId) {
        this.aadharId = aadharId;
    }

    public long getAadharId() {
        return aadharId;
    }

    public void setAadharId(long aadharId) {
        this.aadharId = aadharId;
    }
    
}
