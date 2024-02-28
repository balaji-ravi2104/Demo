package threadclasses;

import java.util.logging.Logger;
   
import utility.ArrayListManager;

public class SynchronizationDemo2 extends Thread{
private static Logger logger = Logger.getLogger(SynchronizationDemo2.class.getName());
	
	ArrayListManager arrayListManager;
	
	public SynchronizationDemo2(ArrayListManager arrayListManager) {
		this.arrayListManager = arrayListManager;
	}
	
	public void run() {
		try {
			for(int i=1;i<=10;i++) {
				arrayListManager.delete(i);
				//Thread.sleep(1000);
			}   
		}catch (Exception e) {
			logger.warning("An Exception Occured");
		}
	}
}
