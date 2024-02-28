package runner;

import java.io.*;

import classes.Student;

public class ExternalizableExample {
    public static void main(String[] args) {
        Student objWrite = new Student(101, "Balaji");

        try {
            FileOutputStream fileOut = new FileOutputStream("externalizable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(objWrite); 
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in externalizable.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Student objRead = null;
        try {
            FileInputStream fileIn = new FileInputStream("externalizable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            objRead = (Student) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Deserialized Object: " + objRead);
    }
}

