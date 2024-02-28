package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import threadclasses.ThreadDumpExample;

public class ArrayListManager {
	private static final List<Integer> list = new ArrayList<>();
	private static Logger logger = Logger.getLogger(ArrayListManager.class.getName());

	public void add(int num) {
		synchronized (this) {
		    try {
		        list.add(num);
		        logger.info("In Add Method :" + list.toString());
		        Thread.sleep(1000);
		        if(num%2==0) {
		        	ThreadDumpExample.takeThreadDump();
		        }
		    } catch (InterruptedException e) {
		        logger.warning("An Exception Occurred");
		    }
		}

	}

	public void delete(int num) {
	    synchronized (this) {
	        try {
	            list.remove(Integer.valueOf(num));
	            logger.info("In Delete Method :" + list.toString());
	            Thread.sleep(1000);
		        if(num%2==1) {
		        	ThreadDumpExample.takeThreadDump();
		        }
	        } catch (InterruptedException e) {
	            logger.warning("An Exception Occurred");
	        }
	    }
	}

}
