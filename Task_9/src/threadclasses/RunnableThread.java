package threadclasses;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RunnableThread implements Runnable {
	private static final Logger logger = Logger.getLogger(ExtendedThread.class.getName());
	private String name;
	private boolean running = true;
	private long sleepTime;

	public RunnableThread() {

	}

	public RunnableThread(String name, long sleepTime) {
		this.name = name;
		this.sleepTime = sleepTime;
	}

//	@Override
//	public void run() {
//		try {
//            logger.log(Level.INFO, "Runnable Thread Current Thread Name :" + Thread.currentThread().getName());
//            logger.log(Level.INFO, "Runnable Thread Current Thread State :" + Thread.currentThread().getState().toString());
//            logger.log(Level.INFO, "Runnable Thread Current Thread Priority :" + Thread.currentThread().getPriority());
//        } catch (InterruptedException e) {
//            logger.log(Level.WARNING, "An unexpected error occurred in thread: " + e.getMessage());
//        }
//	}

//	public void run() {
//	
//}
//	@Override
//	public void run() {
//		logger.log(Level.INFO,"Going to sleep Runnable Thread :"+name);
//		try {
//			Thread.sleep(sleepTime);
//		}catch (InterruptedException e) {
//			logger.log(Level.WARNING, "An unexpected error occurred in thread: " + e.getMessage());
//		}
//		logger.log(Level.INFO,"After Sleeping Runnable Thread :"+name);
//	}

	@Override
	public void run() {
		try {
			while (running) {
				logger.log(Level.INFO, "Runnable Thread is RunnableThread is running...");
				Thread.sleep(1000);
			}
			logger.log(Level.INFO, "RunnableThread stopped.");
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, "An unexpected error occurred in thread: " + e.getMessage());
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
