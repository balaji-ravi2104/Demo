package democlasses;

public class SingletonDemo {
	
//	private static final SingletonDemo instance = new SingletonDemo(); 
//	public static SingletonDemo getInstance() {
//		return instance;
//	}
	
	private static SingletonDemo instance;
	
	private SingletonDemo() {
		
	}
	
//	public static synchronized SingletonDemo getInstance() {
//		if(instance ==null) {
//			instance = new SingletonDemo();
//		}
//		return instance;
//	}
//	
	
	
	public static SingletonDemo getInstance() { 
		if(instance!=null) {
			return instance;
		}
		synchronized (SingletonDemo.class) {
			if(instance==null) {
				instance = new SingletonDemo();
			}
		}
		return instance;
	}
	
	
	

	@Override
	public String toString() {
		return "SingletonDemo [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	// enum based Singleton
	public enum SingletonDemo1 {
	    INSTANCE; // single instance of SingletonDemo1
	}
	
}
