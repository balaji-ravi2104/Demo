package threadclasses;

import java.util.logging.Logger;

import runner.ThreadRunner;

public class ThreadDemo2 extends Thread {
private static Logger logger = Logger.getLogger(ThreadDemo1.class.getName());
	
	public void run() {
		synchronized (ThreadRunner.object2) { 
			 logger.info("Thread 2: Holding lock 2...");
			 
			 try {
				 Thread.sleep(100);
			 }catch (InterruptedException e) {
				logger.warning("An Exception Occured");
			}	
			 
			logger.info("Thread 2: Waiting for lock 1...");
			
			synchronized (ThreadRunner.object1) {
				logger.info("Thread 2: Holding lock 1&2...");
			}
		}
	}
}
