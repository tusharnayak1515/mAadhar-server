package com.mAadhar.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int citizenId;

    private String name;
    private String dob;
    private String address;
    private String email;
    private String mobile;
    private String gender;
    private String role;
    private String dp;
    private String password;
    
    public User() {
		
	}

	public User(String name, String dob, String address, String email, String mobile, String gender, String role,
			String dp, String password) {
		this.name = name;
		this.dob = dob;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.role = role;
		this.dp = dp;
		this.password = password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDp() {
		return dp;
	}

	public void setDp(String dp) {
		this.dp = dp;
	}

	@Override
	public String toString() {
		return "User [citizenId=" + citizenId + ", name=" + name + ", dob=" + dob + ", address=" + address + ", email="
				+ email + ", mobile=" + mobile + ", gender=" + gender + ", role=" + role + ", dp=" + dp + ", password="
				+ password + "]";
	}
	
}
