package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.PatternSyntaxException;

import org.junit.jupiter.api.Test;

import exception.CustomException;
import regex.operations.RegexHelper;

public class NullTestUnit {

	@Test
	void test() throws PatternSyntaxException, CustomException {
		RegexHelper regexHelper = new RegexHelper();
		assertEquals(true,regexHelper.validateMobileNumber("991521777"));
		
	}
	

}
