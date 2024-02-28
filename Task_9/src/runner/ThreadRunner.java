package runner;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import threadclasses.ExtendedThread;
import threadclasses.RunnableThread;
import threadclasses.SynchronizationDemo1;
import threadclasses.SynchronizationDemo2;
import threadclasses.ThreadDemo1;
import threadclasses.ThreadDemo2;
import utility.ArrayListManager;

public class ThreadRunner {
	private static final Logger logger = Logger.getLogger(ThreadRunner.class.getName());
	public static Object object1 = new Object();
	public static Object object2 = new Object();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean isProgramAlive = true;
		try {
			while (isProgramAlive) {
				try {
					logger.log(Level.INFO, "Enter your choice");
					choice = sc.nextInt();
					if (choice < 0) {
						throw new IllegalArgumentException("Choice must be greater than Zero!!");
					}
				} catch (InputMismatchException e) {
					logger.log(Level.WARNING,
							"Please enter the correct choice!!! Avoid entering the Characters for an Integer!!");
					sc.nextLine();
					continue;
				} catch (IllegalArgumentException e) {
					logger.log(Level.WARNING, "Illegal Arrgument Exception Occured");
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				switch (choice) {
				case 1:
					logger.log(Level.INFO, "1.Create a class that extends Thread class");
					try {
						ExtendedThread thread = new ExtendedThread();
						thread.start();

						// thread.sleep(1000);

						thread.join(); // Waits for this thread to die.

						logger.log(Level.INFO, "Runner Class Current Thread Name :" + Thread.currentThread().getName());
						logger.log(Level.INFO,
								"Runner Class Current Thread State :" + Thread.currentThread().getState().toString());
						logger.log(Level.INFO,
								"Runner Class Current Thread Priority :" + Thread.currentThread().getPriority());
					} catch (InterruptedException e) {
						logger.log(Level.WARNING, e.getMessage());
					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 2:
					logger.log(Level.INFO, "2.Create a class that implements Runnable Interface");
					try {
						RunnableThread runnableThread = new RunnableThread();

						Thread thread = new Thread(runnableThread);

						thread.start();

						thread.join();

						logger.log(Level.INFO, "Runner Class Current Thread Name :" + Thread.currentThread().getName());
						logger.log(Level.INFO,
								"Runner Class Current Thread State :" + Thread.currentThread().getState().toString());
						logger.log(Level.INFO,
								"Runner Class Current Thread Priority :" + Thread.currentThread().getPriority());
					} catch (InterruptedException e) {
						logger.log(Level.WARNING, e.getMessage());
					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 3:
					logger.log(Level.INFO, "3.Spawn a new Thread for Extended and Runnable Thread");
					try {
						ExtendedThread thread = new ExtendedThread();
						thread.setName("ExtendedThread");

						logger.log(Level.INFO, "Before Calling Start - Runner Class Current (Extended)Thread Name :"
								+ Thread.currentThread().getName());
						logger.log(Level.INFO, "Before Calling Start - Runner Class Current (Extended)Thread State :"
								+ Thread.currentThread().getState().toString());
						logger.log(Level.INFO, "Before Calling Start - Runner Class Current (Extended)Thread Priority :"
								+ Thread.currentThread().getPriority());

						thread.start();

						logger.log(Level.INFO, "After Calling Start - Runner Class Current (Extended)Thread Name :"
								+ Thread.currentThread().getName());
						logger.log(Level.INFO, "After Calling Start - Runner Class Current (Extended)Thread State :"
								+ Thread.currentThread().getState().toString());
						logger.log(Level.INFO, "After Calling Start - Runner Class Current (Extended)Thread Priority :"
								+ Thread.currentThread().getPriority());

						RunnableThread runnableThread = new RunnableThread();
						Thread thread1 = new Thread(runnableThread);
						thread1.setName("RunnableThread");

						logger.log(Level.INFO, "Before Calling Start - Runner Class Current (Runnable)Thread Name :"
								+ Thread.currentThread().getName());
						logger.log(Level.INFO, "Before Calling Start - Runner Class Current (Runnable)Thread State :"
								+ Thread.currentThread().getState().toString());
						logger.log(Level.INFO, "Before Calling Start - Runner Class Current (Runnable)Thread Priority :"
								+ Thread.currentThread().getPriority());

						thread1.start();

						logger.log(Level.INFO, "After Calling Start - Runner Class Current (Runnable)Thread Name :"
								+ Thread.currentThread().getName());
						logger.log(Level.INFO, "After Calling Start - Runner Class Current (Runnable)Thread State :"
								+ Thread.currentThread().getState().toString());
						logger.log(Level.INFO, "After Calling Start - Runner Class Current (Runnable)Thread Priority :"
								+ Thread.currentThread().getPriority());

					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 4:
					logger.log(Level.INFO, "4.Sleep with Threads");
					long sleepTime = 0;
					try {
						for (int i = 1; i <= 5; i++) {
							logger.log(Level.INFO, "Enter the Sleep Time");
							sleepTime = sc.nextLong();
							if (sleepTime < 0) {
								throw new IllegalArgumentException("Sleep time should be greater than Zero");
							}
							ExtendedThread extendedThread = new ExtendedThread("ExtendedThread-" + i, sleepTime);
							extendedThread.start();
						}

						for (int i = 1; i <= 5; i++) {
							logger.log(Level.INFO, "Enter the Sleep Time");
							sleepTime = sc.nextLong();
							if (sleepTime < 0) {
								throw new IllegalArgumentException("Sleep time should be greater than Zero");
							}
							Thread thread = new Thread(new RunnableThread("RunnableThread-" + i, sleepTime));
							thread.start();
						}

					} catch (IllegalArgumentException e) {
						logger.log(Level.INFO, e.getMessage());
					} catch (InputMismatchException e) {
						logger.log(Level.INFO, "Please enter the valid input");
					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 5:
					logger.log(Level.INFO, "5.Thread Dumping");
					try {
						ExtendedThread extendedThread = new ExtendedThread();
						RunnableThread runnableThread = new RunnableThread();
						Thread thread = new Thread(runnableThread);

						extendedThread.start();
						thread.start();

						Thread.sleep(2 * 60 * 1000);

						extendedThread.setRunning(false);
						runnableThread.setRunning(false);

					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 6:
					try {
						Thread[] threads = new Thread[10];
						for (int i = 0; i < threads.length; i++) {
							threads[i] = new ExtendedThread("Thread-" + (i + 1));
							threads[i].start();
						}

						Thread.sleep(2 * 60 * 1000);

						System.out.println("Take Thread dump...!!!");

						for (int i = 0; i < threads.length; i++) {
							((ExtendedThread) threads[i]).setRunning(false);
							Thread.sleep(60 * 1000);
						}

						for (Thread thread : threads) {
							thread.join();
						}

						boolean allThreadsExited = true;
						for (Thread thread : threads) {
							if (thread.isAlive()) {
								allThreadsExited = false;
								break;
							}
						}

						if (allThreadsExited) {
							logger.log(Level.INFO, "Tasks completed.");
						}

					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 7:
					logger.log(Level.INFO,"7.Synchronization Example");
					try {
						ArrayListManager arrayListManager = new ArrayListManager();
						SynchronizationDemo1 thread1 = new SynchronizationDemo1(arrayListManager);
						SynchronizationDemo2 thread2 = new SynchronizationDemo2(arrayListManager);
						
						thread1.start();
						thread2.start();
					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 8:
					logger.log(Level.INFO,"8.Dead Lock Example");
					try {
						ThreadDemo1 thread1 = new ThreadDemo1();
						ThreadDemo2 thread2 = new ThreadDemo2();

						thread1.start();
						thread2.start();
					} catch (Exception e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 0:
					isProgramAlive = false;
					logger.log(Level.INFO, "Exiting...");
					break;
				default:
					logger.log(Level.INFO, "Please enter the valid choice!!");
				}
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, "An Exception Occured");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

}
