package com.mAadhar.dto;

public class AadharRequest {
	private String issueDate;
	
	public AadharRequest() {
		
	}

	public AadharRequest(int citizenId, String issueDate) {
		super();
		this.issueDate = issueDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
	
}
