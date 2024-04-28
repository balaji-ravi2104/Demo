package democlasses;

public class ObjectCreatingWays {
	
}

//
//// 1.Using New Keyword
//public class MyClass {
//    private int value;
//
//    public MyClass(int value) {
//        this.value = value;
//    }
//
//    public static void main(String[] args) {
//        // Creating an object using the new keyword
//        MyClass obj = new MyClass(10);
//        System.out.println("Value: " + obj.value);
//    }
//}
//
//// 2. Using Class.forName() with Reflection:
//public class MyClass {
//    private int value;
//
//    public MyClass(int value) {
//        this.value = value;
//    }
//
//    public static void main(String[] args) throws Exception {
//        // Creating an object using reflection
//        Class<?> clazz = Class.forName("MyClass");
//        MyClass obj = (MyClass) clazz.getDeclaredConstructor(int.class).newInstance(20);
//        System.out.println("Value: " + obj.value);
//    }
//}
//
//// 3.Using Object Cloning:
//public class MyClass implements Cloneable {
//    private int value;
//
//    public MyClass(int value) {
//        this.value = value;
//    }
//
//    public static void main(String[] args) throws CloneNotSupportedException {
//        // Creating an object using cloning
//        MyClass original = new MyClass(30);
//        MyClass clone = (MyClass) original.clone();
//        System.out.println("Value: " + clone.value);
//    }
//}
//
//// 4.Using Deserialization:
//import java.io.*;
//
//public class MyClass implements Serializable {
//    private int value;
//
//    public MyClass(int value) {
//        this.value = value;
//    }
//
//    public static void main(String[] args) throws Exception {
//        // Serialization
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("object.ser"));
//        MyClass obj1 = new MyClass(40);
//        out.writeObject(obj1);
//        out.close();
//
//        // Deserialization
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("object.ser"));
//        MyClass obj2 = (MyClass) in.readObject();
//        in.close();
//        System.out.println("Value: " + obj2.value);
//    }
//}
//
//// 5.Using Static Factory Methods:
//
//public class MyClass {
//    private int value;
//
//    private MyClass(int value) {
//        this.value = value;
//    }
//
//    public static MyClass create(int value) {
//        return new MyClass(value);
//    }
//
//    public static void main(String[] args) {
//        // Creating an object using a static factory method
//        MyClass obj = MyClass.create(50);
//        System.out.println("Value: " + obj.value);
//    }
//}
//













