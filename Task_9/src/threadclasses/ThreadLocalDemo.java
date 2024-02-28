package threadclasses;
// set,get,remove,initilaValue
public class ThreadLocalDemo extends Thread{

	public static void main(String[] args) throws InterruptedException {
		ThreadLocal<Integer> tl1 = new ThreadLocal<>();
		ThreadLocal<String> tl2 = new ThreadLocal<>();
		
		tl1.set(101);
		tl1.set(102);// the second value overrides the first one..
		System.out.println(tl1.get());
		
		tl2.set("Balaji");
		System.out.println(tl2.get());
		
		tl1.remove();
		System.out.println(tl1.get()); // if list is empty returns null..
		
		ThreadLocal<String> threadLocal = new ThreadLocal<String>();
		
		Thread thread1 = new Thread(()->{
			threadLocal.set("Thread-1");
			try {
				Thread.sleep(2000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(threadLocal.get());
			
			threadLocal.remove();
			
			System.out.println(threadLocal.get()); // null
		});
		  
		Thread thread2 = new Thread(()->{
			threadLocal.set("Thread-2");
			try {
				Thread.sleep(2000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(threadLocal.get());
			
			threadLocal.remove();
			
			System.out.println(threadLocal.get()); //null
		});
		
		thread1.start();
		thread2.start();
		
		ThreadLocal<ThreadLocalDemo> threadLocal1 = ThreadLocal.withInitial(()-> new ThreadLocalDemo());
		
		ThreadLocal<ThreadLocalDemo> threadLocal2 = new ThreadLocal<>() {
			@Override
			protected ThreadLocalDemo initialValue() {
				return new ThreadLocalDemo();
			}
		};
		
		Thread t1 = new Thread(()->{
			System.out.println("threadLocal-1 :"+threadLocal1.get());
			System.out.println("threadlocal-2 :"+threadLocal2.get());
		});
		
		Thread t2 = new Thread(()->{
			System.out.println("threadLocal-1 :"+threadLocal1.get());
			System.out.println("threadlocal-2 :"+threadLocal2.get());
		});
		t1.start();
		t2.start();
		
		// Lazily Set Value
		ThreadLocal<String> threadLocal3 = new ThreadLocal<String>();
		String value = threadLocal3.get();
		if(value==null) {
			threadLocal3.set("Lazily Set Value");
			value = threadLocal3.get();
		}
		System.out.println(value);
		
		//InheritableThreadLocal Example
		
		ThreadLocal<String> thrdLocal = new ThreadLocal<String>();
		InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>();
		
		Thread parentThread = new Thread(()->{
			System.out.println("--- Parent Thread ---");
			thrdLocal.set("Parent Thread - thrdLocal");
			inheritableThreadLocal.set("Parent Thread - inheritableThreadLocal");
			
			System.out.println(thrdLocal.get());
			System.out.println(inheritableThreadLocal.get());
			
			Thread childThread = new Thread(()->{
				System.out.println("--- Child Thread ---");
				System.out.println(thrdLocal.get()); // null
				System.out.println(inheritableThreadLocal.get());
			});
			childThread.start();
		});
		parentThread.start();
		
		// Another thread
		Thread thread = new Thread(()->{
			System.out.println(thrdLocal.get()); // null
			System.out.println(inheritableThreadLocal.get()); //null
		});
		
		thread.start();
	}
	
	public static void printValue() {
		System.out.println(ThreadLocalStorage.getName());
	}

}
