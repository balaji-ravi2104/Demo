package regex.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import exception.CustomException;
import message.ConstantMessage;
import utils.RegexUtils;
import utils.UtilMethods;

public class RegexHelper {

	public boolean validateMobileNumber(String mobileNumber) throws CustomException, PatternSyntaxException {
		UtilMethods.isNull(mobileNumber, ConstantMessage.INPUT_NULL_MESSAGE);
		return RegexUtils.MOBILE_NUMBER_PATTERN.matcher(mobileNumber).matches();
	}

	public boolean validatealphaNumericCharacter(String alphaNumeric) throws CustomException, PatternSyntaxException {
		UtilMethods.isNull(alphaNumeric, ConstantMessage.INPUT_NULL_MESSAGE);
		return RegexUtils.ALPHA_NUMERIC_PATTERN.matcher(alphaNumeric).matches();
	}

	public boolean toFindStartsWith(String givenString, String matchString)
			throws CustomException, PatternSyntaxException {
		validateGivenAndMatcherString(givenString,matchString);
		return RegexUtils.startsWithPattern(matchString).matcher(givenString).find();
	}

	public boolean isContainsString(String givenString, String matchString)
			throws CustomException, PatternSyntaxException {
		validateGivenAndMatcherString(givenString,matchString);
		return RegexUtils.containsPattern(matchString).matcher(givenString).find();
	}

	public boolean toFindEndsWith(String givenString, String matchString)
			throws CustomException, PatternSyntaxException {
		validateGivenAndMatcherString(givenString,matchString);
		return RegexUtils.endsWithPattern(matchString).matcher(givenString).find();
	}

	public boolean isEquals(String givenString, String matchString) throws CustomException, PatternSyntaxException {
		validateGivenAndMatcherString(givenString,matchString);
		return RegexUtils.equalsPattern(matchString).matcher(givenString).matches();
	}

	public List<String> equalsIgnoreCase(String[] stringArray, String matcher)
			throws CustomException, PatternSyntaxException {
		UtilMethods.isNull(stringArray, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);
		UtilMethods.isNull(matcher, ConstantMessage.INPUT_NULL_MESSAGE);
		Pattern pattern = RegexUtils.equalsIgnoreCasePattern(matcher);
		return getAnswerList(pattern, stringArray);
	}

	public boolean validateEmail(String email) throws CustomException, PatternSyntaxException {
		UtilMethods.isNull(email, ConstantMessage.INPUT_NULL_MESSAGE);
		return RegexUtils.EMAIL_VALIDATE_PATTERN.matcher(email).matches();
	}

	public List<String> stringWithMinMax(String[] stringArray, int mininumLength, int maximumLength)
			throws CustomException, PatternSyntaxException {
		UtilMethods.isNull(stringArray, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);
		UtilMethods.validateStartEnd(maximumLength, mininumLength, "Please Enter the Valid start and end length");
		Pattern pattern = RegexUtils.minMaxLengthPattern(mininumLength, maximumLength);
		return getAnswerList(pattern, stringArray);
	}
	
	public Map<String, Integer> getMapUsingList(List<String> list1, List<String> list2) throws CustomException,PatternSyntaxException {
		UtilMethods.isNull(list1, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);
		UtilMethods.isNull(list2, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);
		Pattern pattern = RegexUtils.mapGeneratePattern(list2);
		Map<String,Integer> ansMap = getNewMap();
		for(int i=0;i<list1.size();i++) {
			String listItem = list1.get(i);
			if(pattern.matcher(listItem).find()) {
				ansMap.put(listItem, i);
			}
		}
		return ansMap;
	}
	
	public List<String> extractHTMLTags(String htmlInput) throws CustomException,PatternSyntaxException {
		UtilMethods.isNull(htmlInput, ConstantMessage.INPUT_NULL_MESSAGE);
		Pattern pattern = RegexUtils.HTML_EXTRACT_PATTERN;
		Matcher matcher = pattern.matcher(htmlInput);
		List<String> ansList = getNewArrayList();
		while (matcher.find()) {
			ansList.add(matcher.group());
		}
		return ansList;
	}
	
	public boolean validatePassword(String password) throws CustomException ,PatternSyntaxException{
		UtilMethods.isNull(password, ConstantMessage.INPUT_NULL_MESSAGE);
		return RegexUtils.PASSWORD_VALIDATE_PATTERN.matcher(password).matches();
	}
	
	
	private static void validateGivenAndMatcherString(String given,String matcher) throws CustomException {
		UtilMethods.isNull(given, ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(matcher, ConstantMessage.INPUT_NULL_MESSAGE);
	}

	public static <E> List<E> getNewArrayList() {
		return new ArrayList<>();
	}
	
	public static <K,V> Map<K,V> getNewMap(){
		return new HashMap<>();
		
	}

	private static List<String> getAnswerList(Pattern pattern, String[] array) throws CustomException,PatternSyntaxException {
		UtilMethods.isNull(array, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);
		UtilMethods.isNull(pattern, ConstantMessage.SPLIT_INPUT_NULL_MESSAGE);
		List<String> ansList = getNewArrayList();
		for (String string : array) {
			if (pattern.matcher(string).matches()) {
				ansList.add(string);
			}
		}
		return ansList;
	}

}
