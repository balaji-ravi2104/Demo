package runner;

import java.io.*;

import classes.Student;

public class ExternalizableExample {
	public static void main(String[] args) {
		Student objWrite1 = new Student(101, "Balaji");
		Student objWrite2 = new Student(102, "Ravi");

		try (FileOutputStream fileOut = new FileOutputStream("externalizable.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(objWrite1);
			out.writeObject(objWrite2);
			System.out.println("Serialized data is saved in externalizable.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Student objRead1 = null;
		Student objRead2 = null;
		try (FileInputStream fileIn = new FileInputStream("externalizable.ser")) {
			ObjectInputStream in = new ObjectInputStream(fileIn);
			objRead1 = (Student) in.readObject();
			objRead2 = (Student) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Deserialized Object: " + objRead1);
		System.out.println("Deserialized Object: " + objRead2);
	}
}
