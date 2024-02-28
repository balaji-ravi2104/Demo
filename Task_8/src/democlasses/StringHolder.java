package democlasses;

import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;

public class StringHolder {
	private String string; 

	public StringHolder(String string) throws CustomException {
		UtilMethods.isNull(string, ConstantMessage.INPUT_NULL_MESSAGE);
		this.string = string;
	}

	@Override
	public String toString() { 
		return string;
	}
}
