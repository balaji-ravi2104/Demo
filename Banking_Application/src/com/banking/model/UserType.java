package com.banking.model;

public enum UserType {
	EMPLOYEE,
	CUSTOMER,
	ADMIN;
	
	public static UserType fromString(String type) {
        switch (type.toLowerCase()) {
            case "employee":
                return EMPLOYEE;
            case "customer":
                return CUSTOMER;
            case "admin":
                return ADMIN;
            default:
                throw new IllegalArgumentException("Invalid user type: " + type);
        }
    }
}
