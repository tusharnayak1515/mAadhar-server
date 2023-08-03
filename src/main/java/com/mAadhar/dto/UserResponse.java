package com.mAadhar.dto;

public class UserResponse {
	private int citizenId;
	private String name;
	private String email;
	private String mobile;
	private String dob;
	private String address;
	private String gender;
	private String role;
	private String dp;
	
	public UserResponse() {
		
	}

	public UserResponse(int citizenId, String name, String email, String mobile, String dob, String address,
			String gender, String role, String dp) {
		super();
		this.citizenId = citizenId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.address = address;
		this.gender = gender;
		this.role = role;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDp() {
		return dp;
	}

	public void setDp(String dp) {
		this.dp = dp;
	}
	
}
