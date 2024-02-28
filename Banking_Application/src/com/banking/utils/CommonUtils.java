package com.banking.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {
	 private static final List<String> fields = List.of(
	            "Password",
	            "FirstName",
	            "LastName",
	            "Gender",
	            "Email",
	            "ContactNumber",
	            "Address",
	            "DateOfBirth",
	            "Pan",
	            "Aadhar"
	  );
	 
	 public static Map<Integer, String> generateFieldMap() {
	        Map<Integer, String> fieldMap = new HashMap<>();
	        int key = 1;
	        for (String field : fields) {
	            fieldMap.put(key++, field);
	        }
	        return fieldMap;
	    }

}
