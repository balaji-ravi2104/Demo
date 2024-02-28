package threadclasses;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadDumpExample {
    public static void main(String[] args) {
       
    }
    
    public static void takeThreadDump() {
    	 ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
         long[] threadIds = threadMXBean.getAllThreadIds();
         
         ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
         
         for (ThreadInfo threadInfo : threadInfos) {
             System.out.println("Thread name: " + threadInfo.getThreadName());
             System.out.println("Thread ID: " + threadInfo.getThreadId());
             System.out.println("Thread State: " + threadInfo.getThreadState());
             System.out.println("Stack Trace: ");
             for (StackTraceElement stackTraceElement : threadInfo.getStackTrace()) {
                 System.out.println("\t" + stackTraceElement);
             }
             System.out.println("----------------------------------------");
         }
	}
}
