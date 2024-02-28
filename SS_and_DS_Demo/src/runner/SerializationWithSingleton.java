package runner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import classes.Singleton; 

public class SerializationWithSingleton { 

	public static void main(String[] args) {
		Singleton instance1 = null;
		
		try(FileOutputStream fileOutputStream = new FileOutputStream("singleton.ser");
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			
			instance1 = Singleton.getInstance();
			
			objectOutputStream.writeObject(instance1);
			
			System.out.println("Serialized Singleton instance!!!");
		}catch (IOException e) {
			System.out.println("Errro in Serialization: "+e.getMessage());
		}
		
		
		try(FileInputStream fileInputStream = new FileInputStream("singleton.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			
			Singleton instance2 = (Singleton)objectInputStream.readObject();
			
			System.out.println("Is both instance are Same :"+ (instance1==instance2));
			
			System.out.println("Deserialized Singleton instance!!!");
			
		}catch (IOException | ClassNotFoundException e) {
			System.out.println("Errro in DeSerialization: "+e.getMessage());
		}
	}

}
