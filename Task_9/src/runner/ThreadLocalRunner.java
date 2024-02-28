package runner;

import threadclasses.ThreadLocalDemo;
import threadclasses.ThreadLocalStorage;

public class ThreadLocalRunner {
	public static void main(String[] args) {
		
		Thread thread = new Thread(()->{
			ThreadLocalStorage.setName("Balaji");
			ThreadLocalDemo.printValue();
		});
		
		thread.start();
	}
}
