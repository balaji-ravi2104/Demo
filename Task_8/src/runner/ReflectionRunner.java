package runner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
 

public class ReflectionRunner {
	public static void main(String[] args) { 
		try { 
			 
			// Initializing a class 
		 	Class<?> clazz = Class.forName("democlasses.Student");
			  
			// Invoking Default Constructor 
			Object obj1 = clazz.getDeclaredConstructor().newInstance();
			  
			 
			// Invoking Parameterized Constructor
			Constructor<?> constructor = clazz.getConstructor(String.class,int.class);
			Object obj2 = constructor.newInstance("Balaji",123);
			
			// Invoking get Method
			Method getNameMethod = clazz.getMethod("getString");
			String name = (String) getNameMethod.invoke(obj2);
			System.out.println("Name :"+name);
			
			 
			// Invoking set Method
			Method setNameMethod = clazz.getMethod("setNum", int.class);
			setNameMethod.invoke(obj2, 123);
			
			
			// Getting all the Fields(Instance variables)...
			Field[] fields = obj1.getClass().getDeclaredFields();
			for(Field field:fields) {
				System.out.println(field.getName());
			}
			
			// Getting all the Methods
			
			Method[] method = obj1.getClass().getDeclaredMethods();
			for(Method methods:method) {
				System.out.println(methods.getName());
			}
			
			// Calling or invoking public method
			Method publicMethod = clazz.getMethod("publicHello");
			publicMethod.invoke(obj2); 
			
			// Calling or invoking private method
			Method privateMethod = clazz.getDeclaredMethod("privateHello");
			privateMethod.setAccessible(true);
			privateMethod.invoke(obj2);
			
			// Calling or invoking public static method
			Method publicStaticMethod = clazz.getMethod("publicStaticHello");
			publicStaticMethod.invoke(obj2); 
			//publicStaticMethod.invoke(null);// we can call public static method by pass null object as well...
	
			// Calling or invoking private static method
			Method privateStaticMethod = clazz.getDeclaredMethod("privateStaticHello");
			privateStaticMethod.setAccessible(true);
			privateStaticMethod.invoke(obj2);
			//privateStaticMethod.invoke(null); //we can call private static method by pass null object as well...
						     
			
			//Printing Object
			System.out.println(obj2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
