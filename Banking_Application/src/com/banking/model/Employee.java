package com.banking.model;

public class Employee extends User {
	int branchId;

	@Override
	public String toString() {
		return "Employee [branchId=" + branchId + "]";
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

}
