package com.mAadhar.dto;

public class AadharUser {
    private Long id;
    private String aadharNumber;
    private int duplicates;
    private String status;
    private String issueDate;
    private int citizenId;
    private String name;
    private String dob;
    private String address;
    private String email;
    private String mobile;
    private String gender;
    private String dp;

    public AadharUser() {

    }

    public AadharUser(Long id, String aadharNumber, int duplicates, String status, String issueDate, int citizenId,
            String name, String dob, String address, String email, String mobile, String gender, String dp) {
        this.id = id;
        this.aadharNumber = aadharNumber;
        this.duplicates = duplicates;
        this.status = status;
        this.issueDate = issueDate;
        this.citizenId = citizenId;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.dp = dp;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(int duplicates) {
        this.duplicates = duplicates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

}
