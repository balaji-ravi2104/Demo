package classes;

import java.io.Serializable;

public class Singleton implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Singleton instance=null;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(instance!=null) {
			return instance;
		}
		instance = new Singleton();
		
		return instance;
	}
	
	protected Object readResolve() {
		return getInstance();
	}
}
