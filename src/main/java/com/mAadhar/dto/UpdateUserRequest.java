package com.mAadhar.dto;

public class UpdateUserRequest {
    private String mobile;
	private String address;
	private String dob;

    public UpdateUserRequest() {
        
    }

    public UpdateUserRequest(String mobile, String address, String dob) {
        this.mobile = mobile;
        this.address = address;
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    
}
