package utils;

import java.util.List;
import java.util.regex.Pattern;

import exception.CustomException;
import message.ConstantMessage;

public class RegexUtils {
	public static final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("^[7-9]{1}[0-9]{9}$");
	public static final Pattern ALPHA_NUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]+$");
	public static final Pattern EMAIL_VALIDATE_PATTERN = Pattern
			.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	public static final Pattern HTML_EXTRACT_PATTERN = Pattern.compile("</?[a-z]*>");
	public static final Pattern PASSWORD_VALIDATE_PATTERN = Pattern
			.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{8,}$");

	public static Pattern startsWithPattern(String regex) throws CustomException {
		UtilMethods.isNull(regex, "Regex Pattern Should not be a NUll");
		return Pattern.compile("\\A" + regex + ".*");
	}

	public static Pattern endsWithPattern(String regex) throws CustomException {
		UtilMethods.isNull(regex, "Regex Pattern Should not be a NUll");
		return Pattern.compile(".*" + regex + "\\Z");
	}

	public static Pattern containsPattern(String regex) throws CustomException {
		UtilMethods.isNull(regex, "Regex Pattern Should not be a NUll");
		return Pattern.compile(".*" + regex + ".*");
	}

	public static Pattern equalsPattern(String regex) throws CustomException {
		UtilMethods.isNull(regex, "Regex Pattern Should not be a NUll");
		return Pattern.compile(regex);
	}

	public static Pattern equalsIgnoreCasePattern(String regex) throws CustomException {
		UtilMethods.isNull(regex, "Regex Pattern Should not be a NUll");
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}

	public static Pattern minMaxLengthPattern(int mininumLength, int maximumLength) throws CustomException {
		UtilMethods.validateStartEnd(maximumLength, mininumLength, "Please Enter the Valid start and end length");
		return Pattern.compile("^[\\w\\W]{" + mininumLength + "," + maximumLength + "}$");
	}

	public static Pattern mapGeneratePattern(List<String> list2) throws CustomException {
		UtilMethods.isNull(list2, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);
		return Pattern.compile("\\b(" + String.join("|", list2) + ")\\b");
	}
}
