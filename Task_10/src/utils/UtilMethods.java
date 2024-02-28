package utils;

import exception.CustomException;

public class UtilMethods {
    public static void isNull(Object object,String errorMessage) throws CustomException {
        if(object == null){
            throw new CustomException(errorMessage);
        }
    }

    public static void isPositionValid(int position,int length,String errorMessage) throws CustomException{
        if (position < 0 || position >= length){
            throw new CustomException(errorMessage);
        }
    }

    public static void validateStartEndPositon(int start,int end,int length,String errorMessage) throws CustomException{
        if(start < 0 || start > end || start >= length || end >= length){
            throw new CustomException(errorMessage);
        }
    }

	public static void validateStartEnd(int maximumLength, int mininumLength, String positionMismatchMessage) throws CustomException {
		if(mininumLength<0 || maximumLength<mininumLength) {
			throw new CustomException(positionMismatchMessage);
		}
	}
}
