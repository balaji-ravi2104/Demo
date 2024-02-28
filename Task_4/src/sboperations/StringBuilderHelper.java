package sboperations;
import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;

public class StringBuilderHelper{
	
	public int toFindStringLength(StringBuilder sb) throws CustomException{
		try{
			UtilMethods.isNull(sb,ConstantMessage.INPUT_NULL_MESSAGE);
			return sb.length();
		}catch(CustomException e){
			throw new CustomException("Error in finding string length",e);
		}
	}

	public void appendString(StringBuilder sb, String[] array, String delimiter) throws CustomException {
    try {
    	int length = toFindStringLength(sb);
       	UtilMethods.isNull(sb,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(array, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);

        if (length > 0) {
            sb.append(delimiter);
        }

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(delimiter);
            }
        }

    } catch (CustomException e) {
        throw new CustomException("Error in appending strings", e);
    }
}


	public void insertString(StringBuilder sb,String insertString,String delimiter) throws CustomException{ 
		try{
			int delimiterIndex = findIndex(sb,delimiter);
			int length = toFindStringLength(sb);
			UtilMethods.isPositionValid(delimiterIndex,length,"There is no delimiter "+delimiter+"in the string, so can't do the operations");
			UtilMethods.isNull(insertString,ConstantMessage.INPUT_NULL_MESSAGE);
			sb.insert(delimiterIndex,insertString);		
		}catch(CustomException e){
			throw new CustomException("Error in inserting strings",e);
		}
	}

	public void deleteString(StringBuilder sb,String delimiter,int startPosition) throws CustomException{
		try{
			int delimiterIndex = findIndex(sb,delimiter);
			int length = toFindStringLength(sb);
			UtilMethods.isPositionValid(delimiterIndex,length,"There is no delimiter "+delimiter+"in the string, so can't do the operations");			
			deleteCharacterByIndex(sb,0,delimiterIndex);
		}catch(CustomException e){
			throw new CustomException("Errro in deleting string",e);
		}
	}
	
	public void deleteCharacterByIndex(StringBuilder sb,int start,int end) throws CustomException{
		try{
			startEndIndexValidation(sb,start,end);
			sb.delete(start,end);
		}catch(CustomException e){
			throw new CustomException("Error in deleting Characters from the string",e);
		}
	}

	public void replaceString(StringBuilder sb,String delimiter,char separator) throws CustomException{
		try{
			int delimiterIndex = findIndex(sb,delimiter);
			int length = toFindStringLength(sb);
			UtilMethods.isPositionValid(delimiterIndex,length,"There is no delimiter "+delimiter+"in the string, so can't do the operations");			
			while(delimiterIndex!=-1){
				sb.setCharAt(delimiterIndex,separator);
				delimiterIndex = sb.indexOf(delimiter);
			}			
		}catch (CustomException e) {
			throw new CustomException("Error in replacing space with hypen",e);
		}
	}

	public void reverseString(StringBuilder sb) throws CustomException{
		try{
			UtilMethods.isNull(sb,ConstantMessage.INPUT_NULL_MESSAGE);
			sb.reverse();
		}catch(CustomException e){
			throw new CustomException("Error in reversing string",e);
		}
	}

	

	public void replaceCharacterByIndex(StringBuilder sb,int start,int end,String replace) throws CustomException{
		try{
			startEndIndexValidation(sb,start,end);
			UtilMethods.isNull(replace,ConstantMessage.INPUT_NULL_MESSAGE);
			sb.replace(start,end,replace);
		}catch(CustomException e){
			throw new CustomException("Error in replacing Characters from the string",e);
		}
	}

	public int toFindFirstOccurance(StringBuilder sb,String delimiter) throws CustomException{
		try{
			return findIndex(sb,delimiter);
		}catch(CustomException e){
			throw new CustomException("Error in index of the string",e);
		}
	}

	public int toFindLastOccurance(StringBuilder sb,String delimiter) throws CustomException{
		try{
			validateStringBuilderAndDelimiter(sb,delimiter);
			return sb.lastIndexOf(delimiter);
		}catch(CustomException e){
			throw new CustomException("Error in index of the string",e);
		}	
	}
	
	public StringBuilder getStringBuilder(){
		return new StringBuilder();
	}
	
	public void appendStrings(StringBuilder sb,String string) throws CustomException{
		try{
			validateStringBuilderAndDelimiter(sb,string);
			sb.append(string);
		}catch(CustomException e){
			throw new CustomException("Error in Appending string",e);
		}
	}
	

	private int findIndex(StringBuilder sb,String delimiter) throws CustomException{
	try{
		UtilMethods.isNull(sb,ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(delimiter,ConstantMessage.SPLIT_INPUT_NULL_MESSAGE);
		return sb.indexOf(delimiter);
	}catch(CustomException e){
		throw new CustomException(e.getMessage(),e);
	}	
	}
	
	private void startEndIndexValidation(StringBuilder sb,int start,int end)throws CustomException{
		try{
			int length = toFindStringLength(sb);
			UtilMethods.validateStartEndPositon(start,end,length,"There is a mismatch between start and end Index");
		}catch(CustomException e){
			throw new CustomException(e.getMessage(),e);
		}
	}
	
	private void validateStringBuilderAndDelimiter(StringBuilder sb,String delimiter) throws CustomException{
		try{
			UtilMethods.isNull(sb,ConstantMessage.INPUT_NULL_MESSAGE);
			UtilMethods.isNull(delimiter,ConstantMessage.INPUT_NULL_MESSAGE);
		}catch(CustomException e){
			throw new CustomException(e.getMessage(),e);
		}
	}
}