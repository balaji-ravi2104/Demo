package operations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import classes.Employee;
import exception.CustomException;

public class Helper {
	
	public void serializeListOfObject(List<Employee> empList,String fileName) throws CustomException {
		try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
			
			objectOutputStream.writeObject(empList);
			
		}catch (IOException e) {
			throw new CustomException("Error while Serializing an Object",e);
		}
	}

	public List<Employee> deserializeObjects(String fileName) throws CustomException{
		try(FileInputStream fileInputStream = new FileInputStream(fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			
			@SuppressWarnings("unchecked")
			List<Employee> empList = (List<Employee>) objectInputStream.readObject();
			
			return empList;
		}catch (IOException | ClassNotFoundException e) {
			throw new CustomException("Error while DeSerializing an Object",e);
		}
	}

}
