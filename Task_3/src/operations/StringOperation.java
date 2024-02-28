package operations;

import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;

public class StringOperation {

    public int findStringLength(String string) throws CustomException {
    	   try{
        	UtilMethods.isNull(string,ConstantMessage.INPUT_NULL_MESSAGE);
        	return string.length();
        }catch(CustomException e){
           //System.out.println(e.getMessage());
        	 throw new CustomException("Error in finding string length !! ",e);	
        }
    }

    public char[] convertStringToArray(String string) throws CustomException {
        UtilMethods.isNull(string,ConstantMessage.INPUT_NULL_MESSAGE);
        return string.toCharArray();  
    }

    public char toFindPenultimate(String string, int position) throws CustomException {
        int length = findStringLength(string);
        UtilMethods.isPositionValid(position,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
        
        return string.charAt(length - position);
         
    }

    public int toFindFrequency(String input, char ch) throws CustomException {
        char[] array = convertStringToArray(input);
        int count = 0;
        for (char c : array) {
            if (ch == c) {
                count++;
            }
        }
        return count;
    }

    public int toFindGreatestPosition(String input, char ch) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        return input.lastIndexOf(ch);    
    }

    public String toFindLastNCharacter(String input, int position) throws CustomException {
        int length = findStringLength(input);
        UtilMethods.isPositionValid(position,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
        
        return input.substring(length - position);      
    }

    public String toFindFirstNCharacter(String input, int start, int position) throws CustomException {
        int length = findStringLength(input);
        UtilMethods.isPositionValid(position,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
        
        return input.substring(start, position);
    }

    public String toReplaceFirstNCharacter(String input, int position, String replace) throws CustomException {
        int length = findStringLength(input);
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(replace,ConstantMessage.REPLACE_INPUT_NULL_MESSAGE);
        UtilMethods.isPositionValid(position,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
        
        return replace + input.substring(position);        
    }

    public boolean isStartsWith(String input, String starts) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        return input.startsWith(starts);
    }

    public boolean isEndsWith(String input, String ends) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        return input.endsWith(ends);
    }

    public String convertLowerToUpper(String input) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        return input.toUpperCase();      
    }

    public String convertUpperToLower(String input) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        return input.toLowerCase();      
    }

    public String reverseString(String input) throws CustomException {
        UtilMethods.isNull(input, ConstantMessage.INPUT_NULL_MESSAGE);
        char[] charArray = convertStringToArray(input);
        int arrLength = charArray.length;

        for (int i = 0; i < arrLength / 2; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[arrLength - i - 1];
            charArray[arrLength - i - 1] = temp;
        }
        return new String(charArray);
    }

    public String multipleStringPrint(String string) throws CustomException {
        UtilMethods.isNull(string,ConstantMessage.INPUT_NULL_MESSAGE);
        return string;
    }

    public String stringConcatenate(String input, String splitedString) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(splitedString,ConstantMessage.SPLIT_INPUT_NULL_MESSAGE);
        input = input.replaceAll(splitedString,"");
        
        return input;
    }

    public String[] stringToArray(String input, String splitedString) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(splitedString,ConstantMessage.SPLIT_INPUT_NULL_MESSAGE);
        String[] stringArray = input.split(splitedString);
        
        return stringArray;       
    }

    public boolean isStringEqual(String input1, String input2) throws CustomException {
        UtilMethods.isNull(input1,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(input2,ConstantMessage.INPUT_NULL_MESSAGE);
        
        return input1.equals(input2);      
    }

    public boolean isStringEqualIgnoreCase(String input1, String input2) throws CustomException {
        UtilMethods.isNull(input1,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(input2,ConstantMessage.INPUT_NULL_MESSAGE);
        
        return input1.equalsIgnoreCase(input2);  
    }


    public String trimString(String input) throws CustomException {
        UtilMethods.isNull(input,ConstantMessage.INPUT_NULL_MESSAGE);
        return input.trim();    
    }

    public String mergeStrings(String[] stringArray, String joiner) throws CustomException {
        UtilMethods.isNull(stringArray,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(joiner,ConstantMessage.JOINER_INPUT_NULL_MESSAGE);
        String result = String.join(joiner,stringArray);
        
        return result;
    }
}