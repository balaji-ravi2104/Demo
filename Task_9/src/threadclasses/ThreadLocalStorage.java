package threadclasses;

public class ThreadLocalStorage {
	private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static void setName(String name) {
		threadLocal.set(name);
	}
	
	public static String getName() {
		return threadLocal.get();
	}
}
