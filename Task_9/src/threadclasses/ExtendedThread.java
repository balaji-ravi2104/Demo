package threadclasses;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ExtendedThread extends Thread {
	private static final Logger logger = Logger.getLogger(ExtendedThread.class.getName());
	private long sleepTime;
	private boolean running = true;

	public ExtendedThread() {

	}

	public ExtendedThread(String string) {
		super(string);
	}

	public ExtendedThread(String name, long sleepTime) {
		super(name);
		this.sleepTime = sleepTime;
	}

	// @Override
//	public void run() {
//		try {
//            logger.log(Level.INFO, "Extended Thread Current Thread Name :" + Thread.currentThread().getName());
//            logger.log(Level.INFO, "Extended Thread Current Thread State :" + Thread.currentThread().getState().toString());
//            logger.log(Level.INFO, "Extended Thread Current Thread Priority :" + Thread.currentThread().getPriority());
//        } catch (Exception e) {
//            logger.log(Level.WARNING, "An unexpected error occurred in thread: " + e.getMessage());
//        }
//	}

//	public void run() {
//		
//	}

//	@Override
//	public void run() {
//		logger.log(Level.INFO,"Going to Sleep Extended Thread :"+getName());
//		try {
//			Thread.sleep(sleepTime);
//		}catch (InterruptedException e) {
//			logger.log(Level.WARNING, "An unexpected error occurred in thread: " + e.getMessage());
//		}
//		logger.log(Level.INFO,"After Sleeping Extended Thread :"+getName());
//	}

	@Override
	public void run() {
		try {
			while (running) {
				logger.log(Level.INFO, getName()+" is running...");
				Thread.sleep(1000);
			}
			logger.log(Level.INFO, getName()+" stopped.");
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, "An unexpected error occurred in thread: " + e.getMessage());
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
