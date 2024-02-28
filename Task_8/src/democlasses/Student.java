package democlasses;

import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;

public class Student {
	private String string; 
	private int num; 
 
	public Student() {
		System.out.println("Student Default Constructor");
	}

	public Student(String string, int num) throws CustomException{
		UtilMethods.isNull(string, ConstantMessage.INPUT_NULL_MESSAGE);
		this.string = string;
		this.num = num; 
	}

	@Override
	public String toString() {
		return "string=" + string + ", num=" + num + "";
	}

	public String getString() {
		return string;
	}

	public void setString(String string) throws CustomException{
		UtilMethods.isNull(string, ConstantMessage.INPUT_NULL_MESSAGE);
		this.string = string;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	// Demo Methods
	public void publicHello() {
		System.out.println("Public Hello!!!");
	}
	
	private void privateHello() { 
		System.out.println("Private Hello");
	}
	
	public static void publicStaticHello() {
		System.out.println("Public Static Hello!!!");
	}
	
	private static void privateStaticHello() {
		System.out.println("Private Static Hello");
	}
	
}
