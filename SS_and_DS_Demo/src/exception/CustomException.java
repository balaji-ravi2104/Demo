package exception;

@SuppressWarnings("serial")
public class CustomException extends Exception{

	public CustomException(String message){
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
} 