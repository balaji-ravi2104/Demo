package threadclasses;

import java.util.logging.Logger;

import utility.ArrayListManager;


public class SynchronizationDemo1 extends Thread{
	private static Logger logger = Logger.getLogger(SynchronizationDemo1.class.getName());
	
	ArrayListManager arrayListManager;
	
	public SynchronizationDemo1(ArrayListManager arrayListManager) {
		this.arrayListManager = arrayListManager;
	}
	public void run() {
		try {
			for(int i=1;i<=10;i++) {
				arrayListManager.add(i);
			    //Thread.sleep(500);    
			}
		}catch (Exception e) {
			logger.warning("An Exception Occured");
		}
	}
}
