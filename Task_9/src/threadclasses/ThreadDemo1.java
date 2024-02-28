package threadclasses;

import java.util.logging.Logger;

import runner.ThreadRunner;

public class ThreadDemo1 extends Thread {
	private static Logger logger = Logger.getLogger(ThreadDemo1.class.getName());
	
	public void run() {
		synchronized (ThreadRunner.object1) { 
			 logger.info("Thread 1: Holding lock 1...");
			 
			 try {
				 Thread.sleep(100);
			 }catch (InterruptedException e) {
				logger.warning("An Exception Occured");
			}
			 
			logger.info("Thread 1: Waiting for lock 2...");
			
			synchronized (ThreadRunner.object2) {
				logger.info("Thread 1: Holding lock 1&2...");
			}
		}
	}
}
