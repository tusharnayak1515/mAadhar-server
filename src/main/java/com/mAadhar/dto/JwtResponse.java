package com.mAadhar.dto;

public class JwtResponse {
    private boolean success;
    private String token;
    private String error;
    private UserResponse user;

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

    public JwtResponse(boolean success, String token, String error, UserResponse user) {
        this.success = success;
        this.token = token;
        this.error = error;
        this.user = user;
    }

    public JwtResponse() {
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "JwtResponse [success=" + success + ", token=" + token + ", error=" + error + ", user=" + user + "]";
    }
}

