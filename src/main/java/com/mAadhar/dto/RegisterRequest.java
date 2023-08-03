package com.mAadhar.dto;

public class RegisterRequest {
	private String name;
	private String email;
	private String mobile;
	private String address;
	private String dob;
	private String gender;
	private String password;
	
	public RegisterRequest() {
		
	}

	public RegisterRequest(String name, String email, String mobile, String address, String dob, String gender,
			String password) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.password = password;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterRequest [name=" + name + ", email=" + email + ", mobile=" + mobile + ", address=" + address
				+ ", dob=" + dob + ", gender=" + gender + ", password=" + password + "]";
	}
	
}
