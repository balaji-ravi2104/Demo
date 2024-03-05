package com.banking.model;

public class Customer extends User {
	String panNumber;
	String aadharNumber;

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@Override
	public String toString() {
		return "Customer [panNumber=" + panNumber + ", aadharNumber=" + aadharNumber + "]";
	}

}
